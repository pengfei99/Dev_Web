package org.etriks.security.ldap.entry;

/**
 * Created by pliu on 3/28/14.
 */
import org.apache.log4j.Logger;
import org.etriks.security.ldap.LdapConnector;
import org.etriks.security.ldap.serverprofile.LdapServerConnectorConfig;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class LdapEntryManager {
    private final Logger log= Logger.getLogger(this.getClass());
    //creat an connextion to the Ldap server
    private LdapConnector conLdp;
    //creat the context of the connextion.
    private InitialDirContext ctx;
    private SearchControls searchCtls;
    //Specify the base for the search
    private String searchBase;


    public LdapEntryManager(LdapServerConnectorConfig ldapServerInfo,String searchBase) {

        // Create the instance of the LDAP connection
        conLdp = new LdapConnector(ldapServerInfo);

        // Get the context of the ldap connection
        ctx = (InitialDirContext) conLdp.getContext();

        //create a search controler
        searchCtls=new SearchControls();
        this.searchBase=searchBase;
    }
    /*
     * This method can creat a new entry in the entry parent directory.
     * It takes a list of entry object classes (define the type of the entry),
     *  a map of entry attributes. the first string is the description (type) of attribute (e.g. mail, userPassword),
     *  the second string of the map is the value of the attribute (e.g. pliu@cc.in2p3.fr).
     *  and the entry parent directory.
     *
     *   It returns true if the entry has been added to the Ldap server, if not return false
     */
    public boolean saveEntry(List<String> entryObjClasses,
                             Map<String, String> entryAttributes, String entryParentDir) {

        boolean result=false;
        String entryUid=entryAttributes.get("uid");
        String cnValue=entryAttributes.get("cn");

        String entryCn="cn="+cnValue+",";

        String entryDN=entryCn+entryParentDir;
        if(uidExisted(entryUid)||cnExisted(cnValue)){
            return result;
        }
        else {

            BasicAttribute objClasses = new BasicAttribute("objectclass");
            // Add the object class of the entry
            for (String objClass : entryObjClasses) {
                objClasses.add(objClass);
            }
            // add the attribute of the entry(including the object class)
            BasicAttributes attrs = new BasicAttributes();
            attrs.put(objClasses);

            for (Map.Entry<String, String> entry : entryAttributes.entrySet()) {
                attrs.put(entry.getKey(), entry.getValue());
            }

            try {	ctx.createSubcontext(entryDN, attrs);
                result=true;
            }
            catch (NamingException e) {
                // To add the exception in the server log, for now we just print
                // them in the console
               /* System.out
                        .println("LdapEntryManager unable to add this new entry to the LDAP server. See java exception: "
                                + e.toString());*/
                log.error("LdapEntryManager unable to add this new entry to the LDAP server. See java exception: "
                        + e.toString());

            }
            return result;
        }

    }

    public boolean deleteEntry(String entryDN){
        int endIndex=entryDN.indexOf(",");
        int beginIndex=entryDN.indexOf("=")+1;
        String entryCN=entryDN.substring(beginIndex, endIndex);
        if(cnExisted(entryCN)){
            try {
                ctx.destroySubcontext(entryDN);
            } catch (NamingException e) {
                // To add the exception in the server log, for now we just print
                // them in the console
                /*System.out
                        .println("LdapEntryManager unable to delete this entry from the LDAP server. See java exception: "
                                + e.toString());*/
                log.error("LdapEntryManager unable to delete this entry from the LDAP server. See java exception: "
                        + e.toString());
            }
            return true;
        }
        else return false;
    }

    public boolean uidExisted(String uid){
        if(entryExist("uid", uid)==null) return false;
        else return true;

    }

    public boolean cnExisted(String cn){

        if(entryExist("cn",cn)==null) return false;
        else return true;


    }

// Can't do a search by using dn of the entry, to find out why
//	public boolean dnExisted(String dn){
//
//		return entryExist("dn",dn);
//
//	}

    /* This method takes the name and the value of an attriubte, it returns the entry's dn if it exist,otherwise it return null
    * For example, I want to find out if p.liu are used as uid of a user. I will add attName as "uid", attValue as "p.liu",
    * */

    public String entryExist(String attName,String attValue){
        String dn=null;
        String[] returnAttrs={"cn","uid"};


        searchCtls.setReturningAttributes(returnAttrs);

        //Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        //Specifiy the LDAP search filter
        String searchFilter = "(&("+attName+"="+attValue+") )";

        NamingEnumeration answer=null;
        try {
            answer = ctx.search(searchBase, searchFilter, searchCtls);
        } catch (NamingException e) {
            log.error("Search engine for LDAP server failed! Java exception: "+e.toString());
            /*throw new Error("Search engine for LDAP server failed! Java exception: "+e.toString());*/
        }
        try {
            if(answer==null){
                log.error("No result find in method entryExist in Class LdapEntryManager");
            }
            else{
            if (answer.hasMore()) {
                SearchResult result = (SearchResult) answer.next();
                Attributes attrs = result.getAttributes();
                dn=result.getNameInNamespace();
                //Cannot do this, not all entry have uid, null may result a error
                // String uid=result.getAttributes("uid");
            }}
        } catch (NamingException e) {
           /* System.out.println("LdapEntryManager uidExisted failed. Java exception: "+e.toString());*/
            log.error("LdapEntryManager uidExisted failed. Java exception: "+e.toString());
        }
        return dn;

    }

    /*
     * This method can modify an existing attribute in an Entry, it takes
     *  a entry DN, attribute name and attributeValue
     */
    public boolean modifyEntryAttributes(String entryDn,String attName,String attValue){
        ModificationItem[] mods=new ModificationItem[1];
        mods[0]=new ModificationItem(DirContext.REPLACE_ATTRIBUTE,new BasicAttribute(attName,attValue));
        try {
            ctx.modifyAttributes(entryDn, mods);
            return true;
        } catch (NamingException e) {
            log.error("LdapEntryManager enable to change attribute!!! java exception : "+e.toString());

            return false;
        }
    }

	/*
	 * Inserting a new attribute to an existing entry.
	 * It takes entry Dn, new attribute name and value as argument,
	 * returns true if success
	 *
	 */

    public boolean insertNewAttribute(String entryDn, String newAttName, String attValue){

        ModificationItem[] mods=new ModificationItem[1];
        mods[0]=new ModificationItem(DirContext.ADD_ATTRIBUTE,new BasicAttribute(newAttName,attValue));

        try {
            ctx.modifyAttributes(entryDn, mods);
            return true;
        } catch (NamingException e) {
      log.error("In LdapEntryManager enable to change attribute!!! java exception : "+e.toString());

            return false;
        }
    }

    /*
     * This method list all entries Name of the ldap server
     *
     */
    public NamingEnumeration<NameClassPair> getAllEntries(String contextName){
        NamingEnumeration<NameClassPair> list=null;
        try {
            list = ctx.list(contextName);
        } catch (NamingException e) {
            log.error("In ldapEntryManager, getAllEntries failed!!  Java exception: "+e.toString());
        }
        return list;
    }

    public int getEntriesSize(String contextName){
        NamingEnumeration<NameClassPair> userList=this.getAllEntries(contextName);
        int count=0;
        try {
            while(userList.hasMore()){
            count++;
                userList.next();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return count;
    }

	/*
	 * This method list all the attributes of an entry
	 * It takes uid or cn and returns a map of the attribute name and attribute value
	 *
	 *
	 */

    public Map<String,List<String>> getAllAttributesByEntryDN(String dn) {
        Map<String,List<String>> result=new HashMap<String,List<String>>();
        //get all attributes of the given entry by using the its dn
        Attributes atts=null;
        try {
            atts=ctx.getAttributes(dn);
        } catch (NamingException e) {
           log.error("Unable to get all attriubtes of a entry. "+e.toString());
        }
        if (atts==null){
            //No attribute is found. The entry may not have any attribute or the getAttributes failed.
            //should be add a line in the log if in this bloc
        }
        else {
            //put all attributes in the hashmap
            try {
                for(NamingEnumeration ae=atts.getAll(); ae.hasMore();){
                    List<String> attValues=new ArrayList<String>();
                    Attribute att=(Attribute)ae.next();
                    //get the name of the attribute
                    String attributeName=att.getID();
                    //get a list of values of the attribute
                    for(NamingEnumeration values=att.getAll(); values.hasMore();){
                        attValues.add(values.next().toString());
                    }
                    result.put(attributeName, attValues);
                }
            } catch (NamingException e) {
                log.error("Entry's Attributes list is empty"+e.toString());
            }
        }
        return result;
    }




    /*
     * This method close the ldap connection
     */
    public void closeContext(){
        this.conLdp.closeContext();
    }
}
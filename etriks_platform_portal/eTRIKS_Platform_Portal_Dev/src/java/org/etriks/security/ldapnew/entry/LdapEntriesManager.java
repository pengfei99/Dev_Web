package org.etriks.security.ldapnew.entry;

/**
 * Created by pliu on 1/5/16.
 */

import org.apache.log4j.Logger;
import org.etriks.security.ldapnew.LdapConnector;
import org.etriks.security.ldapnew.resource.LdapProperties;
import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig;

import javax.naming.InvalidNameException;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 3/28/14.
 */


public abstract class LdapEntriesManager {
    private final org.apache.log4j.Logger log= Logger.getLogger(getClass());
    //creat an connextion to the Ldap server
    private LdapConnector ldapConnector;
    //creat the context of the connextion.
    private InitialDirContext connectionContext;
    private SearchControls searchCtls;
    //Specify the base for the search
    private String searchBase;
    //Ldap architecture properties
    protected LdapProperties ldapProperties=new LdapProperties();

    public LdapEntriesManager(LdapConnectorConfig ldapServerInfo) throws IOException {

        // Create the instance of the LDAP connection
        ldapConnector = new LdapConnector(ldapServerInfo);

        // Get the context of the ldap connection
        connectionContext = (InitialDirContext) ldapConnector.getContext();

        //create a search controler
        searchCtls=new SearchControls();
        this.searchBase=ldapProperties.getBaseDN();
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
    public void saveEntry(List<String> entryObjClasses,
                             Map<String, String> entryAttributes, String entryParentDir) throws NamingException {


        String entryUid=entryAttributes.get("uid");
        String cnValue=entryAttributes.get("cn");

        String entryCn="cn="+cnValue+",";

        String entryDN=entryCn+entryParentDir;

        if (uidExisted(entryUid))
        {throw new InvalidAttributeValueException("The uid of the new entry is already existed");}

        if(cnExisted(cnValue)){
            throw new InvalidNameException("The cn of the new entry is already existed");
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
                connectionContext.createSubcontext(entryDN, attrs);
        }

    }

    /*public void saveEntryWithoutObjClasses(Map<String,String> allEntriesAttributes,String entryParentDir) throws InvalidNameException {

        String cnValue=allEntriesAttributes.get("cn");
        String entryCn="cn="+cnValue;
        String entryDn=entryCn+","+entryParentDir;
        if(cnExisted(cnValue)){
            throw new InvalidNameException("The cn of the new entry is already existed");
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
            connectionContext.createSubcontext(entryDN, attrs);
        }

    }*/
    public String getCNFromDN(String entryDN){
        int endIndex=entryDN.indexOf(",");
        int beginIndex=entryDN.indexOf("=")+1;
        return entryDN.substring(beginIndex, endIndex);
    }

    public boolean deleteEntry(String entryDN){
        String entryCN = this.getCNFromDN(entryDN);
        if(cnExisted(entryCN)){
            try {
                connectionContext.destroySubcontext(entryDN);
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

    /*Delete an attribute from an entry*/
    public boolean deleteAttribute(String entryDn,String attributeName,String attributeValue){
        ModificationItem[] mods=new ModificationItem[1];
        mods[0]=new ModificationItem(DirContext.REMOVE_ATTRIBUTE,new BasicAttribute(attributeName, attributeValue));
        try {
            connectionContext.modifyAttributes(entryDn, mods);
            return true;
        } catch (NamingException e) {
            log.error("LdapEntryManager enable to remove attribute!!! java exception : "+e.toString());
            return false;
        }
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


    public String valueExist(String valueName,String value,String entryDN){
        String dn=null;
        String[] returnAttrs={"cn","uid"};


        searchCtls.setReturningAttributes(returnAttrs);

        //Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        //Specifiy the LDAP search filter
        String searchFilter = "(&("+valueName+"="+value+") )";

        NamingEnumeration answer=null;
        try {
            answer = connectionContext.search(entryDN, searchFilter, searchCtls);
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
    public String entryExist(String attName,String attValue){
       return this.valueExist(attName,attValue,this.searchBase);

    }

    /*
     * This method can modify an existing attribute in an Entry, it takes
     *  a entry DN, attribute name and attributeValue
     */
    public boolean modifyEntryAttributes(String entryDn,String attName,String attValue){
        ModificationItem[] mods=new ModificationItem[1];
        mods[0]=new ModificationItem(DirContext.REPLACE_ATTRIBUTE,new BasicAttribute(attName,attValue));
        try {
            connectionContext.modifyAttributes(entryDn, mods);
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
            connectionContext.modifyAttributes(entryDn, mods);
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
            list = connectionContext.list(contextName);
        } catch (NamingException e) {
            log.error("In ldapEntryManager, getAllEntries failed!!  Java exception: "+e.toString());
        }
        return list;
    }

    public int getEntriesSize(String contextName){
        NamingEnumeration<NameClassPair> entriesList=this.getAllEntries(contextName);
        int count=0;
        try {
            while(entriesList.hasMore()){
                count++;
                entriesList.next();
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
            atts=connectionContext.getAttributes(dn);
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

    public Map<String, List<Object>> getAllAttributesAsObjectByEntryDN(String userDN){
        Map<String,List<Object>> result=new HashMap<String,List<Object>>();
        Attributes atts=null;
        try {
            atts=connectionContext.getAttributes(userDN);
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
                    List<Object> attValues=new ArrayList<Object>();
                    Attribute att=(Attribute)ae.next();
                    //get the name of the attribute
                    String attributeName=att.getID();
                    //get a list of values of the attribute
                    for(NamingEnumeration values=att.getAll(); values.hasMore();){
                        attValues.add(values.next());
                    }
                    result.put(attributeName, attValues);
                }
            } catch (NamingException e) {
                log.error("Entry's Attributes list is empty"+e.toString());
            }
        }
        return result;
    }

    public String getGidNumber(String dn){
        Map<String, List<String>> attributes = this.getAllAttributesByEntryDN(dn);
        String gidNumber=attributes.get("gidNumber").get(0);
        return gidNumber;
    }


    /*
     * This method close the ldap connection
     */
    public void closeContext() throws NamingException {
        this.connectionContext.close();
        this.ldapConnector.closeContext();
    }
}
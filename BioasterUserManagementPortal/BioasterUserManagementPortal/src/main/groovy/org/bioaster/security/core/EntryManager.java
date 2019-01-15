package org.bioaster.security.core;

/**
 * Created by pliu on 2/14/17.
 */

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EntryManager {

    /* Get actual class name to be printed on */
    static Logger log = LoggerFactory.getLogger(EntryManager.class);
    //ldap connection context
    private DirContext dirContext;


    /*
     *
     * Class Constructor
     * It reads a ldap connection information file
     * It creates the connection and set the connection context for
     * further actions
     *
     * @param String ldapConfFile
     * @return void
     * */
    public EntryManager(ILdapServerConnectorConfig ldapConfig) throws IOException{
        ConnLDAP conLdp=new ConnLDAP(ldapConfig);
        dirContext=conLdp.getContext();
    }

    /*
     * This method will add an new entry into openldap,
     * it takes a list of objectclass of the entry, a map of entry attributes
     * and the path of the parent dir of this entry.
     * It returns true, if the entry is added.
     * Otherwise, it returns false
     *
     *  @param List<String> objectClassList
     *  @param Map<String,Object> entryAttributesMap
     *  @param String entryParentDir
     *
     *  @return boolean
     *
     *
     * */
    public boolean addEntry(List<String> objectClassList, Map<String,Object> entryAttrsMap,String entryParentDir){

        boolean flag =false;
        Attributes entry = new BasicAttributes();

        //load entry object class
        Attribute objectClass=this.loadEntryClass(objectClassList);
        entry.put(objectClass);

        //Load attribute to entry
        List<Attribute> entryAttrs=this.loadEntryAttrs(entryAttrsMap);
        for (Attribute attrs: entryAttrs){
            entry.put(attrs);
        }

        //Set entry dn
        String userCN=(String) entryAttrsMap.get("cn");
        String entryDN="cn="+userCN.trim()+","+entryParentDir;

        try{
            dirContext.createSubcontext(entryDN, entry);
            flag = true;
        }catch(Exception e){
            log.error(" add entry to ldap server error: " + e.getMessage());
            return flag;
        }
        return flag	;
    }

    /*
     * This method will delete an entry with the given entry DN
     *
     * @param String entryDN
     * @return boolean result
     * */
    public boolean deleteEntry(String entryDN) {
        boolean flag=false;
        try {
            dirContext.destroySubcontext(entryDN);
            flag=true;
        } catch (NamingException e) {
            log.error("can't delete entry"+entryDN+" Java exception"+e.getMessage());
            return flag;
        }
        return flag;
    }

    public boolean modifyEntryAttribute(String entryName,String attrName,String attrValue){
        boolean flag=false;
        ModificationItem[] mods = new ModificationItem[1];

        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                new BasicAttribute(attrName, attrValue));

        try {
            dirContext.modifyAttributes(entryName, mods);
            flag=true;
        } catch (NamingException e) {
            log.error("can't modify entry"+entryName+" attribute. Java exception"+e.getMessage());
            return flag;
        }
        return flag;
    }

    public boolean addEntryAttribute(String entryName,String attrName,String attrValue){
        boolean flag=false;
        ModificationItem[] mods = new ModificationItem[1];

        mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE,
                new BasicAttribute(attrName, attrValue));

        try {
            dirContext.modifyAttributes(entryName, mods);
            flag=true;
        } catch (NamingException e) {
            log.error("can't add new attribute to entry"+entryName+". Java exception"+e.getMessage());
            return flag;
        }
        return flag;

    }

    /*Delete an attribute from an entry*/
//    public boolean deleteEntryAttribute(String entryDN,String attrName,String attrValue){
//        boolean flag=false;
//        ModificationItem[] mods = new ModificationItem[1];
//
//        mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE,
//                new BasicAttribute(attrName,attrValue));
//
//        try {
//            dirContext.modifyAttributes(entryDN, mods);
//            flag=true;
//        } catch (NamingException e) {
//            log.error("can't delete attribute from entry "+entryDN+". Java exception"+e.getMessage());
//            return flag;
//        }
//        return flag;
//
//    }

    public void deleteEntryAttribute(String entryDN,String attrName,String attrValue) throws NamingException {
        ModificationItem[] mods = new ModificationItem[1];

        mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE,
                new BasicAttribute(attrName,attrValue));

        dirContext.modifyAttributes(entryDN, mods);

    }



    /*
         * This method list all the attributes of an entry
         * It takes dn of the entry and returns a map of the attribute name and attribute value
         *
         *
         */

    public Map<String, List<Object>> getAllAttributesAsObjectByEntryDN(String entryDN){
        Map<String,List<Object>> result=new HashMap<String,List<Object>>();
        Attributes atts=null;
        try {
            atts=dirContext.getAttributes(entryDN);
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
                for(NamingEnumeration ae = atts.getAll(); ae.hasMore();){
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



    /*
    * This method list all entries Name of the ldap server
    *
    */
    public NamingEnumeration<NameClassPair> getAllEntries(String contextName){
        NamingEnumeration<NameClassPair> list=null;
        try {
            list = dirContext.list(contextName);
        } catch (NamingException e) {
            log.error("In ldapEntryManager, getAllEntries failed!!  Java exception: "+e.toString());
        }
        return list;
    }
    public List<String> getSubEntryDNList(String parentPath){
        List<String> subEntryDNList=new ArrayList<String>();
        NamingEnumeration<NameClassPair> subEntryList = getAllEntries(parentPath);
        try {
            while (subEntryList.hasMore()){
                subEntryDNList.add(subEntryList.next().getNameInNamespace());
            }
        } catch (NamingException e) {
            log.error("Unable to get sub entry dn list!!! Java exception: "+e.toString());
        }
        return subEntryDNList;
    }

    public List<String> getSubEntryNameList(String parentPath){
        List<String> subEntryNameList=new ArrayList<String>();
        NamingEnumeration<NameClassPair> subEntryList = getAllEntries(parentPath);
        try {
            while (subEntryList.hasMore()){

                subEntryNameList.add(subEntryList.next().getName().substring(3));
            }
        } catch (NamingException e) {
            log.error("Unable to get sub entry cn list!!! Java exception: "+e.toString());
        }
        return subEntryNameList;
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
     * This method build a attribute objectClass of the entry base on a
     * list of object class provided.
     *
     * @param List<String> objectClassList
     * @return Attribute
     * */
    private Attribute loadEntryClass(List<String> objectClassList){
        Attribute objectClass=new BasicAttribute("objectClass");
        for(String oc: objectClassList){
            objectClass.add(oc);
        }
        return objectClass;
    }

    /*
     * This method build a list of attributes of the entry base on a
     * list of attribute name and value provided.
     *
     * @param Map<String,Object> entryAttrsMap
     * @return List<Attribute>
     * */
    private List<Attribute> loadEntryAttrs(Map<String,Object> entryAttrsMap){
        List<Attribute> entryAttrs=new ArrayList<Attribute>();
        for (Map.Entry<String, Object> entry: entryAttrsMap.entrySet()){
            //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            entryAttrs.add(new BasicAttribute(entry.getKey(), entry.getValue()));
        }
        return entryAttrs;
    }
    public void closeConnection() throws NamingException{
        this.dirContext.close();
    }
}

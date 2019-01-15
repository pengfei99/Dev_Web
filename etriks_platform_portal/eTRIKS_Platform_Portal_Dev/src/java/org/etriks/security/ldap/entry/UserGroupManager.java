package org.etriks.security.ldap.entry;

/**
 * Created by pliu on 3/28/14.
 */

import org.apache.log4j.Logger;
import org.etriks.security.ldap.serverprofile.LdapServerConnectorConfig;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 3/12/14.
 */
public class UserGroupManager extends LdapEntryManager {

    //create an logger instance
    private final Logger log= Logger.getLogger(getClass());

   //List of the entry class
    private List<String> userEntryObjClasses=new ArrayList<String>();


    public UserGroupManager(LdapServerConnectorConfig ldapServerInfo,String searchBase) {
        super(ldapServerInfo,searchBase);
      //  this.entryParentDir=entryParentDir;
        this.loadGroupEntryObjClasses();
    }

    /*
    * Load the Organization/workpackage(Group) entry object classes.
    * The object classes needs to be customized for every type of Ldap entry such as user, role
    *
    */
    private void loadGroupEntryObjClasses(){

        userEntryObjClasses.add("posixGroup");
        userEntryObjClasses.add("top");
    }


public String getGidNumber(String dn){
    Map<String, List<String>> attributes = this.getAllAttributesByEntryDN(dn);
    String gidNumber=attributes.get("gidNumber").get(0);
    return gidNumber;
}


    /*
     * This method add a member into a Organization/Work Package as a member
     * It takes a role name and the user DN
     * returns a boolean.
     */
    public boolean addOrganizationMember(String OrganizationName,String UserId){

        String OrganizationDN=OrganizationName+",ou=Organizations,dc=etriks,dc=eu";

        return	this.insertNewAttribute(OrganizationDN, "memberUid",UserId);
    }

    /*
     * This method add a member into a Work Package as a member
     * It takes a role name and the user DN
     * returns a boolean.
     */
    public boolean addWorkPackageMember(String WorkPackageName,String UserId){

        String OrganizationDN=WorkPackageName+",ou=Work Packages,dc=etriks,dc=eu";

        return	this.insertNewAttribute(OrganizationDN, "memberUid",UserId);
    }

    public boolean addGroupsMember(String GroupsCN,String UserId){

        String groupDN=GroupsCN+",ou=Groups,dc=etriks,dc=eu";

        return	this.insertNewAttribute(groupDN, "memberUid",UserId);
    }

    public boolean addTransmartGroupsMember(String transmartGroupCN,String UserId){
        String transmartGroupDN=transmartGroupCN+",ou=Transmart,ou=Applications,dc=etriks,dc=eu";
        return this.insertNewAttribute(transmartGroupDN,"memberUid",UserId);
    }

    /*This method will create a new group in the LDAP server under ou=Groups,dc=etriks,dc=eu
    * It takes a group name and the description of the group as parameters
    * It returns true if the creation is success, otherwise it returns false
    * */
    public boolean createNewGroup(String groupName,String description){
        //the default gidNumber is 5999
        String gidNumber="5999";
        //the parent dir of the group
        String entryParentDir="ou=Groups,dc=etriks,dc=eu";
        //object classs of the group
        List<String> entryObjClasses=new ArrayList<String>();
        //attributes of the group
        Map<String, String> entryAttributes=new HashMap<String, String>();


         entryObjClasses.add("posixGroup");
         entryAttributes.put("cn",groupName);
        //The gidNumber needs to be checked
        Map<String,String> allGroups = this.getAllGroups();
       // System.out.println("group info :"+allGroups.toString());
        //All group gid starts with 5000
        for(int i=5000;i<5001+allGroups.size();i++){
           // System.out.println("in for loop"+i);
            if (!allGroups.containsKey(Integer.toString(i))) {
                gidNumber=Integer.toString(i);
                //System.out.println("in if gid: "+gidNumber);
                break;}
        }
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        boolean result=this.saveEntry(entryObjClasses,entryAttributes,entryParentDir);
        return result;
    }
    /*This method will create a new organization in the LDAP server under ou=Organizations,dc=etriks,dc=eu
   * It takes a organization name and the description of the organization as parameters
   * It returns true if the creation is success, otherwise it returns false
   * */
    public boolean createNewOrganization(String organizationName,String description){
        //The default organization gid is 599
         String gidNumber="599";
        //the parent dir of the group
        String entryParentDir="ou=Organizations,dc=etriks,dc=eu";
        //object classs of the group
        List<String> entryObjClasses=new ArrayList<String>();
        //attributes of the group
        Map<String, String> entryAttributes=new HashMap<String, String>();
        String cn="CP_" + organizationName;

        Map<String,String> allOrganizations = this.getAllOrganizations();
        entryObjClasses.add("posixGroup");
        entryObjClasses.add("top");
       //  System.out.println("Organization info :"+allOrganizations.toString());
        //All organization gid starts with 500
        for(int i=500;i<501+allOrganizations.size();i++){
            // System.out.println("in for loop"+i);
            if (!allOrganizations.containsKey(Integer.toString(i))) {
                gidNumber=Integer.toString(i);
                //System.out.println("in if gid: "+gidNumber);
                break;}
        }
        entryAttributes.put("cn", cn);
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        boolean result=this.saveEntry(entryObjClasses,entryAttributes,entryParentDir);
        return result;
    }

    /*
     * This method returns all available entries in the Ldap server
     */
    public NamingEnumeration<NameClassPair> getAllRoles(String groupsDN){
        return this.getAllEntries(groupsDN);
    }

    /*
     * This method get all members of a role
     */
    public NamingEnumeration<NameClassPair> getAllAttributesOfGroup(String groupDN){
        return this.getAllEntries(groupDN);
    }
    /**This method will return a map of Organization Name and gid which contains all organizations in the ldap server. **/
    public Map<String,String> getAllOrganizations(){
        Map<String,String> organizationGID=new HashMap<String, String>();

        //get all the organization
        NamingEnumeration<NameClassPair> organizations = this.getAllRoles("ou=Organizations,dc=etriks,dc=eu");
        try {
            while(organizations.hasMore()){
                String groupCN=organizations.next().getName();
                String organizationName=groupCN.substring(6);
                String gidNumber = this.getGidNumber(groupCN + ",ou=Organizations,dc=etriks,dc=eu");
                organizationGID.put(gidNumber,organizationName);
            }
        } catch (NamingException e) {
            log.error("In UserGroupManager, fail to get all organizations."+e.toString());
        }
        return organizationGID;
    }
/*This method return all groups which located under ou=Groups,dc=etriks,dc=eu*/
    public Map<String,String> getAllGroups(){
        Map<String,String> userGroups=new HashMap<String, String>();
        NamingEnumeration<NameClassPair> groups = this.getAllRoles("ou=Groups,dc=etriks,dc=eu");
        try {
            while(groups.hasMore()){
                String groupCN=groups.next().getName();
                String groupDN=groupCN + ",ou=Groups,dc=etriks,dc=eu";
                String gidNumber = this.getGidNumber(groupDN);
                String groupName=groupCN.substring(3);
                userGroups.put(gidNumber,groupName);
            }
        } catch (NamingException e) {
            log.error("In UserGroupManager, fail to get all groups." + e.toString());
        }
        return userGroups;
    }

    /*This method return all transmart groups which located under ou=Transmart,ou=Applications,dc=etriks,dc=eu*/

    public Map<String,String> getAllTransmartGroups(){
        Map<String,String> transmartUserGroups=new HashMap<String, String>();
        NamingEnumeration<NameClassPair> groups = this.getAllRoles("ou=Transmart,ou=Applications,dc=etriks,dc=eu");
        try {
            while(groups.hasMore()){
                String groupCN=groups.next().getName();
                String groupDN=groupCN + ",ou=Transmart,ou=Applications,dc=etriks,dc=eu";
                String gidNumber = this.getGidNumber(groupDN);
                String groupName=groupCN.substring(3);
                transmartUserGroups.put(gidNumber,groupName);
            }
        } catch (NamingException e) {
            log.error("In UserGroupManager, fail to get all groups." + e.toString());
        }
        return transmartUserGroups;
    }

    /*This method returns all the groups which user belongs to*/
    public Map<String,String> getUserGroupsByUID(String uid){
        Map<String,String> userGroups=new HashMap<String, String>();
        NamingEnumeration<NameClassPair> groups = this.getAllRoles("ou=Groups,dc=etriks,dc=eu");
        try {
            while(groups.hasMore()){
                String groupCN=groups.next().getName();
                String groupDN=groupCN + ",ou=Groups,dc=etriks,dc=eu";
                String gidNumber = this.getGidNumber(groupDN);
                Map<String, List<String>> allEntry = this.getAllAttributesByEntryDN(groupDN);
                List<String> memberUids = allEntry.get("memberUid");

              /*  System.out.println(groupCN+":"+gidNumber);*/
                if(memberUids!=null){
                    for(int i=0;i<memberUids.size();i++){
                        String memberUid=memberUids.get(i);
                        if(memberUid.equals(uid)){
                           String groupName=groupCN.substring(3);
                            userGroups.put(gidNumber,groupName);
                        }
                       // System.out.println("has member :"+memberUid);
                    }}

            }
           // System.out.println(uid + " has roles :" + userGroups.toString());
        } catch (NamingException e) {
            log.error("In UserGroupManager, fail to get user groups." + e.toString());
        }
        return userGroups;
    }
    
}
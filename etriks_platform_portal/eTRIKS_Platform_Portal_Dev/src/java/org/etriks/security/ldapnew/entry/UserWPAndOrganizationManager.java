package org.etriks.security.ldapnew.entry;

import org.apache.log4j.Logger;
import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 1/7/16.
 */
public class UserWPAndOrganizationManager extends LdapEntriesManager {


    private String organizationsDN;
    private String workPackagesDN;
    //create an logger instance
    private final org.apache.log4j.Logger log= Logger.getLogger(this.getClass());
    //List of the entry class
    private List<String> userEntryObjClasses=new ArrayList<String>();

    public UserWPAndOrganizationManager(LdapConnectorConfig ldapServerInfo) throws IOException {
        super(ldapServerInfo);
        organizationsDN=ldapProperties.getOrganizationsDN();
        workPackagesDN=ldapProperties.getWorkPackagesDN();
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

    /*This method will create a new organization in the LDAP server under ou=Organizations,dc=etriks,dc=eu
 * It takes a organization name and the description of the organization as parameters
 * It returns true if the creation is success, otherwise it returns false
 * */
    public void createNewOrganization(String organizationName,String description) throws NamingException, IOException {
      //The gid base of Organization is defined in LdapConfig.properties.Which is 6000 by default
        int gidBase=this.ldapProperties.getOrganizationsGidNumBase();
       // System.out.println(gidBase);
        //The default organization gid is 6999
        String gidNumber=Integer.toString(gidBase+999);
        //the parent dir of the group
        String entryParentDir=organizationsDN;
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
        for(int i=gidBase;i<gidBase+allOrganizations.size()+1;i++){
            // System.out.println("in for loop"+i);
            if (!allOrganizations.containsKey(Integer.toString(i))) {
                gidNumber=Integer.toString(i);
                //System.out.println("in if gid: "+gidNumber);
                break;}
        }
       // System.out.println("gidNumber: "+gidNumber);
        entryAttributes.put("cn", cn);
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        this.saveEntry(entryObjClasses, entryAttributes, entryParentDir);
    }

    /**This method will return a map of Organization Name and gid which contains all organizations in the ldap server. **/
    public Map<String,String> getAllOrganizations() throws IOException {

        Map<String,String> organizationGID=new HashMap<String, String>();

        //get all the organization
        NamingEnumeration<NameClassPair> organizations = this.getAllEntries(organizationsDN);
        try {
            while(organizations.hasMore()){
                String groupCN=organizations.next().getName();
                String organizationName=groupCN.substring(6);
                String groupDN=groupCN + ","+organizationsDN;
                String gidNumber = this.getGidNumber(groupDN);
                organizationGID.put(gidNumber,organizationName);
            }
        } catch (NamingException e) {
            log.error("In UserGroupManager, fail to get all organizations."+e.toString());
        }
        return organizationGID;
    }

    /*
   * This method add a member into a Organization/Work Package as a member
   * It takes a organizationCN and a userId, The organizatino CN must be full
   * returns a boolean.
   * @param String OrganizationCN
   * @param String userId
   * @return boolean
   */
    public boolean addOrganizationMember(String organizationCN,String userId){

        String OrganizationDN=organizationCN+","+organizationsDN;

        return	this.insertNewAttribute(OrganizationDN, "memberUid",userId);
    }

    public boolean addWorkPackageMember(String workPackageCN,String userId){
        String workPackageDN=workPackageCN+","+workPackagesDN;
        return this.insertNewAttribute(workPackageDN,"memberUid",userId);
    }

    public String getOrganizationNameByGID(String gid) throws IOException {
         Map<String,String> organizationGID=this.getAllOrganizations();
          return organizationGID.get(gid);
    }
}

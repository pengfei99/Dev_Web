package org.etriks.security.ldap.entry;

/**
 * Created by pliu on 3/28/14.
 */
import org.etriks.security.ldap.serverprofile.LdapServerConnectorConfig;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRoleManager extends LdapEntryManager {
    //specify the parent directory of the role entries
    private String roleParentDir;
    //object classes of the new role(group) entry
    private List<String> userRoleEntryObjClasses=new ArrayList<String>();

    public UserRoleManager(LdapServerConnectorConfig ldapServerInfo,String roleParentDir,String searchBase){
        super(ldapServerInfo,searchBase);
        this.roleParentDir=roleParentDir;
        this.loadRoleEntryObjClasses();
    }


    /*
     * This method create a new entry(Role) in the ou=Groups
     *
     * Take a string and then call the method saveEntry which implemented in
     * the abstract parent class LdapEntryManager
     */
    public boolean creatRole(String roleName){
        Map<String,String> roleEntryAttributes=new HashMap<String,String>();
        roleEntryAttributes.put("cn", roleName);
        //add the default member. The admin of the Ldap server will be the super admin
       // roleEntryAttributes.put("member", "cn=admin,dc=etriks,dc=eu");
        return this.saveEntry(this.userRoleEntryObjClasses, roleEntryAttributes, roleParentDir);
    }
	/*
	 * This method take the DN of the userAccount and delete the user account entry from the
	 * LDAP server
	 */

    public boolean deleteRole(String roleCN){
        return this.deleteEntry(roleCN+","+roleParentDir);
    }

    /*
     * This method add a user to an role as a role member
     * It takes a role name and the user DN
     * returns a boolean.
     */
    public boolean addRoleMember(String roleName, String roleMemberDN){

        String entryCn="cn="+roleName+",";

        String entryDN=entryCn+this.roleParentDir;

        return	this.insertNewAttribute(entryDN, "member", roleMemberDN);
    }

    /*
     * This method returns all available role in the Ldap server
     */
    public NamingEnumeration<NameClassPair> getAllRoles(){
        return this.getAllEntries(roleParentDir);
    }

    /*
     * This method get all members of a role
     */
    public NamingEnumeration<NameClassPair> getAllRoleMember(String roleName){
        return this.getAllEntries(roleParentDir);
    }


   /*
    * Load the role entry object classes.
    * The object classes needs to be customized for every case
    */

    private void loadRoleEntryObjClasses() {
        userRoleEntryObjClasses.add("groupOfNames");
        userRoleEntryObjClasses.add("top");
    }

}
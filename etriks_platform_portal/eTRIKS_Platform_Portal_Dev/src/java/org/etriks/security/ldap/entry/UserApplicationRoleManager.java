package org.etriks.security.ldap.entry;

import org.etriks.security.ldap.serverprofile.LdapServerConnectorConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pliu on 1/28/15.
 */
public class UserApplicationRoleManager extends LdapEntryManager {

    //specify the parent directory of the role entries
    private String roleParentDir;
    //object classes of the new role(group) entry
    private List<String> userRoleEntryObjClasses=new ArrayList<String>();

    public UserApplicationRoleManager(LdapServerConnectorConfig ldapServerInfo,String roleParentDir,String searchBase){
        super(ldapServerInfo,searchBase);
        this.roleParentDir=roleParentDir;
        this.loadRoleEntryObjClasses();
    }
    private void loadRoleEntryObjClasses() {
        userRoleEntryObjClasses.add("groupOfUniqueNames");
        userRoleEntryObjClasses.add("top");
    }

    /*
    * This method add a user to an role as a role member
    * We predefine all application roles has the class of groupOfUniqueNames
    * All the members of a role is uniqueMember.
    * It takes a role name and the user DN
    * returns a boolean.
    */
    public boolean addRoleMember(String roleName, String roleMemberDN){

        String entryCn="cn="+roleName+",";

        String entryDN=entryCn+this.roleParentDir;

        return	this.insertNewAttribute(entryDN, "uniqueMember", roleMemberDN);
    }
}

package org.bioaster.security

import grails.transaction.Transactional
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.core.LdapOrganizationSearchController
import org.bioaster.security.ldapManager.LdapOrganizationManager
import org.bioaster.security.ldapManager.LdapUserAccountManager

@Transactional
class OrganizationManagerService {



    def boolean createOrganization(ILdapServerConnectorConfig targetLdapServer, String organizationName, String organizationDescription){
        //Create an instance of groupAccountManager
        def LdapOrganizationManager orgManager = new LdapOrganizationManager(targetLdapServer);
        def boolean result=orgManager.createNewOrganization(organizationName,organizationDescription);
        orgManager.closeConnection();
        return result;
    }

    def Map<String,String> getAllOrganization(ILdapServerConnectorConfig targetLdapServer){
        def orgManager=new LdapOrganizationManager(targetLdapServer);
        Map<String,String> allOrganizations;
        allOrganizations=orgManager.getAllOrganizations();
        orgManager.closeConnection();
        return allOrganizations
    }

    def List<String> getAllOrganizationName(ILdapServerConnectorConfig targetLdapServer){
        def orgManager=new LdapOrganizationManager(targetLdapServer);
        List<String> sortOrganizationNames=orgManager.getAllOrganizationName();
        Collections.sort(sortOrganizationNames);
        orgManager.closeConnection();
        return sortOrganizationNames;
    }

    /*
    * @params userName, user cn (e.g. full name start with familly name (all in maj), then firstName (first Letter in maj)
    * @params orgGidNumber, organization gid numnber
    * @return boolean, return true if the organization gid of user has been changed, else return false
    * */
    def boolean changeUserOrganization(ILdapServerConnectorConfig targetLdapServer,String userName,String orgGidNumber ){
        LdapUserAccountManager luam=new LdapUserAccountManager(targetLdapServer);
        def boolean result=false;
        result=luam.modifyUserGidNum(userName,orgGidNumber);
        luam.closeConnection();
        return result;
    }


    /*
   * This method take a organization name and a list of return attrs, it returns a map of return attrs key and values
    * @params String orgName,
    * @params String[] returnAttrs,
    * @return Map<String,String> orgnaizatinAttrs
   * */

    def Map<String,String> getOrganizationDetails(ILdapServerConnectorConfig targetLdapServer,String organizationName,String[] returnAttrs){
        def orgManager=new LdapOrganizationSearchController(targetLdapServer);
        def orgAttrs=orgManager.getOrganizationAttrs(organizationName,returnAttrs);
        orgManager.closeConnection();
        return orgAttrs;
    }

}

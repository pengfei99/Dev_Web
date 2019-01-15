package org.bioaster.security

import grails.transaction.Transactional
import org.apache.commons.collections.map.HashedMap
import org.bioaster.registration.BioasterUserRegistrationRequest
import org.bioaster.security.config.BUMPConfig
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.core.LdapOrganizationSearchController
import org.bioaster.security.core.LdapRoleSearchController
import org.bioaster.security.core.LdapUserSearchController
import org.bioaster.security.ldapManager.LdapUserAccountManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.naming.NamingException

@Transactional
class UserRegistrationService {

    static Logger log = LoggerFactory.getLogger(UserRegistrationService.class);

    def boolean userEmailExisted(ILdapServerConnectorConfig targetLdapServer,String email) {
        LdapUserSearchController lsc=new LdapUserSearchController(targetLdapServer);
        boolean result= lsc.checkEmailExistence(email);
        lsc.closeConnection();
        return result;
    }

    def List<String> getAdminMailList(ILdapServerConnectorConfig targetLdapServer){
        LdapRoleSearchController lrc=new LdapRoleSearchController(targetLdapServer);
        LdapUserSearchController luc=new LdapUserSearchController(targetLdapServer);
        List<String> adminMailList = new ArrayList<String>();
        List<String> adminList=null;
        try {
            adminList=lrc.getRoleMemberNameList("BUMP_admin","BUMP");
            for(String adminName:adminList){
                adminMailList.add(luc.getUserEmail(adminName));
            }
            lrc.closeConnection();
            luc.closeConnection();
        } catch (NamingException e) {
            log.error("Cant't get admin member list!!! "+e.toString());
        }

      return adminMailList;
    }

    def List<String> getValidatorNameList(ILdapServerConnectorConfig ldapConf){
        LdapRoleSearchController lrc=new LdapRoleSearchController(ldapConf);
        List<String> validatorList=null;
        try {
            validatorList=lrc.getRoleMemberNameList("BUMP_validator","BUMP");
            lrc.closeConnection();
        } catch (NamingException e) {
            log.error("Cant't get validator member list!!! "+e.toString());
        }
        return validatorList;
    }

    def String getValidatorMail(ILdapServerConnectorConfig ldapConf,String validatorName){
        LdapUserSearchController luc=new LdapUserSearchController(ldapConf);
        String validatorMail= null;
        try {
            validatorMail = luc.getUserEmail(validatorName);
            luc.closeConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return validatorMail;
    }

    def Map<String,Object> buildLdapEntryAttrs(ILdapServerConnectorConfig ldapConf, BioasterUserRegistrationRequest userRegistrationRequest){

        LdapUserAccountManager luam=new LdapUserAccountManager(ldapConf);
        LdapOrganizationSearchController losc=new LdapOrganizationSearchController(ldapConf);
        def requestID=userRegistrationRequest.id
        String firstName=userRegistrationRequest.firstName.substring(0, 1).toUpperCase() + userRegistrationRequest.firstName.substring(1).toLowerCase();
        String lastName=userRegistrationRequest.lastName.substring(0, 1).toUpperCase() + userRegistrationRequest.lastName.substring(1).toLowerCase();
        String cn=lastName+" "+firstName;
        String uid=luam.buildUserID(firstName,lastName);
        String uidNumber=luam.getAvailableUidNumFromTail();
        String orgID=losc.getOrganizationGidNum(userRegistrationRequest.organization);

        luam.closeConnection();
        losc.closeConnection();
       /* entryAttrsMap.put("cn", cn);
        entryAttrsMap.put("gidNumber", orgID);
        entryAttrsMap.put("homeDirectory", BUMPConfig.homeDir+uid);
        entryAttrsMap.put("sn", lastName);
        entryAttrsMap.put("uid", uid);
        entryAttrsMap.put("uidNumber", uidNumber);
        entryAttrsMap.put("givenName", firstName);
        entryAttrsMap.put("loginShell", BUMPConfig.loginShell);
        entryAttrsMap.put("mail", userRegistrationRequest.email);
        entryAttrsMap.put("userPassword", userRegistrationRequest.password);*/
        def accountAttrs=[requestID: requestID,cn:cn,uid:uid,sn:lastName,givenName:firstName,password:userRegistrationRequest.password,
                                   uidNumber:uidNumber,gidNumber:orgID,homeDirectory:BUMPConfig.homeDir+uid,mail:userRegistrationRequest.email,
                                   loginShell:BUMPConfig.loginShell,organization:userRegistrationRequest.organization,validator:userRegistrationRequest.validator]
        return accountAttrs;
    }

    def boolean createUserLdapEntry(ILdapServerConnectorConfig ldapConf,Map<String, Object> userEntryAttrs){
        LdapUserAccountManager luam=new LdapUserAccountManager(ldapConf);
         boolean result= luam.addUserAccount(userEntryAttrs)
        luam.closeConnection();
        return result;
    }



}

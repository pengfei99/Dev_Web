package org.bioaster.test;


import org.bioaster.mail.MailManager;
import org.bioaster.mail.MailMessage;
import org.bioaster.security.adManager.ADUserAccountManager;
import org.bioaster.security.auth.UserDetails;
import org.bioaster.security.authorization.PolicyEngine;
import org.bioaster.security.authorization.xacml.PEPClient;
import org.bioaster.security.authorization.xacml.PolicyDecision;
import org.bioaster.security.config.*;
import org.bioaster.security.core.*;
import org.bioaster.security.encryption.PasswordChecker;
import org.bioaster.security.encryption.PasswordEncoder;
import org.bioaster.security.encryption.RandomPwdGenerator;
import org.bioaster.security.ldapManager.*;
import org.bioaster.security.registration.RegistrationValidatorService;
import org.bioaster.utils.StringToIntCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 2/17/17.
 */
public class FunctionTest {

    /* Get actual class name to be printed on */
    static Logger log = LoggerFactory.getLogger(FunctionTest.class);

    public void logTest(){
        log.info("This is an info");
        log.warn("this is an warn");
        //log.error("This is an error, mother fucker");
    }

    public static void main(String[] args){

       ExportADUser adUser= new ExportADUser();
       adUser.getJsonFromObj();

      //  MailMessage mailMess=MailMessage.getInstance();
//        String text=mailMess.getTestMessage("pengfei");
//        System.out.println(text);
       // String text = mailMess.getRegistrationRequestAlertMessage("pengfei");
        //String text = mailMess.getAccountActivationMessage("pliu");
        //String text = mailMess.getAccountCreationConfirmationMessage("pliu","pengfei");
        //String text = mailMess.getRegistrationRequestDeletionMessage("BIOASTER","pengfei");
        //String text = mailMess.getResetPasswordMessage("Bioaster","pliu","pwd");
        //String text = mailMess.getImportSuccessMessage("pengfei");



       // System.out.println(text);


//        ILdapServerConnectorConfig adconfig= ADConfig.getInstance();
        ILdapServerConnectorConfig ldapConfig=OpenLdapConfig.getInstance();
//        PEPClient pep=new PEPClient();
//        PolicyDecision decision = pep.getDecisionByAttributes("pliu", "admins", "book1", "READ","");
//        System.out.println("XML decision: "+decision.getXMLDecision());
//        System.out.println("Bool decision: "+decision.getBooleanDecision());
//        System.out.println("String decision: "+decision.getStringDecision());

       /* RandomPwdGenerator pwdGenerator=RandomPwdGenerator.getInstance();
        System.out.println(pwdGenerator.generateRandomPassword());*/

       /* PasswordChecker pck=PasswordChecker.getInstance();
        System.out.println(pck.checkPasswordStrength("d@dFd12"));*/


/*List<String> groupList=new ArrayList<String>();
        try {
            LdapGroupManager lgm=new LdapGroupManager(ldapConfig);
            List<String> departmentList = lgm.getDepartmentName();
            for(String departmentName:departmentList){
                groupList.addAll(lgm.getDepartmentGroupList(departmentName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

       /* PolicyEngine policyEngine=PolicyEngine.getInstance();
        List<String> userRoles=new ArrayList<String>();
        //userRoles.add("BUMP_admin");
        userRoles.add("BUMP_validator1");
        System.out.println(policyEngine.checkRole("admin",userRoles));
        System.out.println(policyEngine.checkAuthorization("bioasterAdminDashBoard",userRoles));*/


        /*RegistrationValidatorService validatorService=RegistrationValidatorService.getInstance();
        validatorService.getBioasterValidatorList();
        List<String> allEmails = validatorService.getAllBioasterValidatorEmails();
        System.out.println(allEmails.toString());
        List<String> allNames = validatorService.getBioasterValidatorNameList();
        System.out.println(allNames.toString());
        System.out.println(validatorService.getBioasterValidatorEmail("Pengfei Liu"));*/

       /* FunctionTest ft=new FunctionTest();
        ft.logTest();
        try {
            PasswordEncoder penc=new PasswordEncoder("MD5");
            String encPassword = penc.encPasswordMd5("haha");
            System.out.println(encPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*FunctionTest ft=new FunctionTest();
        ft.logTest();*/

        // test code for remove user from group
//        List<String> uidList=new ArrayList<String>();
//        uidList.add("pliu");
//        uidList.add("haha");
//        LdapGroupManager lgm=null;
//
//            try {
//                lgm = new LdapGroupManager(ldapConfig);
//
//
//            } catch (IOException e) {
//                System.out.println("In io exception, " + e.toString());
//            }
//
//for(String uid:uidList) {
//        try {
//            lgm.removeUserFromGroup("UTEC06_test21","ptest");
//        } catch (NamingException e) {
//            System.out.println("In naming exception, "+ e.toString());
//        }
//}
//        try {
//            lgm.closeConnection();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
        /*LdapProjectSearchController lpc=new LdapProjectSearchController(ldapConfig);
        try {
           System.out.println( lpc.checkGroupExistence("PASSPORT_web_dev"));
            System.out.println( lpc.checkGroupNumExistence("50001"));
            System.out.println(lpc.getGroupGidNum("PASSPORT_web_de"));
            lpc.closeConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
//        try {
//            LdapProjectManager lpm=new LdapProjectManager(ldapConfig);
//            //lpm.createNewGroup("testPV","PASSPORT","tesT");
//            lpm.createNewProjectGroupWithGidNum("testgid","51000","PASSPORT","test");
//           // lpm.addUserToGroup("PASSPORT_testPV","pliu");
//            // lpm.removeUserFromGroup("PASSPORT_test1","pliu");
//            // lpm.deleteGroup("PASSPORT_test1");
//            lpm.closeConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }

//        try {
//            LdapGroupManager lgm = new LdapGroupManager(ldapConfig);
//            lgm.createNewDepartmentGroupWithGidNum("testgid","16004","UTEC06","test");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }



       /* LdapGroupSearchController lgsc=new LdapGroupSearchController(ldapConfig);
        LdapProjectSearchController lpsc=new LdapProjectSearchController(ldapConfig);

        try {

            System.out.println(lgsc.checkDepartmentGroupMemberUidExistence("UTEC01_member","pliu"));
            System.out.println(lpsc.checkProjectGroupMemberUidExistence("PASSPORT_web_dev","pveyre"));
            lgsc.closeConnection();
            lpsc.closeConnection();
        } catch (NamingException e) {
            log.error(e.toString());
        }*/



      /*  LdapOrganizationManager ldapOrg= null;
        try {
            ldapOrg = new LdapOrganizationManager(ldapConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       /* LdapUserAccountManager ldapUser= null;
        try {
            ldapUser = new LdapUserAccountManager(ldapConfig);

            ldapUser.modifyUserPwd("LIU Pengfei","toto");

            System.out.println(ldapUser.getALLUserNameList());


            ldapUser.closeConnection();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }*/


        /*try {
            LdapAuthenticator lauth=new LdapAuthenticator(ldapConfig);
            String dn=lauth.checkUserPassword("pliu","toto");
            String fullName=dn.substring(3,dn.indexOf(","));
            System.out.println(dn);
            System.out.println(fullName);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*try {

            System.out.println(ldapOrg.getAllOrganizationName().toString());
           // ldapOrg.createNewOrganization("hahaha","dfhdfdsf");
            //System.out.println(ldapOrg.getOrganizationGIDNum("Bioaster_IP"));
            //System.out.println(ldapOrg.deleteOrganization("hahaha"));
            //ldapOrg.deleteEntry("cn=test1,ou=Organizations,dc=bioaster,dc=org");
            ldapOrg.closeConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }*/


        // ldapUser.modifyUserGidNum("LIU Pengfei","5888");
           /* LdapOrganizationSearchController los=new LdapOrganizationSearchController(ldapConfig);
        try {
            System.out.println(los.checkOrganizationExistence("BIOASTER_IP"));
            System.out.println(los.checkGidNumExistence("5000"));
            Map<String, String> atts = los.getOrganizationAttrs("BIOASTER_IP", new String[]{"cn", "gidNumber", "description"});
            for(Map.Entry<String,String> entry:atts.entrySet()){
                System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
//**
// LdapUserSearchController lsc=new LdapUserSearchController(ldapConfig);
//        try {
//            String mail=lsc.getUserMailWithUid("tliu").toLowerCase();
//            String myMail="pengfei.liu@bioaster.org";
//            System.out.println(mail.equals(myMail));
   /* System.out.println(lsc.checkEmailExistence("pengFei.liu@bioaster.org"));
    System.out.println(lsc.getUserID("LIU Pengfei").toString());
Attributes attr = lsc.getUserAttrs("LIU Test11", new String[]{"uid","mail", "cn", "memberOf","gidNumber","uidNumber","homeDirectory"});
String uid = attr.get("uid").get(0).toString();
            System.out.println(uid);
            System.out.println(attr.get("mail").get(0).toString());
            System.out.println(attr.get("cn").get(0).toString());
            System.out.println(attr.get("gidNumber").get(0).toString());
            System.out.println(attr.get("uidNumber").get(0).toString());
            System.out.println(attr.get("homeDirectory").get(0).toString());;
            List<String> roles=lsc.buildRoles(attr.get("memberOf"));
            System.out.println(roles.toString());
            List<String> bioasterGroup = lsc.getUserPosixBioasterGroup(uid);
            System.out.println(bioasterGroup.toString());
            List<String> projectGroup=lsc.getUserPosixProjectGroup(uid);
            System.out.println(projectGroup.toString());*/
/*lsc.closeConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
      /*  MailManager mail= MailManager.getInstance();
        try {
            mail.sendMail("pierre.dupuis@bioaster.org","warning","Do you fear me?");
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/

       /* LdapRoleSearchController lsc=new LdapRoleSearchController(ldapConfig);
        LdapUserSearchController luc=new LdapUserSearchController(ldapConfig);

        try {
           *//* List<String> adminList=lsc.getRoleMemberNameList("admin","BUMP");
            for(String adminName:adminList){

                System.out.println(luc.getUserEmail(adminName).toString())
                ;
            }*//*
           System.out.println(luc.getUserPosixGroup("pliu","ou=Groups,dc=bioaster,dc=org"));
           luc.closeConnection();

        } catch (NamingException e) {
            e.printStackTrace();
        }*/


      /*  LdapRoleSearchController lrc=new LdapRoleSearchController(ldapConfig);
        List<String> validatorList=null;
        try {
            validatorList=lrc.getRoleMemberNameList("BUMP_validator","BUMP");

            lrc.closeConnection();
        } catch (NamingException e) {
            log.error("Cant't get validator member list!!! "+e.toString());
        }
        System.out.println(validatorList) ;*/
        /*LdapUserSearchController luc=new LdapUserSearchController(ldapConfig);
        String validatorMail= null;
        try {
            validatorMail = luc.getUserEmail("LIU Pengfei");
            luc.closeConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        System.out.println(validatorMail);*/

      /*  String lastName="Liu";
        String firstName="Pengfei";
        String uid="jsnow";
        LdapUserSearchController lusc=new LdapUserSearchController(ldapConfig);
        for(int i=1;i<firstName.length()+1;i++){
            uid=firstName.substring(0,i)+lastName;
            try {
                if(!lusc.checkUidExistence(uid)) break;
            } catch (NamingException e) {
                e.printStackTrace();
            }

        }
        try {
            lusc.closeConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        System.out.println(uid.toLowerCase());
*/
    }
}

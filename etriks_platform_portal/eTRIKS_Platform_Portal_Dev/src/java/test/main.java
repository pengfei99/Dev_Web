package test;


import org.etriks.security.ldapnew.entry.UserAccountsManager;
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig;

import java.io.IOException;

/**
 * Created by pliu on 3/27/14.
 */
public class main {





    public static void main(String[] args){

        PubLdapConnectorConfig prodLdap=new PubLdapConnectorConfig();
        UserAccountsManager uam= null;
        try {
            uam = new UserAccountsManager(prodLdap);
            boolean result=uam.cnExisted("Pengfei Liu");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //String defaultUserDirectory="/mnt/homedirs/users/";

       // ChangeUserHomeDir cuh=new ChangeUserHomeDir();
     //  cuh.changeHomeDir("cn=Pengfei Liu,ou=Users,dc=etriks,dc=eu","p.liu",defaultUserDirectory);

       // cuh.changeAllUsersHomeDir();

       /* EtriksMemberLdapConnectorConfig etriksLdap=new EtriksMemberLdapConnectorConfig();
        AbiriskLdapConnectorConfig abiriskLdap=new AbiriskLdapConnectorConfig();
        TestLdapConnectorConfig testLdap=new TestLdapConnectorConfig();
        OncoTrackLdapConnectorConfig oncotrackLdap=new OncoTrackLdapConnectorConfig();
*/
/*PasswordEncrytor pwdEnc=new PasswordEncrytor();
        String pwd="";
        try {
            pwd=pwdEnc.digestBase64("md5","bioasterPass");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(pwd);*/


       /* String encPassword="{SSHA}Ci/IGNBWolRdZBYro1UQVsEy+iFdxwOF";
        Map<String,String> entryAttributes=new HashMap<String,String>();
        //service which add the user entry to the ldap server. desactivate for now
        //ldapUserEntrySaveService.saveUserEntry(cn, uidNumber, sn, password, uidNumber, gidNumber, homeDirectory, loginShell, mail);
        entryAttributes.put("cn", "Haha2 Test");
        entryAttributes.put("uid", "h2.test");
        entryAttributes.put("sn", "Test");
        entryAttributes.put("userPassword", encPassword);
        entryAttributes.put("uidNumber", "10001");
        entryAttributes.put("gidNumber", "5001");
        entryAttributes.put("homeDirectory", "/home/users/h.test");
        entryAttributes.put("loginShell", "/bin/false/");
        entryAttributes.put("mail", "liu.pengfei@hotmail.fr");
        entryAttributes.put("givenName", "Haha");
        try {
            UserAccountsManager uam=new UserAccountsManager(testLdap);
            uam.createUserAccount(entryAttributes);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

      /*  LdapMigration mig=new LdapMigration(oncotrackLdap,testLdap);
        try {
            mig.transferOncoTrackUser();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NamingException e) {                                           TestLdapConnectorConfig testLdap=new TestLdapConnectorConfig();
            e.printStackTrace();
        }*/
      /*  try {
            UserProjectGroupManager upg=new UserProjectGroupManager(testLdap);
            upg.addProjectGroupOfNamesMember("etriks_member","portal","etriks","cn=Haha2 Test,ou=Users,dc=etriks,dc=eu");
           // System.out.println(upg.getAllProjectPosixGroup("abirisk").toString());
          // upg.createVMGroup("etriks","testForBen","test4");
            //upg.addProjectPosixGroupMember("etriks_testForBen","etriks","p.liu");
           // System.out.println(upg.getUserProjectPosixGroupsByUID("abirisk","p.liu").toString());
          //  System.out.println(upg.getAllUserPosixGroups("p.liu").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       /* try {
            UserWPAndOrganizationManager uw=new UserWPAndOrganizationManager(testLdap);
            uw.createNewOrganization("testOrgForBen","test run");
           // uw.addOrganizationMember("cn=CP_Alacris","p.liu");
          //uw.addWorkPackageMember("cn=wp1","p.liu");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }*/

      /*  LdapProperties lp=new LdapProperties();

        try {
           String  org=lp.getOrganizationsDN();

            System.out.println(org);
            String  base=lp.getBaseDN();

            System.out.println(base);
            String  agr=lp.getProviderGroupsDN();

            System.out.println(agr);
            String  projects=lp.getProjectsDN();

            System.out.println(projects);
            String  users=lp.getUsersDN();

            System.out.println(users);
            String  wp=lp.getWorkPackagesDN();

            System.out.println(wp);
            int gid=lp.getOrganizationsGidNumBase();
            System.out.println(gid+1);
        } catch (IOException e) {
            e.printStackTrace();
        }*/




      /*  LdapAuthenticator newldap=new LdapAuthenticator(testLdap);
        System.out.println(newldap.checkAuthWithLdap("p.liu1", "ok"));*/
/*
        String encPassword="{sha}eoX0dku9ba8cNUXvu/DyeabcC+s=";
        Map<String,String> entryAttributes=new HashMap<String,String>();
        //service which add the user entry to the ldap server. desactivate for now
        //ldapUserEntrySaveService.saveUserEntry(cn, uidNumber, sn, password, uidNumber, gidNumber, homeDirectory, loginShell, mail);
        entryAttributes.put("cn", "Haha2 Test");
        entryAttributes.put("uid", "h2.test");
        entryAttributes.put("sn", "Test");
        entryAttributes.put("userPassword", encPassword);
        entryAttributes.put("uidNumber", "10001");
        entryAttributes.put("gidNumber", "5001");
        entryAttributes.put("homeDirectory", "/home/users/h.test");
        entryAttributes.put("loginShell", "/bin/false/");
        entryAttributes.put("mail", "liu.pengfei@hotmail.fr");
        entryAttributes.put("givenName", "Haha");*/

       /* PubLdapConnectorConfig testLdap=new PubLdapConnectorConfig();*/
       /* LdapAuthenticator ldapAuthenticator=new LdapAuthenticator(testLdap);
        System.out.println(ldapAuthenticator.checkAuthWithLdap("p.liu","Liua1983"));*/
       // TestLdapConnectorConfig testLdap=new TestLdapConnectorConfig();
        /*try {
            UserProjectGroupManager upg=new UserProjectGroupManager(testLdap);
           boolean message= upg.addProjectPosixGroupMember("bioaster_pengfei_test","bioaster","p.liu");
            System.out.println(message);*/
            /*UserAccountsManager uam=new UserAccountsManager(testLdap);
            UserProjectGroupManager upg=new UserProjectGroupManager(testLdap);*/
            //uam.changeUserMail("p.liu","Liua1983","liu.pengfei@hotmail.fr");


          /*  if(upg.valueExist("memberUid","p.liu","cn=etriks_member,ou=posix_groups,ou=etriks,ou=Projects,dc=etriks,dc=eu")==null){
                System.out.println("null");
            }
            else {System.out.println(upg.valueExist("memberUid","p.liu","cn=etriks_member,ou=posix_groups,ou=etriks,ou=Projects,dc=etriks,dc=eu"));}*/
          // System.out.println(upg.deleteUserFromPosixGroup("a.allo","cn=eTRIKS_WP1,ou=Work_Packages,dc=etriks,dc=eu"));
            //System.out.println(upg.getAllUserPosixGroups("p.liu"));
            //System.out.println(uam.userEmailsExisted("pliu@cc.in2p3.fr"));

        } /*catch (InvalidAttributeValueException e) {

            System.out.println("get u attribute!!!");
            e.printStackTrace();
        }*/
        /*catch (InvalidNameException e){
            System.out.println("get u name!!!");
            e.printStackTrace();
        }
        catch (NamingException e){
            System.out.println("fuck u!!!");
            e.printStackTrace();
        } *//*catch (IOException e) {
            e.printStackTrace();
        }*/
 




        /*PasswordEncrytor pwdEnc=new PasswordEncrytor();
        String pwd="";
        try {
            pwd=pwdEnc.digestBase64("md5","bioasterPass");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(pwd);*/

       /* ProjectValidator pv=new ProjectValidator("/home/pliu/Downloads/csvtest.txt");
        pv.init();
        System.out.println(pv.getValidatorNameList());
        System.out.println(pv.getValidatorEmail("pengfei"));*/


      /*  RSAKeyPair rsaKeyPair=new RSAKeyPair();
        rsaKeyPair.generate("RSA",2048);*/
        /*OncoTrackLdapConnectorConfig oncoLdap=new OncoTrackLdapConnectorConfig();
        UserApplicationRoleManager urm=new UserApplicationRoleManager(oncoLdap,"ou=Galaxy,ou=Applications,dc=etriks,dc=eu","ou=Galaxy,ou=Applications,dc=etriks,dc=eu");
       urm.addRoleMember("Galaxy_Explorer","cn=Gino Marchetti,ou=Users,dc=etriks,dc=eu");*/
/*
        ProjectValidator pv=new ProjectValidator("/usr/share/tomcat7/.portal/bioasterValidator.csv");
        pv.init();
       System.out.println(pv.getValidatorNameList().toString());
        System.out.println(pv.getValidatorEmail("Pengfei_Liu"));
        System.out.println(pv.setValidator("Haha_Liu","fff@cc.in2p3.fr"));
        System.out.println(pv.setValidator("Pengfei_Liu","pliu@cc.in2p3.fr"));
        System.out.println(pv.setValidator("Foo_Bar","ffb@cc.in2p3.fr"));*/
/*
        CCJAutoMail mail=new CCJAutoMail();
        try {
            mail.sendMail("pliu@cc.in2p3.fr","coucou","pourquoi ca merde");
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/

       /* JAutoMail mail=new JAutoMail();
        try{
            mail.sendMail("pliu@cc.in2p3.fr","test","test");

        }
        catch (MessagingException e){
            e.printStackTrace();
        }*/

     /*   PasswordEncrytor pe=new PasswordEncrytor();
        try {
            String pwd=pe.digestBase64("md5","haha");
            System.out.println(pwd);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/


       /* RandomPasswordGenerator pg=new RandomPasswordGenerator();
        String pwd1=pg.generateRandomPassword();
        String pwd2=pg.generateRandomPassword();
        System.out.println(pwd1);
        System.out.println(pwd2);*/

       /* RandomPasswordGenerator pg=new RandomPasswordGenerator();
        TestLdapConnectorConfig testldap=new TestLdapConnectorConfig();
        UserAccountManager uam=new UserAccountManager(testldap,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");
        String uid="p.liu";
        String userDn=uam.getUserDn(uid);
        if(userDn==null){
            System.out.println("The uid which you enter does not exist!!!");
            //redirect to the forget password page
        }
        else {
        String mail=uam.getUserMailByDn(userDn);
        System.out.println(mail);

            String email="liu.pengfei@hotmail.fr";
            //If the entered email address match the registered email address, start the reset password procedure
            if(mail.equals(email)){
                String pwd1=pg.generateRandomPassword();
                boolean result=uam.resetUserPassword(userDn,pwd1);
                //Reset password succeful redirect to user space page
                if(result){
                System.out.println("Your password has been reset, you will receive your new password by email. You need to change it as soon as possible "+pwd1);

                //Send new password to user mail box
                CCJAutoMail emailer = new CCJAutoMail();
                String msg=new MailMessage().getResetPasswordMessage("abirisk",uid, pwd1);
                try {
                    emailer.sendMail("pliu@cc.in2p3.fr", "Password reset notification", msg);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                }
                //reset password fail, report to error log
                else {
                    //log error
                }

            }
            //The entered email address dosenot match the registered email address, stop the reset. alert maybe an attack
            else {
                System.out.println("The email which you entered does not correspond your registered email!!! If you trying to hack in the system, be ware, your IP address has been registered.");
            }*/

       /* PolicyEnforcementPoint pep=new PolicyEnforcementPoint();
        PolicyDecision decision1=pep.evaluateRequest("pengfei","bookLover","Novel","read");

        PolicyDecision decision2=pep.evaluateRequest("pengfei","bookLover","book","write");

        PolicyDecision decision3=pep.evaluateRequest("pengfei","bookLover","rice","eat");

        PolicyDecision decision4=pep.evaluateRequest("pengfei","student","TV","watch");

        PolicyDecision decision5=pep.evaluateRequest("haha","Role_" +
                "Admin","dashboard","read");
        PolicyDecision decision6=pep.evaluateRequest("","Role_" +
                "Dev","usermanager","read");

        System.out.println("Decision 1 is :"+decision1.getXMLDecision());

        System.out.println("Decision 2 is :"+decision2.getDecision());

        System.out.println("Decision 3 is :"+decision3.getDecision());

        System.out.println("Decision 4 is :"+decision4.getDecision());

        System.out.println("Decision 5 is :"+decision5.getDecision());

        System.out.println("Decision 6 is :"+decision6.getDecision());*/

       /* CCJAutoMail emailer = new CCJAutoMail();
        String msg=new MailMessage().getAccountCreationConfirmationMessage("p.liu","haha");
        try {
            emailer.sendMail("pliu@cc.in2p3.fr", "Account validation", msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/

       /* String pwd="Liu@1983ab";
        PasswordStrength pwdStr=new PasswordStrength();
        System.out.println(pwdStr.checkPasswordStrength(pwd));*/
/*
        ValidatorMail vm=new ValidatorMail();
        System.out.println(vm.getMail("Fabien Richard"));*/

       /*
       //ldap authenticator test
        TestLdapConnectorConfig testldap=new TestLdapConnectorConfig();
        LdapAuthenticator ldap=new LdapAuthenticator(testldap);

        System.out.println(ldap.checkAuthWithLdap("p.liu","ok"));


        //mail test
        JAutoMail emailer = new JAutoMail();
        String msg=new MailMessage().getAccountActivationMessage("p.liu");
        try {
            emailer.sendMail("liu.pengfei@hotmail.fr", "Account validation", msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/

       /*
        //password hash test
        PasswordEncrytor pwdEnc=new PasswordEncrytor();

        try {
            System.out.println(pwdEnc.digestBase64("SHA", "hello"));
            System.out.println(pwdEnc.digestHex("MD5", "hello"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
       /* AbiriskLdapConnectorConfig abiLdapConfig=new AbiriskLdapConnectorConfig();
        //TestLdapConnectorConfig testldap=new TestLdapConnectorConfig();
        UserAccountManager accReg = new UserAccountManager(abiLdapConfig,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");
        int size=accReg.getUserSize();
        int max=10001+size;
        int uidNum=10000;
        while(uidNum<max){
            if(accReg.uidNumberExisted(Integer.toString(uidNum))){
                uidNum++;
            }
            else {
                break;}
        }
        System.out.println("First empty uidNumber is "+uidNum);
      System.out.println(accReg.getUserDnByuidNumber("10027"));
        System.out.println(size);

    }*/


  /*
        String uid="m.clifford";

        UserGroupManager grpReg = new UserGroupManager(abiLdapConfig,"dc=etriks,dc=eu");
        Map<String,String> organizationGID=grpReg.getAllOrganizations();
        System.out.println("Organization info : "+organizationGID.toString());

        Map<String,String> userGroups=grpReg.getUserGroupsByUID(uid);
        System.out.println(uid + " has role : " + userGroups.toString());
        Map<String,String> gg=grpReg.getAllGroups();
        System.out.println("Groups info : " + gg.toString());
       *//* String gid = grpReg.getGidNumber("cn=CP_Merck,ou=Organizations,dc=etriks,dc=eu");
        System.out.println(gid);*//*
       // AbiriskLdapConnectorConfig abiLdapConfig=new AbiriskLdapConnectorConfig();
        //TestLdapConnectorConfig testldap=new TestLdapConnectorConfig();
        UserAccountManager accReg = new UserAccountManager(abiLdapConfig,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");
String id="Pengfei Liu";
        String dn="cn="+id+",ou=Users,dc=etriks,dc=eu";
        Map<String, List<String>> attrs = accReg.getAllAttributesByEntryDN(dn);
       // System.out.println(attrs.toString());
        String gidNumber=attrs.get("gidNumber").get(0);
        String organizationName=organizationGID.get(gidNumber);
        System.out.println(id+" belongs to "+organizationName);*/
/*
        TestLdapConnectorConfig test=new TestLdapConnectorConfig();

        UserGroupManager grpReg = new UserGroupManager(test,"dc=etriks,dc=eu");
     // System.out.println(grpReg.createNewGroup("Test_Group5","test"))  ;
       System.out.println(grpReg.createNewOrganization("TestTOTOTO2","test")) ;*/

        /*AbiriskLdapConnectorConfig abiLdapConfig=new AbiriskLdapConnectorConfig();
        UserAccountManager accReg = new UserAccountManager(abiLdapConfig,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");
        accReg.changeGIDNumber("p.liu","514");*/
       /* UserGroupManager grpReg = new UserGroupManager(abiLdapConfig,"dc=etriks,dc=eu");
        grpReg.addGroupsMember("cn=Data_Loader","p.liu");*/
        /*Map<String, String> allUsers = accReg.getAllUserNameAndUid();
        System.out.println(allUsers.toString());*/


       // String adminMSG=new MailMessage().getAccountCreationConfirmationMessage("test","pliu");
        /*ValidatorMail validatorMail=new ValidatorMail();
        List<String> allMails=validatorMail.getAllUserMails();
        for(int i=0;i<allMails.size();i++){
            String email= allMails.get(i);
            System.out.println("admin mail : "+email);*/
            //ccEmailer.sendMail(email,"Account validation confirmation ", adminMSG)

       //}
       /*String deletionMail=new MailMessage().getRequestDeletionMessage("Abirisk project","p.liu");
        CCJAutoMail ccEmailer=new CCJAutoMail();
        try {
            ccEmailer.sendMail("pliu@cc.in2p3.fr","test",deletionMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }*/

    }





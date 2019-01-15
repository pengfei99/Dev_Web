package org.etriks.security.Ldap
import grails.transaction.Transactional
import org.etriks.security.encryption.PasswordEncrytor
import org.etriks.security.ldapnew.entry.UserAccountsManager
import org.etriks.security.ldapnew.entry.UserProjectGroupManager
import org.etriks.security.ldapnew.entry.UserWPAndOrganizationManager
import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig
import org.etriks.security.register.AbiriskMemberAccount
import org.etriks.security.register.BioasterMemberAccount
import org.etriks.security.register.EtriksMemberAccount
import org.etriks.security.register.OncoTrackMemberAccount

import javax.naming.InvalidNameException
import javax.naming.NamingException
import javax.naming.directory.InvalidAttributeValueException

/*
* This service create user account in the new ldap archi
*
* */
@Transactional
class CreateNewUserAccountService {

    def defaultUserDirectory="/mnt/homedirs/users/"
    def defaultLoginShell="/bin/bash"

    def LinkedHashMap<String,Object> createNewEtriksAccountPreparation(LdapConnectorConfig targetLdapServer,EtriksMemberAccount etriksMemberAccountInstance ) {
        def UserAccountsManager accountManager=new UserAccountsManager(targetLdapServer);

        def UserWPAndOrganizationManager wpAndOrganizationManager=new UserWPAndOrganizationManager(targetLdapServer);

        /*****************************************Determine the gidNumber for new users****************************/
        def dn="cn=CP_"+etriksMemberAccountInstance.organization+",ou=Organizations,dc=etriks,dc=eu";
        String gid = wpAndOrganizationManager.getGidNumber(dn);
        wpAndOrganizationManager.closeContext();
        /*********************************End of the gidNumber determination***********************************/

        /******************************************Determine the best uidNumber for nes users*****************************/
     int uidNum=accountManager.getAvailableUidNumberFromTile();
        accountManager.closeContext();
        /*****************************************End of the determination********************************************/

        def firstName=etriksMemberAccountInstance.firstName.substring(0, 1).toUpperCase() + etriksMemberAccountInstance.firstName.substring(1).toLowerCase();
        def lastName=etriksMemberAccountInstance.lastName.substring(0, 1).toUpperCase() + etriksMemberAccountInstance.lastName.substring(1).toLowerCase();
        def accountid=etriksMemberAccountInstance.id
        def cn=firstName+" "+lastName
        def uid=firstName.substring(0,1).toLowerCase()+"."+lastName.toLowerCase()
        def sn=lastName
        def givenName=firstName
        def password=etriksMemberAccountInstance.password
        def uidNumber=uidNum;
        def organization=etriksMemberAccountInstance.organization;
        def String organizationCN="cn=CP_"+organization;
        def String organizationDN=organizationCN+",ou=Organizations,dc=etriks,dc=eu";
        def gidNumber=gid;
        def homeDirectory=defaultUserDirectory+uid
        def loginShell=defaultLoginShell
        def mail=etriksMemberAccountInstance.email
        def workPackage=etriksMemberAccountInstance.workPackage;
        def etriksMemberAccount=[id:accountid,cn:cn,uid:uid,sn:sn,givenName:givenName,password:password,uidNumber:uidNumber,gidNumber:gidNumber,homeDirectory:homeDirectory,mail:mail,loginShell:loginShell,organization:organization,workPackage:workPackage]
        return etriksMemberAccount;
}


    def boolean createNewEtriksUserAccountLdapEntry(LdapConnectorConfig targetLdapServer,LinkedHashMap<String,Object> params){
        String etriksMemberGroupDn="cn=etriks_member,ou=posix_groups,ou=etriks,ou=Projects,dc=etriks,dc=eu";
        boolean flag=false;
        //creat the instance of the user account mananger, the ldapurl must be the ldap which you want to put people in
        UserAccountsManager accReg = new UserAccountsManager(targetLdapServer);

        //create the instance of the user group manager
        UserWPAndOrganizationManager orgManager = new UserWPAndOrganizationManager(targetLdapServer);
        UserProjectGroupManager grpReg = new UserProjectGroupManager(targetLdapServer);

        //build user account entry parameter
        Map<String,String> entryAttributes=new HashMap<String,String>();
        entryAttributes.put("cn", params.get("cn"));
        entryAttributes.put("uid", params.get("uid"));
        entryAttributes.put("sn", params.get("sn"));
        entryAttributes.put("userPassword", params.get("userPassword"));
        entryAttributes.put("uidNumber", params.get("uidNumber"));
        entryAttributes.put("gidNumber", params.get("gidNumber"));
        entryAttributes.put("homeDirectory", params.get("homeDirectory"));
        entryAttributes.put("loginShell", params.get("loginShell"));
        entryAttributes.put("mail", params.get("mail"));
        entryAttributes.put("givenName", params.get("givenName"));

        String workPackageCN=params.get("workPackageCN");
        String userDn="cn="+params.get("cn")+",ou=Users,dc=etriks,dc=eu"
        def boolean userNameExisted=accReg.userNameExisted(params.get("cn"));
        def String valueExisted=accReg.valueExist("memberUid",params.get("uid"),etriksMemberGroupDn);
        //if the account doesn't not exist,create account and insert role normally
        if((!userNameExisted)&&valueExisted==null){
            try{
                accReg.createUserAccount(entryAttributes);
                //insert uid to workPackage
                orgManager.addWorkPackageMember(workPackageCN,params.get("uid"));
                //add default group project_member(e.g. abirisk_member,bioaster_member)
                grpReg.addProjectPosixGroupMember("etriks_member","etriks",params.get("uid"))
                grpReg.addProjectGroupOfNamesMember("etriks_member","portal","etriks",userDn)
                flag=true;
            }
            catch (InvalidAttributeValueException e){
                flag=false;
                log.error("Unable to create user account, contains wrong attributes"+e.toString())
            }
            catch (InvalidNameException e){
                flag=false;
                log.error("Unable to create user account, contains wrong cn name"+e.toString())
            }
            catch (NamingException e){
                flag=false;
                log.error(e.toString())
            }
        }
            //if the account exist, but does not have etriks_member role, insert uid into etriks_member role.
        else if(userNameExisted&&valueExisted==null){
           flag=grpReg.addProjectPosixGroupMember("etriks_member","etriks",params.get("uid"));
            grpReg.addProjectGroupOfNamesMember("etriks_member","portal","etriks",userDn)
        }
            //account and role all exists, do nothing, send message
        else{
            flag=false;
            log.info("User account already existed!!! SKipping user account creation and group insertion");
        }


        //close the ldap connection context
        accReg.closeContext();
        orgManager.closeContext();
        return flag;

    }

    def LinkedHashMap<String,Object> createNewAbiriskAccountPreparation(LdapConnectorConfig targetLdapServer,AbiriskMemberAccount accountInstance ) {
        def UserAccountsManager accountManager=new UserAccountsManager(targetLdapServer);

        def UserWPAndOrganizationManager wpAndOrganizationManager=new UserWPAndOrganizationManager(targetLdapServer);

        /*****************************************Determine the gidNumber for new users****************************/
        def dn="cn=CP_"+accountInstance.organization+",ou=Organizations,dc=etriks,dc=eu";
        String gid = wpAndOrganizationManager.getGidNumber(dn);
        wpAndOrganizationManager.closeContext();
        /*********************************End of the gidNumber determination***********************************/

        /******************************************Determine the best uidNumber for nes users*****************************/
        int uidNum=accountManager.getAvailableUidNumberFromTile();
        accountManager.closeContext();
        /*****************************************End of the determination********************************************/

        def firstName=accountInstance.firstName.substring(0, 1).toUpperCase() + accountInstance.firstName.substring(1).toLowerCase();
        def lastName=accountInstance.lastName.substring(0, 1).toUpperCase() + accountInstance.lastName.substring(1).toLowerCase();
        def accountid=accountInstance.id
        def cn=firstName+" "+lastName
        def uid=firstName.substring(0,1).toLowerCase()+"."+lastName.toLowerCase()
        def sn=lastName
        def givenName=firstName
        def password=accountInstance.password
        def uidNumber=uidNum;
        def organization=accountInstance.organization;
        def String organizationCN="cn=CP_"+organization;
        def String organizationDN=organizationCN+",ou=Organizations,dc=etriks,dc=eu";
        def gidNumber=gid;
        def homeDirectory=defaultUserDirectory+uid
        def loginShell=defaultLoginShell
        def mail=accountInstance.email
        def groups=accountInstance.groups;
        def validator=accountInstance.abiriskValidator;
        def abiriskMemberAccount=[accountID:accountid,cn:cn,uid:uid,sn:sn,givenName:givenName,password:password,uidNumber:uidNumber,gidNumber:gidNumber,homeDirectory:homeDirectory,mail:mail,loginShell:loginShell,organization:organization,groups:groups,validator:validator]

        return abiriskMemberAccount;
    }


    def LinkedHashMap<String,Object> createNewBioasterAccountPreparation(LdapConnectorConfig targetLdapServer,BioasterMemberAccount accountInstance ) {
        def UserAccountsManager accountManager=new UserAccountsManager(targetLdapServer);

        def UserWPAndOrganizationManager wpAndOrganizationManager=new UserWPAndOrganizationManager(targetLdapServer);

        /*****************************************Determine the gidNumber for new users****************************/
        def dn="cn=CP_"+accountInstance.organization+",ou=Organizations,dc=etriks,dc=eu";
        String gid = wpAndOrganizationManager.getGidNumber(dn);
        wpAndOrganizationManager.closeContext();
        /*********************************End of the gidNumber determination***********************************/

        /******************************************Determine the best uidNumber for nes users*****************************/
        int uidNum=accountManager.getAvailableUidNumberFromTile();
        accountManager.closeContext();
        /*****************************************End of the determination********************************************/

        def firstName=accountInstance.firstName.substring(0, 1).toUpperCase() + accountInstance.firstName.substring(1).toLowerCase();
        def lastName=accountInstance.lastName.substring(0, 1).toUpperCase() + accountInstance.lastName.substring(1).toLowerCase();
        def accountid=accountInstance.id
        def cn=firstName+" "+lastName
        def uid=firstName.substring(0,1).toLowerCase()+"."+lastName.toLowerCase()
        def sn=lastName
        def givenName=firstName
        def password=accountInstance.password
        def uidNumber=uidNum;
        def organization=accountInstance.organization;
        def String organizationCN="cn=CP_"+organization;
        def String organizationDN=organizationCN+",ou=Organizations,dc=etriks,dc=eu";
        def gidNumber=gid;
        def homeDirectory="/home/users/"+uid
        def loginShell="/bin/bash"
        def mail=accountInstance.email
        def groups=accountInstance.groups;
        def validator=accountInstance.bioasterValidator;
        def bioasterMemberAccount=[accountID:accountid,cn:cn,uid:uid,sn:sn,givenName:givenName,password:password,uidNumber:uidNumber,gidNumber:gidNumber,homeDirectory:homeDirectory,mail:mail,loginShell:loginShell,organization:organization,groups:groups,validator:validator]

        return bioasterMemberAccount;
    }

    def LinkedHashMap<String,Object> createNewOncotrackAccountPreparation(LdapConnectorConfig targetLdapServer,OncoTrackMemberAccount accountInstance ) {
        def UserAccountsManager accountManager=new UserAccountsManager(targetLdapServer);

        def UserWPAndOrganizationManager wpAndOrganizationManager=new UserWPAndOrganizationManager(targetLdapServer);

        /*****************************************Determine the gidNumber for new users****************************/
        def dn="cn=CP_"+accountInstance.organization+",ou=Organizations,dc=etriks,dc=eu";
        String gid = wpAndOrganizationManager.getGidNumber(dn);
        wpAndOrganizationManager.closeContext();
        /*********************************End of the gidNumber determination***********************************/

        /******************************************Determine the best uidNumber for nes users*****************************/
        int uidNum=accountManager.getAvailableUidNumberFromTile();
        accountManager.closeContext();
        /*****************************************End of the determination********************************************/

        def firstName=accountInstance.firstName.substring(0, 1).toUpperCase() + accountInstance.firstName.substring(1).toLowerCase();
        def lastName=accountInstance.lastName.substring(0, 1).toUpperCase() + accountInstance.lastName.substring(1).toLowerCase();
        def accountid=accountInstance.id
        def cn=firstName+" "+lastName
        def uid=firstName.substring(0,1).toLowerCase()+"."+lastName.toLowerCase()
        def sn=lastName
        def givenName=firstName
        def password=accountInstance.password
        def uidNumber=uidNum;
        def organization=accountInstance.organization;
        def String organizationCN="cn=CP_"+organization;
        def String organizationDN=organizationCN+",ou=Organizations,dc=etriks,dc=eu";
        def gidNumber=gid;
        def homeDirectory="/home/users/"+uid
        def loginShell="/bin/bash"
        def mail=accountInstance.email
        def groups=accountInstance.groups;
        def validator=accountInstance.oncoTrackValidator;
        def oncoTrackMemberAccount=[accountID:accountid,cn:cn,uid:uid,sn:sn,givenName:givenName,password:password,uidNumber:uidNumber,gidNumber:gidNumber,homeDirectory:homeDirectory,mail:mail,loginShell:loginShell,organization:organization,groups:groups,validator:validator]

        return oncoTrackMemberAccount;
    }

    def boolean createNewProjectUserAccountLdapEntry(LdapConnectorConfig targetLdapServer,LinkedHashMap<String,Object> params,String projectName){

        boolean flag=false;
        //creat the instance of the user account mananger, the ldap url must be the ldap which you want to put people in
        UserAccountsManager accReg = new UserAccountsManager(targetLdapServer);

        //create the instance of the user group manager
        UserProjectGroupManager grpReg = new UserProjectGroupManager(targetLdapServer);

        //build user account entry parameter
        Map<String,String> entryAttributes=new HashMap<String,String>();
        entryAttributes.put("cn", params.get("cn"));
        entryAttributes.put("uid", params.get("uid"));
        entryAttributes.put("sn", params.get("sn"));
        entryAttributes.put("userPassword", params.get("userPassword"));
        entryAttributes.put("uidNumber", params.get("uidNumber"));
        entryAttributes.put("gidNumber", params.get("gidNumber"));
        entryAttributes.put("homeDirectory", params.get("homeDirectory"));
        entryAttributes.put("loginShell", params.get("loginShell"));
        entryAttributes.put("mail", params.get("mail"));
        entryAttributes.put("givenName", params.get("givenName"))

        String userDn="cn="+params.get("cn")+",ou=Users,dc=etriks,dc=eu"
        def String groups=params.get("groups");
        def String ProjectMemberGroupDn="cn="+projectName+"_member,ou=posix_groups,ou="+projectName+",ou=Projects,dc=etriks,dc=eu"

        def boolean userNameExisted=accReg.cnExisted(params.get("cn"));
        def String valueExisted=accReg.valueExist("memberUid",params.get("uid"),ProjectMemberGroupDn);

        //if the account doesn't not exist,create account and insert role normally
        if((!userNameExisted)&&valueExisted==null){
            try{
                accReg.createUserAccount(entryAttributes);

                //insert uid to workPackage
                grpReg.addProjectPosixGroupMember(groups,projectName,params.get("uid"))

                //add default group project_member(e.g. abirisk_member,bioaster_member)
                grpReg.addProjectPosixGroupMember(projectName+"_member",projectName,params.get("uid"))
                grpReg.addProjectGroupOfNamesMember(projectName+"_member","portal",projectName,userDn)
                flag=true;
            }
            catch (InvalidAttributeValueException e){
                flag=false;
                log.error("Unable to create user account, contains wrong attributes"+e.toString())
            }
            catch (InvalidNameException e){
                flag=false;
                log.error("Unable to create user account, contains wrong cn name"+e.toString())
            }
            catch (NamingException e){
                flag=false;
                log.error(e.toString())
            }
        }
        else if(userNameExisted&&valueExisted==null){
            grpReg.addProjectPosixGroupMember(projectName+"_member",projectName,params.get("uid"))
            grpReg.addProjectGroupOfNamesMember(projectName+"_member","portal",projectName,userDn)
            flag=true;
        }
        else{
            flag=false;
            log.info("User already is existed");
        }

        //close the ldap connection context
        accReg.closeContext();
        grpReg.closeContext();
        return flag;

    }


    def boolean checkUserAccountExistance(LdapConnectorConfig targetLdapServer,LinkedHashMap<String,Object> userParams){
        boolean result=false;
        UserAccountsManager accReg = new UserAccountsManager(targetLdapServer);
        /*********Check If the user already has an account or not*******************/
        String userCN=userParams.get("cn");
        if(accReg.userNameExisted(userCN.substring(3))||accReg.userEmailsExisted(userParams.get("mail"))) {result=true;}
        else {result=false;}
        accReg.closeContext();
        return result;
    }



    /*This method is tempraray for convert list of user param of render page to a list which the create user account service can use*/
    def LinkedHashMap<String,Object> userParamWrapper(LinkedHashMap<String,Object> accountInstance){


        //create the instance of the password encrypter.
        PasswordEncrytor pwdEncrypter=new PasswordEncrytor();
        //Encrypte the password,
        String userPassword=pwdEncrypter.digestBase64("sha", accountInstance.get("password"));
        accountInstance.remove("password")
        String workPackageCN="cn="+accountInstance.get("workPackage")
        accountInstance.remove("workPackage")
        String uidNumber=Integer.toString(accountInstance.get("uidNumber"))
        accountInstance.put("userPassword",userPassword);
        accountInstance.put("workPackageCN",workPackageCN);
        accountInstance.put("uidNumber",uidNumber)
        return accountInstance
    }
}

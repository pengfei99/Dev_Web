package org.bioaster.security

import grails.transaction.Transactional
import org.bioaster.security.adManager.ADAuthenticator
import org.bioaster.security.config.ADConfig
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.core.LdapUserSearchController
import org.bioaster.security.ldapManager.ImportADAccountToLdap
import org.bioaster.security.ldapManager.LdapAuthenticator
import org.bioaster.security.ldapManager.LdapUserAccountManager

import javax.naming.NameClassPair
import javax.naming.NamingEnumeration
import javax.naming.NamingException
import javax.naming.directory.Attributes

@Transactional
class MemberManagerService {

    def List<String> getALLAccountNameList(ILdapServerConnectorConfig targetLdap){
        def accountManager = new LdapUserAccountManager(targetLdap);
        List<String> userNameList=accountManager.getALLUserNameList();
        accountManager.closeConnection();
        return userNameList;
    }
/*This method takes a username(cn) and return a map of attribute
*
* key-> value type:  cn->String,mail->String,memberOf->List<String>,gidNumber->String
*                    uidNumber->String, homeDirectory->String, uid->Sting, bioasterGroup->List<String>
*                     projectGroup->List<String>,bumpRoles->List<String>
*
* @params: ILdapServerConnectorConfig ldapConfig
* @params: String userName
* @return: Map<String,Object> userAttrs
*
* */
    def Map<String,Object> getUserDetails(ILdapServerConnectorConfig ldapConfig,String userName)throws NamingException{
        LdapUserSearchController lsc=new LdapUserSearchController(ldapConfig);
        def Map<String,Object> userAttrs=new HashMap<String,Object>();
        //get user attrs from ou=Users,
        String[] returnAttrs=new String[7];
        returnAttrs[0]="mail";
        returnAttrs[1]="cn";
        returnAttrs[2]="memberOf";
        returnAttrs[3]="gidNumber";
        returnAttrs[4]="uidNumber";
        returnAttrs[5]="homeDirectory";
        returnAttrs[6]="uid"

        Attributes attr = lsc.getUserAttrs(userName, returnAttrs);

        for(int i=0;i<returnAttrs.size();i++){
            if(attr.get(returnAttrs[i])==null){
                userAttrs.put(returnAttrs[i],"No_Value")
            }
            else {userAttrs.put(returnAttrs[i],attr.get(returnAttrs[i]).get(0).toString())}

        }

        //get user group from ou=Groups
       String uid=userAttrs.get("uid");
      List<String> bioasterGroup=lsc.getUserPosixBioasterGroup(uid);
        userAttrs.put("bioasterGroup",bioasterGroup);
        //get user group from ou=Projects
       List<String> projectGroup=lsc.getUserPosixProjectGroup(uid);
        userAttrs.put("projectGroup",projectGroup);
        userAttrs.put("bumpRoles",lsc.buildRoles(attr.get("memberOf")));
        lsc.closeConnection();
        return userAttrs;
    }

    def Map<String,String> getALLUserNameAndUidList(ILdapServerConnectorConfig ldapConfig){
        Map<String,String> userNameUidMap=new HashMap<String,String>();
        LdapUserSearchController lsc=new LdapUserSearchController(ldapConfig);
        List<String> userNameList=getALLAccountNameList(ldapConfig);
        for(String userName: userNameList){
            userNameUidMap.put(userName,lsc.getUserID(userName));
        }
        lsc.closeConnection();
        return userNameUidMap;

    }

    def boolean modifyUserGidNum(ILdapServerConnectorConfig ldapConf,String userFullName,String gidNum){
        LdapUserAccountManager luam=new LdapUserAccountManager(ldapConf);
        luam.modifyUserGidNum(userFullName,gidNum);
    }
/*
* The result of modifyUserPassword can be 4 case
* 0. change failed
* 1. change success
* 2. oldPassword is wrong or uid not existed (To avoid ddos, we don't say it's uid or password)
*
* */
    def int modifyUserPasswordWithFullName(ILdapServerConnectorConfig ldapConf,String fullName,String newPassword){
        int result=0;
        LdapUserAccountManager ldapUser= new LdapUserAccountManager(ldapConf);
        if(ldapUser.modifyUserPwd(fullName,newPassword)) result=1;
        ldapUser.closeConnection();
        println(result)
        return result;
    }

    def int modifyUserPasswordWithUID(ILdapServerConnectorConfig ldapConf,String userID,String newPassword,String oldPassword){
        int result=2;
        try {
            LdapAuthenticator lauth=new LdapAuthenticator(ldapConf);
            String dn=lauth.checkUserPassword(userID,oldPassword);
            lauth.closeConnection();
            if(dn!=null){
                String fullName=dn.substring(3,dn.indexOf(","));
                result=modifyUserPasswordWithFullName(ldapConf,fullName,newPassword)
            }

        } catch (IOException e) {
            log.error("Can't change user password! "+e.toString());
        }

       return result;
    }

    def int modifyUserPasswordWithUIDWithoutPwd(ILdapServerConnectorConfig ldapConf,String userID,String newPassword){
        int result=2;
        LdapUserSearchController lsc=new LdapUserSearchController(ldapConf);
        try {
            String fullName=lsc.getUserFullNameWithUid(userID);
            lsc.closeConnection();
            result=modifyUserPasswordWithFullName(ldapConf,fullName,newPassword)
        } catch (NamingException e) {
            log.error("Can't change user password! "+e.toString())
        }
        return result;
    }

    def boolean checkUserUidWithMail(ILdapServerConnectorConfig ldapConf,String userID,String userMail){
        boolean result=false;
        LdapUserSearchController lsc=new LdapUserSearchController(ldapConf);
        try {
            String mail=lsc.getUserMailWithUid(userID).toLowerCase();
            lsc.closeConnection();
            if(mail==userMail.toLowerCase()){
                result=true;
            }

        } catch (NamingException e) {
            log.error("Can't change user password! "+e.toString())
        }
        return result;
    }

    def int syncUserPwdWithAD(ILdapServerConnectorConfig ldapConf,String userID,String password){
        ILdapServerConnectorConfig adConf=new ADConfig();
        int result=2;
        ADAuthenticator adAuth=new ADAuthenticator(adConf);
        String dn=adAuth.checkUserPasswordWithAD(userID, password);
        if(dn!=null){
            result=modifyUserPasswordWithUIDWithoutPwd(ldapConf,userID,password)
        }
        return result;
    }

    def boolean checkUidExistence(ILdapServerConnectorConfig ldapConf,String uid){
        LdapUserSearchController lusc=new LdapUserSearchController(ldapConf);
        boolean result=lusc.checkUidExistence(uid);
        lusc.closeConnection();
        return result;
    }

    def boolean importADAccountToLdap(ILdapServerConnectorConfig adConf,ILdapServerConnectorConfig ldapConf,String uid,String password){
        boolean result=false;
        try {
            ImportADAccountToLdap importAD=new ImportADAccountToLdap(adConf, ldapConf);
            result = importAD.importUser(uid, password);
        } catch (IOException e) {
            log.error(e.toString())
        } catch (NamingException e) {
            log.error(e.toString())
        }
        return result;
    }
    /*



    def List<String> getUserMailList(String projectName){
        def groupManager=new UserProjectGroupManager(pubLdap);
        def accountManager = new UserAccountsManager(pubLdap);
        def List<String> mailList=new ArrayList<String>();

        List<String> membersDNList=groupManager.getAllProjectMembersDN(projectName);
        for (String memberDN:membersDNList){
            mailList.add(accountManager.getUserMailByDn(memberDN))
        }
        String mailListStr=mailList.toListString()
        groupManager.closeContext();
        accountManager.closeContext();
        return mailList;
    }

    def List<ExportUser> getUserMailListWithUserName(String projectName){
        def groupManager=new UserProjectGroupManager(pubLdap);
        def accountManager = new UserAccountsManager(pubLdap);
        def List<ExportUser> userList=new ArrayList<ExportUser>();

        List<String> membersDNList=groupManager.getAllProjectMembersDN(projectName);
        for (String memberDN:membersDNList){
            String name=memberDN.substring(3,memberDN.indexOf(","));
            String mail=accountManager.getUserMailByDn(memberDN);
            String uid=accountManager.getUid(memberDN);
            userList.add(new ExportUser(name,mail,uid))
        }

        groupManager.closeContext();
        accountManager.closeContext();

        return userList;
    }


    def List<ExportUser> getAllUsersFromLdap(){
        def accountManager = new UserAccountsManager(pubLdap);
        def groupManager=new UserProjectGroupManager(pubLdap);
        def List<ExportUser> userList=new ArrayList<ExportUser>();

        NamingEnumeration<NameClassPair> users = accountManager.getAllUsers();
        try {
            while (users.hasMore()){
                NameClassPair user = users.next();
                String userCn=user.getName();
                String memberDN= user.getNameInNamespace();
                String userName=userCn.substring(3);
                String mail=accountManager.getUserMailByDn(memberDN);
                String uid=accountManager.getUid(memberDN);
                userList.add(new ExportUser(userName,mail,uid));
            }
        } catch (NamingException e) {
            log.error("Unable to get user account information!!! Java exception: "+e.toString());
        }


        groupManager.closeContext();
        accountManager.closeContext();

        return userList;
    }*/

}

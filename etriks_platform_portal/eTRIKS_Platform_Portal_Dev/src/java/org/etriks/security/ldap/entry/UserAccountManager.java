package org.etriks.security.ldap.entry;

/**
 * Created by pliu on 3/28/14.
 */

import org.apache.log4j.Logger;
import org.etriks.security.encryption.PasswordEncrytor;
import org.etriks.security.ldap.authentication.LdapAuthenticator;
import org.etriks.security.ldap.entry.exception.ChangeMailStatus;
import org.etriks.security.ldap.entry.exception.ChangePasswordStatus;
import org.etriks.security.ldap.serverprofile.LdapServerConnectorConfig;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserAccountManager extends LdapEntryManager {
    //create an logger instance
    private final Logger log=Logger.getLogger(getClass());

    private String entryParentDir;
    private List<String> userEntryObjClasses=new ArrayList<String>();
    private LdapServerConnectorConfig ldapServerInfo;

    /*
     * In the constructor, we set the parent directory path of the user account entry
     * then we load the object class of the user account entry
     */
    public UserAccountManager(LdapServerConnectorConfig ldapServerInfo,String searchBase,String entryParentDir){
        super(ldapServerInfo,searchBase);
        this.ldapServerInfo=ldapServerInfo;
        this.entryParentDir=entryParentDir;
        this.loadUserEntryObjClasses();
    }
    /*
     * Take a Map of user account attributes and then call the method saveEntry which implemented in
     * the abstract parent class LdapEntryManager
     */
    public boolean creatUserAccount(Map<String,String> UserAccountAttributes){
        log.info("Create user account in Ldap server"+UserAccountAttributes.toString());
        return this.saveEntry(this.userEntryObjClasses, UserAccountAttributes, entryParentDir);
    }
	/*
	 * This method take the DN of the userAccount and delete the user account entry from the
	 * LDAP server
	 */

    public boolean deleteUserAccount(String userAccountCN){
        return this.deleteEntry(userAccountCN+","+entryParentDir);
    }
    /*
     * Load the user entry object classes.
     * The object classes needs to be customized for every type of Ldap entry such as user, role
     *
     */
    private void loadUserEntryObjClasses(){

        userEntryObjClasses.add("inetOrgPerson");
        //userEntryObjClasses.add("organizationalPerson");
        //userEntryObjClasses.add("person");
        userEntryObjClasses.add("posixAccount");
        userEntryObjClasses.add("top");
    }
    /*
     * This method take the uid of the user, if the uid of the user exist, it return the dn of the user
     * else return null
     */
    public String getUserDn(String uid){

        return this.entryExist("uid", uid);
    }
    public boolean uidNumberExisted(String uidNumber){
        if(this.entryExist("uidNumber",uidNumber)!=null)

        return true;
        else return false;
    }
    public String getUserDnByuidNumber(String uid){
        return this.entryExist("uidNumber",uid);

    }
    /*
     * This method take test first if the user(login use uid) knows the old password
     * if yes, then we change the old password,return a success message
     *
     *  else, we return a fail message which indicates why it failed.
     */
   /* public boolean changeUserPassword(String uid,String oldPassword,String newPassword){

        String userDn=getUserDn(uid);
        if(userDn!=null){
            boolean modifyPwd=false;
            LdapAuthenticator ldapAuth=new LdapAuthenticator(ldapServerInfo);
            boolean answer = ldapAuth.checkAuthWithLdap(uid, oldPassword);
            if(answer){
                PasswordEncrytor pwdEncrypter=new PasswordEncrytor();
                String encpwd="NoPassword";
                try {
                    encpwd = pwdEncrypter.digestBase64("sha", newPassword);
                } catch (NoSuchAlgorithmException e) {
                    log.error(uid+" change password failed! "+e.toString());
                }
                modifyPwd= this.modifyEntryAttributes(userDn, "userPassword", encpwd);

            }

            return modifyPwd;
        }
        else {
            log.error(uid+" change password failed! No such user, check if the login is correct!!!");
            return false;}

    }*/

    /*
        * This method take test first if the user(login use uid) knows the old password
        * if yes, then we change the old password,return a success message
        *
        *  else, we return a fail message which indicates why it failed.
        */
    public ChangePasswordStatus changeUserPassword(String uid,String oldPassword,String newPassword){
        //create the instance of the password encrypter.
        PasswordEncrytor pwdEncrypter=new PasswordEncrytor();
        String encPassword=null;
        try {
            encPassword=pwdEncrypter.digestBase64("sha", newPassword);
        } catch (NoSuchAlgorithmException e) {
            log.error("Change password failed! unable to encrypt the new password!!! Java exception: "+e.toString());
        }
        ChangePasswordStatus result;
        String userDn=getUserDn(uid);
        if(userDn!=null){

            LdapAuthenticator ldapAuth=new LdapAuthenticator(ldapServerInfo);
            boolean authentified = ldapAuth.checkAuthWithLdap(uid, oldPassword);

            if(authentified){
                boolean passwordModified = this.modifyEntryAttributes(userDn, "userPassword", encPassword);
                if (passwordModified) result= ChangePasswordStatus.Success;
                else result= ChangePasswordStatus.Fail;
            }
            else { result= ChangePasswordStatus.WrongPassword;}

        }
        else  {result= ChangePasswordStatus.UidNotFind;}
        //message="No such user, check if your login is correct!!!";
        return result;
    }
    /*This method will reset user password by a auto generated random password*/
    public boolean resetUserPassword(String userDn,String password){
        PasswordEncrytor pwdEncrypter=new PasswordEncrytor();
        String encPassword=null;
        try {
            encPassword=pwdEncrypter.digestBase64("sha", password);
        } catch (NoSuchAlgorithmException e) {
            log.error("Change password failed! unable to encrypt the new password!!! Java exception: "+e.toString());
        }
        if(encPassword==null){
            return false;
        }
        else{
        boolean passwordModified = this.modifyEntryAttributes(userDn, "userPassword", encPassword);
        return passwordModified;}
    }
    public boolean changeGIDNumber(String uid,String GIDNumber){
       boolean result=false;
        String userDn=getUserDn(uid);
        if (userDn!=null){
            result=modifyEntryAttributes(userDn,"gidNumber",GIDNumber);
        }
        return result;
    }

    public ChangeMailStatus changeUserMail(String uid,String password,String mail){
        ChangeMailStatus result;

        String userDn=getUserDn(uid);
        if(userDn!=null){
            LdapAuthenticator ldapAuth=new LdapAuthenticator(ldapServerInfo);
            boolean authentified = ldapAuth.checkAuthWithLdap(uid, password);

            if(authentified){
                boolean mailModified = this.modifyEntryAttributes(userDn, "mail", mail);
                if (mailModified) result= ChangeMailStatus.Success;
                else result= ChangeMailStatus.Fail;
            }
            else { result= ChangeMailStatus.WrongPassword;}

        }
        else  result= ChangeMailStatus.UidNotFind;


        return result;
    }

    public String getUid(String dn){

        Map<String, List<String>> attrs = this.getAllAttributesByEntryDN(dn);
        String uid = attrs.get("uid").get(0);
        return uid;

    }

public Map<String,String> getAllUserNameAndUid(){
    Map<String,String> allUsers=new HashMap<String, String>();
    NamingEnumeration<NameClassPair> users = getAllUsers();
    try {
        while (users.hasMore()){
            NameClassPair user = users.next();
            String userCn=user.getName();
           String userDN= user.getNameInNamespace();
            String userName=userCn.substring(3);
            String userUid=getUid(userDN);
          /*  System.out.println("user name :"+userName+" uid :"+userUid);*/

            allUsers.put(userName,userUid);

        }
    } catch (NamingException e) {
        log.error("Cannot get all users from the Ldap server :"+e.toString());
    }
   // System.out.println(allUsers.toString());
    //allUsers.put("pengfei","ptest");
return allUsers;

}




    public NamingEnumeration<NameClassPair> getAllUsers(){
        return this.getAllEntries(entryParentDir);
    }

    public int getUserSize(){
        return this.getEntriesSize(entryParentDir);
    }

    public String getUserMailByDn(String userDN){
        Map<String, List<String>> allAttributes= this.getAllAttributesByEntryDN(userDN);

        String mail=allAttributes.get("mail").get(0);

        return mail;


    }

    public boolean deletUserAccount(String userID){

        /*first step check if the ID exist, if exist delete the account*/

        /*Second serach all the group or role to delete all dependence*/

        return false;
    }

    public boolean userEmailsExisted(String userEmailsTobeTested) throws NamingException {
        String usersDN="ou=Users,dc=etriks,dc=eu";
        boolean result=false;
        NamingEnumeration<NameClassPair> allUsers = this.getAllEntries(usersDN);
        while(allUsers.hasMore()){
            String userDN=allUsers.next().getName()+","+usersDN;
            String emails=this.getUserMailByDn(userDN);
            if(userEmailsTobeTested.equals(emails)){
                result=true;
                break;
            }
        }
        return result;
    }

}
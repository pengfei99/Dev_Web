package test;

import org.etriks.security.ldapnew.entry.UserAccountsManager;
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig;

import java.io.IOException;
import java.util.Map;

/**
 * Created by pliu on 6/7/16.
 */
public class ChangeUserHomeDir {

    PubLdapConnectorConfig prodLdap=new PubLdapConnectorConfig();
    UserAccountsManager uam;

    public ChangeUserHomeDir(){
        try {
            uam=new UserAccountsManager(prodLdap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean changeHomeDir(String userDn,String uid,String homeDir){
        boolean flag=false;
            flag=uam.modifyEntryAttributes(userDn,"homeDirectory",homeDir+uid);
        return flag;
    }

    public void changeAllUsersHomeDir(){
        String defaultUserDirectory="/mnt/homedirs/users/";
        Map<String, String> allUsers = uam.getAllUserNameAndUid();

        for(Map.Entry<String,String> entry:allUsers.entrySet()){
            String userDn="cn="+entry.getKey()+",ou=Users,dc=etriks,dc=eu";
            String uid=entry.getValue();
            System.out.println("Dn :"+ userDn+ " |||||| uid: "+uid);
            this.changeHomeDir(userDn,uid,defaultUserDirectory);
        }


    }
}

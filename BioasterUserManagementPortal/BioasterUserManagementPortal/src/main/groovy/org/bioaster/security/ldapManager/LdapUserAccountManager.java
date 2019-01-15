package org.bioaster.security.ldapManager;

/**
 * Created by pliu on 2/14/17.
 */



import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.LdapArchitectureInfo;
import org.bioaster.security.core.EntryManager;
import org.bioaster.security.core.LdapUserSearchController;
import org.bioaster.security.encryption.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class LdapUserAccountManager extends EntryManager {

    static Logger log = LoggerFactory.getLogger(LdapUserAccountManager.class);
    private List<String> objectClassList;
    private final String userParentDir=LdapArchitectureInfo.USER_DIR;
    private ILdapServerConnectorConfig ldapConf;
    private int uidBase=100000;


    /*
     *
     * Class Constructor
     * It reads a ldap connection information file
     * It creates the connection and set the connection context for
     * further actions
     *
     * @param String
     * @return void
     * */
    public LdapUserAccountManager(ILdapServerConnectorConfig ldapConf) throws IOException{
        super(ldapConf);
        this.ldapConf=ldapConf;
        this.setDefaultObjectClass();

    }

    public String getAvailableUidNumFromHead() throws NamingException, IOException{
        String uidNum="999999";
        LdapUserSearchController lsc=new LdapUserSearchController(ldapConf);
        for(int i=uidBase;i<999999;){
            if (!lsc.checkUidNumExistence(Integer.toString(i)));
            uidNum=Integer.toString(i);
            break;
        }
        lsc.closeConnection();
        return uidNum;
    }
    public String getAvailableUidNumFromTail() throws IOException, NamingException{
        String uidNum="999999";
        LdapUserSearchController lsc=new LdapUserSearchController(ldapConf);
        int size=lsc.getChildrenEntrySize(LdapArchitectureInfo.USER_DIR, null, false);
        for(int i=uidBase+size;i<999999;){
            if (!lsc.checkUidNumExistence(Integer.toString(i)));
            uidNum=Integer.toString(i);
            break;
        }
        lsc.closeConnection();
        return uidNum;

    }

    public String buildUserID(String firstName,String lastName) throws NamingException {
        String uid="jsnow";
        LdapUserSearchController lusc=new LdapUserSearchController(ldapConf);
        for(int i=1;i<firstName.length()+1;i++){
            uid=firstName.substring(0,i)+lastName;
            try {
                if(!lusc.checkUidExistence(uid)) break;
            } catch (NamingException e) {
                log.error("Can't check uid existence during Ldap account creation"+e.toString());
            }
        }
        try {
            lusc.closeConnection();
        } catch (NamingException e) {
            log.error("Can't close ldapUserSearchController after uid existence check during Ldap account creation"+e.toString());
        }
        return uid.toLowerCase();
    }

    public boolean modifyUserGidNum(String userFullName,String gidNum){
        return this.modifyEntryAttribute(buildUserDn(userFullName),"gidNumber",gidNum);
    }

    public boolean addUserAccount(Map<String,Object> entryAttrsMap){
        return this.addEntry(objectClassList, entryAttrsMap, this.userParentDir);
    }

    public boolean deleteUserAccount(String userFullName){
        return this.deleteEntry(buildUserDn(userFullName));
    }

    public boolean modifyUserPwd(String userFullName,String password){
        PasswordEncoder pwdEncoder=new PasswordEncoder();
        try {
            String encPassword=pwdEncoder.encPassword(password);
            return this.modifyEntryAttribute(buildUserDn(userFullName), "userPassword", encPassword);
        } catch (IOException e) {
            log.error("Can't modify user password"+e.toString());
            return false;
        }
    }

    public boolean modifyUserEmail(String userFullName,String mail){
        return this.modifyEntryAttribute(buildUserDn(userFullName), "mail", mail);
    }

    private void setDefaultObjectClass(){
        this.objectClassList = new ArrayList<String>();
        objectClassList.add("top");
        objectClassList.add("posixAccount");
        objectClassList.add("person");
        objectClassList.add("organizationalPerson");
        objectClassList.add("inetOrgPerson");
    }

    public NamingEnumeration<NameClassPair> getAllUsers(){
       return this.getAllEntries(userParentDir);
    }

    public List<String> getALLUserNameList(){
        return getSubEntryNameList(LdapArchitectureInfo.USER_DIR);
    }

    private String buildUserDn(String userFullName){
        return "cn="+userFullName+","+userParentDir;
    }


}


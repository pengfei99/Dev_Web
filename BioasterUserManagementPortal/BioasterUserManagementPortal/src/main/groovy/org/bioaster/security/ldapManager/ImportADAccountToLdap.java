package org.bioaster.security.ldapManager;

/**
 * Created by pliu on 2/14/17.
 */

import org.bioaster.security.adManager.ADAuthenticator;
import org.bioaster.security.adManager.ADUserAccountManager;
import org.bioaster.security.config.BUMPConfig;
import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.encryption.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportADAccountToLdap {

    /* Get actual class name to be printed on */
    static Logger log = LoggerFactory.getLogger(ImportADAccountToLdap.class);
    //Step1. check user id and password via AD
    //Step2. if pwd ok, return required attributes
    //Step3. build all required Ldap attributes
    //Step4. Create new entry with prepared attributes

    private ADAuthenticator adAuth;
    private ADUserAccountManager adUserManager;
    private ILdapServerConnectorConfig ldapConf;



    public ImportADAccountToLdap(ILdapServerConnectorConfig adConf, ILdapServerConnectorConfig ldapConf) throws IOException{
        adAuth=new ADAuthenticator(adConf);
        adUserManager=new ADUserAccountManager(adConf);
        this.ldapConf=ldapConf;
    }

    public boolean importUser(String uid, String pwd ) throws NamingException, IOException{
        String dn=adAuth.checkUserPasswordWithAD(uid, pwd);

        Map<String,Object> entryAttrsMap;

        try {
            adAuth.closeConnection();
        } catch (NamingException e) {
            throw new NamingException("AdAuthenticator unable to close connection. Java exeception message: "+e.toString());
        }
        if(dn==null) {
            log.warn("user login or password is wrong");
            return false;
        }
        else{
            HashMap<String, String> adAttrs = adUserManager.getUserAttrs(uid);
            String cn=adAttrs.get("cn");
            String mail=adAttrs.get("mail");
            String location=adAttrs.get("physicalDeliveryOfficeName");
            LdapUserAccountManager lam = new LdapUserAccountManager(ldapConf);
            entryAttrsMap=this.buildAdRelatedAttributes(lam,uid,pwd,cn, mail, location);
            lam.addUserAccount(entryAttrsMap);
            lam.closeConnection();
            return true;
        }
    }

    private Map<String,Object> buildAdRelatedAttributes(LdapUserAccountManager lam,String uid,String password,String cn,String mail,String location) throws IOException, NamingException {


        String sn=cn.substring(0, cn.indexOf(" "));
        String givenName=cn.substring(cn.indexOf(" ")+1);
        String orgName=this.getOrganizationName(location);

        LdapOrganizationManager ldapOrgManager=new LdapOrganizationManager(ldapConf);

        String uidNumber = lam.getAvailableUidNumFromTail();

        String orgID=ldapOrgManager.getOrganizationGIDNum(orgName);
        PasswordEncoder pwdEncoder=new PasswordEncoder();
        String encPassword=pwdEncoder.encPassword(password);
        Map<String,Object> entryAttrsMap=new HashMap<String,Object>();
        entryAttrsMap.put("cn", cn);
        entryAttrsMap.put("gidNumber", orgID);
        entryAttrsMap.put("homeDirectory", BUMPConfig.homeDir+uid);
        entryAttrsMap.put("sn", sn);
        entryAttrsMap.put("uid", uid);
        entryAttrsMap.put("uidNumber", uidNumber);
        entryAttrsMap.put("givenName", givenName);
        entryAttrsMap.put("loginShell", BUMPConfig.loginShell);
        entryAttrsMap.put("mail", mail);
        entryAttrsMap.put("userPassword", encPassword);

        return entryAttrsMap;
    }

    public String getOrganizationName(String location){
        List<String> organizationList=new ArrayList<String>();
        organizationList.add("BIOASTER Lyon");
        organizationList.add("BIOASTER IP");

        int i=location.indexOf("-");
       String orgName=location.substring(0, i-1);
       if (organizationList.contains(orgName)){
           return orgName.replace(" ","_");
       }
       else return "BIOASTER_Lyon";
    }

}

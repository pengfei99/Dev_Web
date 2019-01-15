package org.bioaster.security.auth;

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.OpenLdapConfig;
import org.bioaster.security.core.LdapUserSearchController;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pliu on 3/3/17.
 */
public class UserDetails {
    ILdapServerConnectorConfig ldapConfig= OpenLdapConfig.getInstance();
    LdapUserSearchController lsc;
    private final String username;
    private final String fullName;
    private final String mail;
    private final List<String> roles;
    private final String lastLogInTime;


    public UserDetails(String userID) throws NamingException {
        this.lsc=new LdapUserSearchController(ldapConfig);
        Attributes attr = lsc.getUserAttrWithUid(userID, new String[]{"mail", "cn", "memberOf"});
        lsc.closeConnection();
        this.fullName=attr.get("cn").get(0).toString();
        this.username=userID;
        this.mail=attr.get("mail").get(0).toString();
        this.roles=lsc.buildRoles(attr.get("memberOf"));
        lastLogInTime=new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm").format(Calendar.getInstance().getTime());
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getLastLogInTime() {
        return lastLogInTime;
    }

    public String getFullName(){
        return fullName;
    }

    /*private List<String> buildRoles(Attribute rawRoles) throws NamingException {
        String nativeRole="BUMP_user";
        List<String> roles=new ArrayList<String>();
        if(rawRoles==null){roles.add(nativeRole);}
        else{
            for(int i=0;i<rawRoles.size();i++){
            String roleCN=rawRoles.get(i).toString();
            String role=roleCN.substring(3,roleCN.indexOf(","));
            roles.add(role);
        }
        }

        return roles;


    }*/





}

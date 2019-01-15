package org.etriks.security.ldap.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by pliu on 4/1/14.
 */
public class UserDetails {

    private final String username;
    private final Collection<GrantedAuthority> roles;
    private final String lastLogInTime;
    public String authLdapServerId;

    public UserDetails(String username,Collection<GrantedAuthority> roles){
        this.username=username;
        this.roles=roles;
        lastLogInTime=new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm").format(Calendar.getInstance().getTime());
    }


    public String getUserName() {
        return username;
    }


    public Collection<GrantedAuthority> getRole() {
        return roles;
    }

    public List<String> getStrRole(){
        List<String> result=new ArrayList<String>();
        int size=roles.size();
        if(size>0){
            for(GrantedAuthority role:roles){
                String r=role.getAuthority();
                result.add(r);
            }
        }
        return result;
    }

    public String getLastLogInTime(){
        return lastLogInTime;
    }

    public String getAuthLdapServerId() {
        return authLdapServerId;
    }

    public void setAuthLdapServerId(String authLdapServerId) {
        this.authLdapServerId = authLdapServerId;
    }
}

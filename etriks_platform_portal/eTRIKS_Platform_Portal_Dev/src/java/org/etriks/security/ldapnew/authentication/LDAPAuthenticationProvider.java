package org.etriks.security.ldapnew.authentication;

import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pliu on 1/5/16.
 */
public class LDAPAuthenticationProvider implements AuthenticationProvider{

    //Create the ldap connection configure file instance

    private LdapConnectorConfig prodLdap;

    public void setProdLdap(LdapConnectorConfig prodLdap) {
        this.prodLdap = prodLdap;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String uid=(String) authentication.getPrincipal();
        String password=(String) authentication.getCredentials();

        LdapAuthenticator ldapAuthenticator=new LdapAuthenticator(prodLdap);

        boolean decision=ldapAuthenticator.checkAuthWithLdap(uid, password);
        if (decision==true)

        {
            List<String> roles = ldapAuthenticator.getCurrentUserRoles();
            List grantedRoles=new ArrayList();

            int size=roles.size();
            for(int i=0;i<size;i++){
                String role = roles.get(i);
                //  System.out.println("In LdapAuthenticationProvider return role: "+role);
                //
                //grantedRoles.add(new GrantedAuthorityImpl(role));
                grantedRoles.add(new SimpleGrantedAuthority(role));
            }
            //close the primary ldap connection after the authentication success
            ldapAuthenticator.closeContext();
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),grantedRoles);
        }
        else {
            ldapAuthenticator.closeContext();
            throw new BadCredentialsException("Username/Password does not match for " + authentication.getPrincipal());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

package org.etriks.security.ldap.authentication;

import org.etriks.security.ldap.serverprofile.LdapServerConnectorConfig;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pliu on 4/1/14.
 */
public class LDAPAuthenticationProvider implements AuthenticationProvider{



    private LdapServerConnectorConfig PrimaryLdapServer;

    private LdapServerConnectorConfig SecondaryLdapServer;

    private LdapServerConnectorConfig ThirdLdapServer;

    private LdapServerConnectorConfig FourthLdapServer;

    /* The authenticate method must return an AuthenticationToken
 * (non-Javadoc)
 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
 */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        /*check user credential via the primary ldap server*/
        String uid=(String) auth.getPrincipal();
        String password=(String) auth.getCredentials();
        LdapAuthenticator priLdap=new LdapAuthenticator(PrimaryLdapServer);

        boolean decision=priLdap.checkAuthWithLdap(uid, password);

        //primary ldap authentication succesful
        if (decision==true)

        {
            List<String> roles = priLdap.getCurrentUserRoles();
            List<SimpleGrantedAuthority> grantedRoles=this.getGrantedAuthorityFromRoles(roles);

            //close the primary ldap connection after the authentication success
            priLdap.closeContext();
            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(),grantedRoles);
        }
        else  {
            //close the primary ldap connection after the authentication failed
            priLdap.closeContext();


            /*check user credential via the secondary ldap server*/
            LdapAuthenticator secLdap=new LdapAuthenticator(SecondaryLdapServer);
            boolean secDecision=secLdap.checkAuthWithLdap(uid, password);

            //secondary ldap authentication succesful
            if (secDecision==true){

                List<String> roles = secLdap.getCurrentUserRoles();
                List<SimpleGrantedAuthority> grantedRoles=this.getGrantedAuthorityFromRoles(roles);

                //close the second ldap connection after the authentication success
                secLdap.closeContext();
                return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(),grantedRoles);
            }
            else{
            //close the second ldap connection after the authentication failed
                secLdap.closeContext();

             /*check user credential via the third ldap server*/
                LdapAuthenticator thirdLdap=new LdapAuthenticator(ThirdLdapServer);
                boolean thirdDecision=thirdLdap.checkAuthWithLdap(uid, password);

                //third ldap authentication succesful
                if (thirdDecision==true){

                    List<String> roles = thirdLdap.getCurrentUserRoles();
                    List<SimpleGrantedAuthority> grantedRoles=this.getGrantedAuthorityFromRoles(roles);


                    //close the third ldap connection after the authentication success
                    thirdLdap.closeContext();
                    return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(),grantedRoles);
                }
                else{
//close the third ldap connection after the authentication failed
                    thirdLdap.closeContext();

                    /*check user credential via the fourth ldap server*/
                    LdapAuthenticator fourthLdap=new LdapAuthenticator(FourthLdapServer);
                    boolean fourthDecision=fourthLdap.checkAuthWithLdap(uid, password);

                    //third ldap authentication succesful
                    if (fourthDecision==true){

                        List<String> roles = fourthLdap.getCurrentUserRoles();
                        List<SimpleGrantedAuthority> grantedRoles=this.getGrantedAuthorityFromRoles(roles);


                        //close the fourth ldap connection after the authentication success
                        fourthLdap.closeContext();
                        return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(),grantedRoles);
                    }
                    else{
                        //close the fourth ldap connection after the authentication failed
                        fourthLdap.closeContext();
                    throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());}

            }
            }

        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    /*Create the ldap server connector based on the bean created in the resource.groovy*/

    public void setPrimaryLdapServer(LdapServerConnectorConfig primaryLdapServer) {
        PrimaryLdapServer = primaryLdapServer;
    }

    public void setSecondaryLdapServer(LdapServerConnectorConfig secondaryLdapServer) {
        SecondaryLdapServer = secondaryLdapServer;
    }

    public void setThirdLdapServer(LdapServerConnectorConfig thirdLdapServer) {
        ThirdLdapServer = thirdLdapServer;
    }

    public void setFourthLdapServer(LdapServerConnectorConfig fourthLdapServer) {FourthLdapServer=fourthLdapServer;}

    private List<SimpleGrantedAuthority> getGrantedAuthorityFromRoles(List<String> roles){
        List<SimpleGrantedAuthority> grantedRoles=new ArrayList<SimpleGrantedAuthority>();
        for(String role:roles){
            grantedRoles.add(new SimpleGrantedAuthority(role));
        }
        return grantedRoles;
    }

}

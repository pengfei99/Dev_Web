package org.etriks.security.ldapnew;

/**
 * Created by pliu on 1/4/16.
 */

import org.apache.log4j.Logger;
import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;




/*
 * This is a tool class for connecting to ldap
 * @author pengfei liu
 */
public class LdapConnector {
    //Get the log instance
    private final org.apache.log4j.Logger log= Logger.getLogger(getClass());

    //store the connected information
    private Hashtable<String, String> env=null;

    //ldap context
    private LdapContext ctx=null;

    //ldap server connector information file which contains binddn, password, ldapURL, etc.
    private LdapConnectorConfig ldapConfig;



    //In the constructor, we create the instance of the ConnLDAP
    public LdapConnector(LdapConnectorConfig ldapServerInfo){
        //Get the config file which contains
        this.ldapConfig=ldapServerInfo;
        env=new Hashtable<String, String>();
        this.init();
    }


    /*
     * Set the ldap connection context
     * @throws javax.naming.NamingException if connection fails.
     */
    public void init() {
        // set the initial context information
        env.put(Context.INITIAL_CONTEXT_FACTORY,ldapConfig.getINITIAL_CONTEXT_FACTORY());

        // set the URL of the LDAP server
        env.put(Context.PROVIDER_URL, ldapConfig.getPROVIDER_URL());

        //set the authentication mode
        env.put(Context.SECURITY_AUTHENTICATION, ldapConfig.getSECURITY_AUTHENTICATION());

        //set the admin user of the ldap server

        env.put(Context.SECURITY_PRINCIPAL, ldapConfig.getSECURITY_PRINCIPAL());

        //set the password of the admin user
        env.put(Context.SECURITY_CREDENTIALS, ldapConfig.getSECURITY_CREDENTIALS());

        //initialize the ldap context
        try {
            ctx = new InitialLdapContext(env,null);
        } catch (NamingException e) {
            log.error("Connection to the LDAP server failed!!! Java Exception: "+e.toString());
            throw new Error("Connection to the LDAP server failed!!! Java Exception: "+e.toString());
        }

    }
    /*
     * Close the ldap connection
     * @throws javax.naming.NamingException if close the ldap connection failed.
     */
    public void closeContext(){
        try {
            ctx.close();
        } catch (NamingException e) {
            //logging the exeception in the server log
            log.error("In class ConnLDAP, unable to close the LDAP connection!!! Java exception: "+e.toString());
        }
    }

    public LdapContext getContext(){
        return this.ctx;
    }

}

/*
*
*The ldap connection needs a hashmap to store the connection configuration information.
*Once we finish the configuration, we create a Context. This context can help us to do manage the
*LDAP server (add,delete,search)
*PS: The context type can be the DirContext or LdapContext, where ldapContext inherit DirContext
*
*Common errors
*
*525 - cannot find the user certificate
*52e - certificate is not authorized
*530 - not permitted to logon at this time
*532 - password expired
*533 - account is not usable
*701 - account expired
*773 - user needs to reset password
*/
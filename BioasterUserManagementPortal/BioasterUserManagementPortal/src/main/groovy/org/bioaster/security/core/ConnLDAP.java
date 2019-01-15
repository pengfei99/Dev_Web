package org.bioaster.security.core;

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

/**
 * Created by pliu on 2/14/17.
 */
/*
 * This is a tool class for connecting to ldap
 * @author pengfei liu
 */
public class ConnLDAP {

    /* Get actual class name to be printed on */
    static Logger log = LoggerFactory.getLogger(ConnLDAP.class);

    //store the connected information
    private Hashtable<String,String> env=null;


    //ldap context
    private LdapContext ctx=null;

    //set some connected information
    //it's better if we put this information in a config file.
    private final String INITIAL_CONTEXT_FACTORY="com.sun.jndi.ldap.LdapCtxFactory";
    private String PROVIDER_URL;
    private String SECURITY_AUTHENTICATION;
    private String SECURITY_PRINCIPAL;
    private String SECURITY_CREDENTIALS;

    //In the constructor, we create the instance of the ConnLDAP
    public ConnLDAP(ILdapServerConnectorConfig ldapParams){

        env=new Hashtable<String,String>();
        this.PROVIDER_URL=ldapParams.getPROVIDER_URL();
        this.SECURITY_PRINCIPAL=ldapParams.getSECURITY_PRINCIPAL();
        this.SECURITY_CREDENTIALS=ldapParams.getSECURITY_CREDENTIALS();
        this.SECURITY_AUTHENTICATION=ldapParams.getSECURITY_AUTHENTICATION();
        this.init();
    }


    /*
     * Set the ldap connection context
     * @throws javax.naming.NamingException if connection fails.
     */
    public void init() {
        // set the initial context information
        env.put(Context.INITIAL_CONTEXT_FACTORY,INITIAL_CONTEXT_FACTORY);

        // set the URL of the LDAP server
        env.put(Context.PROVIDER_URL, PROVIDER_URL);

        //set the authentication mode
        env.put(Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION);

        //set the admin user of the ldap server

        env.put(Context.SECURITY_PRINCIPAL, SECURITY_PRINCIPAL);

        //set the password of the admin user
        env.put(Context.SECURITY_CREDENTIALS, SECURITY_CREDENTIALS);

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
    public void closeContext() throws NamingException{
        ctx.close();
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
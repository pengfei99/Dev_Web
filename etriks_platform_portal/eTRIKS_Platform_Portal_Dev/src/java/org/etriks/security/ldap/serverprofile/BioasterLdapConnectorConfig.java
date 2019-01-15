package org.etriks.security.ldap.serverprofile;

/**
 * Created by pliu on 3/25/15.
 */
public class BioasterLdapConnectorConfig implements LdapServerConnectorConfig {

    //set some connected information

    private	final static String INITIAL_CONTEXT_FACTORY="com.sun.jndi.ldap.LdapCtxFactory";
    private	final static String PROVIDER_URL="ldap://auth.bioaster.etriks.org:389";

    private	final static String SECURITY_AUTHENTICATION="simple";
    private	final static String SECURITY_PRINCIPAL="cn=admin,dc=etriks,dc=eu";
    private	final static String SECURITY_CREDENTIALS="eTRIKSPass";

    //Specify the attributes to return after the authentication
    private final static String[] ReturnAttributs={"cn","uid","memberOf"};

    //Specify the base for the search
    private final static String SearchBase="dc=etriks,dc=eu";
    private final static String LdapServerName="Bioaster";


    public  String getSearchbase() {
        return SearchBase;
    }

    public String[] getReturnattributs() {
        return ReturnAttributs;
    }

    public String getINITIAL_CONTEXT_FACTORY() {
        return INITIAL_CONTEXT_FACTORY;
    }

    public String getPROVIDER_URL() {
        return PROVIDER_URL;
    }

    public String getSECURITY_AUTHENTICATION() {
        return SECURITY_AUTHENTICATION;
    }

    public String getSECURITY_PRINCIPAL() {
        return SECURITY_PRINCIPAL;
    }

    public String getSECURITY_CREDENTIALS() {
        return SECURITY_CREDENTIALS;
    }
    public String getLdapServerName(){return LdapServerName;}
}

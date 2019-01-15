package org.bioaster.security.config;

/**
 * Created by pliu on 2/14/17.
 */
public class OpenLdapConfig implements ILdapServerConnectorConfig {

    private static final OpenLdapConfig ldapInstance= new OpenLdapConfig();

    private OpenLdapConfig(){}

    public static OpenLdapConfig getInstance(){
        return ldapInstance;
    }

    //set some connected information

    private	final static String INITIAL_CONTEXT_FACTORY="com.sun.jndi.ldap.LdapCtxFactory";
    private	final static String PROVIDER_URL="ldap://auth-ldap.bioaster.org:389";

    private	final static String SECURITY_AUTHENTICATION="simple";
    private	final static String SECURITY_PRINCIPAL="cn=bump,ou=admin_accounts,dc=bioaster,dc=org";
    private	final static String SECURITY_CREDENTIALS="changeMe";

    //Specify the attributes to return after the authentication
    private final static String[] ReturnAttributs={"cn","uid","memberOf"};

    //Specify the base for the search
    private final static String SearchBase="ou=Users,dc=bioaster,dc=org";
    private final static String SearchFilterArg="uid";
    private final static String LdapServerName="OpenLdapP01";

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

    @Override
    public String getSEARCH_FILTER_ARG() {
        return SearchFilterArg;
    }
}

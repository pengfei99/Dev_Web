package org.etriks.security.ldap.serverprofile;

/**
 * Created by pliu on 3/27/14.
 */
public class EtriksMemberLdapConnectorConfig implements LdapServerConnectorConfig {


    //set some connected information

    private	final static String INITIAL_CONTEXT_FACTORY="com.sun.jndi.ldap.LdapCtxFactory";
    private	final static String PROVIDER_URL="ldap://auth.etriks.org:389";
//private	final static String PROVIDER_URL="ldap://134.158.242.9:389";

//address for the public server register
//private	final static String PROVIDER_URL="ldap://ldap.etriks.org:389";

    private	final static String SECURITY_AUTHENTICATION="simple";
    private	final static String SECURITY_PRINCIPAL="cn=admin,dc=etriks,dc=eu";
    private	final static String SECURITY_CREDENTIALS="Sihpn9B";
//password for the test ldap
//private	final static String SECURITY_CREDENTIALS="testLdap";
//password for the public server register
//private	final static String SECURITY_CREDENTIALS="Hk4pFraZGhJC";

//These two are specific for each subclass of the LadpEntryManager, so we cannot fix here.
//private final static String SearchEngine_SearchBase="";
//private final static String EntryManagement_ParentEntry="";

    //Specify the attributes to return after the authentication
    private final static String[] ReturnAttributs={"cn","uid","memberOf"};

    //Specify the base for the search
    private final static String SearchBase="dc=etriks,dc=eu";

    private final static String LdapServerName="Etriks";


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
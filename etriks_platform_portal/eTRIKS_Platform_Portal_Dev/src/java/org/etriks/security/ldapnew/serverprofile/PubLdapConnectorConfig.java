package org.etriks.security.ldapnew.serverprofile;

/**
 * Created by pliu on 4/5/16.
 */
public class PubLdapConnectorConfig implements LdapConnectorConfig{
    //set some connected information
    private	final static String INITIAL_CONTEXT_FACTORY="com.sun.jndi.ldap.LdapCtxFactory";

    //address for the public server register
   // private	final static String PROVIDER_URL="ldap://172.17.88.15:389";
    private	final static String PROVIDER_URL="ldap://127.0.0.1:389";
    //password for the test ldap
    //private	final static String SECURITY_CREDENTIALS="Ae537e$5fffB86@f";
    //private	final static String SECURITY_CREDENTIALS="boMh/Xl0YXFtTgt";
    private	final static String SECURITY_CREDENTIALS="Liua1983";
    private	final static String SECURITY_AUTHENTICATION="simple";

    private	final static String SECURITY_PRINCIPAL="cn=admin,dc=etriks,dc=eu";


//These two are specific for each subclass of the LadpEntryManager, so we cannot fix here.
//private final static String SearchEngine_SearchBase="";
//private final static String EntryManagement_ParentEntry="";

    //Specify the attributes to return after the authentication
    private final static String[] ReturnAttributs={"cn","uid","memberOf"};

    //Specify the base for the search
    private final static String SearchBase="dc=etriks,dc=eu";

    private final static String LdapServerName="prod";


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

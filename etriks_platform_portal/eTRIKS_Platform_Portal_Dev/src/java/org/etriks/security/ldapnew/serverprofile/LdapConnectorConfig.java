package org.etriks.security.ldapnew.serverprofile;

/**
 * Created by pliu on 4/5/16.
 */
public interface LdapConnectorConfig {
    public String getLdapServerName();


    public String getSearchbase() ;

    public String[] getReturnattributs() ;

    public String getINITIAL_CONTEXT_FACTORY();

    public String getPROVIDER_URL() ;

    public String getSECURITY_AUTHENTICATION() ;

    public String getSECURITY_PRINCIPAL() ;

    public String getSECURITY_CREDENTIALS() ;
}

package org.etriks.security.ldap.serverprofile;

/**
 * Created by pliu on 3/27/14.
 */
public interface LdapServerConnectorConfig {

   public String getLdapServerName();


    public String getSearchbase() ;

    public String[] getReturnattributs() ;

    public String getINITIAL_CONTEXT_FACTORY();

    public String getPROVIDER_URL() ;

    public String getSECURITY_AUTHENTICATION() ;

    public String getSECURITY_PRINCIPAL() ;

    public String getSECURITY_CREDENTIALS() ;
}

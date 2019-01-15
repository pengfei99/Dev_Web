package org.bioaster.security.config;

/**
 * Created by pliu on 2/14/17.
 */
public interface ILdapServerConnectorConfig {
    public String getLdapServerName();


    public String getSearchbase() ;

    public String[] getReturnattributs() ;

    public String getINITIAL_CONTEXT_FACTORY();

    public String getPROVIDER_URL() ;

    public String getSECURITY_AUTHENTICATION() ;

    public String getSECURITY_PRINCIPAL() ;

    public String getSECURITY_CREDENTIALS() ;

    public String getSEARCH_FILTER_ARG();

}

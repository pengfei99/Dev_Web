package org.etriks.security.ldapnew.resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by pliu on 1/14/16.
 */
public class LdapProperties {
    private final String ldapConfigFile="LdapConfig.properties";

    private InputStream inputStream;

    private String getPropertyValue(String propertyName) throws IOException {
        Properties properties=new Properties();
        inputStream=this.getClass().getClassLoader().getResourceAsStream(ldapConfigFile);
        if(inputStream!=null){
            properties.load(inputStream);
        }
        else {throw new FileNotFoundException("Ldap config file "+ldapConfigFile+" not Found");}
        String result= properties.getProperty(propertyName);
        this.inputStream.close();
        return result;
    }

    public String getOrganizationsDN() throws IOException {
        return this.getPropertyValue("organizationsDN");
    }

    public String getBaseDN() throws IOException {
        return this.getPropertyValue("baseDN");
    }

    public String getProviderGroupsDN() throws IOException {
        return this.getPropertyValue("providerGroupsDN");
    }

    public String getUsersDN() throws IOException {
        return this.getPropertyValue("usersDN");
    }

    public String getWorkPackagesDN() throws IOException {
        return this.getPropertyValue("workPackagesDN");
    }

    public String getProjectsDN() throws IOException {
        return this.getPropertyValue("projectsDN");
    }

    public int getOrganizationsGidNumBase() throws IOException {
        return Integer.parseInt(this.getPropertyValue("organizationDefaultGid"));
    }

    public int getProjectGroupGidNumBase(String projectName) throws IOException {
        return Integer.parseInt(this.getPropertyValue(projectName+"PosixGroupDefaultGidNum"));
    }
    public int getUidNumBase() throws IOException {
        return Integer.parseInt(this.getPropertyValue("defaultUidNum"));
    }
}

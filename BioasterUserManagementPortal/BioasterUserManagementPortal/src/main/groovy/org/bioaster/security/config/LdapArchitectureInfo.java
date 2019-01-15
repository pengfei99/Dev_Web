package org.bioaster.security.config;

/**
 * Created by pliu on 2/14/17.
 */
public class LdapArchitectureInfo {
    public static final String USER_DIR="ou=Users,dc=bioaster,dc=org";
    public static final String ORGANIZATION_DIR="ou=Organizations,dc=bioaster,dc=org";
    public static final int OrganizationsGidNumBase=5000;
    public static final String GROUP_DIR="ou=Groups,dc=bioaster,dc=org";
    //UTEC01 gid range 10000~10999
    public static final int UTEC01GidNumBase=10000;
    //UTEC02 gid range 11000~11999
    public static final int UTEC02GidNumBase=11000;
    //UTEC03 gid range 12000~12999
    public static final int UTEC03GidNumBase=12000;
    //UTEC04 gid range 13000~13999
    public static final int UTEC04GidNumBase=13000;
    //UTEC05 gid range 14000~14999
    public static final int UTEC05GidNumBase=14000;
    //UTEC07 gid range 15000~15999
    public static final int UTEC07GidNumBase=15000;
    //UTEC06 gid range 16000~16999
    public static final int UTEC06GidNumBase=16000;
    //UTEM gid range 20000~21000
    public static final int UTEMGidNumBase=20000;
    public static final int PASSPORTGidNumBase=50000;
    public static final String PROJECT_DIR="ou=Projects,dc=bioaster,dc=org";
    public static final String APPLICATION_DIR="ou=Applications,dc=bioaster,dc=org";
}

package org.bioaster.security.config;

/**
 * Created by pliu on 2/27/17.
 */
public class BioasterMailConfig {

    public static final String SMTP_HOST_NAME="smtp.bioaster.org";
    public static final String SMTP_PORT = "25";
    public static final String SenderMailAddr="admin@bioaster.org";


    private static BioasterMailConfig mailConfigInstance=new BioasterMailConfig();
    private BioasterMailConfig(){}
    public static BioasterMailConfig getInstance(){
        return mailConfigInstance;
    }

}

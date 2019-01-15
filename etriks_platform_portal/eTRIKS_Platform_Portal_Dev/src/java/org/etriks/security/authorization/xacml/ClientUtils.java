package org.etriks.security.authorization.xacml;

/**
 * Created by pliu on 10/28/14.
 */

import java.util.Properties;

public class ClientUtils {

    public  String TRUST_STORE_PATH = "/home/pliu/Documents/Tool/wso2is-5.0.0/repository/resources/security/wso2carbon.jks";

    public  String TRUST_STORE_PASSWORD = "wso2carbon";

    public  String SERVER_URL = "https://127.0.0.1:9443/services/";

    public  String SERVER_USER_NAME = "p.liu";

    public  String SERVER_PASSWORD = "Liua1983";

    public  String POLICY_PATH = "policyPath";

    private static Properties configProperties;

    public String getTRUST_STORE_PATH() {
        return TRUST_STORE_PATH;
    }

    public void setTRUST_STORE_PATH(String TRUST_STORE_PATH) {
        this.TRUST_STORE_PATH = TRUST_STORE_PATH;
    }

    public String getTRUST_STORE_PASSWORD() {
        return TRUST_STORE_PASSWORD;
    }

    public void setTRUST_STORE_PASSWORD(String TRUST_STORE_PASSWORD) {
        this.TRUST_STORE_PASSWORD = TRUST_STORE_PASSWORD;
    }

    public String getSERVER_URL() {
        return SERVER_URL;
    }

    public void setSERVER_URL(String SERVER_URL) {
        this.SERVER_URL = SERVER_URL;
    }

    public String getSERVER_USER_NAME() {
        return SERVER_USER_NAME;
    }

    public void setSERVER_USER_NAME(String SERVER_USER_NAME) {
        this.SERVER_USER_NAME = SERVER_USER_NAME;
    }

    public String getSERVER_PASSWORD() {
        return SERVER_PASSWORD;
    }

    public void setSERVER_PASSWORD(String SERVER_PASSWORD) {
        this.SERVER_PASSWORD = SERVER_PASSWORD;
    }

    public String getPOLICY_PATH() {
        return POLICY_PATH;
    }

    public void setPOLICY_PATH(String POLICY_PATH) {
        this.POLICY_PATH = POLICY_PATH;
    }

   /* public static String getPolicyDirectoryPath(String samplePolicyName) {
        String path = null;
        if(configProperties != null){
            String policyPath =  configProperties.getProperty(ClientConstants.POLICY_PATH);
            if(policyPath != null && policyPath.trim().length() > 0){
                path = configProperties.getProperty(ClientConstants.POLICY_PATH) + File.separator + samplePolicyName + ".xml";
            }

        }

        if(path == null){
            try{
                File file = new File((new File(".")).getCanonicalPath() + File.separator +"resources" +
                        File.separator +"policy" + File.separator + samplePolicyName + ".xml");
                if(file.exists()){
                    path = file.getCanonicalPath();
                }
            } catch (IOException e) {
                // ignore
            }
        }
        return path;
    }*/



}

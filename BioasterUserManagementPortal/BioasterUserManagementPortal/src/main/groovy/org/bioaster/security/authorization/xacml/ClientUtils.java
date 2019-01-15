package org.bioaster.security.authorization.xacml;

/**
 * Created by pliu on 3/30/17.
 */
public class ClientUtils {
    /*key store path for prod /usr/share/bump/wso_certs*/
    public  String TRUST_STORE_PATH = "/usr/share/bump/wso_certs/wso2carbonNew.jks";
  // public  String TRUST_STORE_PATH = "/home/pliu/Downloads/wso-certs/wso2carbonNew.jks";

    public  String TRUST_STORE_PASSWORD = "bioaster";

    public  String SERVER_URL = "https://acc-wso.bioaster.org:9443/services/";

    public  String SERVER_USER_NAME = "admin";

    public  String SERVER_PASSWORD = "ef64e47cd6";

    public  String POLICY_PATH = "policyPath";

    //Template file path in prod server
   public String TEMPLATE_PATH="/usr/share/bump/template";

 //   public String TEMPLATE_PATH="/home/pliu/Documents/bump_conf/xacml_template";

    public String ENV_ID="BIOASTER_bump";

    //private static Properties configProperties;

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

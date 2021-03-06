package org.bioaster.security.authorization.xacml;

/**
 * Created by pliu on 3/28/17.
 */

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.entitlement.stub.EntitlementServiceException;
import org.wso2.carbon.identity.entitlement.stub.EntitlementServiceStub;

import java.rmi.RemoteException;
import java.text.MessageFormat;


public class PEPClient {
    static Logger log = LoggerFactory.getLogger(PEPClient.class);
    private EntitlementServiceStub entitlementServiceStub;
    private ClientUtils clientUtils=new ClientUtils();


    public PEPClient(){
        /**
         * Call to https://localhost:9443/services/   uses HTTPS protocol.
         * Therefore we to validate the server certificate. The server certificate is looked up in the
         * trust store. Following code sets what trust-store to look for and its JKs password.
         */



        /**
         * Axis2 configuration context
         */
        ConfigurationContext configContext;

        try {
            System.setProperty("javax.net.ssl.trustStore",  clientUtils.getTRUST_STORE_PATH() );

            System.setProperty("javax.net.ssl.trustStorePassword", clientUtils.getTRUST_STORE_PASSWORD());

            /**
             * Create a configuration context. A configuration context contains information for
             * axis2 environment. This is needed to create an axis2 client
             */
            configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);

            String serviceEndPoint = clientUtils.getSERVER_URL() + "EntitlementService";

            entitlementServiceStub =
                    new EntitlementServiceStub(configContext, serviceEndPoint);
            ServiceClient client = entitlementServiceStub._getServiceClient();
            Options option = client.getOptions();


            /**
             * Setting a authenticated cookie that is received from Carbon server.
             * If you have authenticated with Carbon server earlier, you can use that cookie, if
             * it has not been expired
             *
             * Here i am not using the cookie. If cookie verification fails, it would be looked for basic auth headers
             */
            option.setProperty(HTTPConstants.COOKIE_STRING, null);

            /**
             * Setting basic auth headers for authentication for user admin
             */
            HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
            auth.setUsername(clientUtils.getSERVER_USER_NAME());
            auth.setPassword(clientUtils.getSERVER_PASSWORD());
            auth.setPreemptiveAuthentication(true);
            option.setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, auth);
            option.setManageSession(true);

        } catch (Exception e) {
            log.error("Authorization server connection failed!!!"+e.toString());
            System.exit(0);
        }
    }

    //sample XACML request captured from TryIt tool of IdentityServer. this request sample allow you to send authorization request with user role.
   /* private static String sampleRequest = "<Request xmlns=\"urn:oasis:names:tc:xacml:2.0:context:schema:os\"\n" +
            "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
            "    <Resource>\n" +
            "        <Attribute AttributeId=\"urn:oasis:names:tc:xacml:1.0:resource:resource-id\"\n" +
            "                   DataType=\"http://www.w3.org/2001/XMLSchema#string\">\n" +
            "            <AttributeValue>{2}</AttributeValue>\n" +
            "        </Attribute>\n" +
            "    </Resource>\n" +
            "    <Subject>\n" +
            "        <Attribute AttributeId=\"urn:oasis:names:tc:xacml:1.0:subject:subject-id\"\n" +
            "                   DataType=\"http://www.w3.org/2001/XMLSchema#string\">\n" +
            "            <AttributeValue>{0}</AttributeValue>\n" +
            "        </Attribute>\n" +
            "        <Attribute AttributeId=\"http://wso2.org/claims/role\"\n" +
            "                   DataType=\"http://www.w3.org/2001/XMLSchema#string\">\n" +
            "            <AttributeValue>{1}</AttributeValue>\n" +
            "        </Attribute>\n" +
            "    </Subject>\n" +
            "    <Action>\n" +
            "        <Attribute AttributeId=\"urn:oasis:names:tc:xacml:1.0:action:action-id\"\n" +
            "                   DataType=\"http://www.w3.org/2001/XMLSchema#string\">\n" +
            "            <AttributeValue>{3}</AttributeValue>\n" +
            "        </Attribute>\n" +
            "    </Action>\n" +
            "    <Environment/>\n" +
            "</Request>";*/

    public String createRequest(String subjectId,String role,String action,String resourcdId,String envId){
        StringTemplateGroup group=new StringTemplateGroup("myGroup", clientUtils.TEMPLATE_PATH, DefaultTemplateLexer.class);
        StringTemplate request=group.getInstanceOf("xacmlRequest");
        request.setAttribute("resouceId",resourcdId);
        request.setAttribute("subjectId",subjectId);
        request.setAttribute("action",action);
        request.setAttribute("role",role);
        request.setAttribute("envId",envId);
        return request.toString();
    }

    /*This method take subjectId, subjectRole, resourceId, actionId, et the xacml request sample to build a authorization reuqest
    * it return the xacml request
    * @param subjectId id of the subject
    * @param subjectRole  role of the subject
    * @param resourceId the id of the target resource
    * @param actionId id of the action which subject want to execute on the object
    * @return         xacml authorization Request*/

    /*This method take subjectId, subjectRole, resourceId, actionId to build request than evaluate based on the security policy*/
    public PolicyDecision getDecisionByAttributes(String userId,String role,String targetId,String action,String environmentId){
        //String request=buildRequest(userId,role,targetId,action);
        if (environmentId==null || environmentId.isEmpty()){
            environmentId=clientUtils.ENV_ID;
        }

        String request=createRequest(userId,role,action,targetId,environmentId);

        return evaluateXACMLRequest(request);
    }


public PolicyDecision evaluateXACMLRequest(String request){
        String decision="No response";
    if (entitlementServiceStub!=null){
        try {
            decision=entitlementServiceStub.getDecision(request);
        } catch (RemoteException e) {
            log.error("Unable to connect authorization server"+e.toString());
            System.exit(0);
        } catch (EntitlementServiceException e) {
            log.error("Entitlement service failed"+e.toString());
            System.exit(0);
        }

    }
    else {log.error("The initiation of the entitlementServiceStub failed!!!");
        System.exit(0);
    }
    return new PolicyDecision(decision);
}



    /*This method return the authCookie after the pep client authenticate with the Wso server.*/
    public String getAuthCookieInfo(){
        return  (String) entitlementServiceStub._getServiceClient().getServiceContext()
                .getProperty(HTTPConstants.COOKIE_STRING);
    }

}

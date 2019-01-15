package org.bioaster.security.authorization;

import java.util.List;

/**
 * Created by pliu on 3/6/17.
 */
public class PolicyEngine {

    private static PolicyEngine policyEngineInstance=new PolicyEngine();

    private static PolicyRules policyRules;

    private PolicyEngine(){policyRules=PolicyRules.getInstance();}

    public static PolicyEngine getInstance(){
        return policyEngineInstance;
    }
/*
* This method check if user has the required role to access the resource
*
* @params: String requiredRole
* @params: List<String> userRoles
* @return: boolean result
* */
    public static boolean checkRole(String requiredRole,List<String> userRoles){
        return userRoles.contains(requiredRole);
    }

    public static boolean checkAuthorization(String controllerName,List<String> userRoles){
        boolean result=false;
       List<String> requiredRoles=policyRules.getControllerRequireRoles(controllerName);
       for(String role:requiredRoles){

           if (checkRole(role,userRoles)){

               result=true;
               break;
           }
       }
       return result;
    }
}

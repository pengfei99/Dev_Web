package org.etriks.security.authorization;

import org.etriks.security.authorization.xacml.PEPClient;
import org.etriks.security.authorization.xacml.PolicyDecision;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

/**
 * Created by pliu on 4/2/14.
 */
public class PolicyEngine {
    /*This method checks the role of the user who wants to access resource
    * @param requeiredRole  required role for access the resource
    * @param userRoles  a list of roles which the user have
    * @return          the decision is true if user has the required role, or return false
    * */

    public boolean checkRoles(String requiredRole,Collection<GrantedAuthority> userRoles){
        GrantedAuthority requiredAuthority=new SimpleGrantedAuthority(requiredRole);
        int size=userRoles.size();
        boolean result=false;
        if(size>0){
            for(GrantedAuthority role : userRoles){
                if(role.equals(requiredAuthority)){
                    result=true;
                    break;
                }
            }
        }
        return result;
    }
    /*
     * This method checks the request by use pep client to connect wso2 is server,
     * @param userId
     * @param userRoles a list of roles which user has
     * @param targetId
     * @param action
     * @return    decision of the security policy
     */

    public boolean checkRequestByAttributes(String userId,Collection<GrantedAuthority> userRoles,String targetId,String action){

        boolean result=false;
        PEPClient pepClient=new PEPClient();
        PolicyDecision decision;
        for(GrantedAuthority role:userRoles){
            String strRole=role.getAuthority();
            System.out.println("user id is :"+userId);
            System.out.println("user role is :"+strRole);
            System.out.println("target id is :"+targetId);
            System.out.println("action :"+action);
            decision=pepClient.evaluateRequest(userId, strRole, targetId, action);
            if(decision.getBooleanDecision()) {result=true;break;}
        }


        return result;
    }
}

package org.bioaster.intercepter

import org.bioaster.security.auth.UserDetails
import org.bioaster.security.authorization.PolicyEngine


class AccessControlInterceptor {



    AccessControlInterceptor(){
        match controller : 'bioasterAdminDashBoard'
        match controller : 'bioasterUserRegistrationValidation'
        match controller : 'bioasterOrganizationManager'
        match controller : 'bioasterUserManager'
        match controller : 'bioasterGroupManager'
        match controller : 'accessLog'
        match controller : 'bioasterSecurityPolicyTester'

        //match controller : 'loginUserSpace'
    }

    boolean before() {
        PolicyEngine policyEngine=PolicyEngine.getInstance();
        UserDetails userInstance=session.userDetails;
        String controllerName=params.controller

       // def action=params.action
        if(userInstance==null){
            redirect(controller:"accessControlException",action:"notLoggedIn")
        }
        else{if(!policyEngine.checkAuthorization(controllerName,userInstance.getRoles())){
            redirect(controller:"accessControlException",action:"accessDeny")
        }}



        true }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}

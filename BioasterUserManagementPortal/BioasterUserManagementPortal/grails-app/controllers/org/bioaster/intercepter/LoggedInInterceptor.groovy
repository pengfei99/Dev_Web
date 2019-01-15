package org.bioaster.intercepter


class LoggedInInterceptor {

    def springSecurityService
//This interceptor intercept all request except login controller
    LoggedInInterceptor(){
       /* match controller : 'bioasterAdminDashBoard'
        match controller : 'bioasterUserRegistrationValidation'*/
        match controller : 'loginUserSpace'
        /*
 // Match all request except login, etc.

        matchAll().excludes(controller:"login")
                  .excludes(controller:'importADUser')
                  .excludes(controller:'bioasterUserRegistrationRequest')

        */
    }

    boolean before() {
        if(!springSecurityService.isLoggedIn())

        {redirect(controller:"accessControlException",action:"notLoggedIn")}

        true }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}



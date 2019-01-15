package org.etriks.security.enduser

import org.etriks.security.ldap.authentication.UserDetails

class LoginUserSpaceController {

    def index() {

        def UserDetails userInstance=session.userDetails;
        if(userInstance!=null){
            def String userName=userInstance.getUserName();
            def userRole=userInstance.getRole();
            def lastLoginTime=userInstance.getLastLogInTime();



            render (view:"index",model:[userName:userName,userRole:userRole,lastLoginTime:lastLoginTime])
        }
        else redirect(controller:"AccessControlException",action:"notLoggedIn")
    }
}

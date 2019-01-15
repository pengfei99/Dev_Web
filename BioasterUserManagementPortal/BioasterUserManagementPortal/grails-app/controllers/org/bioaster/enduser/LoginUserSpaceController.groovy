package org.bioaster.enduser

import org.bioaster.security.auth.UserDetails

class LoginUserSpaceController {

    def springSecurityService

    def index() {
        UserDetails userInstance=session.userDetails;
        if(userInstance!=null){
            String uid=userInstance.getUsername();
            String fullName=userInstance.getFullName();
            String mail=userInstance.getMail();
            def userRole=userInstance.getRoles();
            def lastLoginTime=userInstance.getLastLogInTime();



            render (view:"index",model:[uid:uid,fullName:fullName,mail:mail,userRole:userRole,lastLoginTime:lastLoginTime])
        }
        else redirect(controller:"AccessControlException",action:"notLoggedIn")
    }


}


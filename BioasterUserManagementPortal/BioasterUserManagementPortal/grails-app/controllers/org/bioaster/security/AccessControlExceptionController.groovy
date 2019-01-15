package org.bioaster.security

import org.bioaster.security.config.BUMPConfig

class AccessControlExceptionController {

    def accessDeny(){

        render(view:"accessDeny",model:[mail:BUMPConfig.webMasterMail])
    }

    def notLoggedIn(){

        render(view:"notLoggedIn",model:[mail:BUMPConfig.webMasterMail])
    }

}

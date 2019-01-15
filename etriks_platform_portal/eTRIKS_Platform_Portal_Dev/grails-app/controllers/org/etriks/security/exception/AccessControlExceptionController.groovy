package org.etriks.security.exception

class AccessControlExceptionController {

    def index() { }

    def accessDeny(){

        render(view:"accessDeny")
    }

    def notLoggedIn(){
        render(view:"notLoggedIn")
    }
}

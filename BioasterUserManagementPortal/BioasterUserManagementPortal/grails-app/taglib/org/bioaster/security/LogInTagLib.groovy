package org.bioaster.security

import org.bioaster.log.AccessLogService
import org.bioaster.log.LogType
import org.bioaster.security.auth.UserDetails

class LogInTagLib {
  /*  static defaultEncodeAs = [taglib: 'html']*/
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def AccessLogService accessLogService;
    def springSecurityService

    def loginControl = {


        if (springSecurityService.isLoggedIn()) {
            if (session.userDetails == null) {
                def username=springSecurityService.principal.username
                session.userDetails = new UserDetails(username);
                accessLogService.saveLog(username + " login succesfully", username, LogType.Info)
                out << userSpace(username)

            } else {
                out << userSpace(session.userDetails.username)
            }
        } else {

            out << login()
        }
    }

    def userSpace(username) {
        StringBuilder sb = new StringBuilder();
        sb << """

        <li id="fat-menu3" class="dropdown">
        <a id="tools" class="dropdown-toggle clearfix" data-toggle="dropdown" role="button" href="#" aria-expanded="true">
              <i class="glyphicon glyphicon-user"> </i>  ${username}
        <span class="caret"></span></a>
        <ul class="dropdown-menu" aria-labelledy="drop2" role="menu">
        <li role="presentation"><a role="menuitem" tabindex="-1" href="${createLink(controller: 'loginUserSpace')}" >User space</a></li>
        <li role="presentation"><a role="menuitem" tabindex="-1" href="${createLink(controller: 'logout')}" >Log out</a></li>
                </ul>  </li>
"""
        sb.toString()
    }

    def login() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder << """
<li> <a href="${createLink(controller: 'login')}"> <i class="glyphicon glyphicon-user"> </i> Log In</a> </li>
"""
        stringBuilder.toString()

    }
}
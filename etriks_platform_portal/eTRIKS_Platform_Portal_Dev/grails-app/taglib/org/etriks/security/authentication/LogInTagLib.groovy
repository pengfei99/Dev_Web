package org.etriks.security.authentication

import grails.transaction.Transactional
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.security.ldapnew.authentication.UserDetails
import org.etriks.security.log.AccessLogService
import org.springframework.security.core.GrantedAuthority

@Transactional
class LogInTagLib {

    //With this line enabled,all the html code will not be interpreted.
   // static defaultEncodeAs = 'html'

    //static encodeAsForTags = [tagName: 'raw']


def AccessLogService accessLogService;
    def springSecurityService

       def loginControl={


           if(springSecurityService.isLoggedIn()){
               if(session.userDetails==null){
               String username=springSecurityService.getPrincipal().toString();
               Collection<GrantedAuthority> roles=springSecurityService.authentication.getAuthorities()
               session.userDetails=new UserDetails(username,roles);
                   accessLogService.saveLog(username+" login succesfully",username,ProjectId.etriks, LogType.Info)
               /*out<<"""${link(controller:"loginUserSpace") {"Hi, ${username}"}}"""*/
                   out<<userSpace(username)

           }
               else { out<< userSpace(session.userDetails.username)
           }
           }

           else {

               out <<  login()
           }
       }

    def userSpace(username){
        StringBuilder sb=new StringBuilder();
        sb << """

        <li id="fat-menu3" class="dropdown">
        <a id="tools" class="dropdown-toggle clearfix" data-toggle="dropdown" role="button" href="#" aria-expanded="true">
              <i class="glyphicon glyphicon-user"> </i>  ${username}
        <span class="caret"></span></a>
        <ul class="dropdown-menu" aria-labelledy="drop2" role="menu">
        <li role="presentation"><a role="menuitem" tabindex="-1" href="${createLink(controller: 'loginUserSpace')}" >User space</a></li>
        <li role="presentation"><a role="menuitem" tabindex="-1" href="${createLink(controller:'logout')}" >Log out</a></li>
                </ul>  </li>
"""
        sb.toString()
    }

    def login(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder<< """
<li> <a href="${createLink(controller: 'login')}"> <i class="glyphicon glyphicon-user"> </i> Log In</a> </li>
"""
        stringBuilder.toString()
    }

}

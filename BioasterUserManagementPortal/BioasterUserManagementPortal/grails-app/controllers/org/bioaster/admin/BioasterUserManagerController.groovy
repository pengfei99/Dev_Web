package org.bioaster.admin

import org.bioaster.log.AccessLogService
import org.bioaster.log.LogType
import org.bioaster.security.MemberManagerService
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.config.OpenLdapConfig

class BioasterUserManagerController {

    def MemberManagerService memberManagerService;
    def AccessLogService accessLogService;
    def springSecurityService
    def ILdapServerConnectorConfig ldapConfig=OpenLdapConfig.getInstance();

    def index() { }

    def userAccountList(){
        List<String> membersList=memberManagerService.getALLAccountNameList(ldapConfig);
        def userAccountTotal=membersList.size()
        String userName=springSecurityService.principal.username;
        accessLogService.saveLog("View all user List",userName,LogType.Info);
        render(view:"userAccountList",model:[userCNList: membersList,userAccountTotal:userAccountTotal])

    }


    /*This controller takes a username(cn) and render his attribute
*
* key-> value type:  cn->String,mail->String,memberOf->List<String>,gidNumber->String
*                    uidNumber->String, homeDirectory->String, uid->Sting, bioasterGroup->List<String>
*                     projectGroup->List<String>,bumpRoles->List<String>
*
* @params: ILdapServerConnectorConfig ldapConfig
* @params: String userName
* @return: Map<String,Object> userAttrs
*
* */
    def showUserDetails(String id){
        String userName=springSecurityService.principal.username;
        accessLogService.saveLog("View the attibutes of "+id,userName,LogType.Info);
        Map<String, Object> userAttrs;
        userAttrs=memberManagerService.getUserDetails(ldapConfig,id);
        if(userAttrs==null){
            flash.message="The user account which you looking for does not exist or has been deleted"
            render(view:"showUserDetails", model : [cn:"unknow",uid:"unknow",mail:"unknow"])
        }
        else{
            String mail=(String)userAttrs.get("mail");
            String cn= (String)userAttrs.get("cn");
            String uid=(String)userAttrs.get("uid");
            String gidNumber=(String)userAttrs.get("gidNumber");
            String uidNumber=(String)userAttrs.get("uidNumber");
            String homeDirectory=(String)userAttrs.get("homeDirectory");
            List<String> bioasterGroup=(List<String>)userAttrs.get("bioasterGroup");
            List<String> projectGroup=(List<String>)userAttrs.get("projectGroup");
            List<String> bumpRoles=(List<String>)userAttrs.get("bumpRoles");

            render(view:"showUserDetails", model : [cn:cn,uid:uid,mail:mail,gidNumber:gidNumber,uidNumber:uidNumber,homeDirectory:homeDirectory,bioasterGroup:bioasterGroup,projectGroup:projectGroup,bumpRoles:bumpRoles])
        }

    }

}

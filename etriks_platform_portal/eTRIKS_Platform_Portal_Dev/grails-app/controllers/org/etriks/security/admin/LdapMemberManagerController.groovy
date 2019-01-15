package org.etriks.security.admin

import org.etriks.admin.dashboard.ExportUser
import org.etriks.security.Ldap.MemberManagerService
import pl.touk.excel.export.WebXlsxExporter

class LdapMemberManagerController {
    def projectName="etriks";

    def MemberManagerService memberManagerService;

    def index() {}

    def userAccountList() {
        List<String> membersList;
        membersList = memberManagerService.getALLAccountNameList();
        def userAccountTotal = membersList.size()
        render(view: "userAccountList", model: [userCNList: membersList, userAccountTotal: userAccountTotal])
    }
    //This action shows some information of a specific user account.
    def showUserDetails(String id) {
        Map<String, Object> userDetails;
        userDetails = memberManagerService.getUserDetails(projectName, id);
        if (userDetails.get("isEmpty") == true) {
            flash.message = "The user account which you looking for does not exist or has been deleted"
            render(view: "showUserDetails", model: [pcn: "unknow", puid: "unknow", mail: "unknow"])
        } else {
            String mail = (String) userDetails.get("mail");
            String pcn = (String) userDetails.get("pcn");
            String puid = (String) userDetails.get("puid");
            String organizationName = (String) userDetails.get("organizationName");
            Map<String, String> userGroups = (Map<String, String>) userDetails.get("userGroups");
            render(view: "showUserDetails", model: [pcn: pcn, puid: puid, mail: mail, organizationName: organizationName, userGroups: userGroups])
        }

    }
    def userMailList(){
        String mailListStr=memberManagerService.getUserMailList(projectName).toString();
        render(view:"userMailList", model:[mailListStr:mailListStr])
    }
    //this action shows all registered user name, uid and mail
    def userMailListWithUserName(){

        List<ExportUser> userList;
        userList=memberManagerService.getAllUsersFromLdap();
        flash.userList=userList;
        render(view:"userMailList", model:[userList: userList]);

    }


    def exportUserList(){

        def List<ExportUser> userList=flash.userList;
        def headers=['Name','Uid','Mail']
        def withProperties=['name','uid','mail']

        new WebXlsxExporter().with{
            setResponseHeaders(response)
            fillHeader(headers)
            add(userList,withProperties)
            save(response.outputStream)
        }


        render (view:"userMailList",model:[userList:userList])
    }

}

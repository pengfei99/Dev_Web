package org.bioaster.admin

import org.bioaster.log.AccessLogService
import org.bioaster.log.LogType
import org.bioaster.security.MemberManagerService
import org.bioaster.security.OrganizationManagerService
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.config.OpenLdapConfig

class BioasterOrganizationManagerController {

    def OrganizationManagerService organizationManagerService;
    def MemberManagerService memberManagerService;
    def AccessLogService accessLogService;
    def ILdapServerConnectorConfig ldapConfig=OpenLdapConfig.getInstance();


    def index() { }

    /*show all organization*/
    def organizationList(){
        Map<String,String> allOrganizations=organizationManagerService.getAllOrganization(ldapConfig);
        def organizationTotal=allOrganizations.size();
        render(view:"organizationList",model:[organizationList: allOrganizations,organizationTotal:organizationTotal]);

    }
    /*show organization details*/
    def showOrganizationDetails(String id){
        String[] returnAttrs=new String[2];
        returnAttrs[0]="gidNumber";
        returnAttrs[1]="description";
        Map<String,String> orgDetails=organizationManagerService.getOrganizationDetails(ldapConfig, id, returnAttrs);
        render(view:"showOrganizationDetails",model:[organizationName:id,gidNumber:orgDetails.get("gidNumber"),description:orgDetails.get("description")])
    }
    /*render the organization creation form*/
    def createOrganization(){}

    /*Insert the new organization into ldap*/
    def createOrganizationLdapEntry(){
        String organizationName=params.organizationName;
        boolean result=organizationManagerService.createOrganization(ldapConfig,organizationName,params.organizationDescription)

        if (result){
            accessLogService.saveLog("Organization "+organizationName+" has been created in the Ldap server",(String)session.userDetails.username, LogType.Info)
            flash.message="Organization "+organizationName+" has been created in the Ldap server";
            render(view: "createOrganization")
        }
        else {
            accessLogService.saveLog("Organization "+organizationName+" cannot be created !!!", (String)session.userDetails.username, LogType.Error)
            flash.error="Organization "+organizationName+" cannot be created !!! Please check if the organization name has been used";
            render(view: "createOrganization")
        }

    }

/*creat a view for change user organization */
    def changeMemberOrganizationView(){

        Map<String,String> allOrganizations=organizationManagerService.getAllOrganization(ldapConfig);
        def allUsers = memberManagerService.getALLUserNameAndUidList(ldapConfig);
        def userSize=allUsers.size();

        render (view:"changeMemberOrganizationView",model:[organizationList:allOrganizations,userList:allUsers,userSize:userSize])
    }

    /*change user organization id in ldap server*/
    def changeMemberOrganization(){

        List<String> resultMessage=new ArrayList<>();
        /*
       * get the chosen organization */
        String organizationGid=params.get("organization");
        def allOrganizations=organizationManagerService.getAllOrganization(ldapConfig)
        println("org gid: "+organizationGid)
        List<String> chosenUserList=new ArrayList<String>();
        def userSize=Integer.parseInt(params.userSize);
        /*
              * get all the chosen user list
              * */
        for(int i=0;i<userSize;i++){
             String index="user_"+Integer.toString(i);
             String uid=params.get(index);
            if(uid!=null){chosenUserList.add(uid);
            }
        }
println("userList: "+chosenUserList)
        /*
      * Change the user gid number to the chosen organization gid number
      * */

        if(!chosenUserList.isEmpty()){
            for(int j=0;j<chosenUserList.size();j++){
                def userName=chosenUserList.get(j);
                boolean result=memberManagerService.modifyUserGidNum(ldapConfig,userName,organizationGid)
                if (result){
                    def orgName=allOrganizations.get(organizationGid);
                    accessLogService.saveLog("User "+userName+" is now member of the "+orgName,session.userDetails.username, LogType.Info)
                    resultMessage.add("User "+userName+" is now member of the "+orgName)
                }
                else {
                    accessLogService.saveLog("User "+userName+" can not change his organization ",session.userDetails.username, LogType.Error)
                    resultMessage.add("User "+userName+" can not change his organization")

                }

            }
        }
        else { flash.message="You must choose at least one user !!!"
            redirect(action:"changeMemberOrganizationView");}


        render (view:"changeMemberOrganizationResult",model:[messageList:resultMessage])
    }




}

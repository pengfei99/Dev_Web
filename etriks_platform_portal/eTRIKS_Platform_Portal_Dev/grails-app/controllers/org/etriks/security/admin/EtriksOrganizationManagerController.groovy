package org.etriks.security.admin
import grails.transaction.Transactional
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.security.Ldap.MemberManagerService
import org.etriks.security.Ldap.OrganizationManagerService
import org.etriks.security.log.AccessLogService

@Transactional
class EtriksOrganizationManagerController {

  /*  def EtriksMemberLdapConnectorConfig etriksLdap=new EtriksMemberLdapConnectorConfig();*/
    private String projectName="etriks";
    def AccessLogService accessLogService;
    def MemberManagerService memberManagerService
    def OrganizationManagerService organizationManagerService;

    def index() {}
    def organizationList(){
        Map<String,String> allOrganizations=organizationManagerService.getAllOrganization();
        def organizationTotal=allOrganizations.size();
        render(view:"organizationList",model:[organizationList: allOrganizations,organizationTotal:organizationTotal])
    }
    def showOrganizationDetails(String id){

        Map<String,String> orgDetails=organizationManagerService.getOrganizationDetails(id);

        render(view:"showOrganizationDetails",model:[organizationName:id,gidNumber:orgDetails.get("gidNumber"),description:orgDetails.get("description")])

    }
    def createOrganization(){}
    def createOrganizationLdapEntry(){
        String organizationName=params.organizationName;
        boolean result=organizationManagerService.createOrganizationInNewLdap(organizationName,params.organizationDescription)
        if (result){
            accessLogService.saveLog("Organization "+organizationName+" has been created in the Ldap server",session.userDetails.username, ProjectId.etriks, LogType.Info)
            flash.message="Organization "+organizationName+" has been created in the Ldap server";
                     render(view: "createOrganization")}
        else {
            accessLogService.saveLog("Organization "+organizationName,session.userDetails.username, ProjectId.etriks, LogType.Error)
            flash.error="Organization "+organizationName+" cannot be created !!! Please check if the organization name has been used";
            render(view: "createOrganization")}

    }
/*********This method render all users and all organizations****************/
    def changeMemberOrganizationView(){

        Map<String,String> allOrganizations=organizationManagerService.getAllOrganization();
        def allUsers = memberManagerService.getPojectUserNameAndUidList(projectName);
        def organizationSize=allOrganizations.size();
        def userSize=allUsers.size();
        render (view:"changeMemberOrganizationView",model:[organizationList:allOrganizations,userList:allUsers,organizationSize:organizationSize,userSize:userSize])
    }
/***************************************************************/
    def changeMemberOrganization(){
        def List<String> resultMessage=new ArrayList<>();
        /*
       * get the chosen organization */
        def organizationGid=params.get("organization");
        def allOrganizations=organizationManagerService.getAllOrganization()

        def List<String> chosenUserList=new ArrayList<String>();
        def userSize=Integer.parseInt(params.userSize);
        /*
              * get all the chosen user list
              * */
        for(int i=0;i<userSize;i++){
            def String index="user_"+Integer.toString(i);
            def uid=params.get(index);
            if(uid!=null){chosenUserList.add(uid);
            }
        }

        /*
      * Change the user gid number to the chosen organization gid number
      * */

        if(!chosenUserList.isEmpty()){
            for(int j=0;j<chosenUserList.size();j++){
                def uid=chosenUserList.get(j);
                /************change user organization in new ldap server*/
                boolean result=organizationManagerService.changeUserOrganizationInNewLdap(uid,organizationGid);


                if (result){
                    def orgName=allOrganizations.get(organizationGid);
                    accessLogService.saveLog("User "+uid+" is now member of the "+orgName,session.userDetails.username, ProjectId.etriks, LogType.Info)
                    resultMessage.add("User "+uid+" is now member of the "+orgName)
                }
                else {
                    accessLogService.saveLog("User "+uid+" can not change his organization ",session.userDetails.username, ProjectId.etriks, LogType.Error)
                    resultMessage.add("User "+uid+" can not change his organization")
                }

            }
        }
        else { flash.message="You must choose at least one user !!!"
            redirect(action:"changeMemberOrganizationView");}


        render (view:"changeMemberOrganizationResult",model:[messageList:resultMessage])
    }

}

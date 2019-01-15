package org.etriks.security.admin
import grails.transaction.Transactional
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.security.Ldap.GroupManagerService
import org.etriks.security.Ldap.MemberManagerService
import org.etriks.security.log.AccessLogService

@Transactional
class BioasterGroupManagerController {
    //database log
    def AccessLogService accessLogService;

    def GroupManagerService groupManagerService;
    def MemberManagerService memberManagerService
    String projectName="bioaster";

    def index() {}
    def groupList(){
        def allGroups= groupManagerService.getAllPosixGroups(projectName);
        render(view:"groupList",model:[groupList: allGroups,groupTotal:allGroups.size()])
    }
    def showGroupDetails(String id){

        def groupDetails=groupManagerService.getGroupDetails(id,projectName);
        render(view:"showGroupDetails",model:[groupName:id,gidNumber:groupDetails.get("gidNumber"),description:groupDetails.get("description"),memberUids:groupDetails.get("memberUids")])

    }
    def createGroup(){}
    def createGroupLdapEntry(){
        def String groupName=params.groupName;
        def String groupDescription=params.groupDescription;
        def boolean result=false;
        //create group in new ldap archi
        try{groupManagerService.createVMGroupInNewLdap(projectName,groupName,groupDescription)
            result=true;
        }
        catch(e){
            //new group creation in new ldap failed
            flash.message="new group creation in new ldap failed!!!"
        }
        //End of group creation in new ldap
        if (result){
            String messageTmp="Group "+groupName+" has been created in the Ldap server";

            accessLogService.saveLog(messageTmp,session.userDetails.username, ProjectId.bioaster, LogType.Info);
            flash.message=messageTmp
            render(view: "createGroup")
        }
        else {
            accessLogService.saveLog("Group "+groupName+" cannot be created !!! Please check if the group name has been used",session.userDetails.username, ProjectId.bioaster, LogType.Error);
            flash.error="Group "+groupName+" cannot be created !!! Please check if the group name has been used";
            render(view: "createGroup")}

    }

    def addMemberToGroupView(){

        def allUsers=memberManagerService.getPojectUserNameAndUidList(projectName);
        def allGroups= groupManagerService.getAllPosixGroups(projectName);
        render (view:"addMemberToGroupView",model:[groupList:allGroups,userList:allUsers,groupSize:allGroups.size(),userSize:allUsers.size()])
    }

   /* def searchGroup(){
        def searchGroupName=params.get("searchGroupName");
        def UserGroupManager grpReg = new UserGroupManager(bioasterLdap,"dc=etriks,dc=eu");
        def allGroups=grpReg.getAllGroups();
        def Iterator it=allGroups.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry groupPairs=(Map.Entry)it.next();
            if (searchGroupName.){}
        }

        flash.message=searchGroupName;
        render (view:"addMemberToGroupView")
    }*/

    def addMemberToGroup(){

        /*
        * get all the chosen group list */
        def List<String> chosenGroupList=new ArrayList<String>();
        def groupSize=Integer.parseInt(params.groupSize);
           for(int i=0;i<groupSize;i++){
                def String index="group_"+Integer.toString(i);
                def groupName=params.get(index);
                  if (groupName!=null){chosenGroupList.add(groupName);}
            }
        /*
        * get all the chosen user list
        * */

        def List<String> chosenUserList=new ArrayList<String>();
        def userSize=Integer.parseInt(params.userSize);

        for(int i=0;i<userSize;i++){
            def String index="user_"+Integer.toString(i);
            def uid=params.get(index);
            if(uid!=null){chosenUserList.add(uid);
            }
        }
        /*
        * We first test if the group list is not empty, if it's empty, redirect to the add User view with a error message
        * Then we test if the group list is not empty, if it's empty, redirect to the add user view with a error message*/
   if(!chosenGroupList.isEmpty()){
       if(!chosenUserList.isEmpty()){
           /*Here we add all users to all groups selected*/

           //add member to new ldap
           def resultMessage=groupManagerService.addMemberToPosixGroupInNewLdap(projectName,chosenUserList,chosenGroupList)
           for(String message:resultMessage){
               accessLogService.saveLog(message,session.userDetails.username, ProjectId.bioaster, LogType.Info);
           }


          render (view:"addMemberToGroupResult", model:[messageList:resultMessage])


       }
       else {
           flash.error="You must chose at least one user";redirect(action:"addMemberToGroupView")
       }
   }
        else{flash.error="You must chose at least one group to add users";redirect(action:"addMemberToGroupView")
   }


    }


}

package org.etriks.security.admin
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.security.Ldap.MemberManagerService
import org.etriks.security.Ldap.TransmartGroupManagerService
import org.etriks.security.log.AccessLogService

class AbiriskTransmartGroupManagerController {

    def TransmartGroupManagerService transmartGroupManagerService;
    def MemberManagerService memberManagerService;
    //database log
    def AccessLogService accessLogService;
    //Abirisk Ldap Server connection config instance
   // def AbiriskLdapConnectorConfig abiriskLdap=new AbiriskLdapConnectorConfig();

    String projectName="abirisk";

    def index() {}

    def transmartGroupList(){
        def allGroups=transmartGroupManagerService.getAllTransmartGroups(projectName);
        render(view:"transmartGroupList",model:[groupList: allGroups,groupTotal:allGroups.size()])
    }


    def showGroupDetails(String id){
        def groupDetails=transmartGroupManagerService.getTransmartGroupDetails(id,projectName);
        render(view:"showGroupDetails",model:[groupName:id,gidNumber:groupDetails.get("gidNumber"),memberUids:groupDetails.get("memberUids")])
    }

    def addMemberToTransmartGroupView(){


        def allUsers=memberManagerService.getPojectUserNameAndUidList(projectName);
        def allGroups=transmartGroupManagerService.getAllTransmartGroups(projectName);
        render (view:"addMemberToTransmartGroupView",model:[groupList:allGroups,userList:allUsers,groupSize:allGroups.size(),userSize:allUsers.size()])
    }

    def addMemberToTransmartGroup(){

        def List<String> resultMessage=new ArrayList<>();
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

                for(int i=0;i<chosenGroupList.size();i++){
                    def groupName=chosenGroupList.get(i);
                    def groupCN="cn="+groupName;
                    for(int j=0;j<chosenUserList.size();j++){
                        def uid=chosenUserList.get(j);
                        def result= transmartGroupManagerService.addTransmartGroupsMember(projectName,groupCN,uid);
                        if(result){
                            accessLogService.saveLog("User "+uid+" has been added to group "+groupName,session.userDetails.username, ProjectId.abirisk, LogType.Info);
                            resultMessage.add("User "+uid+" has been added to group "+groupName)
                        }
                        else {
                            accessLogService.saveLog("User "+uid+" cannot been added to group "+groupName,session.userDetails.username, ProjectId.abirisk, LogType.Error);
                            resultMessage.add("User "+uid+" cannot been added to group "+groupName+". Please check if the user is already in the group")
                        }

                    }
                }

                render (view:"addMemberToTransmartGroupResult", model:[messageList:resultMessage])
                /* redirect(action:"addMemberToGroupResult",params:[resultMessage:resultMessage])*/

            }
            else {
                flash.error="You must chose at least one user";redirect(action:"addMemberToTransmartGroupView")
            }
        }
        else{flash.error="You must chose at least one group to add users";redirect(action:"addMemberToTransmartGroupView")
        }


    }

}

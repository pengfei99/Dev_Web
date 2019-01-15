package org.bioaster.admin

import grails.web.servlet.mvc.GrailsParameterMap
import org.bioaster.log.AccessLogService
import org.bioaster.log.LogType
import org.bioaster.security.MemberManagerService
import org.bioaster.security.PosixGroupManagerService
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.config.OpenLdapConfig
import org.bioaster.security.ldapManager.LdapGroupManager
import org.bioaster.security.ldapManager.LdapProjectManager
import org.bioaster.utils.StringToIntCheck

import javax.naming.NamingException

class BioasterGroupManagerController {
    def PosixGroupManagerService posixGroupManagerService;
    def AccessLogService accessLogService;
    def MemberManagerService memberManagerService;

    ILdapServerConnectorConfig ldapConf=OpenLdapConfig.getInstance();

    def index() { }

    /*****************************************************************************************
    * ******************************Controller methode for department group management ******
    * *************************************************************************************/
    def bioasterGroupList(){
       List<String> departmentList=posixGroupManagerService.getDepartmentNameList(ldapConf);

        render(view:"departmentGroupList",model:[departmentList:departmentList])
    }

    def showDepartmentGroup(){
        List<String> departmentList=posixGroupManagerService.getDepartmentNameList(ldapConf);
        String chosenDepartment=params.get("department");
        List<String> groupList=posixGroupManagerService.getDepartmentGroupList(ldapConf,chosenDepartment)
        render(view:"departmentGroupList",model:[departmentList:departmentList,groupList:groupList])
    }

    def showDepartmentGroupDetails(String id){

        Map<String,Object> groupAttrs=posixGroupManagerService.getDepartmentGroupDetails(ldapConf,id);
        String cn=groupAttrs.get("cn");
        String gidNumber=groupAttrs.get("gidNumber");
        String description=groupAttrs.get("description");
        List<String> memberUidList=groupAttrs.get("memberUidList");
        int userSize = 0;
        if(memberUidList!=null && !memberUidList.isEmpty()){
            userSize = memberUidList.size();
        }

        render(view:"showDepartmentGroupDetails",model:[cn:cn,gidNumber:gidNumber,description:description,memberUidList:memberUidList , userSize:userSize])

    }
/*render the department group creation form*/
    def createDepartmentGroup(){
        List<String> departmentList=posixGroupManagerService.getDepartmentNameList(ldapConf);
        render(view:"createDepartmentGroup",model:[departmentList:departmentList])
    }

    /*Insert the new department group into ldap*/
    def createDepartmentGroupLdapEntry(){


        List<String> departmentList=posixGroupManagerService.getDepartmentNameList(ldapConf);
        String groupName=params.groupName;
        String departmentName=params.departmentName;
        String groupDescription=params.groupDescription;
        String gidNumber=params.gidNumber;

        //if gidNumber is null, call the creat function without gidNumber
        if(gidNumber.equals(null)){
            boolean result=posixGroupManagerService.createDepartmentGroup(ldapConf,departmentName,groupName,groupDescription)
            //boolean result = true

            if (result){
                accessLogService.saveLog("Group "+groupName+" has been created in "+departmentName+" in the Ldap server",(String)session.userDetails.username, LogType.Info)
                flash.message="Group "+groupName+" has been created in "+departmentName+" in the Ldap server";
                render(view: "createDepartmentGroup",model:[departmentList:departmentList])
            }
            else {
                accessLogService.saveLog("Group "+groupName+" can't be created ",(String)session.userDetails.username, LogType.Error)
                flash.error="Group "+groupName+" cannot be created. Please check if the organization name has been used";
                render(view: "createDepartmentGroup",model:[departmentList:departmentList])
            }
        }
        // if gidNumber is not null,  check if the gidNumber is a numeric or not
        else if(StringToIntCheck.isNumeric(gidNumber)){
            int gidNum = Integer.parseInt(gidNumber);
            //if gidNum is greater than 8000, it's a acceptable, call the create function with gidNumber
            if(gidNum >= 8000){
                println("In create with gid Number"+gidNumber)
                boolean result=posixGroupManagerService.createDepartmentGroupWithGidNum(ldapConf,departmentName,groupName,gidNumber,groupDescription)
                //boolean result = true

                if (result){
                    accessLogService.saveLog("Group "+groupName+" has been created in "+departmentName+" in the Ldap server",(String)session.userDetails.username, LogType.Info)
                    flash.message="Group "+groupName+" has been created in "+departmentName+" in the Ldap server";
                    render(view: "createDepartmentGroup",model:[departmentList:departmentList])
                }
                else {
                    accessLogService.saveLog("Group "+groupName+" can't be created ",(String)session.userDetails.username, LogType.Error)
                    flash.error="Group "+groupName+" cannot be created !!! Please check if the organization name has been used";
                    render(view: "createDepartmentGroup",model:[departmentList:departmentList])
                }
            }
            else{
                accessLogService.saveLog("Group "+groupName+" can't be created, not an valid gid Number ",(String)session.userDetails.username, LogType.Error)
                flash.error="The gid Number which you entered is not valide, It must be an integer greater than 8000"
                render(view: "createDepartmentGroup",model:[departmentList:departmentList])
            }
        }
        else {
            accessLogService.saveLog("Group "+groupName+" can't be created, not an valid gid Number ",(String)session.userDetails.username, LogType.Error)
            flash.error="The gid Number which you entered is not valide, It must be an integer greater than 8000"
            render(view: "createDepartmentGroup",model:[departmentList:departmentList])
        }




    }

/*creat a view for change user organization */
    def addUserToDepartmentGroupView(){
        List<String> allDepartmentGroups=posixGroupManagerService.getAllDepartmentGroupList(ldapConf)
        Map<String,String> allUsers = memberManagerService.getALLUserNameAndUidList(ldapConf);
        render (view:"addUserToDepartmentGroupView",model:[allDepartmentGroups:allDepartmentGroups,userList:allUsers,userSize:allUsers.size(),groupSize:allDepartmentGroups.size()])
    }


    def addUserToDepartmentGroup(){

        List<String> chosenGroupList= getChosenGroup(params);
        List<String> chosenUserList=getChosenUser(params);


        /*
        * We first test if the group list is not empty, if it's empty, redirect to the add User view with a error message
        * Then we test if the group list is not empty, if it's empty, redirect to the add user view with a error message*/
        if(!chosenGroupList.isEmpty()){
            if(!chosenUserList.isEmpty()){
               /* Here we add all users to all groups selected*/

                //add member to new ldap
                def resultMessage=posixGroupManagerService.addUserListToDepartmentGroupList(ldapConf,chosenUserList,chosenGroupList)
                for(String message:resultMessage){
                    accessLogService.saveLog(message,(String)session.userDetails.username, LogType.Info);
                }


                render (view:"addUserToDepartmentGroupResult", model:[messageList:resultMessage])


            }
            else {
                flash.error="You must chose at least one user";redirect(action:"addUserToDepartmentGroupView")
            }
        }
        else{flash.error="You must chose at least one group to add users";redirect(action:"addUserToDepartmentGroupView")
        }


    }


    def deleteUserFromDepartmentGroup(){
        String groupName=params.groupName;
        List<String> chosenUidList=getChosenUids(params);
        List<String> resultMessageList=new ArrayList<String>();
        LdapGroupManager lgm=null;

        try {
            lgm = new LdapGroupManager(ldapConf);


        } catch (IOException e) {
            log.error("Can't etablish connextion with ldap server. The java exception is "+e.toString());
            resultMessageList.add("Can't etablish connextion with ldap server, Please contact the admin") ;
        }

        for(String userId:chosenUidList) {
            try {
                lgm.removeUserFromGroup(groupName,userId);
                String message= "User "+userId+" has been deleted from "+groupName;
                resultMessageList.add(message)
                accessLogService.saveLog(message,(String)session.userDetails.username, LogType.Info);

            } catch (NamingException e) {
                String resultMessage = "Can't delete user "+userId+" from group "+groupName+". The user is not in the choosen group";
                resultMessageList.add(resultMessage);
                log.error("Can't delete user "+userId+" from group "+groupName+". The java exception is "+e.toString());
            }
        }
        try {
            lgm.closeConnection();
        } catch (NamingException e) {
            log.error("Can't close connextion with ldap server. The java exception is "+e.toString());
            resultMessageList.add("Can't close connextion with ldap server, Please contact the admin") ;
        }

        render(view:"deleteUserFromDepartmentGroupResult", model: [messageList:resultMessageList])
    }

    /************************************************************************
     * *******************Controller methode for project group management****
     * ************************************************************/
    def projectGroupList(){
        List<String> projectList=posixGroupManagerService.getProjectNameList(ldapConf);
        render(view:"projectGroupList",model:[projectList:projectList])
    }
    def showProjectGroup(){
        List<String> projectList=posixGroupManagerService.getProjectNameList(ldapConf);
        String chosenProject=params.get("project");
        List<String> groupList=posixGroupManagerService.getProjectGroupList(ldapConf,chosenProject)
        render(view:"projectGroupList",model:[projectList:projectList,groupList:groupList])
    }

    def showProjectGroupDetails(String id){
      //id is the group name
        Map<String,Object> groupAttrs=posixGroupManagerService.getProjectGroupDetails(ldapConf,id);
        String cn=groupAttrs.get("cn");
        String gidNumber=groupAttrs.get("gidNumber");
        String description=groupAttrs.get("description");
        List<String> memberUidList=groupAttrs.get("memberUidList");
        int userSize = 0;
        if(memberUidList!=null && !memberUidList.isEmpty()){
            userSize = memberUidList.size();
        }

        println(userSize)
        render(view:"showProjectGroupDetails",model:[cn:cn,gidNumber:gidNumber,description:description,memberUidList:memberUidList,userSize:userSize])

    }

    /*render the department group creation form*/
    def createProjectGroup(){
        List<String> projectList=posixGroupManagerService.getProjectNameList(ldapConf);
        render(view:"createProjectGroup",model:[projectList:projectList])
    }

    /*Insert the new department group into ldap*/
    def createProjectGroupLdapEntry(){
        List<String> projectList=posixGroupManagerService.getProjectNameList(ldapConf);
        String groupName=params.groupName;
        String projectName=params.projectName;
        String groupDescription=params.groupDescription;
        String gidNumber=params.gidNumber;

        //if gidNumber is null, call the creat function without gidNumber
        if(gidNumber.equals(null)){
            boolean result=posixGroupManagerService.createProjectGroup(ldapConf,projectName,groupName,groupDescription)

            if (result){
                accessLogService.saveLog("Group "+groupName+" has been created in "+projectName+" in the Ldap server",(String)session.userDetails.username, LogType.Info)
                flash.message="Group "+groupName+" has been created in "+projectName+" in the Ldap server";
                render(view: "createProjectGroup",model:[projectList:projectList])
            }
            else {
                accessLogService.saveLog("Group "+groupName+" can't be created ",(String)session.userDetails.username, LogType.Error)
                flash.error="Group "+groupName+" cannot be created !!! Please check if the organization name has been used";
                render(view: "createProjectGroup",model:[projectList:projectList])
            }
        }
        // if gidNumber is not null,  check if the gidNumber is a numeric or not
        else if(StringToIntCheck.isNumeric(gidNumber)){
            int gidNum = Integer.parseInt(gidNumber);
            //if gidNum is greater than 8000, it's a acceptable, call the create function with gidNumber
            if(gidNum >= 8000){
                boolean result=posixGroupManagerService.createProjectGroupWithGidNum(ldapConf,projectName,groupName,gidNumber,groupDescription)

                if (result){
                    accessLogService.saveLog("Group "+groupName+" has been created in "+projectName+" in the Ldap server",(String)session.userDetails.username, LogType.Info)
                    flash.message="Group "+groupName+" has been created in "+projectName+" in the Ldap server";
                    render(view: "createProjectGroup",model:[projectList:projectList])
                }
                else {
                    accessLogService.saveLog("Group "+groupName+" can't be created ",(String)session.userDetails.username, LogType.Error)
                    flash.error="Group "+groupName+" cannot be created !!! Please check if the organization name has been used";
                    render(view: "createProjectGroup",model:[projectList:projectList])
                }
            }
            //gidNumber is smaller than 8000
            else{
                accessLogService.saveLog("Group "+groupName+" can't be created, not an valid gid Number ",(String)session.userDetails.username, LogType.Error)
                flash.error="The gid Number which you entered is not valide, It must be an integer greater than 8000"
                render(view: "createProjectGroup",model:[projectList:projectList])
            }

        }
          // gidNumber is not integer
        else {
            accessLogService.saveLog("Group "+groupName+" can't be created, not an valid gid Number ",(String)session.userDetails.username, LogType.Error)
            flash.error="The gid Number which you entered is not valide, It must be an integer greater than 8000"
            render(view: "createProjectGroup",model:[projectList:projectList])
        }





    }


    def addUserToProjectGroupView(){
        List<String> allProjectGroups=posixGroupManagerService.getAllProjectGroupList(ldapConf)
        Map<String,String> allUsers = memberManagerService.getALLUserNameAndUidList(ldapConf);
        render (view:"addUserToProjectGroupView",model:[allProjectGroups:allProjectGroups,userList:allUsers,userSize:allUsers.size(),groupSize:allProjectGroups.size()])
    }

    def deleteUserFromProjectGroup(){
        String groupName=params.groupName;
        List<String> chosenUidList=getChosenUids(params);
        List<String> resultMessageList=new ArrayList<String>();
        LdapProjectManager lgm=null;

        try {
            lgm = new LdapProjectManager(ldapConf);


        } catch (IOException e) {
            log.error("Can't etablish connextion with ldap server. The java exception is "+e.toString());
            resultMessageList.add("Can't etablish connextion with ldap server, Please contact the admin") ;
        }

        for(String userId:chosenUidList) {
            try {
                lgm.removeUserFromGroup(groupName,userId);
                String message= "User "+userId+" has been deleted from "+groupName;
                resultMessageList.add(message)
                accessLogService.saveLog(message,(String)session.userDetails.username, LogType.Info);

            } catch (NamingException e) {
                String resultMessage = "Can't delete user "+userId+" from group "+groupName+". The user is not in the choosen group";
                resultMessageList.add(resultMessage);
                log.error("Can't delete user "+userId+" from group "+groupName+". The java exception is "+e.toString());
            }
        }
        try {
            lgm.closeConnection();
        } catch (NamingException e) {
            log.error("Can't close connextion with ldap server. The java exception is "+e.toString());
            resultMessageList.add("Can't close connextion with ldap server, Please contact the admin") ;
        }


        render(view:"deleteUserFromProjectGroupResult", model: [messageList:resultMessageList])
    }

    def addUserToProjectGroup(){

        List<String> chosenGroupList= getChosenGroup(params);
        List<String> chosenUserList=getChosenUser(params);


        /*
        * We first test if the group list is not empty, if it's empty, redirect to the add User view with a error message
        * Then we test if the group list is not empty, if it's empty, redirect to the add user view with a error message*/
        if(!chosenGroupList.isEmpty()){
            if(!chosenUserList.isEmpty()){
                /* Here we add all users to all groups selected*/

                //add member to new ldap
                def resultMessage=posixGroupManagerService.addUserListToProjectGroupList(ldapConf,chosenUserList,chosenGroupList)
                for(String message:resultMessage){
                    accessLogService.saveLog(message,(String)session.userDetails.username, LogType.Info);
                }


                render (view:"addUserToProjectGroupResult", model:[messageList:resultMessage])


            }
            else {
                flash.error="You must chose at least one user";redirect(action:"addUserToProjectGroupView")
            }
        }
        else{flash.error="You must chose at least one group to add users";redirect(action:"addUserToProjectGroupView")
        }


    }

    private List<String> getChosenUids(Map<String,Objects> params){
        /*
       * get all the chosen group list */
        List<String> chosenUidList=new ArrayList<String>();
        int groupSize=Integer.parseInt((String)params.userSize);
        for(int i=0;i<groupSize;i++){
            String index="user_"+Integer.toString(i);
            String uid=params.get(index);
            if (uid!=null){chosenUidList.add(uid);}
        }
        return chosenUidList;
    }

    private List<String> getChosenGroup(Map<String,Object> params){
        /*
        * get all the chosen group list */
        def List<String> chosenGroupList=new ArrayList<String>();
        def groupSize=Integer.parseInt(params.groupSize);
        for(int i=0;i<groupSize;i++){
            def String index="group_"+Integer.toString(i);
            def groupName=params.get(index);
            if (groupName!=null){chosenGroupList.add(groupName);}
        }
        return chosenGroupList;
    }

    private List<String> getChosenUser(Map<String,Object> params){
        def List<String> chosenUserList=new ArrayList<String>();
        def userSize=Integer.parseInt(params.userSize);

        for(int i=0;i<userSize;i++){
            def String index="user_"+Integer.toString(i);
            def uid=params.get(index);
            if(uid!=null){chosenUserList.add(uid);
            }
        }
        return chosenUserList;
    }
}

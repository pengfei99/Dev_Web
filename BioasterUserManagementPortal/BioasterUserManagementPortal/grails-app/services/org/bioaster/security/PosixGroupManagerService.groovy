package org.bioaster.security

import grails.transaction.Transactional
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.config.LdapArchitectureInfo
import org.bioaster.security.core.LdapGroupSearchController
import org.bioaster.security.ldapManager.LdapGroupManager
import org.bioaster.security.ldapManager.LdapProjectManager

import javax.naming.NamingException
import javax.naming.directory.Attribute
import javax.naming.directory.Attributes

@Transactional
class PosixGroupManagerService {

/***********************************************************************************************************
 * ***********************service method for group management*********************************************
 ***********************************************************************************************************/

    def List<String> getDepartmentNameList(ILdapServerConnectorConfig ldapConf){
        LdapGroupManager lgm=new LdapGroupManager(ldapConf);
        def departmentName=lgm.getDepartmentName();
        lgm.closeConnection();
        return departmentName;
    }

    def List<String> getDepartmentGroupList(ILdapServerConnectorConfig ldapConf, String departmentName){
        LdapGroupManager lgm=new LdapGroupManager(ldapConf);
         def groupList=lgm.getDepartmentGroupList(departmentName)
        lgm.closeConnection();
        return groupList
    }

    /*this method takes a groupName and return a map of his attributes*/
    def Map<String,Object> getDepartmentGroupDetails(ILdapServerConnectorConfig ldapConf,String groupName){

      return getGroupDetails(ldapConf,groupName,LdapArchitectureInfo.GROUP_DIR);
    }

    def List<String> getAllDepartmentGroupList(ILdapServerConnectorConfig ldapConf){
        List<String> groupList=new ArrayList<String>();
        try {
            LdapGroupManager lgm=new LdapGroupManager(ldapConf);
            List<String> departmentList = lgm.getDepartmentName();
            for(String departmentName:departmentList){
                groupList.addAll(lgm.getDepartmentGroupList(departmentName));
            }
        } catch (IOException e) {
           log.error("Can't get all department group List! JAVA EXCEPTION "+e.toString())
        }
        return groupList
    }

    def boolean createDepartmentGroup(ILdapServerConnectorConfig ldapConf,String departmentName, String groupName, String description){
        //  if the gidNumber is empty, use the method createNew

        def result=false;
        try {
            LdapGroupManager lgm=new LdapGroupManager(ldapConf);
            result=lgm.createNewDepartmentGroup(groupName,departmentName,description);
            lgm.closeConnection();
        } catch (IOException e) {
            log.error("can't create new department group in "+departmentName+" department. JAVA EXCEPTION"+e.toString())

        } catch (NamingException e) {
            log.error("can't create new department group in "+departmentName+" department. JAVA EXCEPTION"+e.toString())

        }
        return result;
    }

    def boolean createDepartmentGroupWithGidNum(ILdapServerConnectorConfig ldapConf,String departmentName, String groupName, String gidNumber, String description){
        //  if the gidNumber is empty, use the method createNew

        def result=false;
        try {
            LdapGroupManager lgm=new LdapGroupManager(ldapConf);
            result=lgm.createNewDepartmentGroupWithGidNum(groupName,gidNumber,departmentName,description)
            lgm.closeConnection();
        } catch (IOException e) {
            log.error("can't create new department group in "+departmentName+" department. JAVA EXCEPTION"+e.toString())

        } catch (NamingException e) {
            log.error("can't create new department group in "+departmentName+" department. JAVA EXCEPTION"+e.toString())

        }
        return result;
    }

    def boolean addUserToDepartmentGroup(ILdapServerConnectorConfig ldapConf,String groupName,String uid){

        boolean result=false;
        try {
            LdapGroupManager lgm=new LdapGroupManager(ldapConf);

            result=lgm.addUserToGroup(groupName,uid);

            lgm.closeConnection();
        } catch (IOException e) {
            log.error("can't add user to department group in"+groupName+". JAVA EXECEPTION: "+e.toString())
        } catch (NamingException e) {
            log.error("can't add user to department group in"+groupName+". JAVA EXECEPTION: "+e.toString())
        }
        return result;

    }
    def List<String> addUserListToDepartmentGroupList(ILdapServerConnectorConfig ldapConf,List<String> chosenUserList,List<String> chosenGroupList) throws IOException{
         List<String> resultMessage=new ArrayList<>();
        try{

            /*Here we add all users to all groups selected*/

            for(int i=0;i<chosenGroupList.size();i++){
                String groupName=chosenGroupList.get(i);
                for(int j=0;j<chosenUserList.size();j++){
                    String uid=chosenUserList.get(j);
                    boolean result=  addUserToDepartmentGroup(ldapConf,groupName,uid);
                    if(result){

                        resultMessage.add("User "+uid+" has been added to group "+groupName)
                    }
                    else {
                        resultMessage.add("User "+uid+" cannot been added to group "+groupName+". Please check if the user is already in the group")
                    }
                }
            }
        }
        catch (IOException e){
            throw e;
        }

        return resultMessage;
    }


    /************************************************************************************************************
    * ***********************************Service method for project group management ***************************
    * ********************************************************************************************************/

    def List<String> getProjectNameList(ILdapServerConnectorConfig ldapConf){
        LdapProjectManager lpm=new LdapProjectManager(ldapConf);
        def departmentName=lpm.getProjectName();
        lpm.closeConnection();
        return departmentName;
    }

    def List<String> getProjectGroupList(ILdapServerConnectorConfig ldapConf, String projectName){
        LdapProjectManager lpm=new LdapProjectManager(ldapConf);
        def groupList=lpm.getProjectGroupList(projectName)
        lpm.closeConnection();
        return groupList
    }

    def List<String> getAllProjectGroupList(ILdapServerConnectorConfig ldapConf){
        List<String> groupList=new ArrayList<String>();
        try {
            LdapProjectManager lgm=new LdapProjectManager(ldapConf);
            List<String> projectList = lgm.getProjectName();
            for(String projectName:projectList){
                groupList.addAll(lgm.getProjectGroupList(projectName));
            }
        } catch (IOException e) {
            log.error("Can't get all project group List! JAVA EXCEPTION "+e.toString())
        }
        return groupList
    }

    def Map<String,Object> getProjectGroupDetails(ILdapServerConnectorConfig ldapConf,String groupName){
        return getGroupDetails(ldapConf,groupName,LdapArchitectureInfo.PROJECT_DIR);
    }

    def boolean createProjectGroup(ILdapServerConnectorConfig ldapConf,String projectName,String groupName,String description){
        boolean result=false;
        try {
            LdapProjectManager lpm=new LdapProjectManager(ldapConf);
            result=lpm.createNewProjectGroup(groupName,projectName,description);
            lpm.closeConnection();
        } catch (IOException e) {
            log.error("can't create new project group in"+projectName+". JAVA EXECEPTION: "+e.toString())
        } catch (NamingException e) {
            log.error("can't create new project group in"+projectName+". JAVA EXECEPTION: "+e.toString())
        }
        return result;
    }

    def boolean createProjectGroupWithGidNum(ILdapServerConnectorConfig ldapConf,String projectName,String groupName,String gidNumber,String description){
        boolean result=false;
        try {
            LdapProjectManager lpm=new LdapProjectManager(ldapConf);
            result=lpm.createNewProjectGroupWithGidNum(groupName,gidNumber,projectName,description);
            lpm.closeConnection();
        } catch (IOException e) {
            log.error("can't create new project group in"+projectName+". JAVA EXECEPTION: "+e.toString())
        } catch (NamingException e) {
            log.error("can't create new project group in"+projectName+". JAVA EXECEPTION: "+e.toString())
        }
        return result;
    }

    def boolean addUserToProjectGroup(ILdapServerConnectorConfig ldapConf,String groupName,String uid){
            boolean result = false;
            try {
                LdapProjectManager lpm = new LdapProjectManager(ldapConf);
                result = lpm.addUserToGroup(groupName, uid);
                lpm.closeConnection();
            } catch (IOException e) {
                log.error("can't add user to project group in" + groupName + ". JAVA EXECEPTION: " + e.toString())
            } catch (NamingException e) {
                log.error("can't add user to project group in" + groupName + ". JAVA EXECEPTION: " + e.toString())
            }
            return result;
    }

    def List<String> addUserListToProjectGroupList(ILdapServerConnectorConfig ldapConf,List<String> chosenUserList,List<String> chosenGroupList) throws IOException{
        List<String> resultMessage=new ArrayList<>();
        try{

            /*Here we add all users to all groups selected*/

            for(int i=0;i<chosenGroupList.size();i++){
                String groupName=chosenGroupList.get(i);
                for(int j=0;j<chosenUserList.size();j++){
                    String uid=chosenUserList.get(j);
                    boolean result=  addUserToProjectGroup(ldapConf,groupName,uid);
                    if(result){

                        resultMessage.add("User "+uid+" has been added to group "+groupName)
                    }
                    else {
                        resultMessage.add("User "+uid+" cannot been added to group "+groupName+". Please check if the user is already in the group")
                    }
                }
            }
        }
        catch (IOException e){
            throw e;
        }

        return resultMessage;
    }

    /*Generic method for getting group details*/
    def Map<String,Object> getGroupDetails(ILdapServerConnectorConfig ldapConf,String groupName,String searchBase){

        LdapGroupSearchController lgsc=new LdapGroupSearchController(ldapConf);
        Map<String,Object> groupAttrs=new HashMap<String,Object>();

        String[] returnAttrs=new String[4];
        returnAttrs[0]="cn";
        returnAttrs[1]="gidNumber";
        returnAttrs[2]="description";
        returnAttrs[3]="memberUid";
        try {
            Attributes attrs = lgsc.getGroupAttrs(groupName, returnAttrs,searchBase);
            lgsc.closeConnection();
            for(int i=0;i<3;i++){
                groupAttrs.put(returnAttrs[i],attrs.get(returnAttrs[i]).get(0).toString());
            }
            Attribute memberUid = attrs.get("memberUid");
            List<String> memberUidList=null;
            if(memberUid!=null) {
            memberUidList=new ArrayList<String>();
            for(int i=0;i<memberUid.size();i++){
                memberUidList.add((String) memberUid.get(i));
            }
            }
            groupAttrs.put("memberUidList",memberUidList);

        } catch (NamingException e) {
            log.error(e.toString());
        }
        return groupAttrs;
    }


}

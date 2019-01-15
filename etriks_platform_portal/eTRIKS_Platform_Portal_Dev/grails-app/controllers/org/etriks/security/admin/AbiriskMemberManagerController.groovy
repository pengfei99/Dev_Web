package org.etriks.security.admin
import org.etriks.admin.dashboard.ExportUser
import org.etriks.security.Ldap.MemberManagerService
import pl.touk.excel.export.WebXlsxExporter

class AbiriskMemberManagerController {

    def projectName="abirisk";
    def MemberManagerService memberManagerService;
   /* def AbiriskLdapConnectorConfig abiriskLdap=new AbiriskLdapConnectorConfig();*/





    def index() {}


    /*
    * This action shows all users which store in the ldap server under ou=Users,dc=etriks,dc=eu
    * */

    def userAccountList(){

/*
*New ldap archi
*/
        List<String> membersList;
        membersList=memberManagerService.getProjectUserAccountNameList(projectName)
        def userAccountTotal=membersList.size()
      //  println("Size :"+members.size()+" Content :"+members.toString())


        /*
        * Old ldap
        * */
        /*//Create an instance of userAccountManager
        def	UserAccountManager accReg = new UserAccountManager(abiriskLdap,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");

        //get All users cn.
        NamingEnumeration<NameClassPair> list = accReg.getAllUsers();

        List<String> userCNList=new ArrayList<String>();


        while (list.hasMore()){
            //Eliminate the "cn="
            String cn=list.next().getName();
            String name=cn.substring(3);

            userCNList.add(name);

        }
        //sort the list by alphabe
        Collections.sort(userCNList);
        accReg.closeContext();
        def userAccountTotal=userCNList.size()
        //  println userAccountTotal*/
        render(view:"userAccountList",model:[userCNList: membersList,userAccountTotal:userAccountTotal])
    }

    //This action shows some information of a specific user account.
    def showUserDetails(String id){

        /*
        *
        * New
        * */

        Map<String, Object> userDetails;
        userDetails=memberManagerService.getUserDetails(projectName,id);
        if(userDetails.get("isEmpty")==true){
            flash.message="The user account which you looking for does not exist or has been deleted"
            render(view:"showUserDetails", model : [pcn:"unknow",puid:"unknow",mail:"unknow"])
        }
        else{
            String mail=(String)userDetails.get("mail");
            String pcn= (String)userDetails.get("pcn");
            String puid=(String)userDetails.get("puid");
            String organizationName=(String)userDetails.get("organizationName");
            Map<String,String> userGroups=(Map<String,String>)userDetails.get("userGroups");
            render(view:"showUserDetails", model : [pcn:pcn,puid:puid,mail:mail,organizationName:organizationName,userGroups:userGroups])
        }
       /*  String dn="cn="+id+",ou=Users,dc=etriks,dc=eu";
        def accountManager=new UserAccountsManager(pubLdap);
        def groupManager=new UserProjectGroupManager(pubLdap);
        def orgManager=new UserWPAndOrganizationManager(pubLdap);
        Map<String,List<Object>> userAttrs=accountManager.getAllAttributesAsObjectByEntryDN(dn);

        if(userAttrs.isEmpty()){
            flash.message="The user account which you looking for does not exist or has been deleted"
            render(view:"showUserDetails", model : [pcn:"unknow",puid:"unknow",mail:"unknow"])
        }
        else{
            String mail=userAttrs.get("mail").get(0).toString();
            String pcn= userAttrs.get("cn").get(0).toString();
            String puid=userAttrs.get("uid").get(0).toString();
            String pgid=userAttrs.get("gidNumber").get(0).toString();
            //get the organization name of the selected user
            String organizationName=orgManager.getOrganizationNameByGID(pgid);

            Map<String,String> userGroups=groupManager.getAllUserPosixGroupsInProject(puid,projectName);

        }
        accountManager.closeContext();
        groupManager.closeContext();
        orgManager.closeContext();*/
        /*
        * Old
        * */

         /*//Create an instance of userAccountManager
        def	UserAccountManager accReg = new UserAccountManager(abiriskLdap,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");
        //Create an instance of groupAccountManager
        def UserGroupManager grpReg = new UserGroupManager(abiriskLdap,"dc=etriks,dc=eu");
        //get all the organization name and gid mapping
        def Map<String,String> organizationGID=grpReg.getAllOrganizations();
        //Build the dn of the requested entry
        String dn="cn="+id+",ou=Users,dc=etriks,dc=eu";
        Map<String, List<String>> attrs = accReg.getAllAttributesByEntryDN(dn);

        if(attrs.isEmpty()){
            flash.message="The user account which you looking for does not exist or has been deleted"
            render(view:"showUserDetails", model : [pcn:"unknow",puid:"unknow",mail:"unknow"])
        }
        else {

            String mail=attrs.get("mail").get(0);

            String pcn=attrs.get("cn").get(0);
            String puid=attrs.get("uid").get(0);
            //get the Organization gid number of the selected user
            String pgid=attrs.get("gidNumber").get(0);
            //get the organization name of the selected user
            String organizationName=organizationGID.get(pgid);

            Map<String,String> userGroups=grpReg.getUserGroupsByUID(puid);
            accReg.closeContext();
            grpReg.closeContext();*/

    }

    def userMailList(){
        /*
        * New
        * */

        String mailListStr=memberManagerService.getUserMailList(projectName).toString();
        render(view:"userMailList", model:[mailListStr:mailListStr])

        /*
        * Old
        * */
       /* //Create an instance of userAccountManager
        def	UserAccountManager accReg = new UserAccountManager(abiriskLdap,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");
        def List<String> mailList=new ArrayList<String>();
        NamingEnumeration<NameClassPair> list = accReg.getAllUsers();


        while (list.hasMore()){
            String cn=list.next().getName();
            String dn=cn+",ou=Users,dc=etriks,dc=eu";

            Map<String, List<String>> attrs = accReg.getAllAttributesByEntryDN(dn);
            String mail=attrs.get("mail").get(0);

            mailList.add(mail);

        }
        String mailListStr=mailList.toListString()
        accReg.closeContext();
        render(view:"userMailList", model:[mailListStr:mailListStr])*/
    }
    //this action shows all registered user name, uid and mail
    def userMailListWithUserName(){
        /*new*/
        List<ExportUser> userList;
        userList=memberManagerService.getUserMailListWithUserName(projectName);
        flash.userList=userList;
        render(view:"userMailList", model:[userList: userList])

        /*old*/

      /*  //Create an instance of userAccountManager
        def	UserAccountManager accReg = new UserAccountManager(abiriskLdap,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");
        def  List<ExportUser> userList=new ArrayList<ExportUser>();

        NamingEnumeration<NameClassPair> list = accReg.getAllUsers();


        while (list.hasMore()){
            String cn=list.next().getName();

            String dn=cn+",ou=Users,dc=etriks,dc=eu";

            Map<String, List<String>> attrs = accReg.getAllAttributesByEntryDN(dn);

            String mail=attrs.get("mail").get(0);

            //elimate the "cn="
            String name=cn.substring(3);
            String uid=attrs.get("uid").get(0);
            //create the instance of exportUser
            ExportUser exportUser=new ExportUser(name,mail,uid);
            //add the user to the list
            userList.add(exportUser);

        }
        // String mailListStr=mailList.toMapString()
        flash.userList=userList;
        accReg.closeContext();
        render(view:"userMailList", model:[userList:userList])*/

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
package org.etriks.security.Ldap
import org.etriks.admin.dashboard.ExportUser
import org.etriks.security.ldapnew.entry.UserAccountsManager
import org.etriks.security.ldapnew.entry.UserProjectGroupManager
import org.etriks.security.ldapnew.entry.UserWPAndOrganizationManager
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig

import javax.naming.NameClassPair
import javax.naming.NamingEnumeration
import javax.naming.NamingException

class MemberManagerService {
    def PubLdapConnectorConfig pubLdap=new PubLdapConnectorConfig();

    def List<String> getALLAccountNameList(){
        def accountManager = new UserAccountsManager(pubLdap);
        List<String> userNameList=new ArrayList<String>();
        NamingEnumeration<NameClassPair> users = accountManager.getAllUsers();
        try {
            while (users.hasMore()){
                NameClassPair user = users.next();
                String userCn=user.getName();
                String memberDN= user.getNameInNamespace();
                String userName=userCn.substring(3);
         userNameList.add(userName);
            }
        } catch (NamingException e) {
            log.error("Unable to get user account information!!! Java exception: "+e.toString());
        }


        accountManager.closeContext();

        return userNameList;
    }

    def List<String> getProjectUserAccountNameList(String projectName) {
        def UserProjectGroupManager groupManager=new UserProjectGroupManager(pubLdap);

        List<String> membersList=groupManager.getAllProjectMembersName(projectName);

        groupManager.closeContext();

        return membersList;
    }

    def Map<String,Object> getUserDetails(String projectName,String userName){
        def Map<String,Object> userDetails=new HashMap<String,Object>();
        String dn="cn="+userName+",ou=Users,dc=etriks,dc=eu";
        def accountManager=new UserAccountsManager(pubLdap);
        def groupManager=new UserProjectGroupManager(pubLdap);
        def orgManager=new UserWPAndOrganizationManager(pubLdap);
        Map<String,List<Object>> userAttrs=accountManager.getAllAttributesAsObjectByEntryDN(dn);

        if(userAttrs.isEmpty()){
           userDetails.put("isEmpty",true)
        }
        else{
            userDetails.put("isEmpty",false)
            String mail=userAttrs.get("mail").get(0).toString();
            String pcn= userAttrs.get("cn").get(0).toString();
            String puid=userAttrs.get("uid").get(0).toString();
            String pgid=userAttrs.get("gidNumber").get(0).toString();
            //get the organization name of the selected user
            String organizationName=orgManager.getOrganizationNameByGID(pgid);

            Map<String,String> userGroups=groupManager.getAllUserPosixGroupsInProject(puid,projectName);
            userDetails.put("pcn",pcn);
            userDetails.put("puid",puid);
            userDetails.put("mail",mail);
            userDetails.put("organizationName",organizationName);
            userDetails.put("userGroups",userGroups);
        }
        accountManager.closeContext();
        groupManager.closeContext();
        orgManager.closeContext();
        return userDetails;
    }

    def List<String> getUserMailList(String projectName){
        def groupManager=new UserProjectGroupManager(pubLdap);
        def accountManager = new UserAccountsManager(pubLdap);
        def List<String> mailList=new ArrayList<String>();

        List<String> membersDNList=groupManager.getAllProjectMembersDN(projectName);
        for (String memberDN:membersDNList){
            mailList.add(accountManager.getUserMailByDn(memberDN))
        }
        String mailListStr=mailList.toListString()
        groupManager.closeContext();
        accountManager.closeContext();
        return mailList;
    }

    def List<ExportUser> getUserMailListWithUserName(String projectName){
        def groupManager=new UserProjectGroupManager(pubLdap);
        def accountManager = new UserAccountsManager(pubLdap);
        def List<ExportUser> userList=new ArrayList<ExportUser>();

        List<String> membersDNList=groupManager.getAllProjectMembersDN(projectName);
        for (String memberDN:membersDNList){
            String name=memberDN.substring(3,memberDN.indexOf(","));
            String mail=accountManager.getUserMailByDn(memberDN);
            String uid=accountManager.getUid(memberDN);
            userList.add(new ExportUser(name,mail,uid))
        }

        groupManager.closeContext();
        accountManager.closeContext();

        return userList;
    }

   def Map<String,String> getPojectUserNameAndUidList(String projectName){
       Map<String,String> allUsers=new HashMap<String, String>();
        def accountManager = new UserAccountsManager(pubLdap);
       def groupManager=new UserProjectGroupManager(pubLdap);

       List<String> membersList=groupManager.getAllProjectMembersDN(projectName);
       for(String member:membersList){
           String userName=member.substring(3,member.indexOf(","));
           String userId=accountManager.getUid(member);
           allUsers.put(userName,userId);
       }
       accountManager.closeContext();
       groupManager.closeContext();

        return allUsers;

    }
    def List<ExportUser> getAllUsersFromLdap(){
        def accountManager = new UserAccountsManager(pubLdap);
        def groupManager=new UserProjectGroupManager(pubLdap);
        def List<ExportUser> userList=new ArrayList<ExportUser>();

        NamingEnumeration<NameClassPair> users = accountManager.getAllUsers();
        try {
            while (users.hasMore()){
                NameClassPair user = users.next();
                String userCn=user.getName();
                String memberDN= user.getNameInNamespace();
                String userName=userCn.substring(3);
                String mail=accountManager.getUserMailByDn(memberDN);
                String uid=accountManager.getUid(memberDN);
                userList.add(new ExportUser(userName,mail,uid));
            }
        } catch (NamingException e) {
           log.error("Unable to get user account information!!! Java exception: "+e.toString());
        }


        groupManager.closeContext();
        accountManager.closeContext();

        return userList;
    }
}

package org.etriks.security.ldapnew.entry;


import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 1/18/16.
 *
 * <h1>User project group manager<h1/>
 *
 * <p>
 * This class manage all user project group which can be a posix group(ssh access for vm) or a group of names(web application access)
 * <p/>
 */
public class UserProjectGroupManager extends LdapEntriesManager{

    //List of the entry class
    private List<String> userEntryObjClasses=new ArrayList<String>();

    private String projectsDN;

    public UserProjectGroupManager(LdapConnectorConfig ldapServerInfo) throws IOException {
        super(ldapServerInfo);
        projectsDN=this.ldapProperties.getProjectsDN();
        this.loadGroupEntryObjClasses();
    }
    private void loadGroupEntryObjClasses(){

        userEntryObjClasses.add("posixGroup");
        userEntryObjClasses.add("top");
    }
    public void createVMGroup(String projectName,String groupName,String description) throws NamingException, IOException {
        //the default gidNumber of each project is defined in LdapConfig.properties
        int gidBase=this.ldapProperties.getProjectGroupGidNumBase(projectName);
        String gidNumber=Integer.toString(gidBase+999);
        //the parent dir of the project posix_groups
        String entryParentDir="ou=posix_groups,ou="+projectName+","+projectsDN;
        //object classs of the group
        List<String> entryObjClasses=new ArrayList<String>();
        //attributes of the group
        Map<String, String> entryAttributes=new HashMap<String, String>();

        entryAttributes.put("cn", projectName+"_"+groupName);
        //The gidNumber needs to be checked
        Map<String,String> allGroups = this.getAllProjectPosixGroup(projectName);
        // System.out.println("group info :"+allGroups.toString());
        //All group gid starts with 5000
        if(allGroups==null){gidNumber=Integer.toString(gidBase);}
        else{
        for(int i=gidBase;i<gidBase+allGroups.size()+1;i++){
            // System.out.println("in for loop"+i);
            if (!allGroups.containsKey(Integer.toString(i))) {
                gidNumber=Integer.toString(i);
                //System.out.println("in if gid: "+gidNumber);
                break;}
        }
        }
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        this.saveEntry(userEntryObjClasses, entryAttributes, entryParentDir);

    }

    /*This method return all posix groups of a project which located under ou=posix_groups,ou=projectName,ou=Projects,dc=etriks,dc=eu*/
    public Map<String,String> getAllProjectPosixGroup(String projectName) throws NamingException {
        Map<String,String> posixGroups=new HashMap<String, String>();
        String posixGroupsDN="ou=posix_groups,ou="+projectName+","+projectsDN;
        NamingEnumeration<NameClassPair> allGroups=this.getAllEntries(posixGroupsDN);
        while(allGroups.hasMore()){
            String groupCN=allGroups.next().getName();
            String groupDN=groupCN+","+posixGroupsDN;
            String gidNumber=this.getGidNumber(groupDN);
            String groupName=groupCN.substring(3);
            posixGroups.put(gidNumber,groupName);
        }
        return posixGroups;
    }

    /*This method returns all the groups which user belongs to*/
    public Map<String,String> getUserProjectPosixGroupsByUID(String projectName,String uid) throws NamingException {
        Map<String,String> userGroups=new HashMap<String, String>();
        String projectPosixGroupsDN="ou=posix_groups,ou="+projectName+","+projectsDN;
        NamingEnumeration<NameClassPair> groups = this.getAllEntries(projectPosixGroupsDN);

            while(groups.hasMore()){
                String groupCN=groups.next().getName();
                String groupDN=groupCN + ","+projectPosixGroupsDN;
                String gidNumber = this.getGidNumber(groupDN);
                Map<String, List<String>> allEntry = this.getAllAttributesByEntryDN(groupDN);
                List<String> memberUids = allEntry.get("memberUid");

              /*  System.out.println(groupCN+":"+gidNumber);*/
                if(memberUids!=null){
                    for(int i=0;i<memberUids.size();i++){
                        String memberUid=memberUids.get(i);
                        if(memberUid.equals(uid)){
                            String groupName=groupCN.substring(3);
                            userGroups.put(gidNumber,groupName);
                        }
                        // System.out.println("has member :"+memberUid);
                    }}

            }
            // System.out.println(uid + " has roles :" + userGroups.toString());

        return userGroups;
    }

    public boolean addProjectPosixGroupMember(String groupName,String projectName,String userId){


        String projectPosixGroupDN="cn="+groupName+","+"ou=posix_groups,ou="+projectName+","+projectsDN;

        return	this.insertNewAttribute(projectPosixGroupDN, "memberUid",userId);
    }

    public boolean addProjectGroupOfNamesMember(String groupName,String applicationName,String projectName,String userDN){
        String projectGroupsOfNamesDN="cn="+groupName+",ou="+applicationName+","+"ou=groups_of_names,ou="+projectName+","+projectsDN;
        return this.insertNewAttribute(projectGroupsOfNamesDN,"member",userDN);
    }

    public HashMap<String, Map<String, String>> getAllUserPosixGroups(String userId) throws NamingException {
        HashMap<String,Map<String,String>> allPosixGroups=new HashMap<String,Map<String,String>>();
        ArrayList<String> projectList = (ArrayList<String>)this.getAllProjectsName();
        for(int i=0;i<projectList.size();i++){
            String projectName=projectList.get(i);
            Map<String, String> groups = this.getUserProjectPosixGroupsByUID(projectName, userId);
            allPosixGroups.put(projectName,groups);
        }
        return allPosixGroups;
    }

    public List<String> getAllProjectsName() throws NamingException {
        ArrayList<String> projectNameList=new ArrayList<String>();
        NamingEnumeration<NameClassPair> projectsCN = this.getAllEntries(projectsDN);
        if (projectsCN!=null){
            while(projectsCN.hasMore()){

                String projectName=projectsCN.next().getName().substring(3);
                projectNameList.add(projectName);
            }
        }

         return projectNameList;

    }

    /*
    *
    *This method removes a posix group of a user, it actually removes the attribute memberuid:uid from a given groupDN
     * it returns true if the remove is successful, else returns false
    *@arg String uid user id in ldap
    *@arg String groupDN, the dn of posix group
    *@return boolean, the result of the remove action
    */
    public boolean deleteUserFromPosixGroup(String uid,String groupDN){
        return this.deleteAttribute(groupDN,"memberUid",uid);
    }
    public Map<String,String> getAllUserPosixGroupsInProject(String uid,String projectName) throws NamingException {
        return this.getAllUserPosixGroups(uid).get(projectName);
    }

    public boolean deleteAllUserGroupInProject(String projectName,String userId) throws NamingException {
        boolean result=false;
        Map<String, String> userGroups = this.getAllUserPosixGroupsInProject(userId, projectName);
        for (Map.Entry<String,String> entry:userGroups.entrySet()){
            String groupCN=entry.getValue();
            String groupDN="cn="+groupCN+",ou=posix_groups,ou="+projectName+",ou=Projects,dc=etriks,dc=eu";
            if(!this.deleteUserFromPosixGroup(userId,groupDN)){
                result=false;
            }
        }
        return result;
    }

    public boolean deleteAllUserGroup(String uid) throws NamingException {
        List<String> projectList = this.getAllProjectsName();
        for(String projectName:projectList){
           this.deleteAllUserGroupInProject(projectName,uid);
           // System.out.println("Project name: "+projectName);
        }
        return false;
    }

    public List<String> getAllProjectMembersDN(String projectsName){
        List<String> memberList=new ArrayList<String>();
        String projectDn="cn="+projectsName+"_member,ou=portal,ou=groups_of_names,ou="+projectsName+",ou=Projects,dc=etriks,dc=eu";
        Map<String,List<Object>> attrs= this.getAllAttributesAsObjectByEntryDN(projectDn);
        List<Object> members=attrs.get("member");
for (int i=0;i<members.size();i++){
    memberList.add(members.get(i).toString());
}
        return memberList;
    }

    public List<String> getAllProjectMembersName(String projectsName){
        List<String> memberNameList=new ArrayList<String>();
        List<String> memberCNList = this.getAllProjectMembersDN(projectsName);
        for(int i=0;i<memberCNList.size();i++){
            String memberCN = memberCNList.get(i);
            String memberName=memberCN.substring(3,memberCN.indexOf(","));
            memberNameList.add(memberName);
        }
        return memberNameList;
    }
}

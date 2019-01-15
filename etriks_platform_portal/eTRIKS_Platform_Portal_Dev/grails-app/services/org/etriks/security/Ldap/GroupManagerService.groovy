package org.etriks.security.Ldap
import org.etriks.security.ldapnew.entry.UserProjectGroupManager
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig

class GroupManagerService {


    //New archi ldap Server connection config instance
    def PubLdapConnectorConfig pubLdap=new PubLdapConnectorConfig();

    def createVMGroupInNewLdap(String projectName,String groupName,String groupDescription){

        try{
            UserProjectGroupManager upg=new UserProjectGroupManager(pubLdap);

            upg.createVMGroup(projectName,groupName,groupDescription);

            upg.closeContext();
        }
        catch (IOException e){
            throw e;
        }
    }

    def List<String> addMemberToPosixGroupInNewLdap(String projectName,List<String> chosenUserList,List<String> chosenGroupList) throws IOException{
        def List<String> resultMessage=new ArrayList<>();
        try{
            UserProjectGroupManager upg=new UserProjectGroupManager(pubLdap);
            /*Here we add all users to all groups selected*/

            for(int i=0;i<chosenGroupList.size();i++){
                def groupName=chosenGroupList.get(i);
                def groupCN=groupName;
                for(int j=0;j<chosenUserList.size();j++){
                    def uid=chosenUserList.get(j);
                    def result=  upg.addProjectPosixGroupMember(groupCN,projectName,uid);
                    if(result){

                        resultMessage.add("User "+uid+" has been added to group "+groupName)
                    }
                    else {
                        resultMessage.add("User "+uid+" cannot been added to group "+groupName+". Please check if the user is already in the group")
                    }
                }
            }
            upg.closeContext();
        }
        catch (IOException e){
            throw e;
        }

        return resultMessage;
    }

    def Map<String,String> getAllPosixGroups(String projectName){
        UserProjectGroupManager upg=new UserProjectGroupManager(pubLdap);
        def allGroups=upg.getAllProjectPosixGroup(projectName);
        upg.closeContext();
        return allGroups;
    }

    def List<String> getAllPosixGroupNames(String projectName){
        UserProjectGroupManager upg=new UserProjectGroupManager(pubLdap);
        List<String> sortGroupNames=new ArrayList<String>(upg.getAllProjectPosixGroup(projectName).values())
        Collections.sort(sortGroupNames);
        upg.closeContext();
        return sortGroupNames
    }

    def Map<String,Object> getGroupDetails(String groupName,String projectName){
        Map<String,Object> groupDetails=new HashMap<String,Object>();
        String groupDN="cn="+groupName+",ou=posix_groups,ou="+projectName+",ou=Projects,dc=etriks,dc=eu";
         UserProjectGroupManager upg=new UserProjectGroupManager(pubLdap);
       def groupAttr= upg.getAllAttributesByEntryDN(groupDN);

       groupDetails.put("gidNumber",groupAttr.get("gidNumber").get(0));
       groupDetails.put("description",groupAttr.get("description").get(0) );
       groupDetails.put("memberUids",groupAttr.get("memberUid"));

        upg.closeContext();
        return groupDetails;
    }


}

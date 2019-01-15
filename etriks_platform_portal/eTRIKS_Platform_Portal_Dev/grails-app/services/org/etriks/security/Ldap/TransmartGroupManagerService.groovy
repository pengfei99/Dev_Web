package org.etriks.security.Ldap

import grails.transaction.Transactional
import org.etriks.security.ldapnew.entry.UserProjectTransmartGroupManager
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig

@Transactional
class TransmartGroupManagerService {
    def PubLdapConnectorConfig pubLdap=new PubLdapConnectorConfig();


    def Map<String,String> getAllTransmartGroups(String projectName) {
        UserProjectTransmartGroupManager uptg=new UserProjectTransmartGroupManager(pubLdap);
        Map<String,String> allGroups;
        allGroups=uptg.getAllTransmartGroups(projectName);
        uptg.closeContext();
        return allGroups;
    }

    def Map<String,Object> getTransmartGroupDetails(String groupName,String projectName){
        Map<String,Object> groupDetails=new HashMap<String,Object>();
        UserProjectTransmartGroupManager uptg=new UserProjectTransmartGroupManager(pubLdap);
        String groupDN="cn="+groupName+",ou=transmart,ou=groups_of_names,ou="+projectName+",ou=Projects,dc=etriks,dc=eu";
        def groupAttr=uptg.getAllAttributesByEntryDN(groupDN);

        groupDetails.put("gidNumber",groupAttr.get("gidNumber").get(0));
        groupDetails.put("memberUids",groupAttr.get("memberUid"))

       uptg.closeContext();
        return groupDetails;
    }

   def addTransmartGroupsMember(String projectName,String groupName,String uid){
       UserProjectTransmartGroupManager uptg=new UserProjectTransmartGroupManager(pubLdap);
       boolean result=uptg.addTransmartGroupsMember(groupName,uid,projectName);
    uptg.closeContext();
       return result
   }
}

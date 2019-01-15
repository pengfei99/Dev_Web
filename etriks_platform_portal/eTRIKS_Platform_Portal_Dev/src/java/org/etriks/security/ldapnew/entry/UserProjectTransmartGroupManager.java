package org.etriks.security.ldapnew.entry;

import org.apache.log4j.Logger;
import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pliu on 5/13/16.
 */
public class UserProjectTransmartGroupManager extends LdapEntriesManager {


    //create an logger instance
    private final org.apache.log4j.Logger log= Logger.getLogger(getClass());

    public UserProjectTransmartGroupManager(LdapConnectorConfig ldapServerInfo) throws IOException {
        super(ldapServerInfo);

    }

     /*This method return all transmart groups which located under ou=Transmart,ou=Applications,dc=etriks,dc=eu*/

    public Map<String,String> getAllTransmartGroups(String projectName){
        String transmartGroupDN="ou=transmart,ou=groups_of_names,ou="+projectName+",ou=Projects,dc=etriks,dc=eu";
        Map<String,String> transmartUserGroups=new HashMap<String, String>();
        NamingEnumeration<NameClassPair> groups = this.getAllEntries(transmartGroupDN);
        try {
            while(groups.hasMore()){
                String groupCN=groups.next().getName();
                String groupDN=groupCN +","+ transmartGroupDN;
                String gidNumber = this.getGidNumber(groupDN);
                String groupName=groupCN.substring(3);
                transmartUserGroups.put(gidNumber,groupName);
            }
        } catch (NamingException e) {
            log.error("In UserGroupManager, fail to get all groups." + e.toString());
        }
        return transmartUserGroups;
    }

    public boolean addTransmartGroupsMember(String transmartGroupCN,String UserId,String projectName){
        String transmartGroupDN="ou=transmart,ou=groups_of_names,ou="+projectName+",ou=Projects,dc=etriks,dc=eu";
        String groupDN=transmartGroupCN+","+transmartGroupDN;
        return this.insertNewAttribute(groupDN,"memberUid",UserId);
    }
}

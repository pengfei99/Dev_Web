package org.bioaster.security.ldapManager;

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.LdapArchitectureInfo;
import org.bioaster.security.core.EntryManager;
import org.bioaster.security.core.LdapGroupSearchController;
import org.bioaster.security.core.LdapProjectSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 2/24/17.
 */
public class LdapProjectManager extends EntryManager{
    static Logger log = LoggerFactory.getLogger(LdapProjectManager.class);
    private ILdapServerConnectorConfig ldapConf;
    private List<String> groupObjClasses;

    public LdapProjectManager(ILdapServerConnectorConfig ldapConfig) throws IOException {
        super(ldapConfig);
        ldapConf=ldapConfig;
        groupObjClasses=new ArrayList<String>();
        this.loadProjectEntryObjClasses();
    }



    public boolean createNewProjectGroup(String groupName,String projectName,String description) throws IOException, NamingException {
        String fullGroupName=projectName+"_"+groupName;
        String cn="cn="+fullGroupName;
        String entryParentDir= this.buildProjectDN(projectName);
        Map<String, Object> entryAttributes=new HashMap<String, Object>();
        String  gidNumber= this.getAvailableGidNumFromHead(projectName);
        entryAttributes.put("cn", fullGroupName);
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        return this.addEntry(groupObjClasses, entryAttributes, entryParentDir);

    }

    public boolean createNewProjectGroupWithGidNum(String groupName, String gidNumber,String projectName,String description) throws IOException, NamingException {
        String fullGroupName=projectName+"_"+groupName;
        String cn="cn="+fullGroupName;
        String entryParentDir= this.buildProjectDN(projectName);
        Map<String, Object> entryAttributes=new HashMap<String, Object>();
        entryAttributes.put("cn", fullGroupName);
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        return this.addEntry(groupObjClasses, entryAttributes, entryParentDir);

    }

    public boolean addUserToGroup(String fullGroupName,String userId){
        String entryDN=this.buildGroupDN(fullGroupName);
        return this.addEntryAttribute(entryDN,"memberUid",userId);
    }

    public void removeUserFromGroup(String fullGroupName,String userId) throws NamingException {
        String entryDN=this.buildGroupDN(fullGroupName);
        this.deleteEntryAttribute(entryDN,"memberUid",userId);
    }

    public boolean deleteGroup(String fullGroupName){
        String dn=this.buildGroupDN(fullGroupName);
        return this.deleteEntry(dn);
    }

    public List<String> getProjectName(){
        return getSubEntryNameList(LdapArchitectureInfo.PROJECT_DIR);
    }

     public List<String> getProjectGroupList(String projectName){
         return getSubEntryNameList(this.buildProjectDN(projectName));
     }

    private String getAvailableGidNumFromHead(String projectName) throws NamingException, IOException{
        int gidBase=this.getProjectGidNumBase(projectName);
        int groupSize=999;
        String gidNum=Integer.toString(gidBase+groupSize);
        LdapProjectSearchController lsc=new LdapProjectSearchController(ldapConf);
        for(int i=gidBase;i<gidBase+groupSize;i++){
            if (!lsc.checkGroupNumExistence(Integer.toString(i))){
                gidNum=Integer.toString(i);
                break;}
        }
        lsc.closeConnection();
        return gidNum;
    }

    private String getAvailableGidNumFromTail(String projectName) throws IOException, NamingException{
        int gidBase=this.getProjectGidNumBase(projectName);
        int groupSize=999;
        String gidNum=Integer.toString(gidBase+groupSize);
        LdapProjectSearchController lsc=new LdapProjectSearchController(ldapConf);
        int size=lsc.getChildrenEntrySize(LdapArchitectureInfo.USER_DIR, null, false);
        for(int i=gidBase+size;i<gidBase+groupSize;i++){
            if (!lsc.checkGroupNumExistence(Integer.toString(i))){
                gidNum=Integer.toString(i);
                break;}
        }
        lsc.closeConnection();
        return gidNum;

    }

    private int getProjectGidNumBase(String projectName){
        int groupGidNumBase;
        switch(projectName.toLowerCase()){
            case "passport": groupGidNumBase=LdapArchitectureInfo.PASSPORTGidNumBase;break;
            default: groupGidNumBase=50000;
        }
        return groupGidNumBase;
    }
    private String getProjectName(String fullGroupName){
        int i=fullGroupName.indexOf("_");
        return fullGroupName.substring(0,i);

    }
    private String buildGroupDN(String fullGroupName){
        return "cn="+fullGroupName+",ou="+this.getProjectName(fullGroupName)+","+ LdapArchitectureInfo.PROJECT_DIR;
    }
    private void loadProjectEntryObjClasses() {
        groupObjClasses.add("posixGroup");
        groupObjClasses.add("top");
    }

    private String buildProjectDN(String projectName){
        return "ou="+projectName+","+LdapArchitectureInfo.PROJECT_DIR;
    }
}

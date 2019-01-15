package org.bioaster.security.ldapManager;

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.LdapArchitectureInfo;
import org.bioaster.security.core.EntryManager;
import org.bioaster.security.core.LdapGroupSearchController;
import org.bioaster.security.core.LdapOrganizationSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 2/22/17.
 */
public class LdapGroupManager extends EntryManager{

    static Logger log = LoggerFactory.getLogger(LdapGroupManager.class);
    private ILdapServerConnectorConfig ldapConf;
    private List<String> groupObjClasses;

    public LdapGroupManager(ILdapServerConnectorConfig ldapConf) throws IOException {
        super(ldapConf);
        this.ldapConf=ldapConf;
        groupObjClasses=new ArrayList<String>();
        this.loadGroupEntryObjClasses();
    }

    public boolean createNewDepartmentGroup(String groupName,String departmentName,String description) throws IOException, NamingException {
        String fullGroupName=departmentName+"_"+groupName;
        String entryParentDir=this.buildDepartmentDN(departmentName);
        Map<String, Object> entryAttributes=new HashMap<String, Object>();
        String  gidNumber= this.getAvailableGidNumFromHead(departmentName);
        entryAttributes.put("cn", fullGroupName);
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        return this.addEntry(groupObjClasses, entryAttributes, entryParentDir);

    }

    public boolean createNewDepartmentGroupWithGidNum(String groupName,String gidNumber, String departmentName,String description) throws IOException, NamingException {
        String fullGroupName=departmentName+"_"+groupName;
        String entryParentDir=this.buildDepartmentDN(departmentName);
        Map<String, Object> entryAttributes=new HashMap<String, Object>();
        entryAttributes.put("cn", fullGroupName);
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        return this.addEntry(groupObjClasses, entryAttributes, entryParentDir);

    }

   /* public boolean createNewProjectGroup(String groupName,String projectName,String description) throws IOException, NamingException {
        String fullGroupName=projectName+"_"+groupName;
        String entryParentDir=this.buildProjectDN(projectName);
        Map<String, Object> entryAttributes=new HashMap<String, Object>();
        String  gidNumber= this.getAvailableGidNumFromHead(projectName);
        entryAttributes.put("cn", fullGroupName);
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        return this.addEntry(groupObjClasses, entryAttributes, entryParentDir);
    }*/

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

    public List<String> getDepartmentName(){
        return getSubEntryNameList(LdapArchitectureInfo.GROUP_DIR);
    }

    public List<String> getDepartmentGroupList(String departmentName){
        String departmentDn=this.buildDepartmentDN(departmentName);
        return getSubEntryNameList(departmentDn);

    }

    /*public List<String> getProjectName(){ return getSubEntryNameList(LdapArchitectureInfo.PROJECT_DIR);}

    public List<String> getProjectGroupList(String projectName){
        String projectDn=this.buildProjectDN(projectName);
        return getSubEntryNameList(projectDn);
    }*/



    private String getAvailableGidNumFromHead(String utecName) throws NamingException, IOException{
        int gidBase=this.getUtecGidNumBase(utecName);
        int groupSize=999;
        String gidNum=Integer.toString(gidBase+groupSize);
        LdapGroupSearchController lsc=new LdapGroupSearchController(ldapConf);
        for(int i=gidBase;i<gidBase+groupSize;i++){
            if (!lsc.checkGroupNumExistence(Integer.toString(i))){
                gidNum=Integer.toString(i);
                break;}
        }
        lsc.closeConnection();
        return gidNum;
    }

    private String getAvailableGidNumFromTail(String utecName) throws IOException, NamingException{
        int gidBase=this.getUtecGidNumBase(utecName);
        int groupSize=999;
        String gidNum=Integer.toString(gidBase+groupSize);
        LdapGroupSearchController lsc=new LdapGroupSearchController(ldapConf);
        int size=lsc.getChildrenEntrySize(LdapArchitectureInfo.USER_DIR, null, false);
        for(int i=gidBase+size;i<gidBase+groupSize;i++){
            if (!lsc.checkGroupNumExistence(Integer.toString(i))){
                gidNum=Integer.toString(i);
                break;}
        }
        lsc.closeConnection();
        return gidNum;

    }



    private int getUtecGidNumBase(String utecName){
        int groupGidNumBase=20000;
        switch(utecName.toLowerCase()){
            case "utec01": groupGidNumBase=LdapArchitectureInfo.UTEC01GidNumBase;break;
            case "utec02": groupGidNumBase=LdapArchitectureInfo.UTEC02GidNumBase;break;
            case "utec03": groupGidNumBase=LdapArchitectureInfo.UTEC03GidNumBase;break;
            case "utec04": groupGidNumBase=LdapArchitectureInfo.UTEC04GidNumBase;break;
            case "utec05": groupGidNumBase=LdapArchitectureInfo.UTEC05GidNumBase;break;
            case "utec06": groupGidNumBase=LdapArchitectureInfo.UTEC06GidNumBase;break;
            case "utec07": groupGidNumBase=LdapArchitectureInfo.UTEC07GidNumBase;break;
            case "utem": groupGidNumBase=LdapArchitectureInfo.UTEMGidNumBase;break;
            default: groupGidNumBase=20000;
        }
        return groupGidNumBase;
    }

    private String getSubParentDir(String fullGroupName){
        int i=fullGroupName.indexOf("_");
        return fullGroupName.substring(0,i);

    }
    private String buildGroupDN(String fullGroupName){
        return "cn="+fullGroupName+",ou="+this.getSubParentDir(fullGroupName)+","+LdapArchitectureInfo.GROUP_DIR;
    }

    private String buildDepartmentDN(String departmentName){
        return "ou="+departmentName+","+LdapArchitectureInfo.GROUP_DIR;
    }

    private void loadGroupEntryObjClasses() {

        this.groupObjClasses.add("posixGroup");
        this.groupObjClasses.add("top");

    }
   /* private String buildProjectDN(String projectName) {
        return "ou="+projectName+","+LdapArchitectureInfo.PROJECT_DIR;
    }*/
}

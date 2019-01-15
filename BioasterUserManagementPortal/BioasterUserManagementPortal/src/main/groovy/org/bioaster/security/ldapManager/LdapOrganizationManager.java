package org.bioaster.security.ldapManager;

/**
 * Created by pliu on 2/14/17.
 */
import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.LdapArchitectureInfo;
import org.bioaster.security.core.EntryManager;
import org.bioaster.security.core.LdapOrganizationSearchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LdapOrganizationManager extends EntryManager{

    static Logger log = LoggerFactory.getLogger(LdapOrganizationManager.class);
    private String organizationsDN;
    private ILdapServerConnectorConfig ldapConf;
    //List of the entry class
    private List<String> orgnizationEntryObjClasses=new ArrayList<String>();
    private int gidBase=LdapArchitectureInfo.OrganizationsGidNumBase;

    public LdapOrganizationManager(ILdapServerConnectorConfig ldapConfig) throws IOException {
        super(ldapConfig);
        this.ldapConf=ldapConfig;
        organizationsDN= LdapArchitectureInfo.ORGANIZATION_DIR;
        this.loadGroupEntryObjClasses();


    }


    /*This method will create a new organization in the LDAP server under ou=Organizations,dc=etriks,dc=eu
* It takes a organization name and the description of the organization as parameters
* It returns true if the creation is success, otherwise it returns false
* */
    public boolean createNewOrganization(String organizationName, String description) throws NamingException, IOException {

        //the parent dir of the group
        String entryParentDir=organizationsDN;
        //attributes of the group
        Map<String, Object> entryAttributes=new HashMap<String, Object>();
         String  gidNumber= this.getAvailableGidNumFromHead();
        entryAttributes.put("cn", organizationName);
        entryAttributes.put("gidNumber",gidNumber);
        entryAttributes.put("description",description);
        return this.addEntry(orgnizationEntryObjClasses, entryAttributes, entryParentDir);
    }

    public boolean deleteOrganization(String organizationsName){
        String orgDN="cn="+organizationsName+","+LdapArchitectureInfo.ORGANIZATION_DIR;
        return this.deleteEntry(orgDN);
    }

    private String getAvailableGidNumFromHead() throws NamingException, IOException{
        String gidNum="5999";
        LdapOrganizationSearchController lsc=new LdapOrganizationSearchController(ldapConf);
        for(int i=gidBase;i<5999;i++){
            if (!lsc.checkGidNumExistence(Integer.toString(i))){
            gidNum=Integer.toString(i);
            break;}
        }
        lsc.closeConnection();
        return gidNum;
    }

    private String getAvailableGidNumFromTail() throws IOException, NamingException{
        String gidNum="5999";
        LdapOrganizationSearchController lsc=new LdapOrganizationSearchController(ldapConf);
        int size=lsc.getChildrenEntrySize(LdapArchitectureInfo.USER_DIR, null, false);
        for(int i=gidBase+size;i<5999;i++){
            if (!lsc.checkGidNumExistence(Integer.toString(i))){
            gidNum=Integer.toString(i);
            break;}
        }
        lsc.closeConnection();
        return gidNum;

    }

    private void loadGroupEntryObjClasses() {
        orgnizationEntryObjClasses.add("posixGroup");
        orgnizationEntryObjClasses.add("top");
    }

/*
* This method take a organization name (e.g. BIOASTER) and return the gid number of the organization
* */
    public String getOrganizationGIDNum(String organizationName) throws NamingException {
        LdapOrganizationSearchController lsc=new LdapOrganizationSearchController(ldapConf);
        if(lsc.checkOrganizationExistence(organizationName)){
             return lsc.getOrganizationGidNum(organizationName);
        }
        else return null;

    }

    /**This method will return a map of Organization Name and gid which contains all organizations in the ldap server. **/
    public Map<String,String> getAllOrganizations() throws IOException, NamingException {

        Map<String,String> organizationGID=new HashMap<String, String>();

        //get all the organization
        NamingEnumeration<NameClassPair> organizations = this.getAllEntries(organizationsDN);
        try {
            while(organizations.hasMore()){
                String groupCN=organizations.next().getName();
                String organizationName=groupCN.substring(3);
                String gidNumber = this.getOrganizationGIDNum(organizationName);
                organizationGID.put(gidNumber,organizationName);
            }
        } catch (NamingException e) {
            log.error("OrganizationManagerService fail to get all organizations."+e.toString());
            throw new NamingException("OrganizationManagerService fail to get all organizations. "+e.toString());
        }
        return organizationGID;
    }

    public List<String> getAllOrganizationName() throws NamingException {
        List<String> orgNameList=new ArrayList<String>();
        //get all the organization
        NamingEnumeration<NameClassPair> organizations = this.getAllEntries(organizationsDN);
        try {
            while(organizations.hasMore()){
                String groupCN=organizations.next().getName();
                String organizationName=groupCN.substring(3);
                orgNameList.add(organizationName);
            }
        } catch (NamingException e) {

            log.error("OrganizationManagerService fail to get all organizations. "+e.toString());
            throw new NamingException("OrganizationManagerService fail to get all organizations Name. "+e.toString());
        }
        return orgNameList;

    }


}


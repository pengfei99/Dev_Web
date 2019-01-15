package org.etriks.security.Ldap
import grails.transaction.Transactional
import org.etriks.security.ldap.entry.UserAccountManager
import org.etriks.security.ldap.entry.UserGroupManager
import org.etriks.security.ldap.serverprofile.LdapServerConnectorConfig
import org.etriks.security.ldapnew.entry.UserAccountsManager
import org.etriks.security.ldapnew.entry.UserWPAndOrganizationManager
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig

import javax.naming.NamingException

@Transactional
class OrganizationManagerService {

    def PubLdapConnectorConfig pubLdap=new PubLdapConnectorConfig();

    def boolean createOrganizationInEtriksLdap (LdapServerConnectorConfig targetLdapServer,String organizationName,String organizationDescription){
        //Create an instance of groupAccountManager
        def UserGroupManager grpReg = new UserGroupManager(targetLdapServer,"dc=etriks,dc=eu");
        def boolean result=grpReg.createNewOrganization(organizationName,organizationDescription);
        grpReg.closeContext();
        return result;
    }

    def boolean changeUserOrganizationInEtriksLdap (LdapServerConnectorConfig targetLdapServer,uid,String organizationGid){
        UserAccountManager accReg = new UserAccountManager(targetLdapServer,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");
        boolean result=accReg.changeGIDNumber(uid,organizationGid);
        accReg.closeContext();
        return result;
    }

    def boolean createOrganizationInNewLdap(String organizationName,String organizationDescription){
        def boolean result=false;
        def UserWPAndOrganizationManager grpReg=new UserWPAndOrganizationManager(pubLdap);
        try{
            grpReg.createNewOrganization(organizationName,organizationDescription);
            result=true;
        }
        catch(NamingException e){log.error("Unable to create new Organization: "+organizationName+"Java exeception :"+e.toString())}
        catch(IOException e){log.error("Unable to create new Organization: "+organizationName+"Java exeception :"+e.toString())}

        grpReg.closeContext()
        return result
    }
    def boolean changeUserOrganizationInNewLdap(String uid,String organizationGid){
        def UserAccountsManager accReg=new UserAccountsManager(pubLdap);
        boolean result=accReg.changeGIDNumber(uid,organizationGid);
        accReg.closeContext();
        return result;
    }

    def Map<String,String> getAllOrganization(){
        def orgManager=new UserWPAndOrganizationManager(pubLdap);
        Map<String,String> allOrganizations;
        allOrganizations=orgManager.getAllOrganizations();
        orgManager.closeContext();
        return allOrganizations
    }

    def Map<String,String> getOrganizationDetails(String organizationName){
        Map<String,String> orgDetails=new HashMap<String,String>();
        String organizationDN="cn=CP_"+organizationName+",ou=Organizations,dc=etriks,dc=eu";
        def orgManager=new UserWPAndOrganizationManager(pubLdap);
        def groupAttr=orgManager.getAllAttributesAsObjectByEntryDN(organizationDN);
        String gidNumber=groupAttr.get("gidNumber").get(0);
        String description=groupAttr.get("description").get(0);
        orgDetails.put("gidNumber",gidNumber);
        orgDetails.put("description",description);
        orgManager.closeContext();
        return orgDetails;
    }

    def List<String> getAllOrganizationName(){
        def orgManager=new UserWPAndOrganizationManager(pubLdap);
        List<String> sortOrganizationNames=new ArrayList<String>(orgManager.getAllOrganizations().values());
        Collections.sort(sortOrganizationNames);
        orgManager.closeContext();
        return sortOrganizationNames;
    }
}

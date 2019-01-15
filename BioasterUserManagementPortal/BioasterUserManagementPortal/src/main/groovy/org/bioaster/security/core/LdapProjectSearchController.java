package org.bioaster.security.core;

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.LdapArchitectureInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * Created by pliu on 2/24/17.
 */
public class LdapProjectSearchController extends LdapSearchController {

    static Logger log = LoggerFactory.getLogger(LdapProjectSearchController.class);
    private String parentSearchBase= LdapArchitectureInfo.PROJECT_DIR;
    private SearchControls searchControls;

    public LdapProjectSearchController(ILdapServerConnectorConfig ldapConfig) {
        super(ldapConfig);
        searchControls=new SearchControls();
    }

    public boolean checkGroupExistence(String fullGroupName) throws NamingException {
        String searchFilter="(&(cn="+fullGroupName+"))";
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String searchBase="ou="+this.getProjectName(fullGroupName)+","+parentSearchBase;
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore()) {return true;}

        else return false;
    }

    public boolean checkGroupNumExistence(String gidNum) throws NamingException{
        String searchFilter="(&(gidNumber="+gidNum+"))";
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> answer = searchEntry(parentSearchBase, searchFilter, searchControls);
        if(answer.hasMore())
            return true;
        else return false;

    }
    public boolean checkProjectGroupMemberUidExistence(String groupName,String uid) throws NamingException {
        String[] returnAttrs=new String[1];
        returnAttrs[0]="memberUid";
        String searchFilter="(&(cn="+groupName+"))";
        Attribute groupMembers = getEntryAttrs(LdapArchitectureInfo.PROJECT_DIR, searchFilter, returnAttrs).get("memberUid");
        return groupMembers.contains(uid);
    }

    public String getGroupGidNum(String fullGroupName) throws NamingException {
        String searchFilter="(&(cn="+fullGroupName+"))";
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        //set the return attributes to the searchControls
        String[] returnAttrs={"gidNumber"};
        searchControls.setReturningAttributes(returnAttrs);
        NamingEnumeration<SearchResult> answer = searchEntry(parentSearchBase, searchFilter, searchControls);
        if(answer.hasMore()) {
            return answer.next().getAttributes().get("gidNumber").get(0).toString();
        }
        else throw new NamingException("Group Name which you entered does not exist!!!");

    }

    private String getProjectName(String fullGroupName){
        int i=fullGroupName.indexOf("_");
        return fullGroupName.substring(0,i);

    }
}

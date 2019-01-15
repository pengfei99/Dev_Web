package org.bioaster.security.core;

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.LdapArchitectureInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Map;

/**
 * Created by pliu on 2/23/17.
 */
public class LdapGroupSearchController extends LdapSearchController{

    static Logger log = LoggerFactory.getLogger(LdapGroupSearchController.class);
    private String parentSearchBase= LdapArchitectureInfo.GROUP_DIR;
    private SearchControls searchControls;

    public LdapGroupSearchController(ILdapServerConnectorConfig ldapConfig) {
        super(ldapConfig);
        searchControls=new SearchControls();
    }

    public boolean checkGroupExistence(String fullGroupName) throws NamingException {
        String searchFilter="(&(cn="+fullGroupName+"))";
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String searchBase="ou="+this.getDepartmentName(fullGroupName)+","+parentSearchBase;
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

    public boolean checkDepartmentGroupMemberUidExistence(String groupName,String uid) throws NamingException {
        String[] returnAttrs=new String[1];
        returnAttrs[0]="memberUid";
        String searchFilter="(&(cn="+groupName+"))";
        Attribute groupMembers = getEntryAttrs(LdapArchitectureInfo.GROUP_DIR, searchFilter, returnAttrs).get("memberUid");
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
        else throw new NamingException("Can't get group gid. Group Name which you entered does not exist!!!");

    }

    /*
        * This method take a group name and a list of return attrs, it returns a map of return attrs key and values
         * @params String groupName,
         * @params String[] returnAttrs,
         * @return Attributes groupAttrs
        * */
    public Attributes getGroupAttrs(String groupName, String[] returnAttrs,String searchBase) throws NamingException {

        String searchFilter="(&(cn="+groupName+"))";

        return getEntryAttrs(searchBase,searchFilter,returnAttrs);

    }
    private String getDepartmentName(String fullGroupName){
        int i=fullGroupName.indexOf("_");
        return fullGroupName.substring(0,i);

    }

}

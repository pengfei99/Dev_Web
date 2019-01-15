package org.bioaster.security.core;

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.LdapArchitectureInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by pliu on 2/22/17.
 */
public class LdapOrganizationSearchController extends LdapSearchController {

    static Logger log = LoggerFactory.getLogger(LdapOrganizationSearchController.class);
    private String searchBase= LdapArchitectureInfo.ORGANIZATION_DIR;
    private SearchControls searchControls;

    public LdapOrganizationSearchController(ILdapServerConnectorConfig ldapConfig) {
        super(ldapConfig);
        searchControls=new SearchControls();
    }

    public boolean checkOrganizationExistence(String orgName) throws NamingException {
        String searchFilter="(&(cn="+orgName+"))";
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore()) {
            return true;}
        else return false;
    }

    public boolean checkGidNumExistence(String gidNum) throws NamingException{
        String searchFilter="(&(gidNumber="+gidNum+"))";
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore())
            return true;
        else return false;

    }

    public String getOrganizationGidNum(String orgName) throws NamingException {
        String searchFilter="(&(cn="+orgName+"))";
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        //set the return attributes to the searchControls
        String[] returnAttrs={"gidNumber"};
        searchControls.setReturningAttributes(returnAttrs);
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore()) {
             return answer.next().getAttributes().get("gidNumber").get(0).toString();
        }

        else return "5000";

    }

    /*
    * This method take a organization name and a list of return attrs, it returns a map of return attrs key and values
     * @params String orgName,
     * @params String[] returnAttrs,
     * @return Map<String,String> orgnaizatinAttrs
    * */
    public Map<String,String> getOrganizationAttrs(String orgName, String[] returnAttrs) throws NamingException {

        String searchFilter="(&(cn="+orgName+"))";
        return this.getEntryAttrsAsString(searchBase,searchFilter,returnAttrs);
    }
}

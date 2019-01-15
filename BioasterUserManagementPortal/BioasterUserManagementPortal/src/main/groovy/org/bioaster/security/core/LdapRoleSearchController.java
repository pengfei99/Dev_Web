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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pliu on 2/28/17.
 */
public class LdapRoleSearchController extends LdapSearchController{

    static Logger log = LoggerFactory.getLogger(LdapRoleSearchController.class);
    private String parentSearchBase= LdapArchitectureInfo.APPLICATION_DIR;
    private SearchControls searchControls;

    public LdapRoleSearchController(ILdapServerConnectorConfig ldapConfig) {
        super(ldapConfig);
        searchControls=new SearchControls();
    }

    public List<String> getRoleMemberDNList(String fullGroupName,String applicationName) throws NamingException {
        List<String> memberList=new ArrayList<String>();
        String searchFilter="(&(cn="+fullGroupName+"))";
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String searchBase="cn="+fullGroupName+",ou="+applicationName+","+parentSearchBase;
        //set the return attributes to the searchControls
        String[] returnAttrs={"member"};
        searchControls.setReturningAttributes(returnAttrs);
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore()) {
            NamingEnumeration members = answer.next().getAttributes().get("member").getAll();
            while (members.hasMore()){
                memberList.add(members.next().toString());
            }
        }
        else throw new NamingException("Can't get group gid. Group Name which you entered does not exist!!!");

       return memberList;
    }

    public List<String> getRoleMemberNameList(String fullGroupName,String applicationName) throws NamingException {
        List<String> memberList=this.getRoleMemberDNList(fullGroupName,applicationName);
        List<String> memberNameList=new ArrayList<String>();
        for(String memberDN:memberList){
            memberNameList.add(memberDN.substring(3,memberDN.indexOf(",")));
        }
        return memberNameList;
    }


}

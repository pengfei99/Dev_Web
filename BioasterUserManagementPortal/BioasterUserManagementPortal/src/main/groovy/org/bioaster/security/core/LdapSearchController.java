package org.bioaster.security.core;

/**
 * Created by pliu on 2/14/17.
 */


import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.LdapArchitectureInfo;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LdapSearchController {


    private DirContext dirContext;

    public LdapSearchController(ILdapServerConnectorConfig ldapConfig){
        ConnLDAP conLdp=new ConnLDAP(ldapConfig);
        this.dirContext=(InitialDirContext) conLdp.getContext();

    }

    public List<String> getAllEntries(String searchBase,String searchFilter,boolean searchSubTree) throws NamingException {
        List<String> dnList=new ArrayList<String>();
        SearchControls searchCtls = new SearchControls();
        if(searchSubTree) {
            //Specifiy the search scope
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        }
        if(searchFilter==null){
            searchFilter="(objectClass=*)";
        }

        NamingEnumeration<SearchResult> answers = dirContext.search(searchBase, searchFilter, searchCtls);

        while(answers.hasMore()){
            SearchResult answer = answers.next();
            //String dn=answer.getName();
            String dn=answer.getNameInNamespace();
            dnList.add(dn);
        }
        return dnList;
    }

    public int getChildrenEntrySize(String searchBase,String searchFilter,boolean searchSubTree) throws NamingException{
        return getAllEntries(searchBase, searchFilter, searchSubTree).size();
    }


    public NamingEnumeration<SearchResult> searchEntry(String searchBase,String searchFilter,SearchControls searchControls) throws NamingException{

        return dirContext.search(searchBase, searchFilter,searchControls);}



    public void closeConnection() throws NamingException{
        this.dirContext.close();
    }

    public Map<String,String> getEntryAttrsAsString(String searchBase,String searchFilter, String[] returnAttrs) throws NamingException {
        Map<String,String> entryAttrs=new HashMap<String,String>();
        SearchControls searchControls=new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setReturningAttributes(returnAttrs);
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore()) {
            Attributes attrs = answer.next().getAttributes();
            for(int i=0;i<returnAttrs.length;i++){
                String key=returnAttrs[i];
                String value=attrs.get(key).get(0).toString();
                entryAttrs.put(key,value);
            }
        }
        return entryAttrs;

    }

    public Attributes getEntryAttrs(String searchBase,String searchFilter,String[] returnAttrs) throws NamingException {

        SearchControls searchControls=new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setReturningAttributes(returnAttrs);
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore()) {
            return answer.next().getAttributes();
        }
        else throw new NamingException("Can't get entry attrs");

    }
}

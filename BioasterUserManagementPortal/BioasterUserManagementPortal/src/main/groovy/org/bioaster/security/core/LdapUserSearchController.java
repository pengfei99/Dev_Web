package org.bioaster.security.core;

/**
 * Created by pliu on 2/14/17.
 */
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class search the entry with an given search base. we can also
 * specify the attributes of the return entry.
 */
public class LdapUserSearchController extends LdapSearchController{

    /* Get actual class name to be printed on */
    static Logger log = LoggerFactory.getLogger(LdapUserSearchController.class);


    //Specify the base for the search, because this class is used for both AD and openldap, it depends on the type
    //so don't change it

    private String searchBase;

    //Specify the searchFilterArgument, because this class is used for both AD and openldap, it depends on the type
    //so don't change it
    private String searchFilterArg;



    /*
     * The constructor create a new instance connection to the LDAP server
     * and searchControls for search.
     */
    public LdapUserSearchController(ILdapServerConnectorConfig ldapConf){
        super(ldapConf);
        this.searchFilterArg=ldapConf.getSEARCH_FILTER_ARG();
        this.searchBase=ldapConf.getSearchbase();
    }

    public NamingEnumeration<SearchResult> searchByUID(String uid,String[] returnAttrs){

        SearchControls searchCtls=new SearchControls();
        //Specifiy the LDAP search filter,
        //String searchFilter = "(&(uid="+uid+"))";

        String searchFilter = "(&("+searchFilterArg+"="+uid+"))";

        //Specifiy the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        //set the return attributes to the searchControls
        searchCtls.setReturningAttributes(returnAttrs);

        NamingEnumeration<SearchResult> answer=null;

        try {
            answer=this.searchEntry(searchBase,searchFilter,searchCtls);
        } catch (NamingException e) {
            log.error(e.toString());
        }


        return answer;

    }
    public boolean checkUidExistence(String uid) throws NamingException{

        String searchFilter="(&(uid="+uid+"))";
        SearchControls searchControls=new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore()) {return true;}

        else return false;
    }


    public boolean checkUidNumExistence(String uidNum) throws NamingException{
        String searchFilter="(&(uidNumber="+uidNum+"))";
        SearchControls searchControls=new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore())
            return true;
        else return false;

    }

    public boolean checkEmailExistence(String email) throws NamingException {
        String searchFilter="(&(mail="+email+"))";
        SearchControls searchControls=new SearchControls();
        NamingEnumeration<SearchResult> answer = searchEntry(searchBase, searchFilter, searchControls);
        if(answer.hasMore())
            return true;
        else return false;
    }

    public Map<String,String> getUserAttrsAsString(String userName, String[] returnAttrs) throws NamingException {
        String searchFilter="(&(cn="+userName+"))";
        return this.getEntryAttrsAsString(searchBase,searchFilter,returnAttrs);
    }

    public Attributes getUserAttrs(String userName,String[] returnAttrs) throws NamingException {
        String searchFilter="(&(cn="+userName+"))";
        return this.getEntryAttrs(searchBase,searchFilter,returnAttrs);
    }

     public String getUserEmail(String userName) throws NamingException {

         //set the return attributes to the searchControls
         String[] returnAttrs={"mail"};
         Map<String,String> answer = this.getUserAttrsAsString(userName, returnAttrs);
         if(answer.containsKey("mail")) {
             return answer.get("mail");
         }
         else throw new NamingException("Can't get user mail. User Name which you entered does not exist!!!");
     }

     public String getUserID(String userName) throws NamingException {
         String[] returnAttrs={"uid"};
         Map<String,String> answer = this.getUserAttrsAsString(userName, returnAttrs);
         if(answer.containsKey("uid")) {
             return answer.get("uid");
         }
         else throw new NamingException("Can't get user ID. User Name which you entered does not exist!!!");
     }


     public Attributes getUserAttrWithUid(String userID, String[] returnAttrs) throws NamingException {
         String searchFilter="(&(uid="+userID+"))";
        return getEntryAttrs(searchBase,searchFilter,returnAttrs);

     }

     public String getUserMailWithUid(String userID) throws NamingException {
         String[] returnAttrs=new String[1];
         returnAttrs[0]="mail";
         return getUserAttrWithUid(userID,returnAttrs).get("mail").get(0).toString();
     }

     public String getUserFullNameWithUid(String userID) throws NamingException {
        String[] returnAttrs=new String[1];
        returnAttrs[0]="cn";
        return getUserAttrWithUid(userID,returnAttrs).get("cn").get(0).toString();
     }

     public List<String> getUserPosixGroup(String userID,String groupBaseName) throws NamingException {
         List<String> groupList=null;
        if(checkUidExistence(userID)){
            groupList=new ArrayList<String>();
            String searchFilter="(&(memberUid="+userID+"))";
            SearchControls searchControls=new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            //set the return attributes to the searchControls
            String[] returnAttrs={"cn"};
            searchControls.setReturningAttributes(returnAttrs);
            NamingEnumeration<SearchResult> answer = searchEntry(groupBaseName, searchFilter, searchControls);

            if(answer.hasMore()){

                while(answer.hasMore()) {

                    groupList.add(answer.next().getAttributes().get("cn").get(0).toString());
                }
            }
else groupList.add("NO_VALUE");
        }

        else {throw new NamingException("Can't get user group. User Name which you entered does not exist!!!");}


         return groupList;
     }

     public List<String> getUserPosixBioasterGroup(String userID ) throws NamingException {
         String groupBaseName=LdapArchitectureInfo.GROUP_DIR;
         return this.getUserPosixGroup(userID,groupBaseName);
     }

     public List<String> getUserPosixProjectGroup(String userID) throws NamingException {
        String groupBaseName=LdapArchitectureInfo.PROJECT_DIR;
        return this.getUserPosixGroup(userID,groupBaseName);
     }

     public List<String> buildRoles(Attribute rawRoles) throws NamingException {
         String nativeRole="BUMP_user";
         List<String> roles=new ArrayList<String>();
         if(rawRoles==null){roles.add(nativeRole);}
         else{
             for(int i=0;i<rawRoles.size();i++){
                 String roleCN=rawRoles.get(i).toString();
                 String role=roleCN.substring(3,roleCN.indexOf(","));
                 roles.add(role);
             }
         }

         return roles;
     }

}


package org.etriks.security.ldapnew.authentication;

import org.apache.log4j.Logger;
import org.etriks.security.ldapnew.LdapConnector;
import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by pliu on 3/27/14.
 */
/*
 * 1.connect to ldap;
   2.auth with "super"-user (user1): special user with known DN and password;
   3.search another user (user2): user who must be tested, search him with some attribute (for example sAMAccountName);
   4.determine DN of user;
   5.open one more connection to ldap with just found DN and password of user2;
   6.no exception - ok, otherwise user has entered wrong password.
 */


public class LdapAuthenticator {
    //get the logger instance
    private final org.apache.log4j.Logger log= Logger.getLogger(getClass());

    //Create a search Control for the ldap server
    private SearchControls searchCtls;
    //Create a connection to the ldap server
    private LdapConnector ldapConnector;
    //Create a context for the LDAP connection
    private InitialDirContext connectionContext;
    //Create a config class which stores the config information
    private LdapConnectorConfig ldapConfig;





    private List<String> CurrentUserRoles;

    public LdapAuthenticator(LdapConnectorConfig ldapConfig){

        //Connect the ldap Authenticator to the test ldap server need to be changed if want to connect to other ldap
        this.ldapConfig=ldapConfig;

        //Create the instance of the LDAP connection
        ldapConnector=new LdapConnector(ldapConfig);
        //Create the instance of the search controls
        searchCtls=new SearchControls();
		/*
		 * Step 1 and 2. connect to the LDAP server with admin account is done by using ConnLDAP
		 */
        //Get the context of the ldap connection
        connectionContext=(InitialDirContext) ldapConnector.getContext();
    }


    /**
     * Returns {@} with addition information in case of user exists and authicated correctly, otherwise null
	 * @param uid username
     * @param password password
     * @return a list of UserRole or null
     */
    public boolean checkAuthWithLdap(String uid, String password) {

		/*
		 * Step 3. search the entry which has the username(in our case is the uid of the entry)
		 */
        //List to store the user roles
        List<String> userRoles= new ArrayList<String>();

        searchCtls.setReturningAttributes(ldapConfig.getReturnattributs());

        //Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        //Specifiy the LDAP search filter
        String searchFilter = "(&(uid="+uid +") )";

        NamingEnumeration<?> answer = null;
        try {
            answer = connectionContext.search(ldapConfig.getSearchbase(), searchFilter, searchCtls);
        } catch (NamingException e) {
            log.error("Search engine for LDAP server failed! Java exception: "+e.toString());
        }

	        /*
	         * Step 4. determin the dn of the found entry
	         */

        try {
            if (answer.hasMore()) {

                SearchResult result = (SearchResult) answer.next();
                Attributes attrs = result.getAttributes();
                String dn=result.getNameInNamespace();

                Attribute member=attrs.get("memberOf");

                //add the role of member
                if (member!=null){
                    userRoles.addAll(refineMemberInfo(member));}

                Properties env1 = new Properties();
                env1.put(Context.INITIAL_CONTEXT_FACTORY, ldapConfig.getINITIAL_CONTEXT_FACTORY());
                env1.put(Context.PROVIDER_URL, ldapConfig.getPROVIDER_URL());
                env1.put(Context.SECURITY_PRINCIPAL, dn);
                env1.put(Context.SECURITY_CREDENTIALS, password);
					    /*
					     * Step 5. Open one more connection to ldap with just found DN and password of user
					     *
					     */
                new InitialDirContext(env1); // in case of problem exception will be threw
                //System.out.println("Authentication successful");
                //assign the current user roles
                this.setCurrentUserRoles(userRoles);
                log.info(uid+" login with "+userRoles.toString()+" successfully");
                return true;
            }

            else {
                log.info(uid+" try to login with a non existing uid.");
                return false;
            }
        } catch (NamingException e) {

					/*
				     * Step 6. no exception - ok, otherwise user2 has entered wrong password.
				     *
				     */
            log.info(uid+" try to login with a wrong password.");

            return false;
        }

    }


    public List<String> getCurrentUserRoles() {
        return CurrentUserRoles;
    }


    private void setCurrentUserRoles(List<String> currentUserRoles) {
        CurrentUserRoles = currentUserRoles;
    }

    private ArrayList<String> refineMemberInfo(Attribute member){
        ArrayList<String> userRoles= new ArrayList<String>();
        //refine the return attribute,keep only the role,delete the ou=Group,dc=...
        int size=member.size();

        for(int i=0;i<size;i++){

            String str;
            try {
                str = member.get(i).toString();
                int end=str.indexOf(",");
                String role=str.substring(3,end);
                userRoles.add(role);
            } catch (NamingException e) {

                e.printStackTrace();
            }


        }
        return userRoles;

    }
    public void closeContext(){
        try {
            this.connectionContext.close();
            this.ldapConnector.closeContext();
        } catch (NamingException e) {
            //to be completed. log the exception in the server log
            log.error("unalbe to close the Ldap connection!!! java exception: "+e.toString());
        }

    }
}
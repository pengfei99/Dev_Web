package org.bioaster.security.adManager;

/**
 * Created by pliu on 2/14/17.
 */

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.core.ConnLDAP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.Properties;

/*
 * This class uses double bind technique to authenticate user
 * via Active directory
 *
 * */

public class ADAuthenticator {

    /* Get actual class name to be printed on */
    static Logger log = LoggerFactory.getLogger(ADAuthenticator.class);
    private SearchControls searchCtls;
    private ConnLDAP conLdp;
    private InitialDirContext ctx;
    private String ldapUrl;

    //Specify the base for the search
    private String searchBase="dc=bioaster, dc=local";


    /*
     * Constructor takes a configuration file locate at conf/ad.properties, it builds connection with AD
     * setup dirContext for search user and bind user with uid and password
     * */
    public ADAuthenticator(ILdapServerConnectorConfig adConf) throws IOException{
        ldapUrl=adConf.getPROVIDER_URL();
        conLdp=new ConnLDAP(adConf);
        searchCtls=new SearchControls();
		/*
		 * Step 1 and 2. connect to the LDAP server with admin account is done by using ConnLDAP
		 */
        ctx=(InitialDirContext) conLdp.getContext();
    }


    /**
     * Search user in the AD first, if it exist, then bind to AD with uid and password of this user.
     * If bind correctly, it returns the dn in th AD or the user, otherwise it returns null
     * @param uid username
     * @param password password
     * @return String or null
     */
    public String checkUserPasswordWithAD(String uid, String password) {

		/*
		 * Step 3. search the entry which has the username(in our case is the uid of the entry)
		 */


        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        //Specifiy the LDAP search filter
        String searchFilter = "(&(sAMAccountName="+uid +"))";
        NamingEnumeration<SearchResult> answer;
        try {
            answer = ctx.search(searchBase, searchFilter, searchCtls);
        } catch (NamingException e) {
            log.error("Search engine for LDAP server failed! Java exception: "+e.toString());
            throw new Error("Search engine for LDAP server failed! Java exception: "+e.toString());
        }

	        /*
	         * Step 4. determin the dn of the found entry
	         */

        try {
            if (answer.hasMore()) {

                SearchResult result = (SearchResult) answer.next();

                String dn=result.getNameInNamespace();

                Properties env1 = new Properties();
                env1.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                env1.put(Context.PROVIDER_URL, ldapUrl);
                env1.put(Context.SECURITY_PRINCIPAL, dn);
                env1.put(Context.SECURITY_CREDENTIALS, password);
					    /*
					     * Step 5. Open one more connection to ldap with just found DN and password of user
					     *
					     */

                new InitialDirContext(env1); // in case of problem exception will be threw

                return dn;
            }

            else {
                return null;
            }
        } catch (NamingException e) {

					/*
				     * Step 6. no exception - ok, otherwise user2 has entered wrong password.
				     *
				     */
					log.warn("Your Login or password is wrong"+e.toString());
            return null;
        }

    }
    public void closeConnection() throws NamingException{
        this.ctx.close();
    }

}


package test;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

/**
 * Created by pliu on 3/24/16.
 */
public class LdapSearchFilterTest {
    public static void main (String[] args)     {

        Hashtable env = new Hashtable();
        String adminName = "CN=admin,DC=etriks,DC=eu";
        String adminPassword = "eTRIKSPass";
        String ldapURL = "ldap://172.17.88.30:389";
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        //set security credentials, note using simple cleartext authentication
        env.put(Context.SECURITY_AUTHENTICATION,"simple");
        env.put(Context.SECURITY_PRINCIPAL,adminName);
        env.put(Context.SECURITY_CREDENTIALS,adminPassword);

        //connect to my domain controller
        env.put(Context.PROVIDER_URL,ldapURL);

        try {

            //Create the initial directory context
            LdapContext ctx = new InitialLdapContext(env,null);

            //Create the search controls
            SearchControls searchCtls = new SearchControls();

            //Specify the search scope
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            //specify the LDAP search filter
            String searchFilter = "(&(memberOf=cn=abirisk_member,ou=portal,ou=groups_of_names,ou=abirisk,ou=Projects,dc=etriks,dc=eu))";

            //Specify the Base for the search
            String searchBase = "OU=Users,DC=etriks,DC=eu";

            //initialize counter to total the group members
            int totalResults = 0;

            //Specify the attributes to return
            String returnedAtts[]={"uid"};
            searchCtls.setReturningAttributes(returnedAtts);

            //Search for objects using the filter
            NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);

            //Loop through the search results
            while (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult)answer.next();

                System.out.println(">>>" + sr.getName());

                //Print out the groups

                Attributes attrs = sr.getAttributes();
                if (attrs != null) {

                    try {
                        for (NamingEnumeration ae = attrs.getAll();ae.hasMore();) {
                            Attribute attr = (Attribute)ae.next();
                            System.out.println("Attribute: " + attr.getID());
                            for (NamingEnumeration e = attr.getAll();e.hasMore();totalResults++) {

                                System.out.println(" " +  totalResults + ". " +  e.next());
                            }

                        }

                    }
                    catch (NamingException e)     {
                        System.err.println("Problem listing membership: " + e);
                    }

                }
            }

            System.out.println("Total groups: " + totalResults);
            ctx.close();

        }

        catch (NamingException e) {
            System.err.println("Problem searching directory: " + e);
        }
    }
}

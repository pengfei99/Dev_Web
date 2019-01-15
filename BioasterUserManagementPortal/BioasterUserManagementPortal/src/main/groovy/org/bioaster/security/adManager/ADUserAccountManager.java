package org.bioaster.security.adManager;

/**
 * Created by pliu on 2/14/17.
 */

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.core.LdapUserSearchController;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import java.util.HashMap;

public class ADUserAccountManager {

    private String[] returnAttrs={"cn","mail","physicalDeliveryOfficeName"};
    private LdapUserSearchController lsc;


    public ADUserAccountManager(ILdapServerConnectorConfig ldapConfig){

        lsc=new LdapUserSearchController(ldapConfig);
    }


    public HashMap<String,String> getUserAttrs(String uid) throws NamingException {
        HashMap<String, String> userAttrs = new HashMap<String, String>();
        NamingEnumeration<SearchResult> searchResult = lsc.searchByUID(uid,returnAttrs);
        //Loop through the search results
        while (searchResult.hasMoreElements()) {
            SearchResult sr = (SearchResult) searchResult.next();
            Attributes adAttrs = sr.getAttributes();

            userAttrs.put("dn", sr.getName());
            userAttrs.put("uid", uid);


            //Print out some of the attributes
            if (adAttrs != null) {

                for (String attribute: returnAttrs) {
                    userAttrs.put(attribute, adAttrs.get(attribute).get(0).toString());
                }
            }
        }

        return userAttrs;
    }

    public void closeConnection() throws NamingException{
        this.lsc.closeConnection();
    }

}


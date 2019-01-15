package org.bioaster.security.ldapManager;

import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.config.LdapArchitectureInfo;
import org.bioaster.security.core.EntryManager;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 10/4/17.
 */
public class ADUserNoAuthAccountManager extends EntryManager {

    public ADUserNoAuthAccountManager(ILdapServerConnectorConfig ldapConfig) throws IOException {
        super(ldapConfig);
    }


    public List<String> getALLADUserDNList(List<String> parentEntryList){
        List<String> userList= new ArrayList<String>();
        while(!parentEntryList.isEmpty()){
            String entryDN=parentEntryList.get(0);
            parentEntryList.remove(0);
            NamingEnumeration<NameClassPair> subEntryList = this.getAllEntries(entryDN);
            List<String> subDnList = new ArrayList<String>();
            try {
                while (subEntryList.hasMore()){

                    subDnList.add(subEntryList.next().getName()+","+entryDN);
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
            for(String subEntry:subDnList){
                if (subEntry.startsWith("CN=")){
                    userList.add(subEntry);
                }
                else{
                   parentEntryList.add(subEntry);
                }
            }
        }
        return userList;
    }

    public Map<String,List<Object>> getUserAttrs(String userDn){

        Map<String, List<Object>> allAttrs = this.getAllAttributesAsObjectByEntryDN(userDn);

      //  System.out.println("country: "+country);
       // System.out.println("city: "+city);


       // System.out.println(allAttrs);
        return allAttrs;
    }

}

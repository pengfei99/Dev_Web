package org.etriks.security.Ldap
import grails.transaction.Transactional
import org.etriks.security.ldapnew.entry.UserAccountsManager
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig

@Transactional
class UserRegistrationService {

    def PubLdapConnectorConfig pubLdap=new PubLdapConnectorConfig();

    /*
    * Check user email existed in the old ldap archi
    * */

    /*  def boolean userEmailExisted(LdapServerConnectorConfig targetLdapServer,String emails) {
        UserAccountManager uam=new UserAccountManager(targetLdapServer,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");
        boolean result= uam.userEmailsExisted(emails);
        uam.closeContext();
        return result;
    }*/

    def boolean userEmailExisted(String emails) {
        UserAccountsManager uam=new UserAccountsManager(pubLdap);
        boolean result= uam.userEmailsExisted(emails);
        uam.closeContext();
        return result;
    }
}

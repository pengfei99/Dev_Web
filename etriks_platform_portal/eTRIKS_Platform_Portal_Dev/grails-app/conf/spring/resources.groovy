import org.etriks.security.ldapnew.authentication.LDAPAuthenticationProvider
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices

// Place your Spring DSL code here
beans = {
    LDAPAuthenticationProvider(org.etriks.security.ldapnew.authentication.LDAPAuthenticationProvider){
        prodLdap=ref("pubLdapConnectorConfig");
    }
    pubLdapConnectorConfig(org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig){}

  /*
  *
  * The following code are for the
  *
  *
  *
  *
  * */

   //Bean of the ldapAuthenticationProvider
   /* LDAPAuthenticationProvider(org.etriks.security.ldap.authentication.LDAPAuthenticationProvider){
//attributes of class LDAPAuthenticationProvider
        primaryLdapServer=ref("etriksLdapConnectorConfig");
        secondaryLdapServer=ref("abiriskLdapConnectorConfig");
        thirdLdapServer=ref("oncoTrackLdapConnectorConfig");
        fourthLdapServer=ref("bioasterLdapConnectorConfig");
    }*/
/*//Bean of the testldapConnector configuration class
    testLdapConnectorConfig(org.etriks.security.ldap.serverprofile.TestLdapConnectorConfig){}*/

    //Bean of the abiriskldapConnector configuration class
   // abiriskLdapConnectorConfig(org.etriks.security.ldap.serverprofile.AbiriskLdapConnectorConfig){}

    //Bean of the etriksldapConnector configuration class
    //etriksLdapConnectorConfig(org.etriks.security.ldap.serverprofile.EtriksMemberLdapConnectorConfig){}

  /*  //Bean of the PublicConnector configuration class
    publicLdapConnectorConfig(org.etriks.security.ldap.serverprofile.PublicMemberLdapConnectorConfig){}*/

    //Bean of the oncoTrackldapConnector configuration class
    //oncoTrackLdapConnectorConfig(org.etriks.security.ldap.serverprofile.OncoTrackLdapConnectorConfig){}

    //Bean of the BioasterldapConnector configuration class
    //bioasterLdapConnectorConfig(org.etriks.security.ldap.serverprofile.BioasterLdapConnectorConfig){}

}

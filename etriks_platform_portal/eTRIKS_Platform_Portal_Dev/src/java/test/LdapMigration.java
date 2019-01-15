package test;

import org.etriks.security.ldapnew.entry.UserAccountsManager;
import org.etriks.security.ldapnew.entry.UserProjectGroupManager;
import org.etriks.security.ldapnew.entry.UserWPAndOrganizationManager;
import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig;

import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 1/20/16.
 */
public class LdapMigration {
    private LdapConnectorConfig origin;
    private LdapConnectorConfig destination;
    public LdapMigration(LdapConnectorConfig origin,LdapConnectorConfig destination){
        this.origin=origin;
        this.destination=destination;
    }

    public void transfereTRIKSUser() throws IOException, NamingException {
        UserAccountsManager originUsers=new UserAccountsManager(origin);
        UserAccountsManager destinationUsers=new UserAccountsManager(destination);
        NamingEnumeration<NameClassPair> allUsers = originUsers.getAllUsers();

        while(allUsers.hasMore()){

            String uid="";
            Map<String,String> userAttributes=new HashMap<String, String>();
            String userDN = allUsers.next().getName()+",ou=Users,dc=etriks,dc=eu";

            Map<String, List<Object>> userEntries = originUsers.getAllAttributesAsObjectByEntryDN(userDN);

           //System.out.println(userEntries.toString());
            for(Map.Entry<String,List<Object>> entry:userEntries.entrySet()){
                String key=entry.getKey();
                String value;
                if(key.equals("userPassword")){byte[] pwdByte=(byte[])entry.getValue().get(0);
                value=new String(pwdByte);
                }
                else {  value=entry.getValue().get(0).toString();}

                if(key.equals("gidNumber")){

                       value=this.eTRIKSLdapOrganizationMig(value);
                }
                if(key.equals("uid")){
                    uid=value;
                }


                userAttributes.put(key, value);
                System.out.println(key + ":" + value);
            }
           // System.out.println("uid:"+uid);
         //   System.out.println("userDN:"+userDN);
            userAttributes.remove("objectClass");

           // System.out.println(destinationUsers.updateUserAccountWithAutoUidNum(userAttributes).toString());
            destinationUsers.createUserAccount(destinationUsers.updateUserAccountWithAutoUidNum(userAttributes));
            this.addEtriksMembership(userDN, uid);

        }
    }

    public void addEtriksMembership(String userDN,String userId) throws IOException, NamingException {
        //Add user
        UserProjectGroupManager upg=new UserProjectGroupManager(destination);
        upg.addProjectPosixGroupMember("etriks_member","etriks",userId);
        upg.addProjectGroupOfNamesMember("etriks_member","portal","etriks",userDN);
        upg.closeContext();
    }

    public void addAbiriskMembership(String userDN,String userId) throws IOException, NamingException {
        UserProjectGroupManager upg=new UserProjectGroupManager(destination);
        upg.addProjectPosixGroupMember("abirisk_member","abirisk",userId);
        upg.addProjectGroupOfNamesMember("abirisk_member","portal","abirisk",userDN);
        upg.closeContext();
    }

    public String eTRIKSLdapOrganizationMig(String oldGidNum){
        String result;
        Map<String,String> OrgGidNumMap=new HashMap<String, String>();
        // alacris_old="506", new ="6000";
        OrgGidNumMap.put("506","6000");
        //AstraZeneca old="510",new=6001;
        OrgGidNumMap.put("510","6001");
        //Bayer old=532, new 6002
        OrgGidNumMap.put("532","6002");
        //BioSci old 501 new 6003
        OrgGidNumMap.put("501","6003");
        //Cdisc old 507 new 6004
        OrgGidNumMap.put("507","6004");
        //Cnrs old 506 new 6005
        OrgGidNumMap.put("506","6005");
        //Gsk old 512 new 6006
        OrgGidNumMap.put("512","6006");
        //Idbs 505 new 6007
        OrgGidNumMap.put("505","6007");
        //Imperial_college old 500 new 6008
        OrgGidNumMap.put("500","6008");
        //JNJ old 513 new 6009
        OrgGidNumMap.put("513","6009");
        //doubled jnj old 502 new 6009
        OrgGidNumMap.put("502","6009");
        // lundbeck old 509 new 6010
       OrgGidNumMap.put("509","6010");

        //merck 503 6011
        OrgGidNumMap.put("503","6011");

        //pfizer 526 6012
        OrgGidNumMap.put("526","6012");

        //QueenMary 504 6013
OrgGidNumMap.put("504","6013");
        //sanofi 508 6014
OrgGidNumMap.put("508","6014");
        //Hyve 529 6015
        OrgGidNumMap.put("529","6015");

        //uMich 530 6016
        OrgGidNumMap.put("530","6016");

        //lux 511 6017
        OrgGidNumMap.put("511","6017");

        OrgGidNumMap.put("518","6003");


        result=OrgGidNumMap.get(oldGidNum);
        if(result==null) result="6018";

        return result;
    }

 public void getNonExistingOrg() throws IOException {
     UserWPAndOrganizationManager oldldap=new UserWPAndOrganizationManager(origin);
     UserWPAndOrganizationManager newldap=new UserWPAndOrganizationManager(destination);
     Map<String, String> oldOrg = oldldap.getAllOrganizations();
     Map<String, String> newOrg = newldap.getAllOrganizations();
Map<String,String> result=new HashMap<String, String>();
     for(Map.Entry<String,String> entry:oldOrg.entrySet()){
         if(!newOrg.containsValue(entry.getValue())){
             result.put(entry.getKey(),entry.getValue());
         }
     }
     System.out.println("Size: " + result.size());
     System.out.println(result);

 }

    public Map<String,String> reverseMap(Map<String,String> map){
        Map<String,String> result=new HashMap<String, String>();
        for(Map.Entry<String,String> entry:map.entrySet()){
            result.put(entry.getValue(),entry.getKey());
        }

        return result;
    }

    public Map<String, String> getAbiriskOrgGidMapping() throws IOException {
        Map<String,String> result=new HashMap<String, String>();
        UserWPAndOrganizationManager oldldap=new UserWPAndOrganizationManager(origin);
        UserWPAndOrganizationManager newldap=new UserWPAndOrganizationManager(destination);
        Map<String, String> oldOrg = this.reverseMap(oldldap.getAllOrganizations());
        Map<String, String> newOrg = this.reverseMap(newldap.getAllOrganizations());
        for(Map.Entry<String,String> entry: oldOrg.entrySet()){
            if (newOrg.containsKey(entry.getKey())){
                result.put(entry.getValue(),newOrg.get(entry.getKey()));
            }

        }
        //System.out.println("Size"+result.size());
        //System.out.println("Contenu"+result.toString());
        return result;
    }

    public Map<String, String> getOncoTrackOrgGidMapping() throws IOException {
        Map<String,String> result=new HashMap<String, String>();
        UserWPAndOrganizationManager oldldap=new UserWPAndOrganizationManager(origin);
        UserWPAndOrganizationManager newldap=new UserWPAndOrganizationManager(destination);
        Map<String, String> oldOrg = this.reverseMap(oldldap.getAllOrganizations());
        Map<String, String> newOrg = this.reverseMap(newldap.getAllOrganizations());
        for(Map.Entry<String,String> entry: oldOrg.entrySet()){
            if (newOrg.containsKey(entry.getKey())){
                result.put(entry.getValue(),newOrg.get(entry.getKey()));
            }
            else {System.out.println(entry.getKey()+":"+entry.getValue());
            System.out.println(newOrg.get("University_Uppsala"));
            }

        }
        System.out.println(oldOrg.toString());
        System.out.println(newOrg.size());
        System.out.println("Size"+result.size());
        System.out.println("UU:"+result.get("524"));
        System.out.println("Contenu"+result.toString());
        return result;
    }

    public void transferAbiriskUser() throws IOException, NamingException {

        UserAccountsManager originUsers=new UserAccountsManager(origin);
        UserAccountsManager destinationUsers=new UserAccountsManager(destination);
        Map<String, String> abiOrgGid = this.getAbiriskOrgGidMapping();
        NamingEnumeration<NameClassPair> allOriginUsers = originUsers.getAllUsers();
        List<String> doubledUser=new ArrayList<String>();

        int counter=0;

        while(allOriginUsers.hasMore()){


            Map<String,String> userAttributes=new HashMap<String, String>();

            String userCN = allOriginUsers.next().getName();
            String userName=userCN.substring(3);
           String userDN=userCN+",ou=Users,dc=etriks,dc=eu";
            String uid=originUsers.getUid(userDN);

           /* if(!destinationUsers.userNameExisted(userName)){
                   counter++;
                Map<String, List<Object>> userEntries = originUsers.getAllAttributesAsObjectByEntryDN(userDN);
                for(Map.Entry<String,List<Object>> entry:userEntries.entrySet()){
                    String key=entry.getKey();
                    String value;
                    if(key.equals("userPassword")){byte[] pwdByte=(byte[])entry.getValue().get(0);
                        value=new String(pwdByte);
                    }
                    else {  value=entry.getValue().get(0).toString();}

                    if(key.equals("gidNumber")){

                        value=abiOrgGid.get(value);
                    }
                    if(key.equals("uid")){
                        uid=value;
                    }


                    userAttributes.put(key, value);
                  //  System.out.println(key + ":" + value);

                }*/
                // System.out.println("uid:"+uid);
                //   System.out.println("userDN:"+userDN);
               // userAttributes.remove("objectClass");
               // userAttributes.remove("uidNumber");
               // userAttributes.put("uidNumber", Integer.toString(uidNumberBase));
               // uidNumberBase++;
               // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
               // System.out.println(userAttributes.toString());
                //destinationUsers.createUserAccount(userAttributes);
                this.addAbiriskMembership(userDN,uid);
            /*}
            else {
                doubledUser.add(userDN);

            }*/



        }
       // System.out.println("Total user to be migrated:"+counter);
       // System.out.println("User already existed in the new ldap"+doubledUser.size()+doubledUser.toString());
    }

    public void transferOncoTrackUser() throws IOException, NamingException {
        //int uidNumberBase=10185;
        UserAccountsManager originUsers=new UserAccountsManager(origin);
        UserAccountsManager destinationUsers=new UserAccountsManager(destination);
       /* Map<String, String> oncoTrackOrgGid = this.getOncoTrackOrgGidMapping();
        oncoTrackOrgGid.put("524","6034");*/
        NamingEnumeration<NameClassPair> allOriginUsers = originUsers.getAllUsers();
        List<String> doubledUser=new ArrayList<String>();

        int counter=0;

        while(allOriginUsers.hasMore()){


            Map<String,String> userAttributes=new HashMap<String, String>();

            String userCN = allOriginUsers.next().getName();
            String userName=userCN.substring(3);
            String userDN=userCN+",ou=Users,dc=etriks,dc=eu";
            String uid=originUsers.getUid(userDN);

          /*  if(!destinationUsers.userNameExisted(userName)){
                counter++;
                Map<String, List<Object>> userEntries = originUsers.getAllAttributesAsObjectByEntryDN(userDN);
                for(Map.Entry<String,List<Object>> entry:userEntries.entrySet()){
                    String key=entry.getKey();
                    String value;
                    if(key.equals("userPassword")){byte[] pwdByte=(byte[])entry.getValue().get(0);
                        value=new String(pwdByte);
                    }
                    else {  value=entry.getValue().get(0).toString();}

                    if(key.equals("gidNumber")){

                        value=oncoTrackOrgGid.get(value);
                    }
                    if(key.equals("uid")){
                        uid=value;
                    }


                    userAttributes.put(key, value);
                    //  System.out.println(key + ":" + value);

                }
                // System.out.println("uid:"+uid);
                //   System.out.println("userDN:"+userDN);
                userAttributes.remove("objectClass");
                userAttributes.remove("uidNumber");
                userAttributes.put("uidNumber", Integer.toString(uidNumberBase));
                uidNumberBase++;
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                System.out.println(userAttributes.toString());
                destinationUsers.createUserAccount(userAttributes);
               //
            }
            else {
                doubledUser.add(userDN);

            }
*/
            this.addOncoTrackMembership(userDN,uid);

        }
        System.out.println("Total user to be migrated:"+counter);
        System.out.println("User already existed in the new ldap"+doubledUser.size()+doubledUser.toString());
    }

    public void addOncoTrackMembership(String userDN, String userId) throws IOException, NamingException {
        UserProjectGroupManager upg=new UserProjectGroupManager(destination);
        upg.addProjectPosixGroupMember("oncotrack_member","oncotrack",userId);
        upg.addProjectGroupOfNamesMember("oncotrack_member","portal","oncotrack",userDN);
        upg.closeContext();
    }

}

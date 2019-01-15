package org.etriks.utils;

import org.etriks.security.encryption.PasswordEncrytor;
import org.etriks.security.ldap.entry.UserAccountManager;
import org.etriks.security.ldap.entry.UserGroupManager;
import org.etriks.security.ldap.serverprofile.EtriksMemberLdapConnectorConfig;
import org.etriks.security.password.RandomPasswordGenerator;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 3/10/15.
 *
 * Copy the code inside generate method to main, it will work
 * Be ware of which ldap server you are connecting and uid number without double
 */
public class TrainingAccountGenerator {

    public void generate(){

        /*Script for generating trainnig account with random password and emails of ben*/

//Creat a csv file to store trainee login and password

        CSVFileManager csvFM=new CSVFileManager("/home/pliu/Downloads/trainAccount.txt");
        //Enter the column value
        csvFM.writeToCsvFile();
        List<String> columnValue=new ArrayList<String>();
        columnValue.add("LOGIN");
        columnValue.add("Password");
        csvFM.setColumnValue(columnValue);
        // The ldap config for the etriks member ldap server
        EtriksMemberLdapConnectorConfig etriksLdap=new EtriksMemberLdapConnectorConfig();


        //creat the instance of the user account mananger, the ldapurl must be the ldap which you want to put people in
        UserAccountManager accReg = new UserAccountManager(etriksLdap,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");

        //create the instance of the user group manager

        UserGroupManager grpReg = new UserGroupManager(etriksLdap,"dc=etriks,dc=eu");

        //create the instance of the password encrypter.
        PasswordEncrytor pwdEncrypter=new PasswordEncrytor();

        //generate a random password
        RandomPasswordGenerator rpg=new RandomPasswordGenerator();




        for(int i=1;i<50;i++){

            Map<String,String> entryAttributes=new HashMap<String,String>();
            //service which add the user entry to the ldap server. desactivate for now
            //ldapUserEntrySaveService.saveUserEntry(cn, uidNumber, sn, password, uidNumber, gidNumber, homeDirectory, loginShell, mail);
            String cn="Transmart Trainee"+i;
            String uid="t.trainee"+i;
            String sn="Trainee"+i;
            int uidNumber=17000+i;
            int gidNumber=666;
            String homeDirectory="/home/users/c.trainee"+i;
            String loginShell="/bin/false";
            String mail="cc-etriks@cc.in2p3.fr";
            String givenName="Transmart";

            String password=rpg.generateRandomPassword();
            //Encrypte the password,
            String encPassword= null;
            try {
                encPassword = pwdEncrypter.digestBase64("sha", password);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("traning account password encryption fail"+e.toString());
            }

            entryAttributes.put("cn", cn);
            entryAttributes.put("uid", uid);
            entryAttributes.put("sn", sn);
            entryAttributes.put("userPassword", encPassword);
            entryAttributes.put("uidNumber", Integer.toString(uidNumber));
            entryAttributes.put("gidNumber", Integer.toString(gidNumber));
            entryAttributes.put("homeDirectory", homeDirectory);
            entryAttributes.put("loginShell", loginShell);
            entryAttributes.put("mail", mail);
            entryAttributes.put("givenName", givenName);

            //write to the file
            List<String> values=new ArrayList<String>();
            values.add(uid);
            values.add(password);
            csvFM.addline(values);

            System.out.println(entryAttributes.toString());
            //save the account on ldap server
            boolean flag = accReg.creatUserAccount(entryAttributes);

            if (flag==true){
                System.out.println("ldap creation ok");
            }
            else {
                System.out.println("ldap creation fail");
            }



        }

        csvFM.stopWriting();


        /*End of the script*/
}
}

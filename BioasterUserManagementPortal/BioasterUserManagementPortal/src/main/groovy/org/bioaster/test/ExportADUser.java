package org.bioaster.test;

import com.google.gson.Gson;
import org.bioaster.security.adManager.ADUserAccountManager;
import org.bioaster.security.config.ADConfig;
import org.bioaster.security.config.ILdapServerConnectorConfig;
import org.bioaster.security.ldapManager.ADUserNoAuthAccountManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by pliu on 9/27/17.
 */
public class ExportADUser{

    private ILdapServerConnectorConfig adConf;
    private ADUserNoAuthAccountManager adUserManager;

    public ExportADUser(){
        adConf=ADConfig.getInstance();
        try {
            adUserManager=new ADUserNoAuthAccountManager(adConf);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<String, Map> getAllUserAttr() {
        Map<String, Map> result = new HashMap<>();
        List<String> parentEntryList= new ArrayList<String>();
        parentEntryList.add("OU=UTILISATEURS,DC=Bioaster,DC=local");
        List<String> userList = adUserManager.getALLADUserDNList(parentEntryList);
       System.out.println(userList);
       for(String userDn: userList){
           Map<String, List<Object>> allAttrs = adUserManager.getUserAttrs(userDn);
           Map<String, String> requiredAttrs = buildRequiredUserAttr(allAttrs);
           result.put(userDn,requiredAttrs);
       }

//        String userName = "pengfei";
//        Map<String, String> userAttr = new HashMap<String, String>();
//        userAttr.put("LastName", "Liu");
//        userAttr.put("FirstName", "Pengfei");
//        userAttr.put("Login", "pliu");
//        userAttr.put("Mail", "pengfei.liu@bioaster.org");
//        userAttr.put("Department", "DUT-UTEC06");
//        userAttr.put("Location", "BIOASTER Lyon - Niveau 4 - Bureau A4.2");
//        userAttr.put("City", "Lyon");
//        userAttr.put("Country", "France");
//        result.put(userName, userAttr);
        return result;
    }

    public void getJsonFromObj(){
        Gson gson = new Gson();
        String json = gson.toJson(this.getAllUserAttr());
        System.out.println(json);
    }

    public Map<String,String> buildRequiredUserAttr(Map<String, List<Object>> userAttrs) {
        Map<String,String> requiredAttrs = new HashMap<String,String>();
        String mail=null;
        String department=null;
        String cn=null;
        String physicalDeliveryOfficeName=null;
        String uid=null;
        try {
             mail= userAttrs.get("mail").get(0).toString();
             department= userAttrs.get("department").get(0).toString();
             cn = userAttrs.get("cn").get(0).toString();
             physicalDeliveryOfficeName = userAttrs.get("physicalDeliveryOfficeName").get(0).toString();
             uid = userAttrs.get("sAMAccountName").get(0).toString();
        }
        catch(NullPointerException e){

        }
        requiredAttrs.put("Mail",mail);
        requiredAttrs.put("Department",department);
        requiredAttrs.put("Location", physicalDeliveryOfficeName);
        requiredAttrs.put("uid",uid);

        System.out.println("mail: "+mail);
        System.out.println("department: "+department);
        System.out.println("cn: "+cn);
        System.out.println("physicalDeliveryOfficeName:"+physicalDeliveryOfficeName);
        System.out.println("sAMAccountName:"+uid);
        return requiredAttrs;

    }


}



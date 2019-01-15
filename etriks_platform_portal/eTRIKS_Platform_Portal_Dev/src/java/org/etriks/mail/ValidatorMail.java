package org.etriks.mail;

import java.util.*;

/**
 * Created by pliu on 4/3/14.
 */
public class ValidatorMail {

    private static Map<String,String> userMailMap=new HashMap<String,String>();

    public ValidatorMail(){
        this.initUserMailMap();
    }

    public String getMail(String userName){

        return userMailMap.get(userName);
    }

    public void putNewUserMail(String userName,String email){

    }
    public List<String> getAllUserMails(){
        Iterator it=userMailMap.entrySet().iterator();
        List<String> allUserMails=new ArrayList<String>();
        while (it.hasNext()){
           Map.Entry paires = (Map.Entry)it.next();
            String mail=(String)paires.getValue();
           // System.out.println(paires.getKey()+"="+mail);
            allUserMails.add(mail);
        }
        return allUserMails;
    }

    public void initUserMailMap(){

        userMailMap.put("Ghita_Rahal","ghita.rahal@cc.in2p3.fr");
        userMailMap.put("Benjamin_Guillon","benjamin.guillon@cc.in2p3.fr");
        userMailMap.put("Pengfei_Liu","pliu@cc.in2p3.fr");

    }
}

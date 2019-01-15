package org.etriks.admin.dashboard;

/**
 * Created by pliu on 4/15/14.
 */
public class ExportUser {
   private final String name;
    private final String mail;
    private final String uid;

    public ExportUser(String name, String mail, String uid) {
        this.name = name;
        this.mail = mail;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }



    public String getMail() {
        return mail;
    }


    public String getUid() {
        return uid;
    }


}

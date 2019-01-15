package org.bioaster.registration

import org.bioaster.log.LogType
import org.bioaster.security.MemberManagerService
import org.bioaster.security.config.ADConfig
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.config.OpenLdapConfig
import org.bioaster.security.ldapManager.ImportADAccountToLdap
import org.bioaster.log.AccessLogService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.naming.NamingException

class ImportADUserController {
    //Application access log
    AccessLogService accessLogService;
    MemberManagerService memberManagerService;


    def index() {
    }


    def importADEntry(){
        String uid=params.uid
        String password=params.userPassword

        List<String> messageList=new ArrayList<String>();

        ILdapServerConnectorConfig adConf=ADConfig.getInstance();
        ILdapServerConnectorConfig ldapConf=OpenLdapConfig.getInstance();
        if(memberManagerService.checkUidExistence(ldapConf,uid)){
            flash.error="Your uid already exists in OPENLdap server. You may have already imported your AD account to OPENLdap"
            redirect (view:"importADEntry")
        }
        else{

        boolean result=memberManagerService.importADAccountToLdap(adConf,ldapConf,uid,password);
        if (result) {
            String message= "Your account with uid: "+uid+" has been imported from AD to Openldap server";
            flash.message=message
            String userIp=request.getRemoteAddr()
            //Write this information into the access log
            accessLogService.saveLog(message,userIp,LogType.Info);
            render (view:"index")
        }
        else {
            flash.error="Your login or password id wrong"
            redirect (action:"index")}
    }
    }

}

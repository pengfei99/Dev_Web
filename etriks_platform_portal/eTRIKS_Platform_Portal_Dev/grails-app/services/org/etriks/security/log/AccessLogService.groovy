package org.etriks.security.log

import grails.transaction.Transactional
import org.etriks.log.LogType
import org.etriks.log.ProjectId

import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional
class AccessLogService {


    @Transactional
    def void saveLog(String logMessage,String userId,ProjectId projectId,LogType type){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        def AccessLog accessLogInstance=new AccessLog();
        accessLogInstance.setLogMessage(logMessage);
        accessLogInstance.setUserId(userId);
        accessLogInstance.setProjectId(projectId);
        accessLogInstance.setLogType(type);
        accessLogInstance.setDateCreated(date);
        accessLogInstance.save(flush:true)
    }
}

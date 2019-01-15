package org.bioaster.log

import grails.transaction.Transactional


@Transactional
class AccessLogService {


    @Transactional
    def void saveLog(String logMessage, String userId, LogType type){


        Date date = new Date();
        AccessLog accessLogInstance=new AccessLog();
        accessLogInstance.setLogMessage(logMessage);
        accessLogInstance.setUserId(userId);
        accessLogInstance.setLogType(type);
        accessLogInstance.setDateCreated(date);
        println(accessLogInstance.toString())
        accessLogInstance.save(flush:true)
    }
}


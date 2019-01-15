package org.etriks.security.log

import org.etriks.log.LogType
import org.etriks.log.ProjectId

class AccessLog {

    def Date dateCreated;
    def String logMessage;
    def String userId;
    def ProjectId projectId;
    def LogType logType;

    static constraints = {
    }
}

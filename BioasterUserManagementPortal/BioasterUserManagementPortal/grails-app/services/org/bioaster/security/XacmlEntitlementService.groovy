package org.bioaster.security

import grails.transaction.Transactional
import org.bioaster.security.authorization.xacml.PEPClient
import org.bioaster.security.authorization.xacml.PolicyDecision

@Transactional
class XacmlEntitlementService {

    PEPClient client=new PEPClient();

     PolicyDecision getDecisionByAttributes(String uid, String role, String action, String targetId, String envId) {

         return client.getDecisionByAttributes(uid, role, targetId, action, envId);
    }
}

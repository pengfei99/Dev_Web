package org.bioaster.admin

import org.bioaster.security.XacmlEntitlementService
import org.bioaster.security.authorization.xacml.PolicyDecision

class BioasterSecurityPolicyTesterController {

  def XacmlEntitlementService xacmlEntitlementService;

    def index() {}

    def evaluateRequest() {

        String uid = params.userId;
        String uRole = params.userRole;
        String targetId = params.targetId;
        String action = params.uaction;
        String envId= params.envId
        //println(uid+uRole+targetId+action);
        PolicyDecision decision = xacmlEntitlementService.getDecisionByAttributes(uid, uRole, action,targetId, envId);
        String xmlDecision = decision.getXMLDecision();
        String boolDecision = decision.getBooleanDecision();
        String textDecision = decision.getStringDecision();
        render(view: "decision", model: [xmlDecision: xmlDecision, boolDecision: boolDecision, textDecision: textDecision]);

    }
}

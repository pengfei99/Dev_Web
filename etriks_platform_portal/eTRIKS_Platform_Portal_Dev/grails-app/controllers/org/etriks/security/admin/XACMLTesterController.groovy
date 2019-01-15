package org.etriks.security.admin

import org.etriks.security.authorization.xacml.PEPClient
import org.etriks.security.authorization.xacml.PolicyDecision

class XACMLTesterController {

    def PEPClient client=new PEPClient();
    def index() {}
    def evaluateRequest(){

        def String uid=params.userId;
        def String uRole=params.userRole;
        def String targetId=params.targetId;
        def String action=params.uaction;
        //println(uid+uRole+targetId+action);
        PolicyDecision decision=client.evaluateRequest(uid,uRole,targetId,action);
        String xmlDecision=decision.getXMLDecision();
        String boolDecision=decision.getBooleanDecision();
        String textDecision=decision.getStringDecision();
        render(view:"decision" ,model:[xmlDecision:xmlDecision,boolDecision:boolDecision,textDecision:textDecision]);
    }
}

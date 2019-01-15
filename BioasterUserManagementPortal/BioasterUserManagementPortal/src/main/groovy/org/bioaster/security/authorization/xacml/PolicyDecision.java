package org.bioaster.security.authorization.xacml;

/**
 * Created by pliu on 3/28/17.
 */
public class PolicyDecision {
    private final String decision;
    public PolicyDecision(String decision){
        this.decision = decision;
    }

    public boolean getBooleanDecision(){
        if(decision.contains("Permit")) {
            return true;
        }
        else return false;
    }
    public String getXMLDecision(){
        return decision;
    }

    public String getStringDecision(){
        if(decision.contains("Permit")) {
            return "Permit";
        }
        else if(decision.contains("Deny")){ return "Deny";}
        else if(decision.contains("NotApplicable")){return "NotApplicable"; }
        else return "NotApplicable";
    }

}

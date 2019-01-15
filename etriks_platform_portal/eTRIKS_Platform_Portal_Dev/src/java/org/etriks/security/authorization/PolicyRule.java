package org.etriks.security.authorization;

/**
 * Created by pliu on 4/2/14.
 */
public interface PolicyRule {
    public Subject getSubjcet();
    public Action getAction();
    public TargetObject getTargetObject();
    public boolean getDecision();
    public boolean applicabaleCheck(Request r);
}

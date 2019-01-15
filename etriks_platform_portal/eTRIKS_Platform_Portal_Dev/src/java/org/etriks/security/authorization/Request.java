package org.etriks.security.authorization;

/**
 * Created by pliu on 4/2/14.
 */
public interface Request {

    public Subject getSubject();
    public Action getAction();
    public TargetObject getTargetObject();
}

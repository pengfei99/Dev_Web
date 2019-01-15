package org.etriks.security.authorization;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by pliu on 4/2/14.
 */
public interface Subject {
    public String getSubjectId();
    public Collection<GrantedAuthority> getSubjectRoles();
}

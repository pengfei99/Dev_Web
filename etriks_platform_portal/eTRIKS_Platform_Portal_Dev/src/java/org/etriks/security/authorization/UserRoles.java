package org.etriks.security.authorization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Created by pliu on 4/2/14.
 */
public class UserRoles {
    public static GrantedAuthority Role_Admin=new SimpleGrantedAuthority("Role_Admin");
    public static GrantedAuthority Role_Dev=new SimpleGrantedAuthority("Role_Dev");
    public static GrantedAuthority Role_Manager=new SimpleGrantedAuthority("Role_Admin");
}

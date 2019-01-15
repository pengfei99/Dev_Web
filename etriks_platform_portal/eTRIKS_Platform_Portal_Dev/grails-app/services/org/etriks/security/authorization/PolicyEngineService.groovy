package org.etriks.security.authorization

import grails.transaction.Transactional
import org.springframework.security.core.GrantedAuthority

@Transactional
class PolicyEngineService {
    def PolicyEngine pe=new PolicyEngine();

    def boolean checkRoles(String requiredRole,Collection<GrantedAuthority> userRoles){
     return  pe.checkRoles(requiredRole,userRoles);
    }

    def boolean checkRequestByAttributes(String userId,Collection<GrantedAuthority> userRoles,String targetId,String action){



        return     pe.checkRequestByAttributes(userId,userRoles,targetId,action);

    }
}

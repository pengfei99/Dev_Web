package org.bioaster.security.authorization;

import org.bioaster.security.config.BUMPConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 3/6/17.
 */
public class PolicyRules {

    private static PolicyRules policyRulesInstance=new PolicyRules();
    private static Map<String,List<String>> rules;
    private static String admin;
    private static String validator;

    private PolicyRules(){
        rules=new HashMap<String,List<String>>();
        admin=BUMPConfig.adminRole;
        validator= BUMPConfig.validatorRole;
        this.init();}

    public static PolicyRules getInstance(){
        return policyRulesInstance;
    }


    /*This method gets the authorized roles of a controller, by default only admin is allowed, for specific controller, it will get
    rules from rules
    * */
    public static List<String> getControllerRequireRoles(String controllerName){
        List<String> requiredRoles = rules.get(controllerName);

        if (requiredRoles==null){
            requiredRoles=new ArrayList<String>();
            requiredRoles.add(admin);
        }

        return requiredRoles;
    }

    /*This method specifis special access control rules for controller,*/
    public static void init(){

        //rules for bioaster admin dashboard
        List<String> bioasterAdminDashBoardRoles=new ArrayList<String>();
        bioasterAdminDashBoardRoles.add(validator);
        bioasterAdminDashBoardRoles.add(admin);
        rules.put("bioasterAdminDashBoard",bioasterAdminDashBoardRoles);

        //rules for bioasterUserRegistrationValidation
        List<String> bioasterUserRegistrationValidationRoles=new ArrayList<String>();
        bioasterUserRegistrationValidationRoles.add(validator);
        bioasterUserRegistrationValidationRoles.add(admin);
        rules.put("bioasterUserRegistrationValidation",bioasterUserRegistrationValidationRoles);


        //rules for other controller
    }
}

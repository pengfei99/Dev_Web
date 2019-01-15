package org.bioaster.security.config;

/**
 * Created by pliu on 3/16/17.
 */
public class BUMPConfig {
    public static final int passwordStrength=25;
    public static final String validatorRole="BUMP_validator";
    public static final String adminRole="BUMP_admin";
    public static final String supportMail="admin@bioaster.org";
    public static final String webMasterMail="bump_admin@biodater.org";
    public static final String loginShell="/bin/bash";
    public static final String homeDir="/home/";
    public static final String mailTemplatePath="/home/pliu/workspace/BioasterUserManagementPortal/src/main/source/templates/";
   //public static final String mailTemplatePath="/etc/bump/mails/templates/";

}

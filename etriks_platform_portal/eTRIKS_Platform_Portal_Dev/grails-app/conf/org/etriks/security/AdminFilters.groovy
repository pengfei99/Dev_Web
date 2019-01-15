package org.etriks.security

import org.etriks.security.authorization.PolicyEngineService

class AdminFilters {

    def PolicyEngineService policyEngineService;

    def filters = {



        //	this rule defins that only admin has the access of the admin dashbord

        dashBoardFilters(controller:'dashBoard',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)){
                    if(!policyEngineService.checkRoles("Role_AbiriskAdmin",userRoles)){
                        if(!policyEngineService.checkRoles("Role_OncoTrackAdmin",userRoles)){
                            if(!policyEngineService.checkRoles("Role_BioasterAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                          }
                    }
                }
                }
         //
        }
        }
        //end of dashBoardFilters

        /*****************************************************
         * The access control rules for the etriks admin dashboard
         * *********************************************************/

        //	this rule defins that only admin has the access of the Etriks registrationRequestValidation

        etriksRegistrationRequestValidationFilters(controller:'registrationRequestValidation',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of registrationRequestValidationFilters

        //	this rule defins that only admin has the access of the etriks MemberManager

        etriksMemberManagerFilters(controller:'etriksMemberManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of etriksMemberManagerFilters

        //	this rule defins that only admin has the access of the etriks organization Manager

        etriksOrganizationManagerFilters(controller:'etriksOrganizationManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of etriks OrganizationManagerFilters

        /*****************************************************
        * The access control rules for the abirisk admin dashboard
        * *********************************************************/

        //	this rule defins that only admin and abirisk admin has the access of the abirisk registrationRequestValidation

        abiriskRegistrationRequestValidationFilters(controller:'abiriskRegistrationRequestValidation',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_AbiriskAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk registrationRequestValidationFilters


//	this rule defins that only admin and abirisk admin has the access of the abirisk MemberManager

        abiriskMemberManagerFilters(controller:'abiriskMemberManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_AbiriskAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk MemberManagerFilters


//	this rule defins that only admin and abirisk admin has the access of the abirisk group Manager

        abiriskGroupManagerFilters(controller:'abiriskGroupManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_AbiriskAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk GroupManagerFilters


//	this rule defins that only admin and abirisk admin has the access of the abirisk organization Manager

        abiriskOrganizationManagerFilters(controller:'abiriskOrganizationManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_AbiriskAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk OrganizationManagerFilters

//	this rule defins that only admin and abirisk admin has the access of the abirisk transmart group MemberManager

        abiriskMemberManagerFilters(controller:'abiriskTransmartGroupManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_AbiriskAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk transmart group manager Filters

        /*****************************************************
         * The access control rules for the oncoTrack admin dashboard
         * *********************************************************/

        //	this rule defins that only admin and oncoTrack admin has the access of the oncoTrack registrationRequestValidation

        oncoTrackRegistrationRequestValidationFilters(controller:'oncoTrackRegistrationRequestValidation',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_OncoTrackAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk registrationRequestValidationFilters


//	this rule defins that only admin and oncoTrack admin has the access of the oncoTrack MemberManager

        oncoTrackMemberManagerFilters(controller:'oncoTrackMemberManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_OncoTrackAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk MemberManagerFilters


//	this rule defins that only admin and oncoTrack admin has the access of the oncoTrack group Manager

        oncoTrackGroupManagerFilters(controller:'oncoTrackGroupManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_OncoTrackAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk GroupManagerFilters


//	this rule defins that only admin and oncoTrack admin has the access of the oncoTrack organization Manager

        oncoTrackOrganizationManagerFilters(controller:'oncoTrackOrganizationManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_OncoTrackAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
        //	this rule defins that only admin and abirisk admin has the access of the abirisk transmart group MemberManager

        oncoTrackMemberManagerFilters(controller:'oncoTrackTransmartGroupManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_OncoTrackAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk transmart group manager Filters
/*****************************************End of access rule of oncotrack*******************************************/
        //	this rule defins that admin and oncotrack admin, abirisk admin and bioaster admin has the access of the access log

        accessLogManagerFilters(controller:'accessLog',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_OncoTrackAdmin",userRoles)&&!policyEngineService.checkRoles("Role_AbiriskAdmin",userRoles)&&!policyEngineService.checkRoles("Role_BioasterAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of roleManagerFilters



        /*****************************************************
         * The access control rules for the bioaster admin dashboard
         * *********************************************************/

        //	this rule defins that only admin and bioaster admin has the access of the bioaster registrationRequestValidation

        bioasterRegistrationRequestValidationFilters(controller:'bioasterRegistrationRequestValidation',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_BioasterAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of bioaster registrationRequestValidationFilters


//	this rule defins that only admin and abirisk admin has the access of the abirisk MemberManager

        bioasterMemberManagerFilters(controller:'bioasterMemberManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_BioasterAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of abirisk MemberManagerFilters


//	this rule defins that only admin and abirisk admin has the access of the abirisk group Manager

        bioasterGroupManagerFilters(controller:'bioasterGroupManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_BioasterAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of bioaster GroupManagerFilters


//	this rule defins that only admin and bioaster admin has the access of the bioaster organization Manager

        bioasterOrganizationManagerFilters(controller:'bioasterOrganizationManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)&&!policyEngineService.checkRoles("Role_BioasterAdmin",userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of bioaster OrganizationManagerFilters

//	this rule defins that only admin has the access of the bioaster organization Manager

        bioasterOrganizationManagerFilters(controller:'ldapMemberManager',action:"*"){
            before={
                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def  userRoles=session.userDetails.getRole();
                if(!policyEngineService.checkRoles("Role_Admin", userRoles)){
                    flash.message="Sorry, the requested services are reserved for admin user only"
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }

            }
        }
//end of ldap member manager Filters

        //	this rule defins that admin has the access of the XACML tester

        XACMLTesterFilters(controller:'XACMLTester',action:"*"){
            before={

                if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                    return false}

                def uid=session.userDetails.getUserName();
                def userRoles=session.userDetails.getRole();
                def targetId= actionName;
                def actionId="read";


                if(!policyEngineService.checkRequestByAttributes(uid,userRoles,targetId,actionId)){
                   /* flash.message="Sorry, the requested services are reserved for admin user only"*/
                    redirect(controller:"AccessControlException",action:"accessDeny")
                    return false
                }
                }

            }

//end of XACMLManagerFilters


    //	this rule defins that admin has the access of the videoStream

   /* VideoStreamFilters(controller:'VideoStream',action:"*"){
        before={

            if(session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")
                return false}

            def uid=session.userDetails.getUserName();
            def userRoles=session.userDetails.getRole();
            def targetId= actionName;
            def actionId="read";


            if(!policyEngineService.checkRequestByAttributes(uid,userRoles,targetId,actionId)){
                *//* flash.message="Sorry, the requested services are reserved for admin user only"*//*
                redirect(controller:"AccessControlException",action:"accessDeny")
                return false
            }
        }

    }*/
        //end of XACMLManagerFilters
}
}







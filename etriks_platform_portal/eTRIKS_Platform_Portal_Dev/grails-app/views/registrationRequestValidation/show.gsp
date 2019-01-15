
<%@ page import="org.etriks.security.register.EtriksMemberAccount" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="EtriksMemberAccount" />

		<title>eTRIKS member registration validation</title>
	</head>
	<body>


		<div id="show-etriksMemberAccount" class="content scaffold-show" role="main">
			<h3>eTRIKS member registration validation</h3>
            <hr>

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

            %{--User information container--}%
            <div class="container">
        <div class="row">
        <div class="col-md-3 text-right">
                <g:if test="${etriksMemberAccountInstance?.firstName}">
                    <span id="firstName-label" class="property-label">First Name: </span>
                </div>
                    <div class="col-md-3 text-left">
                        <span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${etriksMemberAccountInstance}" field="firstName"/></span>
                    </div>
                 </div>
                </g:if>
<br>
                <g:if test="${etriksMemberAccountInstance?.lastName}">
                <div class="row">
                    <div class="col-md-3 text-right">
                    <span id="lastName-label" class="property-label">Last Name: </span>
                   </div>
                    <div class="col-md-3 text-left">
                    <span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${etriksMemberAccountInstance}" field="lastName"/></span>
                    </div>
                </div>
                    </g:if>
<br>
                    <g:if test="${etriksMemberAccountInstance?.email}">
                        <div class="row">
                            <div class="col-md-3 text-right">
                            <span id="email-label" class="property-label">Email:</span>
                            </div>
                            <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${etriksMemberAccountInstance}" field="email"/></span>
                         </div>
                        </div>
                    </g:if>
<br>
                    <g:if test="${etriksMemberAccountInstance?.organization}">
                        <div class="row">
                            <div class="col-md-3 text-right">
                            <span id="organization-label" class="property-label">Organization:</span>
                            </div>
                            <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="organization-label"><g:fieldValue bean="${etriksMemberAccountInstance}" field="organization"/></span>
                            </div>
                        </div>
                    </g:if>

                <br>

                    <g:if test="${etriksMemberAccountInstance?.workPackage}">
                        <div class="row">
                            <div class="col-md-3 text-right">
                            <span id="workPackage-label" class="property-label">Work Package:</span>
</div>
                            <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="workPackage-label"><g:fieldValue bean="${etriksMemberAccountInstance}" field="workPackage"/></span>
</div>
                        </div>
                    </g:if>

<br>

            </div>
   </div>




%{--data privacy protection training--}%
            <div class="row">
            <g:form name="myform">

                <div id="privacyProtectionAgreement">
                    <span style="color:red"  font-size="200%">Data Privacy Protection training :</span>
                    <br>
                    <br>
                    Every user who wants to have an account in eTRIKS platform must receive a privacy protection training. This training are provided here(To be replaced by a link offered by wp7).
                    As the validator of the user account, you need to make sure this user has received the training information. If the user is not informed, please do not validate his registration
                    request.

                </div>

                <br>
                <div id="privacyProtectionAgreementRadioBox"><input type="checkbox" name="validatorAgreePrivacyTraining" value=true> I confirm that I have proivded privcacy protection training information to this user </div>

<hr>
                    <g:hiddenField name="id" value="${etriksMemberAccountInstance?.id}" />
                   <div class="row">
                       <div class="col-md-6 text-center">
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </div>
                       <div class="col-md-3 text-center">
                    <g:actionSubmit class="edit" action="checkPrivacyTraining" id="${etriksMemberAccountInstance?.id}" value="Validate request"/>
                     </div>
                       </div>

            </g:form>

            </div>


		</div>
	</body>
</html>

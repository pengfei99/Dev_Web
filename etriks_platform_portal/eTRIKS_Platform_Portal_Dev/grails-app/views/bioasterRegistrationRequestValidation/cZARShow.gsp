
<%@ page import="org.etriks.security.register.BioasterMemberAccount" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<title>bioaster member account validation</title>
	</head>
	<body>

		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/dashBoard/bioasterAdminDashBoard')}">bioaster admin dashboard</a></li>
			</ul>
		</div>

		<div id="show-bioasterMemberAccount" class="content scaffold-show" role="main">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
                        </div>
                    </div>
                <br>
			
				<g:if test="${bioasterMemberAccountInstance?.firstName}">
				<div class="row">
                    <div class="col-md-3 text-right">
                        <span id="firstName-label" class="property-label"> First Name: </span>
                    </div>
                    <div class="col-md-3 text-left">
                        <span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${bioasterMemberAccountInstance}" field="firstName"/></span>
                    </div>
				</div>
				</g:if>
                <br>

				<g:if test="${bioasterMemberAccountInstance?.lastName}">
                    <div class="row">
                        <div class="col-md-3 text-right">
                            <span id="lastName-label" class="property-label">Last Name: </span>
                        </div>
                        <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${bioasterMemberAccountInstance}" field="lastName"/></span>
                        </div>
                    </div>
				</g:if>
                <br>

				<g:if test="${bioasterMemberAccountInstance?.email}">
                    <div class="row">
                        <div class="col-md-3 text-right">
                            <span id="email-label" class="property-label"> Email: </span>
                        </div>
                        <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${bioasterMemberAccountInstance}" field="email"/></span>
                        </div>
                    </div>
				</g:if>
                <br>
			
				<g:if test="${bioasterMemberAccountInstance?.organization}">
                    <div class="row">
                        <div class="col-md-3 text-right">
                            <span id="organization-label" class="property-label">Organization: </span>
                        </div>
                        <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="organization-label"><g:fieldValue bean="${bioasterMemberAccountInstance}" field="organization"/></span>
                        </div>
                    </div>
				</g:if>
                <br>
				<g:if test="${bioasterMemberAccountInstance?.groups}">
                    <div class="row">
                        <div class="col-md-3 text-right">
                            <span id="workPackage-label" class="property-label"> Groups: </span>
                        </div>
                        <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="workPackage-label"><g:fieldValue bean="${bioasterMemberAccountInstance}" field="groups"/></span>
                        </div>
                    </div>
				</g:if>
                <br>
                <br>

<div class="row">
      <div class="col-md-12">
                <div id="privacyProtectionAgreement">
                    <span style="color:red"  font-size="200%">Data Privacy Protection training :</span>
                    <br>
                    <br>
                    Every user who wants to have an account in eTRIKS platform must receive a privacy protection training. This training are provided here(To be replaced by a link offered by wp7).
                    As the validator of the user account, you need to make sure this user has received the training information. If the user is not informed, please do not validate his registration
                    request.

                </div>
</div>
</div>
			<g:form>
                <br>
                <div id="privacyProtectionAgreementRadioBox"><input type="checkbox" name="validatorAgreePrivacyTraining" value=true> I confirm that I have proivded privcacy protection training information to this user </div>
<div class="row">
                <fieldset class="buttons">

					<g:hiddenField name="id" value="${bioasterMemberAccountInstance?.id}" />
					%{--<g:link class="edit" action="edit" id="${bioasterMemberAccountInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--}%
<div class="col-md-6 text-center">
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </div>
                    <div class="col-md-4 text-center">
                    <g:actionSubmit class="edit" action="checkPrivacyTraining"  id="${bioasterMemberAccountInstance?.id}" value="Validate request as project CZAR"  />
                </div>
                </fieldset>
</div>
			</g:form>
		</div>
            </div>
	</body>
</html>

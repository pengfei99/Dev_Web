
<%@ page import="org.etriks.security.register.AbiriskMemberAccount" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>abirisk Member account registration</title>
	</head>

	<body>

		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard')}">abirisk admin dashboard</a></li>
			</ul>
		</div>

    <br>
    <br>
		<div id="show-abiriskMemberAccount" class="content scaffold-show" role="main">

            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                    </div>
                </div>

                <div class="row">
                    <g:if test="${abiriskMemberAccountInstance?.firstName}">
                        <div class="col-md-3 text-right">
                            <span id="firstName-label" class="property-label">First Name:</span>
                        </div>
                        <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${abiriskMemberAccountInstance}" field="firstName"/></span>
                        </div>
                    </g:if>
                </div>

                <div class="row">
                    <g:if test="${abiriskMemberAccountInstance?.lastName}">
                        <div class="col-md-3 text-right">
                            <span id="lastName-label" class="property-label">Last Name:</span>
                        </div>
                        <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${abiriskMemberAccountInstance}" field="lastName"/></span>
                        </div>
                    </g:if>
                </div>

                <div class="row">
                    <g:if test="${abiriskMemberAccountInstance?.email}">
                        <div class="col-md-3 text-right">
                            <span id="email-label" class="property-label">Email:</span>
                        </div>
                        <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${abiriskMemberAccountInstance}" field="email"/></span>
                        </div>
                    </g:if>
                </div>

                <div class="row">
                    <g:if test="${abiriskMemberAccountInstance?.organization}">
                        <div class="col-md-3 text-right">
                            <span id="organization-label" class="property-label">Organization: </span>
                        </div>
                        <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="organization-label"><g:fieldValue bean="${abiriskMemberAccountInstance}" field="organization"/></span>
                        </div>
                    </g:if>
                </div>


                <div class="row">
                    <g:if test="${abiriskMemberAccountInstance?.groups}">
                        <div class="col-md-3 text-right">
                            <span id="workPackage-label" class="property-label">Groups: </span>
                        </div>
                        <div class="col-md-3 text-left">
                            <span class="property-value" aria-labelledby="workPackage-label"><g:fieldValue bean="${abiriskMemberAccountInstance}" field="groups"/></span>
                        </div>
                    </g:if>
                </div>

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
                <div class="row">
                    <div class="col-md-12">
                        <br>
                        <div id="privacyProtectionAgreementRadioBox"><input type="checkbox" name="validatorAgreePrivacyTraining" value=true> I confirm that I have proivded privcacy protection training information to this user </div>
                    </div>
                </div>



<div class="row">


    <fieldset class="buttons">

        <g:hiddenField name="id" value="${abiriskMemberAccountInstance?.id}" />
        %{--<g:link class="edit" action="edit" id="${abiriskMemberAccountInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--}%
        <div class="col-md-6 text-center">
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </div>
        <div class="col-md-4 text-center"><g:actionSubmit class="edit" action="checkPrivacyTraining"  id="${abiriskMemberAccountInstance?.id}" value="Validate request as project CZAR"  />
        </div>
    </fieldset>
</div>



			</g:form>
		</div>
        </div>
	</body>
</html>

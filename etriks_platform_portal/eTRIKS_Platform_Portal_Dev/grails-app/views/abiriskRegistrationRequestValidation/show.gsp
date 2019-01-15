
<%@ page import="org.etriks.security.register.AbiriskMemberAccount" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>abirisk member registration validation</title>
	</head>
	<body>

		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard')}">abirisk admin dashboard</a></li>
			</ul>
		</div>

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

			<g:form>
				<fieldset class="buttons">
                    <div class="row">
                        <div class="col-md-6 text-center">
                            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </div>
                        <div class="col-md-3 text-center">
                            <g:actionSubmit class="edit" action="ldapUserAccountEntry" id="${abiriskMemberAccountInstance?.id}" value="Validate request"  />
                        </div>
                    </div>
					<g:hiddenField name="id" value="${abiriskMemberAccountInstance?.id}" />
                </fieldset>
			</g:form>
		</div>
        </div>
	</body>
</html>

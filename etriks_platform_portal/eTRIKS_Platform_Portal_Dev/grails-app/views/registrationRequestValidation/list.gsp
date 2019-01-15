
<%@ page import="org.etriks.security.register.EtriksMemberAccount" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'etriksMemberAccount.label', default: 'EtriksMemberAccount')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-etriksMemberAccount" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-etriksMemberAccount" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="firstName" title="${message(code: 'etriksMemberAccount.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'etriksMemberAccount.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'etriksMemberAccount.email.label', default: 'Email')}" />
					
						%{--<g:sortableColumn property="password" title="${message(code: 'etriksMemberAccount.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="confirmPassword" title="${message(code: 'etriksMemberAccount.confirmPassword.label', default: 'Confirm Password')}" />--}%
					
						<g:sortableColumn property="organization" title="${message(code: 'etriksMemberAccount.organization.label', default: 'Organization')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${etriksMemberAccountInstanceList}" status="i" var="etriksMemberAccountInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${etriksMemberAccountInstance.id}">${fieldValue(bean: etriksMemberAccountInstance, field: "firstName")}</g:link></td>
					
						<td>${fieldValue(bean: etriksMemberAccountInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: etriksMemberAccountInstance, field: "email")}</td>
					
						%{--<td>${fieldValue(bean: etriksMemberAccountInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: etriksMemberAccountInstance, field: "confirmPassword")}</td>--}%
					
						<td>${fieldValue(bean: etriksMemberAccountInstance, field: "organization")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${etriksMemberAccountInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

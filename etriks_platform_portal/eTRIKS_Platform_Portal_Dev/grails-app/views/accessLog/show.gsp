
<%@ page import="org.etriks.security.log.AccessLog" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<title>Show event details</title>
	</head>
	<body>
		<a href="#show-accessLog" class="skip" tabindex="-1"></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index">access event list</g:link></li>

			</ul>
		</div>
		<div id="show-accessLog" class="content scaffold-show" role="main">
			<h1>Access event details</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list accessLog">
			
				<g:if test="${accessLogInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label">Event creation date</span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${accessLogInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${accessLogInstance?.logMessage}">
				<li class="fieldcontain">
					<span id="logMessage-label" class="property-label">Event Message</span>
					
						<span class="property-value" aria-labelledby="logMessage-label"><g:fieldValue bean="${accessLogInstance}" field="logMessage"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accessLogInstance?.logType}">
				<li class="fieldcontain">
					<span id="logType-label" class="property-label">Event Type</span>
					
						<span class="property-value" aria-labelledby="logType-label"><g:fieldValue bean="${accessLogInstance}" field="logType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accessLogInstance?.projectId}">
				<li class="fieldcontain">
					<span id="projectId-label" class="property-label">Project Id</span>
					
						<span class="property-value" aria-labelledby="projectId-label"><g:fieldValue bean="${accessLogInstance}" field="projectId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${accessLogInstance?.userId}">
				<li class="fieldcontain">
					<span id="userId-label" class="property-label">Event Owner</span>
					
						<span class="property-value" aria-labelledby="userId-label"><g:fieldValue bean="${accessLogInstance}" field="userId"/></span>
					
				</li>
				</g:if>
			
			</ol>

		</div>
	</body>
</html>

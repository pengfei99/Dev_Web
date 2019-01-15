
<%@ page import="org.etriks.security.log.AccessLog" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<title>Access event Log</title>
	</head>
	<body>

		<div id="list-accessLog" class="content scaffold-list" role="main">

			<h1>Access event List</h1>

			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<table class="table table-striped">
			<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="Event Date" />
					
						<g:sortableColumn property="logMessage" title="Event Message" />
					
						<g:sortableColumn property="logType" title="Event Type" />
					
						<g:sortableColumn property="projectId" title="Project Id" />
					
						<g:sortableColumn property="userId" title="Event Owner" />
					
					</tr>
				</thead>

				<tbody>
				<g:each in="${accessLogInstanceList}" status="i" var="accessLogInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${accessLogInstance.id}">${fieldValue(bean: accessLogInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: accessLogInstance, field: "logMessage")}</td>
					
						<td>${fieldValue(bean: accessLogInstance, field: "logType")}</td>
					
						<td>${fieldValue(bean: accessLogInstance, field: "projectId")}</td>
					
						<td>${fieldValue(bean: accessLogInstance, field: "userId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${accessLogInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

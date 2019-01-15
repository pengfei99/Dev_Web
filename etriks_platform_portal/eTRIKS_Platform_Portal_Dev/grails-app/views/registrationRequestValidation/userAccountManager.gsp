<%@ page contentType="text/html; charset=UTF-8" %>>
<%@ page import="org.etriks.security.register.EtriksMemberAccount" %>

<!DOCTYPE html>
<html>
	<head>
	<meta name="layout" content="main">

		<title>eTRIKS member registration request</title>
	</head>
	<body>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

		
		<!-- The table statement can create the typical grails list table
		    the head of the table is the sortale column of all propertiy names of the object
		    instance.
		 -->
		
		<table class="table table-striped">
				<thead>
					<tr>

                        <g:sortableColumn property="firstName" title="First Name" />

                        <g:sortableColumn property="lastName" title="Last Name" />

                        <g:sortableColumn property="email" title="Email" />

                        <g:sortableColumn property="organization" title="Organization" />

                        <g:sortableColumn property="organization" title="Work_Package" />
					
					</tr>
				</thead>
        <tbody>
        <g:each in="${etriksMemberAccountInstanceList}" status="i" var="etriksMemberAccountInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${etriksMemberAccountInstance.id}">${fieldValue(bean: etriksMemberAccountInstance, field: "firstName")}</g:link></td>

                <td>${fieldValue(bean: etriksMemberAccountInstance, field: "lastName")}</td>

                <td>${fieldValue(bean: etriksMemberAccountInstance, field: "email")}</td>

                <td>${fieldValue(bean: etriksMemberAccountInstance, field: "organization")}</td>

                <td>${fieldValue(bean: etriksMemberAccountInstance, field: "workPackage")}</td>

            </tr>
        </g:each>
        </tbody>
			</table>

	</body>
</html>
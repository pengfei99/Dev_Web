<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 4/2/14
  Time: 2:34 PM
--%>

<%@ page contentType="text/html; charset=UTF-8" %>>
<%@ page import="org.etriks.security.register.BioasterMemberAccount" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="BioasterMemberAccount" />
    <title>Bioaster member registration request list</title>
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/bioasterAdminDashBoard')}">bioaster admin dashboard</a></li>
    </ul>
</div>


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

        <g:sortableColumn property="groups" title=" Groups" />
        %{--<g:sortableColumn property="bioasterValidatorDecision" title="CZAR_validation"/>--}%
        <g:sortableColumn property="dateCreated" title="Creation time"/>

    </tr>
    </thead>
    <tbody>
    <g:each in="${bioasterMemberAccountInstanceList}" status="i" var="bioasterMemberAccountInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link action="show" id="${bioasterMemberAccountInstance.id}">${fieldValue(bean: bioasterMemberAccountInstance, field: "firstName")}</g:link></td>

            <td>${fieldValue(bean: bioasterMemberAccountInstance, field: "lastName")}</td>

            <td>${fieldValue(bean: bioasterMemberAccountInstance, field: "email")}</td>

            <td>${fieldValue(bean: bioasterMemberAccountInstance, field: "organization")}</td>

            <td>${fieldValue(bean: bioasterMemberAccountInstance, field: "groups")}</td>
           %{-- <td>${fieldValue(bean: bioasterMemberAccountInstance, field: "bioasterValidatorDecision")}</td>--}%
            <td>${fieldValue(bean: bioasterMemberAccountInstance, field: "dateCreated")}</td>


        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>
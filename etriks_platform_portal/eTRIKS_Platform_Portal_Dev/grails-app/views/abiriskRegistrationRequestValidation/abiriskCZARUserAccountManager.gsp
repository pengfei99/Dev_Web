<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 4/2/14
  Time: 2:34 PM
--%>

<%@ page contentType="text/html; charset=UTF-8" %>>
<%@ page import="org.etriks.security.register.AbiriskMemberAccount" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Abirisk member registration request list</title>
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard')}">abirisk admin dashboard</a></li>
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

        <g:sortableColumn property="dateCreated" title="Creation time"/>

    </tr>
    </thead>
    <tbody>
    <g:each in="${abiriskMemberAccountInstanceList}" status="i" var="abiriskMemberAccountInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link action="show" id="${abiriskMemberAccountInstance.id}">${fieldValue(bean: abiriskMemberAccountInstance, field: "firstName")}</g:link></td>

            <td>${fieldValue(bean: abiriskMemberAccountInstance, field: "lastName")}</td>

            <td>${fieldValue(bean: abiriskMemberAccountInstance, field: "email")}</td>

            <td>${fieldValue(bean: abiriskMemberAccountInstance, field: "organization")}</td>

            <td>${fieldValue(bean: abiriskMemberAccountInstance, field: "groups")}</td>

            <td>${fieldValue(bean: abiriskMemberAccountInstance, field: "dateCreated")}</td>


        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>
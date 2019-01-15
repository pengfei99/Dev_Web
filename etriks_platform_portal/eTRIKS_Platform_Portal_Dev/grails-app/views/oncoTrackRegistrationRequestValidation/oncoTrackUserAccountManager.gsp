<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 4/2/14
  Time: 2:34 PM
--%>

<%@ page contentType="text/html; charset=UTF-8" %>>
<%@ page import="org.etriks.security.register.OncoTrackMemberAccount" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="OncoTrackMemberAccount" />
    <title>OncoTrack account validation</title>
</head>

<body>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/oncoTrackAdminDashBoard')}">oncoTrack admin dashboard</a></li>
    </ul>
</div>

<div class="container">
<div class="row">
    <div class="col-md-12">
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
</div>
</div>
<!-- The table statement can create the typical grails list table
		    the head of the table is the sortale column of all propertiy names of the object
		    instance.
		 -->
<div class="row">
<table class="table table-striped">
    <thead>
    <tr>

        <g:sortableColumn property="firstName" title="First Name" />

        <g:sortableColumn property="lastName" title="Last Name" />

        <g:sortableColumn property="organization" title="Organization" />

        <g:sortableColumn property="groups" title=" Groups" />
        <g:sortableColumn property="oncoTrackValidatorDecision" title="CZAR_validation"/>
        <g:sortableColumn property="dateCreated" title="Creation time"/>

    </tr>
    </thead>
    <tbody>
    <g:each in="${oncoTrackMemberAccountInstanceList}" status="i" var="oncoTrackMemberAccountInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link action="show" id="${oncoTrackMemberAccountInstance.id}">${fieldValue(bean: oncoTrackMemberAccountInstance, field: "firstName")}</g:link></td>

            <td>${fieldValue(bean: oncoTrackMemberAccountInstance, field: "lastName")}</td>

            <td>${fieldValue(bean: oncoTrackMemberAccountInstance, field: "organization")}</td>

            <td>${fieldValue(bean: oncoTrackMemberAccountInstance, field: "groups")}</td>
            <td>${fieldValue(bean: oncoTrackMemberAccountInstance, field: "oncoTrackValidatorDecision")}</td>
            <td>${fieldValue(bean: oncoTrackMemberAccountInstance, field: "dateCreated")}</td>


        </tr>
    </g:each>
    </tbody>
</table>
</div>
</div>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8"%>>
<%@ page import="org.etriks.security.ldap.authentication.UserDetails"%>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Account details</title>
</head>
<body>
%{--<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message
                code="default.home.label" /></a></li>
    </ul>
</div>--}%
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<div id="bodyText">
    <p>
        <br>
        username : ${userName}
        <br>
        <br>
        %{-- projectId : ${projectId}
         <br>
         <br>--}%
        userrole :   <g:each in="${userRole}" status="i" var="role">
            ${role},
        </g:each>
        <br>
        <br>

        Last login time: ${lastLoginTime}
        <br>
        <br>
    </p>
</div>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 6/27/16
  Time: 2:22 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Welcome to the ldap member manager dashboard</title>
</head>

<body>
<!-- This bloc is the navigation bloc  -->
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/bioasterAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="page-body" role="main">
    <br>

    <g:link url="[action:'userAccountList',controller:'ldapMemberManager']">
        View all registered members in ldap server
    </g:link>


</p>
</div>

</body>
</html>
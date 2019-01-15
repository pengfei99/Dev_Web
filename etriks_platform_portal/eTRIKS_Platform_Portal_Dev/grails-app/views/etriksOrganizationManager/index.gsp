<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 3/11/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Welcome to etriks group manager</title>
</head>

<body>
<!-- This bloc is the navigation bloc  -->
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/eTRIKSAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
        <li><a class="list" href="${createLink(uri: '/etriksOrganizationManager/index')}"> Organization manager </a></li>
    </ul>
</div>

<div id="page-body" role="main">

<br>
<br>
<p>

<h2>Congratulations, you have successfully connected to the etriks organization manager.</h2>

    <br>
    <br>

    <g:link url="[action:'organizationList',controller:'etriksOrganizationManager']">
        View all organization of etriks project
    </g:link>

    <br>
    <br>

    <g:link url="[action:'createOrganization',controller:'etriksOrganizationManager']">
        Create a new organization in etriks project
    </g:link>

    <br>
    <br>

    <g:link url="[action:'changeMemberOrganizationView',controller:'etriksOrganizationManager']">
        Change users organizations in etriks project
    </g:link>
</p>
</div>

</body>
</html>


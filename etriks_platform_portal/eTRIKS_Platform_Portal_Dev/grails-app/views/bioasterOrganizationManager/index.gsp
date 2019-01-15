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
    <title>Welcome to abirisk organization manager</title>
</head>

<body>

<!-- This bloc is the navigation bloc  -->
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/bioasterAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
        <li><a class="list" href="${createLink(uri: '/bioasterOrganizationManager/index')}"> Organization manager </a></li>
    </ul>
</div>

<div id="page-body" role="main">

<br>
<br>
<p>

<h2>Congratulations, you have successfully connected to the bioaster organization manager.</h2>

    <br>
    <br>

    <g:link url="[action:'organizationList',controller:'bioasterOrganizationManager']">
        View all organization of bioaster project
    </g:link>

    <br>
    <br>

    <g:link url="[action:'createOrganization',controller:'bioasterOrganizationManager']">
        Create a new organization in bioaster project
    </g:link>

    <br>
    <br>

    <g:link url="[action:'changeMemberOrganizationView',controller:'bioasterOrganizationManager']">
        Change users organizations in bioaster project
    </g:link>
</p>
</div>

</body>
</html>


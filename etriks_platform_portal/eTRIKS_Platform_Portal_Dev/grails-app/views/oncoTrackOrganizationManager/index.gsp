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
    <title>Welcome to OncoTrack organization manager</title>
</head>

<body>

<!-- This bloc is the navigation bloc  -->
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/oncoTrackAdminDashBoard')}">Home</a></li>
        <li><a class="list" href="${createLink(uri: '/oncoTrackOrganizationManager/index')}"> Organization manager </a></li>
    </ul>
</div>

<div id="page-body" role="main">

<br>
<br>
<p>

<h2>Congratulations, you have successfully connected to the oncoTrack organization manager.</h2>

    <br>
    <br>

    <g:link url="[action:'organizationList',controller:'oncoTrackOrganizationManager']">
        View all organization of oncoTrack project
    </g:link>

    <br>
    <br>

    <g:link url="[action:'createOrganization',controller:'oncoTrackOrganizationManager']">
        Create a new organization in oncoTrack project
    </g:link>

    <br>
    <br>

    <g:link url="[action:'changeMemberOrganizationView',controller:'oncoTrackOrganizationManager']">
        Change users organizations in oncoTrack project
    </g:link>
</p>
</div>

</body>
</html>


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
    <title>Welcome to abirisk transmart group manager</title>
</head>

<body>
<!-- This bloc is the navigation bloc  -->
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard/')}">Home</a></li>
        <li><a class="list" href="${createLink(uri: '/abiriskTransmartGroupManager/')}"> Transmart Group manager </a></li>
    </ul>
</div>

<div id="page-body" role="main">

<br>
<br>
<p>

<h2>Congratulations, you have successfully connected to the abirisk transmart group manager.</h2>

    <br>
    <br>

    <g:link url="[action:'transmartGroupList',controller:'abiriskTransmartGroupManager']">
        View all transmart groups of abirisk project
    </g:link>

    <br>
    <br>


    <g:link url="[action:'addMemberToTransmartGroupView',controller:'abiriskTransmartGroupManager']">
        Add users to a transmart group in abirisk project
    </g:link>
</p>
</div>

</body>
</html>


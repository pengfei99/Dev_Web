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
    <title>Welcome to bioaster group manager</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/dashBoard/bioasterAdminDashBoard/')}">Home</a></li>
                    <li><a class="list" href="${createLink(uri: '/bioasterGroupManager/')}"> Group manager </a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <br>
            <br>


            <h2>Congratulations, you have successfully connected to the bioaster group manager.</h2>

            <br>
            <br>

            <g:link url="[action:'groupList',controller:'bioasterGroupManager']">
                View all groups of bioaster project
            </g:link>

            <br>
            <br>

            <g:link url="[action:'createGroup',controller:'bioasterGroupManager']">
                Create a new group in bioaster project
            </g:link>

            <br>
            <br>

            <g:link url="[action:'addMemberToGroupView',controller:'bioasterGroupManager']">
                Add users to a group in bioaster project
            </g:link>
        </div>
    </div>
</div>


</body>
</html>


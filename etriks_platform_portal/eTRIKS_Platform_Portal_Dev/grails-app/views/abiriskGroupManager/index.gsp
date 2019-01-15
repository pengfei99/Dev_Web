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
    <title>Abirisk group manager</title>
</head>

<body>
<div class="container">
<div class="row">
    <div class="col-md-12">
    <!-- This bloc is the navigation bloc  -->
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard/')}">Home</a></li>
            <li><a class="list" href="${createLink(uri: '/abiriskGroupManager/')}"> Group manager </a></li>
        </ul>
    </div>
        </div>
</div>
    %{--Row for the groupe manager text--}%
    <div class="row">
    <div class="col-md-12">
    <h2>Congratulations, you have successfully connected to the abirisk group manager.</h2>
        <br>
        <br>
        <ul>
            <li><g:link url="[action:'groupList',controller:'abiriskGroupManager']">
                View all groups of abirisk project
            </g:link></li>
            <br>
            <br>
            <li>    <g:link url="[action:'createGroup',controller:'abiriskGroupManager']">
                Create a new group in abirisk project
            </g:link>
            </li>
            <br>
            <br>
            <li>
                <g:link url="[action:'addMemberToGroupView',controller:'abiriskGroupManager']">
                    Add users to a group in abirisk project
                </g:link>
            </li>
        </ul>

    </div>
    </div>

    %{--Row for the link list--}%

    <div class="row">
        <div class="col-md-12">


        </div>
    </div>
</div>

</body>
</html>


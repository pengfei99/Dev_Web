<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 7/10/14
  Time: 11:48 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Group infomation </title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/dashBoard/bioasterAdminDashBoard/')}">Home</a></li>
                    <li><a class="list" href="${createLink(uri: '/bioasterGroupManager/groupList')}"> bioaster group List </a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <br>
            <br>
            Group Name : ${groupName}
            <br>
            <br>
            Group ID : ${gidNumber}
            <br>
            <br>

            Group description : ${description}
            <br>
            <br>

            Group members : <g:each in="${memberUids}" var="memberUid">

                ${memberUid},

            </g:each>
        </div>
    </div>
</div>

</body>
</html>
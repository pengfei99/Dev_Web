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
<div class="nav" role="navigation">
    <ul>
        <li><a class="list" href="${createLink(uri: '/abiriskGroupManager/groupList')}"> abirisk group List </a></li>
    </ul>
</div>
<div class="body">
    <div id="page-body" role="main">
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
</body>
</html>
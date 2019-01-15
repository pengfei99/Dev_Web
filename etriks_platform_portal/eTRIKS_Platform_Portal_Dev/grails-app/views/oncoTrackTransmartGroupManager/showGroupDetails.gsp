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
    <title>Transmart Group infomation </title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/dashBoard/oncoTrackAdminDashBoard')}">Home</a></li>
                    <li><a class="list" href="${createLink(uri: '/oncoTrackTransmartGroupManager/')}"> Transmart Group manager </a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div id="page-body" role="main">
                <br>
                <br>
                Group Name : ${groupName}
                <br>
                <br>
                Group ID : ${gidNumber}
                <br>
                <br>

                Group members : <g:each in="${memberUids}" var="memberUid">

                    ${memberUid},

                </g:each>

            </div>
        </div>
    </div>
</div>


</body>
</html>
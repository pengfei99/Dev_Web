<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 7/16/14
  Time: 4:17 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Welcome to Abirisk group manager</title>
</head>

<body>

<!-- This bloc is the navigation bloc  -->
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard')}">abirisk admin dashboard</a></li>
    </ul>
</div>
<div class="container">
<div class="row">
    <div class="col-md-12 text-center">
        <div id="mainText" >
            <br>
            <h1>Congratulations !!!</h1>
            <br>
            <br>
    </div>
</div>
    <g:each in="${messageList}" status="i" var="message">
        <div class="row">
            <div class="col-md-12 text-center">
                ${message}
            </div>
        </div>
       <br>
    </g:each>





</div>
</div>
</body>
</html>
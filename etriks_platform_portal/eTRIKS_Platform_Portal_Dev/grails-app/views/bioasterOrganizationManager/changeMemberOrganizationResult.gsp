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
    <title>Welcome to bioaster organization manager</title>
</head>

<body>

<!-- This bloc is the navigation bloc  -->
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard/bioasterAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
        <li><a class="list" href="${createLink(uri: '/bioasterOrganizationManager/index')}"> Organization manager </a></li>
    </ul>
</div>

<div id="mainText" >
<br>
<h1>Congratulations !!!</h1>
<br>
<br>
    %{--${messageList}--}%
<g:each in="${messageList}" status="i" var="message">
    ${message}
    <br>
    <br>
 </g:each>

    you can go to <a class="list" href="${createLink(uri: '/bioasterMemberManager/userAccountList')}"> bioaster user list  </a> to check the new organization information
</div>

</body>
</html>
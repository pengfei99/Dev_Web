<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 7/10/14
  Time: 10:25 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Abirisk group List</title>
</head>

<body>
<div class="row">
    <div class="col-md-12">
        <ul>
            <li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard/')}">Home</a></li>
            <li><a class="list" href="${createLink(uri: '/abiriskGroupManager/')}"> Group manager </a></li>
        </ul>
        %{--End of the navigation bloc--}%
    </div>
</div>


%{--This bloc is the main page body bloc--}%

<div id="mainText">
    <br>
    <br>
    There are ${groupTotal} groups in Abirisk project LDAP server
    <br>
    <br>
</div>
<table class="table table-striped">
    <thead>
    <tr>

        <g:sortableColumn property="Name" title="Group Name" />
        <g:sortableColumn property="Name" title="Group GID" />


    </tr>
    </thead>
    <tbody>
    <g:each in="${groupList}" status="i" var="group">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link action="showGroupDetails" id="${group.value}">${group.value}</g:link></td>
            <td>${group.key}</td>

        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>

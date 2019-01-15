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
    <title>Welcome to abirisk group List</title>
</head>

<body>
<div class="container">
    %{--Row for the admin dashboard navigation--}%

<div class="row">
    <div class="col-md-12">
        <ul>
            <li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard/')}">Home</a></li>
            <li><a class="list" href="${createLink(uri: '/abiriskTransmartGroupManager/')}"> Transmart Group manager </a></li>
        </ul>
    </div>
    </div>
    %{--Row for main content text--}%
    <div class="row">
        <div class="col-md-12">
            <br>
            <br>
            There are ${groupTotal} transmart groups in Abirisk project LDAP server
            <br>
            <br>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-responsive">
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
        </div>
    </div>
</div>








</body>
</html>

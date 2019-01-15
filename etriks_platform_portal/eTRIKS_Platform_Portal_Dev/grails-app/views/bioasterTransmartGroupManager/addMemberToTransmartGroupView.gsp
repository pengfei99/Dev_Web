<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 7/10/14
  Time: 5:29 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Welcome to bioaster transmart group manager</title>
</head>

<body>

<div class="container">
    <!-- This bloc is the navigation bloc  -->
    <div class="row">
        <div class="col-md-12">
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/dashBoard/bioasterAdminDashBoard/')}">Home</a></li>
                    <li><a class="list" href="${createLink(uri: '/bioasterTransmartGroupManager/')}"> Transmart Group manager </a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
                <div class="errors" role="alert">${flash.error}</div>
            </g:if>
        </div>
    </div>

    <form  action="addMemberToTransmartGroup" method="get" name="addMemberToTransmartGroup">
    <div class="row">
        <div class="col-md-6">
            Choose users which you want to add to a group or groups:
            <br>

            <div class="scrollableTable" style="height: 600px;">


                <table class="table table-striped">
                    <thead>
                    <tr>

                        <g:sortableColumn property="Name" title="User Name" />
                        <g:sortableColumn property="Name" title="User ID" />

                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userList}" status="i" var="user">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                            <td>
                                <input type="checkbox" name="user_${i}" value="${user.value}">
                                <g:link controller="bioasterMemberManager" action="showUserDetails" id="${user.key}">${user.key}</g:link></td>
                            <td>${user.value}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>

            </div>

        </div>

        <div class="col-md-6">


            Choose groups which you want to add user as members:
            <br>
            <div class="scrollableTable" style="height: 600px;">

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

                            <td>
                                <input type="checkbox" name="group_${i}" value="${group.value}">
                                <g:link action="showGroupDetails" id="${group.value}">${group.value}</g:link></td>
                            <td>${group.key}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>

            </div>


        </div>
        </div>


        <div class="row" style="text-align: center">
            <br>
            <br>
            <div class="col-md-12">
                <g:hiddenField name="groupSize" value="${groupSize}"/>
                <g:hiddenField name="userSize" value="${userSize}"/>

                <span class="button">
                    <button type="submit">Submit</button>
                </span>

            </div>
        </div>
    </form>
    </div>
</div>


</body>
</html>


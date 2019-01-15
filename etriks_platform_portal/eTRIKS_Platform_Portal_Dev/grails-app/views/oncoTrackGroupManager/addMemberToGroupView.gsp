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
    <title>Welcome to oncoTrack group manager</title>
</head>

<body>

<div class="container">
    %{--navigation row--}%
    <div class="row">
        <div class="col-md-12">
            <ul>
                <li><a class="home" href="${createLink(uri: '/dashBoard/oncoTrackAdminDashBoard')}">Home</a></li>
                <li><a class="list" href="${createLink(uri: '/oncoTrackGroupManager/')}"> Group manager </a></li>
            </ul>
        </div>
    </div>
    %{--error message row--}%
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
    <form  action="addMemberToGroup" method="get" name="addMemberToGroup">
        %{--user and group panel row--}%
        <div class="row">
            %{--available User information --}%
            <div class="col-md-6">

                <h3>                Choose users which you want to add to a group or groups:</h3>
                <br>

                <div class="scrollableTable" style="height: 600px;">
                    <table class="table table-striped" >
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
                                    <g:link controller="oncoTrackMemberManager" action="showUserDetails" id="${user.key}">${user.key}</g:link></td>
                                <td>${user.value}</td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
            %{--Available Group Information--}%
            <div class="col-md-6">

                <h3>    Choose groups which you want to add user as members:</h3>
                <br>

                %{-- The code for search the group
                   <br>
                    <form name="input" action="searchGroup" method="get">
                        Enter group Name:

                        <input type="text" name="searchGroupName">
                        <input type="submit" value="Search">
                    </form>
                    <br>--}%
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

                                <td class="filterable-cell">
                                    <input type="checkbox" name="group_${i}" value="${group.value}">
                                    <g:link action="showGroupDetails" id="${group.value}">${group.value}</g:link></td>
                                <td class="filterable-cell">${group.key}</td>

                            </tr>
                        </g:each>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>
        <br>
        <br>
        %{--submit button row--}%
        <div class="row">
            <div class="col-md-12" style="text-align: center;">

                <g:hiddenField name="groupSize" value="${groupSize}"/>
                <g:hiddenField name="userSize" value="${userSize}"/>

                <span class="button">
                    <button type="submit">Submit</button>
                </span>
            </div>
        </div>
    </form>
</div>

</body>
</html>


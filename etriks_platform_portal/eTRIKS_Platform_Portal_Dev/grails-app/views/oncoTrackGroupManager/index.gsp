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
    <title>Welcome to OncoTrack group manager</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/dashBoard/oncoTrackAdminDashBoard')}">Home</a></li>
                    <li><a class="list" href="${createLink(uri: '/oncoTrackGroupManager/')}"> Group manager </a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <br>
            <br>

            <h2>Congratulations, you have successfully connected to the OncoTrack group manager.</h2>

            <br>
            <br>

            <g:link url="[action:'groupList',controller:'oncoTrackGroupManager']">
                View all groups of oncoTrack project
            </g:link>

            <br>
            <br>

            <g:link url="[action:'createGroup',controller:'oncoTrackGroupManager']">
                Create a new group in oncoTrack project
            </g:link>

            <br>
            <br>

            <g:link url="[action:'addMemberToGroupView',controller:'oncoTrackGroupManager']">
                Add users to a group in oncoTrack project
            </g:link>
        </div>
    </div>
</div>
<!-- This bloc is the navigation bloc  -->


</body>
</html>


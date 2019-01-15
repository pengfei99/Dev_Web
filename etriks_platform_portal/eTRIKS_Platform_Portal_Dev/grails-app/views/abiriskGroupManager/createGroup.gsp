<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 7/10/14
  Time: 2:36 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Create a new group </title>
</head>
<body>
<div class="container">
    %{--row for the navigation --}%
    <div class="row">
    <div class="col-md-12">
        <ul>
            <li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard/')}">Home</a></li>
            <li><a class="list" href="${createLink(uri: '/abiriskGroupManager/')}"> Group manager </a></li>
        </ul>
    </div>
    </div>
  %{--row for error message--}%
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
    %{--row for group name form--}%
<g:form>
    <div class="row">
        <div class="col-md-3">
            <h4>Group Name:</h4>
        </div>
        <div class="col-md-9">
            <g:textField name="groupName" maxlength="40" required=""  value="Enter the group name"/>
        </div>
    </div>
    %{--row for group description form--}%
    <div class="row">
        <div class="col-md-3">
            <h4>Group description:</h4>
        </div>
        <div class="col-md-9">
            <g:textField name="groupDescription" maxlength="80" required=""  value="Enter the group description"/>
        </div>
    </div>
%{--Row for submit button --}%
    <div class="row">
        <div class="col-md-8" style="text-align: center;">
            <fieldset class="buttons">

                <g:actionSubmit class="save" action="createGroupLdapEntry" value="submit"  />
            </fieldset>
        </div>
    </div>
</g:form>
</div>
</body>
</html>
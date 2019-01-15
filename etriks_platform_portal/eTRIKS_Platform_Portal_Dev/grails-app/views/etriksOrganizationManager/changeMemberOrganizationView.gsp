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
    <title>Welcome to etriks organization manager</title>
</head>

<body>



<div class="container">
    %{--Navigation row--}%
    <div class="row">
        <div class="col-md-12">
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/dashBoard/eTRIKSAdminDashBoard')}">Home</a></li>
                    <li><a class="list" href="${createLink(uri: '/etriksOrganizationManager/index')}"> Organization manager </a></li>
                </ul>
            </div>
        </div>
    </div>
    %{--Error message row--}%
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


    <form  action="changeMemberOrganization" method="get" name="changeMemberOrganization">
        %{--user and organization row--}%
        <div class="row">

            %{--This colone  will show all members--}%
            <div class="col-md-6">
                Choose users which you want to change their organization:
                <br>
                <br>



                <div class="scrollableTable" style="height: 700px;">
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
                                    <g:link controller="etriksMemberManager" action="showUserDetails" id="${user.key}">${user.key}</g:link></td>
                                <td>${user.value}</td>

                            </tr>
                        </g:each>
                        </tbody>
                    </table>

                </div>
            </div>
            <div class="col-md-6">
                %{--Available organization Information--}%

                Choose organizations which the users belongs to:
                <br>
                <br>



                <div class="scrollableTable" style="height: 700px;">
                    <table class="table table-striped">
                        <thead>
                        <tr>

                            <g:sortableColumn property="Name" title="Organization Name" />
                            <g:sortableColumn property="Name" title="Organization GID" />


                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${organizationList}" status="i" var="organization">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                <td>
                                    <input type="radio" name="organization" value="${organization.key}">
                                    <g:link action="showOrganizationDetails" id="${organization.value}">${organization.value}</g:link></td>
                                <td>${organization.key}</td>

                            </tr>
                        </g:each>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>

        %{--Button row--}%
        <div class="row" style="text-align: center">
            <g:hiddenField name="organizationSize" value="${organizationSize}"/>
            <g:hiddenField name="userSize" value="${userSize}"/>
            <br>
            <br>
            <span class="button">
                <button type="submit">Submit</button>
            </span>
        </div>
    </form>
</div>


</body>
</html>


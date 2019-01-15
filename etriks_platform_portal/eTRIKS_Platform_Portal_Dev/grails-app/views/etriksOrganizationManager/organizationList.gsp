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
    <title>Welcome to etriks group List</title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-12"><div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/dashBoard/eTRIKSAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
                <li><a class="list" href="${createLink(uri: '/etriksOrganizationManager/index')}"> Organization manager </a></li>
            </ul>
        </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div id="mainText">
                <br>
                <br>
                There are ${organizationTotal} organizations in etriks project LDAP server
                <br>
                <br>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-10">
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

                        <td><g:link action="showOrganizationDetails" id="${organization.value}">${organization.value}</g:link></td>
                        <td>${organization.key}</td>

                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>
</div>



</body>
</html>

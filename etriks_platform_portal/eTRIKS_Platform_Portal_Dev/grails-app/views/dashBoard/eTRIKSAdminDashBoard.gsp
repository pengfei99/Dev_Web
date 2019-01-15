<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 4/28/14
  Time: 2:10 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Welcome to the eTRIKS platform admin dashboard</title>
</head>

<body>

<!-- This bloc is the navigation bloc  -->
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/dashBoard')}">Admin dashboard</a></li>
    </ul>
</div>

<div id="page-body" role="main">


    <br>
    <br>
    <g:link url="[action:'index',controller:'registrationRequestValidation']">
        Manage new Etriks member accounts creation request
    </g:link>

    <br>
    <br>


    <g:link url="[action:'index',controller:'etriksMemberManager']">
        Manage etriks member account
    </g:link>


    <br>
    <br>


    <g:link url="[action:'index',controller:'etriksOrganizationManager']">
        Manage the organization of etriks member
    </g:link>


    <br>
    <br>

</p>
</div>

</body>
</html>


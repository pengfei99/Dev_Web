<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 4/28/14
  Time: 2:18 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Welcome to the Abirisk admin dashboard</title>
</head>

<body>

<!-- This bloc is the navigation bloc  -->
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="page-body" role="main">

    <br>
    <br>
    <p>

    <h2>Congratulations, you have successfully connected to the Abirisk project admin dashboard.</h2>



    <br>
    <br>

    <g:link url="[action:'index',controller:'abiriskRegistrationRequestValidation']">
        Manage new Abirisk member accounts creation request
    </g:link>

    <br>
    <br>

    <g:link url="[action:'index',controller:'abiriskMemberManager']">
        Manage Abirisk member account
    </g:link>


    <br>
    <br>


    <g:link url="[action:'index',controller:'abiriskGroupManager']">
        Manage the group of Abirisk member
    </g:link>


    <br>
    <br>

    <g:link url="[action:'index',controller:'abiriskOrganizationManager']">
        Manage the organization of Abirisk member
    </g:link>


    <br>
    <br>

    <g:link url="[action:'index',controller:'abiriskTransmartGroupManager']">
        Manage the transmart group of Abirisk member
    </g:link>


    <br>
    <br>

    <g:link url="[action:'index',controller:'accessLog']">
        View the event log of the abirisk project
    </g:link>


    <br>
    <br>

</p>
</div>

</body>
</html>


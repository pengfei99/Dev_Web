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
    <title>Welcome to the Bioaster admin dashboard</title>
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

    <h2>Congratulations, you have successfully connected to the Bioaster project admin dashboard.</h2>



    <br>
    <br>

    <g:link url="[action:'index',controller:'bioasterRegistrationRequestValidation']">
        Manage new Bioaster member accounts creation request
    </g:link>

    <br>
    <br>

    <g:link url="[action:'index',controller:'bioasterMemberManager']">
        Manage Bioaster member account
    </g:link>


    <br>
    <br>


    <g:link url="[action:'index',controller:'bioasterGroupManager']">
        Manage the group of Bioaster member
    </g:link>


    <br>
    <br>

    <g:link url="[action:'index',controller:'bioasterOrganizationManager']">
        Manage the organization of Bioaster member
    </g:link>


    <br>
    <br>

    <g:link url="[action:'index',controller:'bioasterTransmartGroupManager']">
        Manage the transmart group of Bioaster member
    </g:link>


    <br>
    <br>

    <g:link url="[action:'index',controller:'accessLog']">
        View the event log of the bioaster project
    </g:link>


    <br>
    <br>

</p>
</div>

</body>
</html>


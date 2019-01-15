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
    <title>Welcome to the eTRIKS platform admin dashboard</title>
</head>

<body>

<!-- This bloc is the navigation bloc  -->

<div id="page-body" role="main">

    <br>
    <br>
    <p>

    <h2>Congratulations, you have successfully connected to the eTRIKS platform admin dashboard.</h2>

    <br>
    <br>
    <g:link url="[action:'eTRIKSAdminDashBoard',controller:'dashBoard']">
        Access to the <span style="text-transform: uppercase ;color:red;font-weight:bold ">Etriks </span>platform admin dashboard
    </g:link>

    <br>
    <br>

    <g:link url="[action:'abiriskAdminDashBoard',controller:'dashBoard']">
        Access to the <span style="text-transform: uppercase ;color:red;font-weight:bold ">Abirisk</span> project admin dashboard
    </g:link>

    <br>
    <br>

    <g:link url="[action:'oncoTrackAdminDashBoard',controller:'dashBoard']">
        Access to the <span style="text-transform: uppercase ;color:red;font-weight:bold ">OncoTrack</span> project admin dashboard
    </g:link>

    <br>
    <br>

    <g:link url="[action:'bioasterAdminDashBoard',controller:'dashBoard']">
        Access to the <span style="text-transform: uppercase ;color:red;font-weight:bold ">Bioaster</span> project admin dashboard
    </g:link>

    <br>
    <br>

    <g:link url="[action:'ldapMemberManagerDashBoard',controller:'dashBoard']">
        Access to the <span style="text-transform: uppercase ;color:red;font-weight:bold ">Ldap</span> member manager dashboard
    </g:link>

    <br>
    <br>

    <g:link url="[action:'index',controller:'accessLog']">
        Access to the <span style="text-transform: uppercase ;color:red;font-weight:bold ">Portal</span> access log
    </g:link>

    <br>
    <br>


    %{--<br>
    <br>
    <g:link url="[action:'index',controller:'registrationRequestValidation']">
        Manage new Etriks member accounts creation request
    </g:link>

    <br>
    <br>

    <g:link url="[action:'index',controller:'abiriskRegistrationRequestValidation']">
        Manage new Abirisk member accounts creation request
    </g:link>

    <br>
    <br>

    <g:link url="[action:'index',controller:'etriksMemberManager']">
        Manage etriks member account
    </g:link>


    <br>
    <br>


    <g:link url="[action:'index',controller:'roleManager']">
        Manage the role of etriks member
    </g:link>


    <br>
    <br>

</p>
</div>--}%

</body>
</html>


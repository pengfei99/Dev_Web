<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />

        <title>Welcome to bioaster admin dash board</title>
    </head>
    <body>

    <!-- This bloc is the navigation bloc  -->
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/bioasterAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
        </ul>
    </div>

    <div id="page-body" role="main">

        <br>
        <br>
        <p>

        <h2>Congratulations, you have successfully connected to the Bioaster user management portal admin dashboard.</h2>



        <br>
        <br>

        <g:link url="[action:'index',controller:'bioasterUserRegistrationValidation']">
            Manage new Bioaster user accounts creation request
        </g:link>

        <br>
        <br>

        <g:link url="[action:'index',controller:'bioasterUserManager']">
            Manage bioaster user account
        </g:link>


        <br>
        <br>


        <g:link url="[action:'index',controller:'bioasterGroupManager']">
            Manage the group of Bioaster user
        </g:link>


        <br>
        <br>

        <g:link url="[action:'index',controller:'bioasterOrganizationManager']">
            Manage the organization of Bioaster user
        </g:link>

        <br>
        <br>

        <g:link url="[action:'index',controller:'bioasterSecurityPolicyTester']">
            Bioaster security policy tester
        </g:link>

        <br>
        <br>

        <g:link url="[action:'index',controller:'accessLog']">
            View the access log of BUMP
        </g:link>

    </body>
</html>
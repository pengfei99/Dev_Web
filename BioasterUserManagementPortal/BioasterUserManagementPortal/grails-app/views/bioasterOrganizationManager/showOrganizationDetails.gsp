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
            <li><a class="list" href="${createLink(uri: '/bioasterOrganizationManager/')}"> Organization manager </a></li>
        </ul>
    </div>

    <div id="page-body" role="main">

        <br>
        <br>
        <p>

            <br>
            <br>
            Organization Name : ${organizationName}
            <br>
            <br>
            Organization ID : ${gidNumber}
            <br>
            <br>

            Group description : ${description}
            <br>
            <br>

        </p>
        </div>


    </body>
</html>
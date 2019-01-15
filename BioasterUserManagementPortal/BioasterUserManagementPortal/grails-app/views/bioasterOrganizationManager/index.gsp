<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />

        <title>Welcome to bioaster admin dash board</title>
    </head>
    <body>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <!-- This bloc is the navigation bloc  -->
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/bioasterAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
                        <li><a class="list" href="${createLink(uri: '/bioasterOrganizationManager/')}"> Organization manager </a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <h2>Congratulations, you have successfully connected to the Bioaster user organization manager.</h2>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'organizationList',controller:'bioasterOrganizationManager']">
                    View the list of all organizations
                </g:link>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'createOrganization',controller:'bioasterOrganizationManager']">
                    Create a new organization in BIOASTER OPENLDAP server
                </g:link>
            </div>
        </div>

        <br>
        <br>

        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'changeMemberOrganizationView',controller:'bioasterOrganizationManager']">
                    Change users organizations in BIOASTER OPENLDAP server
                </g:link>
            </div>
        </div>
    </div>


    </body>
</html>
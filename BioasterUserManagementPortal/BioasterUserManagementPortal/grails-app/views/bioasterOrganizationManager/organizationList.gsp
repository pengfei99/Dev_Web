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

   %{-- End of navigation bloc--}%

    <div class="row">
        <div class="col-md-12">
            <div id="mainText">
                <br>
                <br>
                There are ${organizationTotal} organizations in BIOASTER OPENLDAP server
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
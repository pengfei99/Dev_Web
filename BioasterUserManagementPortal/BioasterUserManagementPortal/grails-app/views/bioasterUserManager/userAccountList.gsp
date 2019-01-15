<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />

        <title>Welcome to bioaster admin dash board</title>
    </head>
    <body>
    <div class="container">
        <!-- This bloc is the navigation bloc  -->
        <div class="row">
            <div class="col-md-12">
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/bioasterAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12" style="text-align: center;">
                <br>
                <br>

                <h4> There are ${userAccountTotal} registered user in BIOASTER OPENLDAP server</h4>


            </div>
        </div>

        %{--row for the user list--}%
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="scrollableTable" style="height: 700px;">
                    <table class="table table-striped">
                        <thead>
                        <tr>

                            <g:sortableColumn property="Name" title="User Name" />

                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${userCNList}" status="i" var="userCN">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                <td><g:link action="showUserDetails" id="${userCN}">${userCN}</g:link></td>

                            </tr>
                        </g:each>
                        </tbody>
                    </table>

                </div>
            </div>

        </div>
    </div>
    


    </body>
</html>
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
            <div class="col-md-12">
                <br>
                <br>


                <h2>Congratulations, you have successfully connected to the Bioaster user manager.</h2>
                <br>
                <br>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'userAccountList',controller:'bioasterUserManager']">
                    View all users in BIOASTER OPENLDAP server
                </g:link>
            </div>
        </div>
    </div>



    </body>
</html>
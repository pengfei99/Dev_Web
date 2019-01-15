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
            <div class="col-md-12 text-center">
                <br>
                <h1>Congratulations!</h1>
                <br>
                <br>
            %{--${messageList}--}%
                <g:each in="${messageList}" status="i" var="message">
                    ${message}
                    <br>
                    <br>
                </g:each>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 text-center">
                you can go to <a class="list" href="${createLink(uri: '/bioasterUserManager/userAccountList')}"> bioaster user list  </a> to check the new organization information
            </div>
        </div>
    </div>


    </body>
</html>
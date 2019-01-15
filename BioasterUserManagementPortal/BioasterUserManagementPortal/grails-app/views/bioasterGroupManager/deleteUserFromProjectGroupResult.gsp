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
                        <li><a class="list" href="${createLink(uri: '/bioasterGroupManager/')}"> Group manager </a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <h2>Congratulations!</h2>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-md-12">
                <g:each in="${messageList}" status="i" var="message">
                    ${message}
                    <br>
                    <br>
                </g:each>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-md-12">
                you can go to <a class="list" href="${createLink(uri: '/bioasterGroupManager/projectGroupList')}"> bioaster project group list  </a> to check the new group member information
            </div>
        </div>
        <br>
        <br>

    </div>
    %{--<body onLoad="document.addUserToProjectGroup.reset()">--}%
    </body>
</html>
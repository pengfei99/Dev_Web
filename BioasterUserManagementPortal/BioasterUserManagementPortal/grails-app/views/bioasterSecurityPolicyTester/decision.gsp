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
                        <li><a class="home" href="${createLink(uri: '/bioasterAdminDashBoard')}">Home</a></li>
                        <li><a class="glyphicon-file" href="${createLink(uri: '/bioasterSecurityPolicyTester/index')}"> XACML policy tester</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <br>
        <br>

        <div class="row">
            <div class="col-md-10">
                <g:if test="${flash.message}">
                    <div class="alert alert-info">${flash.message}</div>
                </g:if>
                <g:if test="${flash.error}">
                    <div class="alert alert-danger">${flash.error}</div>
                </g:if>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3">
                Decision in xml format:
            </div>
            <div class="col-md-6">
                ${xmlDecision}
            </div>
        </div>

        <br>
        <br>
        <div class="row">
            <div class="col-md-3">
                Decision in boolean format:
            </div>
            <div class="col-md-6">
                ${boolDecision}
            </div>
        </div>

        <br>
        <br>
        <div class="row">
            <div class="col-md-3">
                Decision in text format:
            </div>
            <div class="col-md-6">
                ${textDecision}
            </div>
        </div>


    </div>

    </body>
</html>
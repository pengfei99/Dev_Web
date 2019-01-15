<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Access Log details</title>
    </head>
    <body>
    <div class="container">
        %{-- flash message row--}%
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

        %{--navigation row--}%
        <div class="row">
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
                <li><g:link class="list" action="index">Access Log List</g:link></li>

            </ul>
        </div>
        </div>

        %{--log details row--}%

        <div class="row">
            <div id="show-accessLog" class="content scaffold-show" role="main">
            <h1>Access log details</h1>
            <f:display bean="accessLog" />
            </div>
        </div>

    </div>


    </body>
</html>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />

        <title>Access denied</title>
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

        <div class="row">
            <div class="col-md-12 text-center">
                <h1>Permission denied. To access this page, you have to login first</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-center">
                <br>
                <br>
                For more information, please contact <a href="mailto:${mail}">the admin</a>
            </div>
        </div>

    </div>
    </body>
</html>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>BUMP Access Log</title>
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
                </ul>
            </div>
        </div>

        %{--access log row--}%
        <div class="row">
        <div id="list-accessLog" class="content scaffold-list" role="main">
            <h1>Access Log List</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${accessLogList}" />

            <div class="pagination">
                <g:paginate total="${accessLogCount ?: 0}" />
            </div>
        </div>
        </div>
    </div>



    </body>
</html>
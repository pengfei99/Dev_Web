<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />

    <title>Validation Result</title>
</head>
<body>

<div class="container">

    %{--Flash message and error row--}%

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
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/bioasterAdminDashBoard')}">Home</a></li>
            </ul>
        </div>
    </div>
        <br>

    <div class="row">
        <div class="col-md-12 text-center">
            <div id="mainText" >
                <br>
                <h1>Congratulations!</h1>
                <br>
                <br>
            </div>
        </div>
        <g:each in="${messageList}" status="i" var="message">
            <div class="row">
                <div class="col-md-12 text-center">
                    ${message}
                </div>
            </div>
            <br>
        </g:each>

    </div>


</div>





</body>
</html>

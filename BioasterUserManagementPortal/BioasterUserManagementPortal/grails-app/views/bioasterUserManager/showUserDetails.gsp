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
            <div class="col-md-10">
                <g:if test="${flash.message}">
                    <div class="alert alert-info">${flash.message}</div>
                </g:if>
                <g:if test="${flash.error}">
                    <div class="alert alert-danger">${flash.error}</div>
                </g:if>
            </div>
        </div>
%{--uid row--}%
        <div class="row">
            <div class="col-md-3 text-right" >
            User Login:
            </div>
            <div class="col-md-8 text-left" >
                ${uid}
            </div>

        </div>
        <br>
%{--user name row--}%
        <div class="row">
            <div class="col-md-3 text-right" >
            User Name:
            </div>
            <div class="col-md-8 text-left" >
            ${cn}
            </div>
        </div>
        <br>
        %{--mail row--}%
        <div class="row">
            <div class="col-md-3 text-right" >
                User Email:
            </div>
            <div class="col-md-8 text-left" >
                ${mail}
            </div>

        </div>
        <br>
        %{--user gidNumber row--}%
        <div class="row">
            <div class="col-md-3 text-right" >
                User gidNumber:
            </div>
            <div class="col-md-8 text-left" >
                ${gidNumber}
            </div>
        </div>
        <br>
        %{--uidNumber row--}%
        <div class="row">
            <div class="col-md-3 text-right" >
                User uidNumber:
            </div>
            <div class="col-md-8 text-left" >
                ${uidNumber}
            </div>

        </div>
        <br>
        %{--user home dir row--}%
        <div class="row">
            <div class="col-md-3 text-right" >
                User homeDirectory:
            </div>
            <div class="col-md-8 text-left" >
                ${homeDirectory}
            </div>
        </div>
        <br>
        %{--user bioaster group row--}%
        <div class="row">
            <div class="col-md-3 text-right" >
                User BIOASTER Group:
            </div>
            <div class="col-md-8 text-left" >
                <g:each in="${bioasterGroup}" var="group">

                    ${group},

                </g:each>

            </div>
        </div>
        <br>
        %{--user project group row--}%
        <div class="row">
            <div class="col-md-3 text-right" >
                User Project Group:
            </div>
            <div class="col-md-8 text-left" >
                <g:each in="${projectGroup}" var="group">

                    ${group},

                </g:each>

            </div>
        </div>
        <br>
        %{--user bump role row--}%
        <div class="row">
            <div class="col-md-3 text-right" >
                User BUMP roles:
            </div>
            <div class="col-md-8 text-left" >
                <g:each in="${bumpRoles}" var="role">

                    ${role},

                </g:each>

            </div>
        </div>

    </div>



    </body>
</html>
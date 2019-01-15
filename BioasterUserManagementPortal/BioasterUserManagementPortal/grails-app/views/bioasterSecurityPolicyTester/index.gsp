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

        <div class="row">
            <div class="col-md-12">
                <h3> This page is for testing the XACML security policy. </h3>


                <br>
                Enter the user id, user role, target id and action in the following form. Then you will receive a decision.
            </div>
        </div>


    <br>
    <br>


        <g:form>
            %{--User id row--}%
            <div class="row">
                <div class="col-md-3"><label for="userId">Enter the user Id:</label></div>
                <div class="col-md-6"> <g:textField name="userId" maxlength="40" required=""  value=""/></div>
            </div>
            <br>
           %{--User role row--}%
            <div class="row">
                <div class="col-md-3"><label for="userRole">Enter the user role:</label></div>
                <div class="col-md-6"> <g:textField name="userRole" maxlength="40" required=""  value=""/></div>
            </div>
            <br>
            %{--Target Id row--}%
            <div class="row">
                <div class="col-md-3"><label for="targetId">Enter the target Id:</label></div>
                <div class="col-md-6"><g:textField name="targetId" maxlength="40" required=""  value=""/></div>
            </div>
            <br>
            %{--action row--}%
            <div class="row">
                <div class="col-md-3"><label for="uaction">Enter the user action:</label></div>
                <div class="col-md-6"><g:textField name="uaction" maxlength="40" required=""  value=""/></div>
            </div>
            <br>
            %{--environment row--}%
            <div class="row">
                <div class="col-md-3"><label for="envId">Enter the environment ID:</label></div>
                <div class="col-md-6"><g:textField name="envId" maxlength="40"  value=""/></div>
            </div>

            <br>
            <div class="row ">
                <div class="col-md-6 text-center">
                <fieldset class="buttons">
                    <g:actionSubmit class="save" action="evaluateRequest" value="evaluate"  />
                </fieldset>
                </div>
            </div>


        </g:form>

        <br>
        <br>
    </div>

    </body>
</html>
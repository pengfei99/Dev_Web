<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />

        <title>Bioaster User registration validation</title>
    </head>
    <body>

    <div class="container">
        <!-- This bloc is the navigation bloc  -->
        <div class="row">
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/bioasterAdminDashBoard')}">admin dashboard</a></li>
                </ul>
            </div>
        </div>

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


        <!-- The table statement can create the typical grails list table
		    the head of the table is the sortale column of all propertiy names of the object
		    instance.
		 -->
        <div class="row">
            <table class="table table-striped">
                <thead>
                <tr>

                    <g:sortableColumn property="firstName" title="First Name" />

                    <g:sortableColumn property="lastName" title="Last Name" />

                    <g:sortableColumn property="email" title="Email" />

                    <g:sortableColumn property="organization" title="Organization" />

                    <g:sortableColumn property="validator" title="Validator" />

                    <g:sortableColumn property="dateCreated" title="Creation time"/>

                </tr>
                </thead>
                <tbody>
                <g:each in="${bioasterUserRegistrationRequestList}" status="i" var="userInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "firstName")}</g:link></td>

                        <td>${fieldValue(bean: userInstance, field: "lastName")}</td>

                        <td>${fieldValue(bean: userInstance, field: "email")}</td>

                        <td>${fieldValue(bean: userInstance, field: "organization")}</td>

                        <td>${fieldValue(bean: userInstance, field: "validator")}</td>

                        <td>${fieldValue(bean: userInstance, field: "dateCreated")}</td>

                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>

    </body>
</html>
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
                <h2>This page shows all groups inside BIOASTER Project</h2>
            </div>
        </div>
        <br>
        <br>
        <form  action="showProjectGroup" method="get" name="showProjectGroup">
        <div class="row">
            <div class="col-md-3">

                <div class="scrollableTable" style="height: 700px;">
                    <table class="table table-striped">
                        <thead>
                        <tr>

                            <g:sortableColumn property="Name" title="Project Name" />


                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${projectList}" status="i" var="project">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                <td>
                                    <input type="radio" name="project" value="${project}">

                                   ${project}
                                </td>

                            </tr>
                        </g:each>
                        </tbody>
                    </table>

                </div>

            </div>

            <div class="col-md-3 text-center">
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <span class="button">
                    <button type="submit">Show Project Group List >>></button>
                </span>
            </div>

            <div id="ProjectRoleList" class="col-md-3">
                <table class="table table-striped">
                    <thead>
                    <tr>

                        <g:sortableColumn property="Name" title="Group Name" />



                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${groupList}" status="i" var="group">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                            <td><g:link action="showProjectGroupDetails" id="${group}">${group}</g:link></td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>

       %{-- <div class="row">

        </div>--}%
        </form>
    </div>

    </body>
</html>
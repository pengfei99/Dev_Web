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
            <div class="col-md-10">
                <g:if test="${flash.message}">
                     <div class="alert alert-info">${flash.message}</div>
                </g:if>
                <g:if test="${flash.error}">
                    <div class="alert alert-danger">${flash.error}</div>
                </g:if>
            </div>
        </div>
        <br>
        <br>


        <form  action="addUserToDepartmentGroup" method="get" name="addUserToDepartmentGroup">
            %{--user and organization row--}%
            <div class="row">

                %{--This colone  will show all members--}%
                <div class="col-md-6">
            Choose users which you want to add to a group or groups:
                    <br>
                    <br>



                    <div class="scrollableTable" style="height: 700px;">
                        <table class="table table-striped">
                            <thead>
                            <tr>

                                <g:sortableColumn property="Name" title="User Name" />
                                <g:sortableColumn property="Name" title="User ID" />


                            </tr>
                            </thead>
                            <tbody>
                            <g:each in="${userList}" status="i" var="user">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                    <td>
                                        <input type="checkbox" name="user_${i}" value="${user.value}">
                                        <g:link controller="bioasterUserManager" action="showUserDetails" id="${user.key}">${user.key}</g:link></td>
                                    <td>${user.value}</td>

                                </tr>
                            </g:each>
                            </tbody>
                        </table>

                    </div>
                </div>
                <div class="col-md-6">
                    %{--Available organization Information--}%

                    Choose groups which you want to add user as members:
                    <br>
                    <br>



                    <div class="scrollableTable" style="height: 700px;">
                        <table class="table table-striped">
                            <thead>
                            <tr>

                                <g:sortableColumn property="Name" title="Group Name" />


                            </tr>
                            </thead>
                            <tbody>
                            <g:each in="${allDepartmentGroups}" status="i" var="groupName">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                    <td>
                                        <input type="checkbox" name="group_${i}" value="${groupName}">
                                        <g:link action="showDepartmentGroupDetails" id="${groupName}">${groupName}</g:link></td>

                                </tr>
                            </g:each>
                            </tbody>
                        </table>

                    </div>

                </div>

            </div>

            %{--Button row--}%
            <div class="row" style="text-align: center">
                <g:hiddenField name="groupSize" value="${groupSize}"/>
                <g:hiddenField name="userSize" value="${userSize}"/>

            <br>
                <br>
                <span class="button">
                    <button type="submit">Submit</button>
                </span>
            </div>
        </form>
    </div>
    %{--<body onLoad="document.addUserToDepartmentGroup.reset()">--}%
    </body>
</html>
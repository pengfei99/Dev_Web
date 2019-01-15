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
                        <li><a class="list" href="${createLink(uri: '/bioasterGroupManager/bioasterGroupList')}"> BIOASTER Department Group List </a></li>
                    </ul>
                </div>
            </div>
        </div>
        <br>
        <hr>
%{--Group name row--}%
        <div class="row">
            <div class="col-md-2 text-right">
                Group Name:
            </div>
            <div class="col-md-6">
                ${cn}
            </div>
        </div>
        <br>
        <br>
        %{--group gid number row--}%
        <div class="row">
            <div class="col-md-2 text-right">
                Group GidNumber:
            </div>
            <div class="col-md-6">
                ${gidNumber}
            </div>
        </div>
        <br>
        <br>

        %{--group description row--}%
        <div class="row">
            <div class="col-md-2 text-right">
               Group Description:
            </div>
            <div class="col-md-6">
                ${description}
            </div>
        </div>

        %{--group member uid row--}%
        <br>
        <br>
        <div class="row">
            <div class="col-md-2 text-right">
                Group Member:
            </div>
            <div class="col-md-6">
                <g:form  controller="bioasterGroupManager" action="deleteUserFromDepartmentGroup" method="get" name="deleteUserFromDepartmentGroup">

                    <div class="scollable-zone">
                        <table class="table " >

                            <tbody>
                            <g:each in="${memberUidList}" status="i" var="user">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                    <td>
                                        <input type="checkbox" name="user_${i}" value="${user}"></td>

                                    <td>${user}</td>

                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>
                    <g:hiddenField name="groupName" value="${cn}"/>
                    <g:hiddenField name="userSize" value="${userSize}"/>
                    <br>
                    Choose users which you want to delete from this group and click delete
                    <br>
                    <span class="button">
                        <button type="submit">Delete</button>
                    </span>
                    <br>
                </g:form>
            </div>
        </div>
    </div>


    </body>
</html>
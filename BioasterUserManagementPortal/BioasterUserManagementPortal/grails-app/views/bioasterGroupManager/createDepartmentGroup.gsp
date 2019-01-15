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
<br>
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
            <div class="col-md-10">

                <div class="alert alert-warning">
                    <h5>In normal case, you should not edit the group id number. The group id number is automatically generated based on the department you choose. Be ware, if
                    you edit the group id number by yourself. You may create collision with other group which has the same id number. Make sure, there is no other group which use the
                    same id number. </h5>
                </div>
            </div>
        </div>


        <g:form>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-9">

                    <table class="table table-responsive">
                        <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><label for="departmentName">Department Name:</label>
                            </td>

                            <td valign="top">
                                <g:select name="departmentName" from="${departmentList}"  required="" value="" />
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><label for="groupName">Group Name:</label>
                            </td>

                            <td valign="top">
                                <g:textField name="groupName" maxlength="40" required=""  value=""/>
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><label for="groupName">Group ID Number:</label>
                            </td>



                            <td valign="top">
                                <input type="text", id="gidNumber" name="gidNumber" disabled>

                                <button type="button" class="btn btn-danger" onclick="EnableGidNumText()">Edit gidNumber </button>
                            </td>


                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><label for="groupDescription">Group description:</label>
                            </td>
                            <td valign="top">
                                <g:textField name="groupDescription" maxlength="80" required=""  value=""/>
                            </td>
                        </tr>



                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 text-center">
                    <fieldset class="buttons">

                        <g:actionSubmit class="save" action="createDepartmentGroupLdapEntry" value="submit"  />
                    </fieldset>
                </div>
            </div>
        </g:form>
    </div>


    <script>
        function EnableGidNumText() {
            document.getElementById("gidNumber").disabled = false;
        }
    </script>

    </body>
</html>
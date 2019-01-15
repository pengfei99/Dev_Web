<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />

        <title>Welcome to bioaster admin dash board</title>
    </head>
    <body>

    <!-- This bloc is the navigation bloc  -->
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/bioasterAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
            <li><a class="list" href="${createLink(uri: '/bioasterOrganizationManager/')}"> Organization manager </a></li>
        </ul>
    </div>

   %{--End navigation bloc--}%

<div class="container">

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


<g:form>
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-9">

            <table class="table table-responsive">
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name"><label for="organizationName">Organization Name:</label>
                    </td>

                    <td valign="top">
                        <g:textField name="organizationName" maxlength="40" required=""  value=""/>
                        %{--<input type="text" id="organizationName" name="organizationName" value="Enter the organization name"/>--}%
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><label for="organizationDescription">Organization description:</label>
                    </td>
                    <td valign="top">
                        %{-- <input type="text" id="organizationDescription" name="organizationDescription" value="Enter the organization description" />--}%
                        <g:textField name="organizationDescription" maxlength="80" required=""  value=""/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <fieldset class="buttons">

                <g:actionSubmit class="save" action="createOrganizationLdapEntry" value="submit"  />
            </fieldset>
        </div>
    </div>
</g:form>

    </div>


    </body>
</html>
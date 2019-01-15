<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />

        <title>Welcome to bioaster user account manager</title>
    </head>
    <body>
    <div class="container">

        %{--Row for the error messages--}%
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
                <h3>BIOASTER User password Manager.</h3>
                <hr>
            </div>
        </div>

        <!-- This rows explains the password change here only affects openldap  -->
        <div class="row">
            <div class="col-md-10">
                <div class="alert alert-warning">
                    <h5>Be ware, In the bioaster user management portal, you can only change or reset your
            password in OPENLdap. If you want to change your password in Active Directory, you need to contact IT/IS.</h5>

                </div>
            </div>
        </div>


        <g:form>
        %{--Row for user id form--}%
            <div class="row">
                <div class="col-md-3">
                    <h4>uid :</h4>
                </div>
                <div class="col-md-9">
                    <input type="text" id="uid" name="uid"/>
                </div>
            </div>
        %{--Row for user AD password--}%
            <div class="row">
                <div class="col-md-3">
                    <h4>User AD password :</h4>
                </div>
                <div class="col-md-9">
                    <input type="password" id="password" name="password" />
                </div>
            </div>
        %{--Row for submit button--}%
            <div class="row">
                <div class="col-md-8" style="text-align:center;">
                    <fieldset class="buttons">
                        <g:actionSubmit class="save" action="synchronizeLdapPwdWithADPwd" value="submit"  />
                    </fieldset>
                </div>
            </div>
        </g:form>




    </div>
    </body>
</html>
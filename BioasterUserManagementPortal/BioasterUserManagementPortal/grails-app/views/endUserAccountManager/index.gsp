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

        %{--change password row--}%

                <div class="row">

                    <div class="col-md-8">
                        %{--public server panel--}%
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <div class="panel-title">
                                    <a href="#" data-toggle="collapse" data-target="#changePwd">
                                         Change OPENLDAP Password
                                    </a>
                                </div>
                            </div>
                            <div id="changePwd" class="panel-collapse collapse">
                                <div class="panel-body">


                                    <div class="scollist">
                                        <h4>Notice:</h4>
                                        You need to know your old password to change your password. If your account is imported from AD,
                                        after you changed your password, your password will be not the same as your AD password. If you want
                                        to use the same password as your AD account. Please go to synchronize with AD account password
                                        <h4>Link:</h4>
                                        <g:link action="changePassword">Change OPENLDAP password</g:link>
                                    </div>
                                </div>


                            </div><!--/panel content-->

                        </div>

                    </div>
                </div>
        %{--end of change password row--}%

        <div class="row">

            <div class="col-md-8">
                %{--public server panel--}%
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <a href="#" data-toggle="collapse" data-target="#forgotPwd">
                                Forgot OPENLDAP Password
                            </a>
                        </div>
                    </div>
                    <div id="forgotPwd" class="panel-collapse collapse">
                        <div class="panel-body">


                            <div class="scollist">
                                <h4>Notice:</h4>
                                If you forgot your password, and you want the system regenrate one for you, please click on the following link.
                                After the password reset, you will receive an email which contains the temporary password, you need to change it as soon as possible.
                                If your account is imported from AD,
                                after you changed your password, your password will be not the same as your AD password. If you want
                                to use the same password as your AD account. Please go to synchronize with AD account password
                                <h4>Link:</h4>
                                <g:link action="forgotPassword">Forgot OPENLDAP password</g:link>
                            </div>
                        </div>


                    </div><!--/panel content-->

                </div>

            </div>

        </div>

        %{--synchronize AD password row--}%

        <div class="row">

            <div class="col-md-8">
                %{--public server panel--}%
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <a href="#" data-toggle="collapse" data-target="#synchronizeADPwd">
                                Synchronize OPENLDAP password with AD
                            </a>
                        </div>
                    </div>
                    <div id="synchronizeADPwd" class="panel-collapse collapse">
                        <div class="panel-body">


                            <div class="scollist">
                                <h4>Notice:</h4>
                                If you account has been imported from AD, and your password AD has been changed, you can use the
                                following link to synchronize OPENLdap password with AD password. After the action, your OPENLdap password will
                                 be the same as your AD account password.
                                <h4>Link:</h4>
                                <g:link action="synchronizeWithADPassword">Synchronize OPENLDAP password with AD</g:link>
                            </div>
                        </div>


                    </div><!--/panel content-->

                </div>

            </div>
        </div>
        %{--end of synchronize AD password row--}%




    </div>
    </div>
    </body>
</html>
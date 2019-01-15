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
            <div class="col-md-4">
                <h4>User Login:</h4>
            </div>
            <div class="col-md-8">
                <h4> ${uid} </h4>
            </div>
        </div>


        <g:form>

        %{--Row for the old password form--}%
            <div class="row">
                <div class="col-md-4">

                    <h4>Old Password :</h4>
                </div>
                <div class="col-md-8">
                    <input type="password" id="oldPassword" name="oldPassword"/>
                </div>
            </div>
        %{--Row for the new password form--}%

            <div class="row">
                <div class="col-md-4">
                    <h4>New Password :</h4>
                </div>

                <div class="col-md-4">
                    <input type="password" id="newPassword" name="newPassword" />

                    <div class="registrationFormAlert" id="divCheckPasswordStrength">
                        <br>
                    </div>
                </div>

            </div>

        %{--Row for confirm password form --}%
            <div class="row">

                <div class="col-md-4"><h4>Confirm your New Password :</h4></div>
                <div class="col-md-8"><input type="password" id="confNewPassword" name="confNewPassword"/></div>

            </div>
            <hr>
            <div class="row">
                  <div class="col-md-8" style="text-align: center;">
                    <fieldset class="buttons">
            <g:hiddenField name="uid" value="${uid}" />
            <g:actionSubmit class="save" action="changeLdapUserAccountPassword" value="submit"  />
            </fieldset>
            </div>
            </div>
        </g:form>


        <script>
            function checkPasswordMatch() {
                var password = $("#newPassword").val();
                $.ajax({

                    url:'${g.createLink( controller:'bioasterUserRegistrationRequest', action:'checkPassword' )}',
                    data:{ password: password },
                    success: function(response){
                        $("#divCheckPasswordStrength").html(response);
                    }
                });

            }
            $(document).ready(function () {
                $("#newPassword").keyup(checkPasswordMatch);
            });


        </script>


    </div>
    </body>
</html>
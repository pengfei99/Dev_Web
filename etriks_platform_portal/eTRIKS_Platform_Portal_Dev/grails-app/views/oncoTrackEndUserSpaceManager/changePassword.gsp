<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    %{--<g:javascript library="jquery"/>--}%
    <title>Change Your password</title>
</head>
<body>

%{--Begin of the bootstrap container--}%
<div class="container">
    %{--Row for the error messages--}%
    <div class="row">
        <g:if test="${flash.message}">
            <div class="message" role="alert">${flash.message}</div>
        </g:if>
        <br>
    </div>

    %{--Row for the userid--}%

    <div class="row">
        <div class="col-md-3">
            <h4>User Id:</h4>
        </div>
        <div class="col-md-9">
            <h4> ${uid} </h4>
        </div>
    </div>
    <g:form>

    %{--Row for the old password form--}%
        <div class="row">
            <div class="col-md-3">

                <h4>Old Password :</h4>
            </div>
            <div class="col-md-9">
                <input type="password" id="oldPassword" name="oldPassword"/>
            </div>
        </div>
    %{--Row for the new password form--}%

        <div class="row">
            <div class="col-md-3">
                <h4>New Password :</h4>
            </div>

            <div class="col-md-3">
                <input type="password" id="newPassword" name="newPassword" onkeyup="checkPasswordStrength(this.value)" />
            </div>
            <div class="col-md-6">
                <div id="passWordrules">
                    The Password must contain at least 8 letters, symbols or numbers!
                </div>
            </div>
        </div>

    %{--Row for confirm password form --}%
        <div class="row">

            <div class="col-md-3"><h4>Confirm your New Password :</h4></div>
            <div class="col-md-9"><input type="password" id="confNewPassword" name="confNewPassword"/></div>

        </div>
        <hr>
        <div class="row">
              <div class="col-md-9" style="text-align: center;">
                <fieldset class="buttons">
        <g:hiddenField name="uid" value="${uid}" />
        <g:actionSubmit class="save" action="changeLdapUserAccountPassword" value="submit"  />
        </fieldset>
    </g:form>
</div>
</div>


</div>
</div>
%{--End of the bootrstrap container--}%


%{--The javascript function checkPasswordStrength call the action checkPasswordStrength in controller, which will update the message in the text box of passWordrules--}%
<script>

    function checkPasswordStrength(password) {
        <g:remoteFunction controller="etriksEndUserAccountManager" action="checkPasswordStrength" update="passWordrules" params="'password='+password"/>
    }
</script>
</body>
</html>
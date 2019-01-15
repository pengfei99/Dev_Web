<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 10/21/14
  Time: 5:46 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Reset your password</title>
</head>

<body>
<div class="container">
    %{--Row for exception and error message--}%
    <div class="row">
        <div class="col-md-12">
            <g:if test="${flash.message}">
                <div class="message" role="alert">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
                <div class="message" role="error">${flash.error}</div>
            </g:if>
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
    %{--Row for user register emails--}%
        <div class="row">
            <div class="col-md-3">
                <h4>registered email :</h4>
            </div>
            <div class="col-md-9">
                <input type="text" id="mail" name="mail"/>
            </div>
        </div>
    %{--Row for submit button--}%
        <div class="row">
            <div class="col-md-8" style="text-align:center;">
                <fieldset class="buttons">
                    <g:actionSubmit class="save" action="resetLdapUserAccountPassword" value="submit"  />
                </fieldset>
            </div>
        </div>
    </g:form>

</div>






</div>
</body>
</html>
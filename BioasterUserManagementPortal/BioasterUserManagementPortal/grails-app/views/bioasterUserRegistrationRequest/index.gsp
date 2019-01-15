<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'bioasterUserRegistrationRequest.label', default: 'BioasterUserRegistrationRequest')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

        %{--Capcha css--}%
        <style>
        #capcha{
        border: 5px solid black;
       /* background:transparent url(../images/capchaBackGround.jpg) ;*/
        }
    </style>
        %{--End of Capcha css--}%
    </head>
    <body>
    <div class="container">

        %{-- flash message row--}%
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

        %{--Description row--}%
        <div class="row">
            <div class="col-md-10">
                <H3>
                    You are about to submit a request to create a user account in BIOASTER. </H3>
                <div class="alert alert-warning">
                This account allows you to access some of the BIOASTER services.
                Before you start, make sure you have an organization email. This email will be used to verify your information, modify your password.
                You also need to choose a validator from BIOASTER which knows who you are.</div>
            </div>
        </div>
        %{--End of Description row--}%

%{--Capcha Image row--}%
        <div class="row">
        <div class="col-md-3"></div>
            <div class="col-md-4 text-center">
                <div id="capcha">
                    <img src="${createLink(controller:'bioasterUserRegistrationRequest', action:'createCaptcha')}" alt="captchaImage" width="160px"height="50px" />
                </div>
            </div>
        <div class="col-md-5"></div>
        </div>

        %{--End of Capcha Image row--}%

        <br>
        <g:form action="capchaCheck">
        %{--Capcha answer row--}%
            <div class="row">
                <div class="col-md-3 text-right">
                    <label> Enter the answer
                        <span class="required-indicator">*</span>
                    </label>
                </div>
                <div class="col-md-4 text-left required">
                    <g:field type="text" name="captchaResponse" maxlength="10" required=""/>
                </div>


                <div class="col-md-5 text-left">


                </div>
            </div>
            <br>
            <br>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-4">
                    <g:submitButton name="submit" value="Submit" />
                </div>
            </div>

        </g:form>



        %{--End of Capcha answer row--}%


    </div>
    </body>
</html>
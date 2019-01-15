<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'bioasterUserRegistrationRequest.label', default: 'BioasterUserRegistrationRequest')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>



            <h1>Congratulation</h1>



    <!-- This bloc shows the user accounts information which just be created -->

    <div id="show-registrationRequestInfo" class="container">

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
            <h3>Your registration request has been sent to the validator. You will receive a confirmation email if your registration request is validated!</h3>

        </div>
        <hr>

        <div class="row">
            <h4>Your account information :</h4>
            <br>
            <dl class="dl-horizontal">
            %{--first name row--}%
                <g:if test="${firstName}">
                    <dt><span id="firstName-label" class="property-label"> First Name : </span></dt>
                    <dd> <span class="property-value" aria-labelledby="firstName-label"> ${firstName}</span> </dd>
                </g:if>
                <br>
            %{--last name row--}%
                <g:if test="${lastName}">
                    <dt><span id="lastName-label" class="property-label">Last Name : </span></dt>
                    <dd> <span class="property-value" aria-labelledby="lastName-label">${lastName}</span></dd>
                </g:if>
                <br>
            %{--email row--}%
                <g:if test="${email}">
                    <dt><span id="email-label" class="property-label">Email : </span></dt>
                    <dd> <span class="property-value" aria-labelledby="email-label">${email}</span></dd>
                </g:if>
                <br>
            %{--organisation row--}%
                <g:if test="${organization}">
                    <dt><span id="organization-label" class="property-label">Organization : </span></dt>
                    <dd> <span class="property-value" aria-labelledby="organization-label">${organization}</span> </dd>
                </g:if>
                <br>
            %{--groups row--}%
                <g:if test="${validator}">
                    <dt> <span id="groups-label" class="property-label">Validator : </span></dt>
                    <dd><span class="property-value" aria-labelledby="groups-label">${validator}</span></dd>
                </g:if>

            </dl>
        </div>
    </div>


    </body>
</html>

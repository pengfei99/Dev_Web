<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Bioaster User registration validation</title>
</head>
<body>

<div class="container">

    %{--Flash message and error row--}%
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

    %{--firstName Row--}%

        <g:if test="${bioasterUserRegistrationRequest?.firstName}">
            <div class="row">
                <div class="col-md-3 text-right">
                    <span id="firstName-label" class="property-label"> First Name: </span>
                </div>
                <div class="col-md-3 text-left">
                    <span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${bioasterUserRegistrationRequest}" field="firstName"/></span>
                </div>
            </div>
        </g:if>
        <br>

    %{--lastName row--}%
        <g:if test="${bioasterUserRegistrationRequest?.lastName}">
            <div class="row">
                <div class="col-md-3 text-right">
                    <span id="lastName-label" class="property-label">Last Name: </span>
                </div>
                <div class="col-md-3 text-left">
                    <span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${bioasterUserRegistrationRequest}" field="lastName"/></span>
                </div>
            </div>
        </g:if>
        <br>

    %{--email row--}%
        <g:if test="${bioasterUserRegistrationRequest?.email}">
            <div class="row">
                <div class="col-md-3 text-right">
                    <span id="email-label" class="property-label"> Email: </span>
                </div>
                <div class="col-md-3 text-left">
                    <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${bioasterUserRegistrationRequest}" field="email"/></span>
                </div>
            </div>
        </g:if>
        <br>

    %{--organization row--}%
        <g:if test="${bioasterUserRegistrationRequest?.organization}">
            <div class="row">
                <div class="col-md-3 text-right">
                    <span id="organization-label" class="property-label">Organization: </span>
                </div>
                <div class="col-md-3 text-left">
                    <span class="property-value" aria-labelledby="organization-label"><g:fieldValue bean="${bioasterUserRegistrationRequest}" field="organization"/></span>
                </div>
            </div>
        </g:if>
        <br>

        <br>
        <br>


        <g:form>
            <br>

            <div class="row">
                <fieldset class="buttons">

                    <g:hiddenField name="id" value="${bioasterUserRegistrationRequest?.id}" />
                    %{--<g:link class="edit" action="edit" id="${bioasterUserRegistrationRequest?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--}%
                    <div class="col-md-6 text-center">
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </div>
                    <div class="col-md-4 text-center">
                        <g:actionSubmit class="edit" action="validatorValidation"  id="${bioasterUserRegistrationRequest?.id}" value="Validate registration request"  />
                    </div>
                </fieldset>
            </div>
        </g:form>
    </div>
</div>





</body>
</html>

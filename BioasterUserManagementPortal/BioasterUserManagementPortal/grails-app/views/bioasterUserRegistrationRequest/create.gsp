<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />

        <title>BioasterUserRegistrationRequest</title>

        <style>
            .fieldcontain{
                margin: 0;
            }
            .fieldcontain label, .fieldcontain .property-label{
                width: 100%;
            }
        </style>
    </head>
    <body>



    %{--<div id="create-bioasterUserRegistrationRequest"  class="content scaffold-create" role="main">--}%

        <div class="container">
    %{--title and error message role--}%
             <div class="row">
<div class="col-md-9">
                 <h1>Create BioasterUserRegistrationRequest</h1>
                 <hr>
                 <g:if test="${flash.message}">
                     <h4> <div class="alert alert-danger">${flash.message}</div> </h4>
                 </g:if>
                 <g:hasErrors bean="${this.bioasterUserRegistrationRequest}">
                     <ul class="alert alert-danger">
                         <g:eachError bean="${this.bioasterUserRegistrationRequest}" var="error">
                             <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                         </g:eachError>
                     </ul>
                 </g:hasErrors>
</div>
             </div>



            <g:form action="save">

%{--Beging form--}%


                   %{--First name row--}%

                        <div class="row">
                            <div class="col-md-3 text-right">
                                <div class="fieldcontain ${hasErrors(bean:bURRInstance , field: 'firstName', 'error')} required">
                                   <label for="firstName">
                                       First_Name
                                       <span class="required-indicator">*</span>
                                    </label>
                                </div>


                            </div>
                            <div class="col-md-3 text-left">
                                <g:textField name="firstName" maxlength="40" required="" value="${bURRInstance?.firstName}"/>
                            </div>

                        </div>
                        <br>


                        %{--last name row--}%

                        <div class="row">
                            <div class="col-md-3 text-right">
                                <div class="fieldcontain ${hasErrors(bean: bURRInstance, field: 'lastName', 'error')} required">
                                    <label for="lastName">
                                        Last Name
                                        <span class="required-indicator">*</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-3 text-left">
                                <g:textField name="lastName" maxlength="40" required="" value="${bURRInstance?.lastName}"/>
                            </div>
                        </div>
                        <br>

                        %{--emails row--}%

                        <div class="row">
                            <div class="col-md-3 text-right">
                                <div class="fieldcontain ${hasErrors(bean: bURRInstance, field: 'email', 'error')} required">
                                    <label for="email">
                                        Email
                                        <span class="required-indicator">*</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-3 text-left">
                                <g:field type="email" name="email" maxlength="80" required="" value="${bURRInstance?.email}"/>
                            </div>

                        </div>
                        <br>

                        %{--password row--}%

                        <div class="row">
                            <div class="col-md-3 text-right">
                                <div class="fieldcontain ${hasErrors(bean: bURRInstance, field: 'password', 'error')} required">
                                    <label for="password">
                                        Password
                                        <span class="required-indicator">*</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-4 text-left">
                                <g:field id="txtPassword" type="password" name="password" maxlength="40" required="" value="${bURRInstance?.password}"/>

                                <div class="registrationFormAlert" id="divCheckPasswordStrength">
                                <br>
                                </div>



                            </div>


                        </div>


                        %{--confirm password row--}%


                        <div class="row">
                            <div class="col-md-3 text-right">
                                <div class="fieldcontain ${hasErrors(bean: bURRInstance, field: 'confirmPassword', 'error')} required">
                                    <label for="confirmPassword">
                                        Confirm Password
                                        <span class="required-indicator">*</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-3 text-left">
                                <g:field type="password" name="confirmPassword" maxlength="40" required="" value="${bURRInstance?.confirmPassword}"/>
                            </div>
                        </div>
                        <br>

                        %{--organization row--}%

                        <div class="row">
                            <div class="col-md-3 text-right">
                                <div class="fieldcontain ${hasErrors(bean: bURRInstance, field: 'organization', 'error')} required">
                                    <label for="organization">
                                        Organization
                                        <span class="required-indicator">*</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-3 text-left">
                                <g:select name="organization" from="${organizationList}"  required="" value="${bURRInstance?.organization}" />
                            </div>
                        </div>
                        <br>




                        %{-- validator row--}%

                        <div class="row">
                            <div class="col-md-3 text-left">
                                <div class="fieldcontain ${hasErrors(bean: bURRInstance, field: 'validator', 'error')} required">
                                    <label for="validator">
                                        BIOASTER Validator <span class="required-indicator">*</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-3 text-left">
                                <g:select name="validator" from="${validatorList}" required="" value="${bURRInstance?.validator}" />
                            </div>
                        </div>
                        <br>



                        <g:hiddenField name="validatorDecision" value="false"/>
                    </div>


                    %{--End form--}%
              %{--  </fieldset>--}%
                <div class="row">
                    <div class="col-md-1" ></div>
                    <div class="col-md-6 text-center">
                        <fieldset class="buttons">
                            <g:submitButton name="create" class="save" value="Submit request" />
                        </fieldset>
                    </div>
                </div>

            </g:form>
       %{-- </div>--}%

    %{--javascript for password strength check--}%

    <script>
        function checkPasswordMatch() {
            var password = $("#txtPassword").val();
            $.ajax({

                url:'${g.createLink( controller:'bioasterUserRegistrationRequest', action:'checkPassword' )}',
                data:{ password: password },
                success: function(response){
                    $("#divCheckPasswordStrength").html(response);
                }
            });

        }
        $(document).ready(function () {
            $("#txtPassword").keyup(checkPasswordMatch);
        });


    </script>

    </body>
</html>

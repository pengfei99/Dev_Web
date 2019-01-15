<%@ page import="org.etriks.security.register.EtriksMemberAccount" %>


%{--First name row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'firstName', 'error')} required">
            <label for="firstName">
                First Name
                <span class="required-indicator">*</span>
            </label>


        </div>
    </div>
    <div class="col-md-3 text-left"> <g:textField name="firstName" maxlength="40" required="" value="${etriksMemberAccountInstance?.firstName}"/></div>
</div>
<br>

%{--Last name row--}%
<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'lastName', 'error')} required">
            <label for="lastName">
               Last Name
                <span class="required-indicator">*</span>
            </label>

        </div>
    </div>
    <div class="col-md-3 text-left">
        <g:textField name="lastName" maxlength="40" required="" value="${etriksMemberAccountInstance?.lastName}"/>
    </div>
</div>
<br>

%{--email row--}%
<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'email', 'error')} required">
            <label for="email">
                Email
                <span class="required-indicator">*</span>
            </label>

        </div>
    </div>
    <div class="col-md-3 text-left">
        <g:field type="email" name="email" maxlength="80" required="" value="${etriksMemberAccountInstance?.email}"/>
    </div>

</div>
<br>

%{--password row--}%
<div class="row">
    <div class="col-md-2 text-right">

        <div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'password', 'error')} required">
            <label for="password">
                Password
                <span class="required-indicator">*</span>
            </label>

            %{--The message after password case--}%
        </div>
    </div>

    <div class="col-md-3 text-left">

        %{--The onkeyup event occurs when the user releases a key (on the keyboard), Here when the new characters are keyin the password it calls function checkPasswordStrength--}%
        <g:field id="password" type="password" name="password" maxlength="40" required="" data-toggle="tooltip" onkeyup="checkPasswordStrength(this.value)" value="${etriksMemberAccountInstance?.password}"/>

        %{--The javascript function checkPasswordStrength call the action checkPasswordStrength in controller, which will update the message in the text box of passWordrules--}%
        <script>

            function checkPasswordStrength(password) {
                <g:remoteFunction controller="etriksMemberAccount" action="checkPasswordStrength" update="passWordrules" params="'password='+password"/>
            }

        </script>
    </div>

    <div class="col-md-7 text-left">
        <div  id="passWordrules">
            The Password must contain at least 8 letters, symbols or numbers!
        </div>
    </div>
</div>

<br>

%{--confirm password--}%
<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'confirmPassword', 'error')} required">
            <label for="confirmPassword">
                <g:message code="etriksMemberAccount.confirmPassword.label" default="Confirm Password" />
                <span class="required-indicator">*</span>
            </label>
    </div>
    </div>

    <div class="col-md-3 text-left">
        <g:field type="password" name="confirmPassword" maxlength="40" required="" value="${etriksMemberAccountInstance?.confirmPassword}"/>
    </div>

</div>

<br>

%{--organization row--}%
<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'organization', 'error')} required">
            <label for="organization">
                <g:message code="etriksMemberAccount.organization.label" default="Organization" />
                <span class="required-indicator">*</span>
            </label>

    </div>
    </div>
    <div class="col-md-3 text-left">

        <g:select name="organization" from="${org.etriks.security.registration.Organization?.values()}" keys="${org.etriks.security.registration.Organization.values()*.name()}" required="" value="${etriksMemberAccountInstance?.organization?.name()}" />

    </div>

</div>

<br>

%{--workpackage row--}%
<div class="row">
    <div class="col-md-2 text-right">

        <div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'workPackage', 'error')} required">
            <label for="workPackage">
                <g:message code="etriksMemberAccount.workPackage.label" default="Work Package" />
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>

    <div class="col-md-3 text-left">

        <g:select name="workPackage" from="${org.etriks.security.registration.WorkPackage?.values()}" keys="${org.etriks.security.registration.WorkPackage.values()*.name()}" required="" value="${etriksMemberAccountInstance?.workPackage?.name()}" />

    </div>

</div>

<br>




%{--validator row--}%
<div class="row">
    <div class="col-md-2 text-right">

        <div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'validator', 'error')} required">
            <label for="validator">
                <g:message code="etriksMemberAccount.validator.label" default="Validator" />
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>

    <div class="col-md-3 text-left">
        <g:select name="validator" from="${org.etriks.security.registration.Validator?.values()}" keys="${org.etriks.security.registration.Validator.values()*.name()}" required="" value="${etriksMemberAccountInstance?.validator?.name()}" />


    </div>

</div>
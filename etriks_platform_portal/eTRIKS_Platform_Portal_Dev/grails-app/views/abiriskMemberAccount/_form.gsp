<%@ page import="org.etriks.security.register.AbiriskMemberAccount" %>

%{--First name row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: abiriskMemberAccountInstance, field: 'firstName', 'error')} required">
            <label for="firstName">
                First Name
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>
    <div class="col-md-3 text-left">
        <g:textField name="firstName" maxlength="40" required="" value="${abiriskMemberAccountInstance?.firstName}"/>
    </div>
</div>
<br>


%{--last name row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: abiriskMemberAccountInstance, field: 'lastName', 'error')} required">
            <label for="lastName">
                Last Name
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>
    <div class="col-md-3 text-left">
        <g:textField name="lastName" maxlength="40" required="" value="${abiriskMemberAccountInstance?.lastName}"/>
    </div>
</div>
<br>

%{--emails row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: abiriskMemberAccountInstance, field: 'email', 'error')} required">
            <label for="email">
                Email
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>
    <div class="col-md-3 text-left">
        <g:field type="email" name="email" maxlength="80" required="" value="${abiriskMemberAccountInstance?.email}"/>
    </div>
</div>
<br>

%{--password row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: abiriskMemberAccountInstance, field: 'password', 'error')} required">
            <label for="password">
               Password
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>
    <div class="col-md-3 text-left">
        <g:field type="password" name="password" maxlength="40" required="" onkeyup="checkPasswordStrength(this.value)" value="${abiriskMemberAccountInstance?.password}"/>

        %{--The javascript function checkPasswordStrength call the action checkPasswordStrength in controller, which will update the message in the text box of passWordrules--}%
        <script>

            function checkPasswordStrength(password) {
                <g:remoteFunction controller="abiriskMemberAccount" action="checkPasswordStrength" update="passWordrules" params="'password='+password"/>
            }
        </script>
    </div>
    <div class="col-md-7 text-left">
        %{--The message after password case--}%
        <div id="passWordrules">
            The Password must contain at least 8 letters, symbols or numbers!
        </div>
    </div>
</div>
<br>

%{--confirm password row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: abiriskMemberAccountInstance, field: 'confirmPassword', 'error')} required">
            <label for="confirmPassword">
                Confirm Password
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>
    <div class="col-md-3 text-left">
        <g:field type="password" name="confirmPassword" maxlength="40" required="" value="${abiriskMemberAccountInstance?.confirmPassword}"/>
    </div>
</div>
<br>

%{--organization row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: abiriskMemberAccountInstance, field: 'organization', 'error')} required">
            <label for="organization">
                Organization
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>
    <div class="col-md-3 text-left">
        <g:select name="organization" from="${organizationList}"  required="" value="" />
    </div>
</div>
<br>


%{--group row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: abiriskMemberAccountInstance, field: 'groups', 'error')} required">
            <label for="groups">
                Groups
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>
    <div class="col-md-3 text-left">
        <g:select name="groups" from="${groupList}" required="" value="" />
    </div>
</div>
<br>

%{--abirisk validator row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: abiriskMemberAccountInstance, field: 'abiriskValidator', 'error')} required">
            <label for="abiriskValidator">
                Abirisk Validator
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>
    <div class="col-md-3 text-left">
        <g:select name="abiriskValidator" from="${abiriskValidatorList}" required="" value="" />
    </div>
</div>
<br>

%{--hosting validator row--}%

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: abiriskMemberAccountInstance, field: 'hostingValidator', 'error')} required">
            <label for="hostingValidator">
                Hosting Validator
                <span class="required-indicator">*</span>
            </label>
            </div>
    </div>
    <div class="col-md-3 text-left">
        <g:select name="hostingValidator" from="${hostingValidatorList}" required="" value="" />
    </div>
</div>
<br>

<g:hiddenField name="abiriskValidatorDecision" value="false"/>

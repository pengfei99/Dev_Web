<%@ page import="org.etriks.security.register.OncoTrackMemberAccount" %>


%{--First name row--}%

<div class="row">
    <div class="col-md-3 text-right">
        <div class="fieldcontain ${hasErrors(bean: oncoTrackMemberAccountInstance, field: 'firstName', 'error')} required">
            <label for="firstName">
                First Name
                <span class="required-indicator">*</span>
            </label>
        </div>
    </div>
    <div class="col-md-3 text-left">
        <g:textField name="firstName" maxlength="40" required="" value="${oncoTrackMemberAccountInstance?.firstName}"/>
    </div>
</div>
<br>

%{--last name row--}%

<div class="row">
    <div class="col-md-3 text-right">
        <div class="fieldcontain ${hasErrors(bean: oncoTrackMemberAccountInstance, field: 'lastName', 'error')} required">
            <label for="lastName">
                Last Name
                <span class="required-indicator">*</span>
            </label>
        </div>
    </div>
    <div class="col-md-3 text-left">
        <g:textField name="lastName" maxlength="40" required="" value="${oncoTrackMemberAccountInstance?.lastName}"/>
    </div>
</div>
<br>

%{--emails row--}%

<div class="row">
    <div class="col-md-3 text-right">
        <div class="fieldcontain ${hasErrors(bean: oncoTrackMemberAccountInstance, field: 'email', 'error')} required">
            <label for="email">
                Email
                <span class="required-indicator">*</span>
            </label>
        </div>
    </div>
    <div class="col-md-3 text-left">
        <g:field type="email" name="email" maxlength="80" required="" value="${oncoTrackMemberAccountInstance?.email}"/>
    </div>
</div>
<br>

%{--password row--}%

<div class="row">
    <div class="col-md-3 text-right">
        <div class="fieldcontain ${hasErrors(bean: oncoTrackMemberAccountInstance, field: 'password', 'error')} required">
            <label for="password">
                Password
                <span class="required-indicator">*</span>
            </label>
        </div>
    </div>
    <div class="col-md-3 text-left">
        <g:field type="password" name="password" maxlength="40" required="" onkeyup="checkPasswordStrength(this.value)" value="${bioasterMemberAccountInstance?.password}"/>

        %{--The javascript function checkPasswordStrength call the action checkPasswordStrength in controller, which will update the message in the text box of passWordrules--}%
        <script>
            function checkPasswordStrength(password) {
                <g:remoteFunction controller="oncoTrackMemberAccount" action="checkPasswordStrength" update="passWordrules" params="'password='+password"/>
            }
        </script>
    </div>
    <div class="col-md-6 text-left">
        %{--The message after password case--}%
        <div id="passWordrules">
            The Password must contain at least 8 letters, symbols or numbers!
        </div>
    </div>
</div>
<br>

%{--confirm password row--}%

<div class="row">
    <div class="col-md-3 text-right">
        <div class="fieldcontain ${hasErrors(bean: oncoTrackMemberAccountInstance, field: 'confirmPassword', 'error')} required">
            <label for="confirmPassword">
                Confirm Password
                <span class="required-indicator">*</span>
            </label>
        </div>
    </div>
    <div class="col-md-3 text-left">
        <g:field type="password" name="confirmPassword" maxlength="40" required="" value="${oncoTrackMemberAccountInstance?.confirmPassword}"/>
    </div>
</div>
<br>

%{--organization row--}%

<div class="row">
    <div class="col-md-3 text-right">
        <div class="fieldcontain ${hasErrors(bean: oncoTrackMemberAccountInstance, field: 'organization', 'error')} required">
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
    <div class="col-md-3 text-right">
        <div class="fieldcontain ${hasErrors(bean: oncoTrackMemberAccountInstance, field: 'groups', 'error')} required">
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

%{--bioaster validator row--}%

<div class="row">
    <div class="col-md-3 text-right">
        <div class="fieldcontain ${hasErrors(bean: oncoTrackMemberAccountInstance, field: 'oncoTrackValidator', 'error')} required">
            <label for="oncoTrackValidator">
                Oncotrack Validator
                <span class="required-indicator">*</span>
            </label>
        </div>
    </div>
    <div class="col-md-3 text-left">
        <g:select name="oncoTrackValidator" from="${oncotrackValidatorList}" required="" value="" />
    </div>
</div>
<br>

%{--hosting validator row--}%

<div class="row">
    <div class="col-md-3 text-right">
        <div class="fieldcontain ${hasErrors(bean: oncoTrackMemberAccountInstance, field: 'hostingValidator', 'error')} required">
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


<g:hiddenField name="oncoTrackValidatorDecision" value="false"/>

<g:hiddenField name="validatorDecision" value="false"/>
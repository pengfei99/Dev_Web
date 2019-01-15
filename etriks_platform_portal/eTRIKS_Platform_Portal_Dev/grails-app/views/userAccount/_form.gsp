<%@ page import="org.etriks.security.register.UserAccount" %>

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'firstName', 'error')} required">
            <label for="firstName">
                 First Name
                <span class="required-indicator">*</span>
            </label>

        </div>
    </div>
    <div class="col-md-3 text-left">  <g:textField name="firstName" maxlength="40" required="" value="${userAccountInstance?.firstName}"/> </div>
    <div class="col-md-7"></div>
</div>
<br>


<div class="row">
    <div class="col-md-2 text-right">

        <div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'lastName', 'error')} required">
        <label for="lastName">
            Last Name
            <span class="required-indicator">*</span>
        </label>


    </div></div>
    <div class="col-md-3 text-left"> <g:textField name="lastName" maxlength="40" required="" value="${userAccountInstance?.lastName}"/></div>
    <div class="col-md-6"></div>
</div>

<br>

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'email', 'error')} required">
            <label for="email">
                Email
                <span class="tab-space">   </span>
                <span class="required-indicator">*</span>
            </label>


        </div>
        </div>
    <div class="col-md-3 text-left"><g:field type="email" name="email" maxlength="80" required=""  value="${userAccountInstance?.email}"/></div>
</div>


<br>

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'password', 'error')} required">
            <label for="password">
                Password
                <span class="required-indicator">*</span>
            </label>

        </div>
    </div>
    <div class="col-md-3 text-left">
        <g:field type="password" name="password" maxlength="40" required="" onkeyup="checkPasswordStrength(this.value)" value="${userAccountInstance?.password}"/>

        %{--The javascript function checkPasswordStrength call the action checkPasswordStrength in controller, which will update the message in the text box of passWordrules--}%
        <script>

            function checkPasswordStrength(password) {
                <g:remoteFunction controller="etriksMemberAccount" action="checkPasswordStrength" update="passWordrules" params="'password='+password"/>
            }
        </script>
        %{--The message after password case--}%
    </div>
    <div class="col-md-7 text-left">
        <div id="passWordrules">
        The Password must contain at least 8 letters, symbols or numbers!
    </div>
    </div>
</div>

<br>

<div class="row">
    <div class="col-md-2 text-right">
        <div class="fieldcontain ${hasErrors(bean: userAccountInstance, field: 'confirmPassword', 'error')} required">
            <label for="confirmPassword">
                Confirm Password
                <span class="required-indicator">*</span>
            </label>


        </div>
    </div>
    <div class="col-md-3 text-left"><g:field type="password" name="confirmPassword" maxlength="40" required="" value="${userAccountInstance?.confirmPassword}"/></div>
</div>

<br>


<div class="row">

    <div class="col-md-2 text-right required">
<label> Enter the answer
    <span class="required-indicator">*</span>
</label>

    </div>
    <div class="col-md-3 text-left">
        <input type='text' name='captchaResponse' value=''>
    </div>

    <div class="col-md-7 text-left">


    </div>
</div>

<br>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-3 text-left">
        <div id="capcha">
            <img src="${createLink(controller:'userAccount', action:'createCaptcha')}" alt="captchaImage" width="160px"height="50px" />
        </div>
    </div>
</div>


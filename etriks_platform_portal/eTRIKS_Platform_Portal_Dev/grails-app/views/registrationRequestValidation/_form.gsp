<%@ page import="org.etriks.security.register.EtriksMemberAccount" %>



<div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="etriksMemberAccount.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="40" required="" value="${etriksMemberAccountInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="etriksMemberAccount.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" maxlength="40" required="" value="${etriksMemberAccountInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="etriksMemberAccount.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" maxlength="80" required="" value="${etriksMemberAccountInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="etriksMemberAccount.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" maxlength="40" required="" value="${etriksMemberAccountInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'confirmPassword', 'error')} required">
	<label for="confirmPassword">
		<g:message code="etriksMemberAccount.confirmPassword.label" default="Confirm Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="confirmPassword" maxlength="40" required="" value="${etriksMemberAccountInstance?.confirmPassword}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'organization', 'error')} required">
	<label for="organization">
		<g:message code="etriksMemberAccount.organization.label" default="Organization" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="organization" from="${org.etriks.security.register.Organization?.values()}" keys="${org.etriks.security.register.Organization.values()*.name()}" required="" value="${etriksMemberAccountInstance?.organization?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: etriksMemberAccountInstance, field: 'workPackage', 'error')} required">
	<label for="workPackage">
		<g:message code="etriksMemberAccount.workPackage.label" default="Work Package" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="workPackage" from="${org.etriks.security.register.WorkPackage?.values()}" keys="${org.etriks.security.register.WorkPackage.values()*.name()}" required="" value="${etriksMemberAccountInstance?.workPackage?.name()}"/>
</div>


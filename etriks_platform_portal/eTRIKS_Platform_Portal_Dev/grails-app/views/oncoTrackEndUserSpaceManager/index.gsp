<%@ page contentType="text/html; charset=UTF-8"%>>
<%@ page import="org.etriks.security.ldap.authentication.UserDetails"%>

<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>Abirisk end user space</title>
</head>
<body>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
		</ul>
	</div>
	<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
<div id="bodyText">
<h3> This page is reserved for the <span style="text-transform: uppercase ;color:red;font-weight:bold ">oncoTrack</span> project member only. All the chang you make in this page only affect your <span style="text-transform: uppercase ;color:red;font-weight:bold ">oncoTrack</span> etriks platform account </h3>

    <br>
    <br>

    If you know your password and you just want to change it , please follow this link : <g:link action="changePassword"> Change password</g:link>.
    <br>
    <br>

    If you forgot your password and want to reset it, please follow this link :  <g:link action="forgotPassword"> Forgot password</g:link>.

    <br>
    <br>

    </div>

</body>
</html>
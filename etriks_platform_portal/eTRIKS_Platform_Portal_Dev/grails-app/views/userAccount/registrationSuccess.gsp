<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.etriks.security.register.UserAccount" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Account registration success</title>
</head>
<body>

		<!-- This bloc shows the user accounts information which just be created -->
		
		<div id="show-userAccount" class="container">

			<h3>Congratulations!!! Your account has been created, you will receive a confirmation email shortly!!!</h3>
			<hr>
			<p>
			<h4>Your account information :</h4>
    <br>
			</p>
			<dl class="dl-horizontal">

				<dt>
                <g:if test="${firstName}">
					<span id="firstName-label" class="property-label">First Name:</span>
				</dt>
				</g:if>

                <dd> <span class="property-value">${firstName}</span>  </dd>

                <br>
				<dt>
                <g:if test="${lastName}">
					<span id="lastName-label" class="property-label">Last Name:</span>
				</dt>
                    <dd>	<span class="property-value">${lastName}</span></dd>
				</g:if>
                 <br>
                <g:if test="${uid}">
                    <dt>
                    <span id="lastName-label" class="property-label">User Id:</span>
                    </dt>
                    <dd>	<span class="property-value">${uid}</span></dd>
                </g:if>
                <br>
                <dt> <g:if test="${email}">
                <span id="email-label" class="property-label">Email:</span></dt>
                <dd><span class="property-value" aria-labelledby="email-label">${email}</span></dd>
<hr>

				</g:if>
            </dl>
		</div>
</body>
</html>
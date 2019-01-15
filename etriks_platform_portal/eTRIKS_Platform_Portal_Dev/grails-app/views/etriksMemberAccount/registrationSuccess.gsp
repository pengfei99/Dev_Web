<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.etriks.security.register.EtriksMemberAccount" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Account registration success</title>
</head>
<body>
		
		<!-- This bloc shows the user accounts information which just be created -->

   <div id="show-etriksMemberAccount" class="container">

					<h3>Congratulations!!! Your registration request has been sent to the validator, you will receive a confirmation email if your registration request is validated!!!</h3>
<hr>
    <dl class="dl-horizontal">

        <g:if test="${firstName}">
            <dt> <span id="firstName-label" class="property-label">First Name</span></dt>

            <dd>   <span class="property-value" aria-labelledby="firstName-label">${firstName}</span> </dd>

        </g:if>
<br>
        <g:if test="${lastName}">
            <dt>    <span id="lastName-label" class="property-label">Last Name</span> </dt>

            <dd>    <span class="property-value" aria-labelledby="lastName-label">${lastName}</span></dd>
        </g:if>
        <br>
        <g:if test="${email}">
            <dt><span id="email-label" class="property-label">Email</span>   </dt>

              <dd>  <span class="property-value" aria-labelledby="email-label">${email}</span> </dd>

        </g:if>
        <br>

        <g:if test="${organization}">

            <dt>    <span id="organization-label" class="property-label">Organization</span></dt>

            <dd>  <span class="property-value" aria-labelledby="organization-label">${organization}</span> </dd>
        </g:if>
        <br>
        <g:if test="${workPackage}">

            <dt>    <span id="workPackage-label" class="property-label">Work Package</span> </dt>

               <dd> <span class="property-value" aria-labelledby="workPackage-label">${workPackage}</span> </dd>
        </g:if>

    </dl>
			
		</div>
</body>
</html>
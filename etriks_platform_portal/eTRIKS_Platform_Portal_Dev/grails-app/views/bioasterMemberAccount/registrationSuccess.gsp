<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.etriks.security.register.BioasterMemberAccount" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Account registration success</title>
</head>
<body>


<!-- This bloc shows the user accounts information which just be created -->

<div id="show-bioasterMemberAccount" class="container">
    <div class="row">
        <h3>Your registration request has been sent to the validator. You will receive a confirmation email if your registration request is validated!!!</h3>

    </div>
    <hr>

    <div class="row">
        <h4>Your account information :</h4>
        <br>
        <dl class="dl-horizontal">
        %{--first name row--}%
            <g:if test="${firstName}">
                <dt><span id="firstName-label" class="property-label"> First Name : </span></dt>
                <dd> <span class="property-value" aria-labelledby="firstName-label"> ${firstName}</span> </dd>
            </g:if>
            <br>
        %{--last name row--}%
            <g:if test="${lastName}">
                <dt><span id="lastName-label" class="property-label">Last Name : </span></dt>
                <dd> <span class="property-value" aria-labelledby="lastName-label">${lastName}</span></dd>
            </g:if>
            <br>
        %{--email row--}%
            <g:if test="${email}">
                <dt><span id="email-label" class="property-label">Email : </span></dt>
                <dd> <span class="property-value" aria-labelledby="email-label">${email}</span></dd>
            </g:if>
            <br>
        %{--organisation row--}%
            <g:if test="${organization}">
                <dt><span id="organization-label" class="property-label">Organization : </span></dt>
                <dd> <span class="property-value" aria-labelledby="organization-label">${organization}</span> </dd>
            </g:if>
            <br>
        %{--groups row--}%
            <g:if test="${groups}">
                <dt> <span id="groups-label" class="property-label">Groups : </span></dt>
                <dd><span class="property-value" aria-labelledby="groups-label">${groups}</span></dd>
            </g:if>

        </dl>
    </div>
</div>

</body>
</html>
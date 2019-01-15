<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Bioaster user account details</title>
</head>

<body>

<div class="container">

%{--The messages here is a list of message--}%
<div class="row">
<div class="col-md-12">
    %{--<g:if test="${flash.messages}">--}%

        %{--<ul class="errors" role="alert">--}%
            %{--<g:each in="${flash.messages}" var="message">--}%
                %{--<li>--}%
                    %{--${message}--}%
                %{--</li>--}%
            %{--</g:each>--}%
        %{--</ul>--}%

    %{--</g:if>--}%

</div>
</div>
%{--End of message row--}%

    %{--uid row--}%
    <div class="row">
        <div class="col-md-4">
   User ID :
        </div>

    <div class="col-md-6">
      ${uid}
    </div>
    </div>
    <br>
    <br>
%{--end of uid row--}%

%{--full name row--}%
    <div class="row">
        <div class="col-md-4">
            User Name :
        </div>

        <div class="col-md-6">
            ${fullName}
        </div>
    </div>
    <br>
    <br>
    %{--end of full name row--}%


    %{--mail row--}%
    <div class="row">
        <div class="col-md-4">
            User mail :
        </div>

        <div class="col-md-6">
            ${mail}
        </div>
    </div>
    <br>
    <br>
    %{--end of mail row--}%

    %{--user role row--}%
    <div class="row">
        <div class="col-md-4">
            User BUMP roles :
        </div>

        <div class="col-md-6">
            <g:each in="${userRole}" status="i" var="role">
                ${role},
            </g:each>
        </div>
    </div>
    <br>
    <br>
    %{--end of user role row--}%

    %{--last login row--}%
    <div class="row">
        <div class="col-md-4">
            User last login time :
        </div>

        <div class="col-md-6">
            ${lastLoginTime}
        </div>
    </div>
    %{--end of mail row--}%


</div>



</body>
</html>

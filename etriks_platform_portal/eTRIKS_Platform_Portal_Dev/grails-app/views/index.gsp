<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>eTRIKS Service Portal</title>
    <style>
    body  {
        background-image: url("/images/etriksImage/eTRIKS_backGround.jpg");
    }
    </style>
</head>
<body>
%{--Begin of the container--}%
<div class="container">

%{--Begin of the etriks portal module list--}%



<g:render template="/frontPage/publicServer"/>
<g:render template="/frontPage/etriksDocument"/>

<g:render template="/frontPage/etriksLabs"/>

<g:render template="/frontPage/hostedProjects"/>


<g:render template="/frontPage/deploymentTools"/>

%{--End of the module list--}%

%{--Begin of the etriks project presenation--}%
%{--<g:render template="/frontPage/etriksProject"/>--}%
    %{--End of the etriks project presentation--}%
</div>

</body>
</html>

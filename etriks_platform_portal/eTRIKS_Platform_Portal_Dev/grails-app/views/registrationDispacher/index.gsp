<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 3/11/14
  Time: 4:48 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Welcome to the eTRIKS registration page</title>
</head>

<body>

<!-- This bloc is the navigation bloc  -->
%{--<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>--}%



<div id="page-body" role="main">

    <br>
    <br>
    <p>

    <h2>Congratulations, you have successfully connected to the eTRIKS Registration platform .</h2>
    <br>
    <br>
    <hr>
    %{--The first row for community registration--}%
    <div class="well">
        <div class="row">

            <div class="col-md-12 text-center">
                <span style="color:grey;font-weight:bold "> Register as an eTRIKS public community member allow you to receive news of eTRIKS community.</span><br>
                <br>
                <g:link url="[action:'index',controller:'userAccount']">
                    Register as an <span style="text-transform: uppercase ;color:red;font-weight:bold ">eTRIKS public community member</span>
                </g:link>

            </div>
        </div>
    </div>




    %{--Etriks project member registration--}%


    <div class="well">
        <div class="row">

            <div class="col-md-12 text-center">
                <span style="color:grey;font-weight:bold "> <p> Register as an eTRIKS project member allow you to access eTRIKS collaboration tools. Your request will be validated by the project manager.</p></span>
               <br>
                <g:link url="[action:'index',controller:'etriksMemberAccount']">
                    Register as an <span style="text-transform: uppercase ;color:red;font-weight:bold ">eTRIKS project member</span>
                </g:link>

            </div>
        </div>
    </div>

    %{--Abirisk project member registration--}%
    <div class="well">
        <div class="row">

            <div class="col-md-12 text-center">
                <span style="color:grey;font-weight:bold "> <p> Register as an Abirisk project member allows you to access Abirisk eTRIKS platform. Your request will be validated by the project manager.</p></span>
                <br>
                <g:link url="[action:'index',controller:'abiriskMemberAccount']">
                    Register as an <span style="text-transform: uppercase ;color:red;font-weight:bold ">Abirisk project member</span>
                </g:link>

            </div>
        </div>
    </div>



    %{--Oncotrack project member registration--}%
    <div class="well">
        <div class="row">

            <div class="col-md-12 text-center">
                <span style="color:grey;font-weight:bold "> <p> Register as an OncoTrack project member allows you to access OncoTrack eTRIKS platform. Your request will be validated by the project manager.</p></span>
                <br>
                <g:link url="[action:'index',controller:'oncoTrackMemberAccount']">
                    Register as an <span style="text-transform: uppercase ;color:red;font-weight:bold ">OncoTrack project member</span>
                </g:link>

            </div>
        </div>
    </div>

    %{--Bioaster project member registration--}%
    <div class="well">
        <div class="row">

            <div class="col-md-12 text-center">
                <span style="color:grey;font-weight:bold "> <p> Register as an Bioaster project member allows you to access Bioaster eTRIKS platform. Your request will be validated by the project manager.</p></span>
                <br>
                <g:link url="[action:'index',controller:'bioasterMemberAccount']">
                    Register as an <span style="text-transform: uppercase ;color:red;font-weight:bold ">Bioaster project member</span>
                </g:link>

            </div>
        </div>
    </div>

</p>
</div>

</body>
</html>


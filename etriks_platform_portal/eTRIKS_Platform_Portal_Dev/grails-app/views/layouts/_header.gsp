

%{--New header with bootstrap--}%

<div id="header">
    <img alt="eTRIKS-logo" src="${resource(dir:'images',file:'projectLogo/etriks_project_logo.png')}">
    <br>
<br>
    <br>
    <br>
    <hr>
    <div class="row">
        <div class="col-md-12 text-center" >

            <h1>Welcome to eTRIKS service portal</h1>
            <br>
            %{--eTRIKS portal is designed to facilitate the access of eTRIKS platform resources.--}%
       <span style="font-style: italic;"> Find out all the tools and services that eTRIKS can provide you with!</span>

        </div>

    </div>
    <br>
    <br>
    <div id="top-nav" class="navbar navbar-default ">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-navbar-collapse" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${createLink(uri: '/')}">eTRIKS portal %{--<img alt="eTRIKS-brand-logo" src="${resource(dir:'images',file: 'etriks-logo.png')}" width="25" height="25">--}% </a>
            </div>

            <div class="navbar-collapse collapse" id="bs-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li > <a href="http://etriks.org" target="_blank"> eTRIKS project</a>    </li>
                    <li  id="fat-menu" class="dropdown">
                        <a id="hostedProject" class="dropdown-toggle clearfix" data-toggle="dropdown" role="button" href="#">
                            <span >Hosted project</span>
                            <i class="caret"></i></a>
                        <ul class="dropdown-menu" aria-labelledy="drop2" role="menu">
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="http://www.abirisk.eu/" target="_blank">Abirisk</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="http://www.bioaster.org/" target="_blank">Bioaster</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="http://www.oncotrack.eu/" target="_blank">OncoTrack</a></li>
                        </ul>

                    </li>
                    <li id="fat-menu2" class="dropdown">
                        <a id="tools" class="dropdown-toggle clearfix" data-toggle="dropdown" role="button" href="#" aria-expanded="true">
                            Collaboration Tools
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledy="drop2" role="menu">
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="https://git.etriks.org/" target="_blank">eTRIKS Git</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="https://requirements.etriks.org/twiki/bin/view/RequestManagement/FeatureRequest" target="_blank">eTRIKS wiki</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="https://ws.onehub.com/workspaces/284851/" target="_blank">Document center</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="${createLink(controller: 'etriksEndUserAccountManager')}">Forgot or change password</a></li>


                        </ul>  </li>

                    <li > <a href="${createLink(controller:'registrationDispacher')}"> Register</a>  </li>
                    <li > <a href="${createLink(controller:'dashBoard',action:'index')}"> <i class="glyphicon glyphicon-lock"></i> Admin dashboard</a>  </li>
                </ul>
                <ul class="nav navbar-nav pull-right">
                    <li >   <g:loginControl/>   </li>

                    <li > <a href="http://usersupport.etriks.org" target="_blank"> <i class="glyphicon glyphicon-tag"> </i> User support</a>    </li>

                </ul>

            </div>

            <!-- Single button -->

        </div><!-- /container -->
    </div>
</div>


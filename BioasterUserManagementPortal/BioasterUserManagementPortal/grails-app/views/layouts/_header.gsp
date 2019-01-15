<div id="header">

    %{-- Bioaster Logo row--}%
    <div class="row">
        <div class="col-md-12">
            <asset:image src="bioaster/logoBioaster.png" width="300" height="200" align="left"/>

            <br>
            <br>
        </div>
    </div>
    <hr>
%{--End Bioaster Logo row--}%

    %{--nav bar row--}%
    <div id="top-nav" class="navbar navbar-default ">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-navbar-collapse" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${createLink(uri: '/')}">BIOASTER User Management Portal </a>
            </div>

            <div class="navbar-collapse collapse" id="bs-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li > <a href="http://bioaster.org" target="_blank"> BIOASTER-HOME</a>    </li>

                    <li id="fat-menu2" class="dropdown">
                        <a id="tools" class="dropdown-toggle clearfix" data-toggle="dropdown" role="button" href="#" aria-expanded="true">
                            User Registration
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu" aria-labelledy="drop2" role="menu">
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="${createLink(controller: 'importADUser')}">Import AD account</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="${createLink(controller: 'bioasterUserRegistrationRequest')}">Create new account</a></li>

                        </ul>  </li>

                    <li><a href="${createLink(controller: 'endUserAccountManager')}">Forgot or change password</a></li>


                    <li > <a href="${createLink(controller:'bioasterAdminDashBoard',action:'index')}"> <i class="glyphicon glyphicon-lock"></i> Admin dashboard</a>  </li>
                </ul>
                <ul class="nav navbar-nav pull-right">
                    <li > <g:loginControl/>   </li>

                    <li > <a href="https://helpdesk.bioaster.org/index.php" target="_blank"> <i class="glyphicon glyphicon-tag"> </i> User support</a>    </li>

                </ul>

            </div>


        </div><!-- /container -->
       </div>

    %{--end nav bar row--}%


</div>
<div class="well">
    <div class="row">
        <div class="col-md-10">
            <h1>   Hosted Projects </h1>
            Currently, eTRIKS platform in CC-IN2P3/CNRS hosts three projects. Only project members can click on the project to access the proposed services. For visitors who wants to know more about the specific projects, please click on the "hosted project" link in the navigation bar above.
            <br>
            <br>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            %{--Abirisk project panel--}%
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">

                        <a data-toggle="collapse" data-target="#collapseAbirisk">
                            <img class="img-responsive" alt="eTRIKS-brand-logo" src="${resource(dir:'images/projectLogo',file: 'logo-abirisk-trans.png')}" width="80" height="80" >
                            %{-- <h4>Abirisk Project </h4>--}%
                        </a>
                    </div>
                </div>
                <div id="collapseAbirisk" class="collapse panel-collapse">
                    <div class="panel-body">
                        <h3>Services : </h3>
                       %{-- <a  href="https://abirisk.etriks.org" target="_blank">Production tranSMART v1.1</a><br>--}%
                      %{--  <a  href="https://abirisk.etriks.org/awstats/awstats.pl" target="_blank">Monitoring dashboard <i class="glyphicon glyphicon-lock"></i></a><br>--}%
                        <a href="https://abirisk-testing.etriks.org/transmart/" target="_blank">Testing tranSMART v1.2</a><br>
                        <g:link url="[action:'index',controller:'abiriskEndUserSpaceManager']"> Forgot or change your password</g:link>
                    </div><!--/panel content-->
                    <div class="panel-footer">
                        <h4>Production Platform Version: </h4>
                        eTRIKS Platform version: 1.1 <br>
                        tranSMART core version: 1.1.3 <br>
                        R version: 2.15.2
                    </div>
                </div>
            </div><!--/end of abirisk panel-->


        </div>

        <div class="col-md-4">
            %{--Bioaster project panel--}%
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">

                        <a data-toggle="collapse" data-target="#collapseBioaster">
                            %{--<h4>Bioaster Project </h4>--}%
                            <img class="img-responsive" alt="bioaster-logo" src="${resource(dir:'images',file: 'projectLogo/Logo-bioaster-trans.png')}" width="80" height="80" >
                        </a>
                    </div>
                </div>
                <div class="collapse collapse-panel" id="collapseBioaster">
                    <div class="panel-body">
                        <h3>Services : </h3>
                        <a  href="https://bioaster.etriks.org" target="_blank">Production Transmart v1.2</a><br>
                        <g:link url="[action:'index',controller:'bioasterEndUserSpaceManager']"> Forgot or change your password</g:link>
                    </div><!--/panel content-->
                    <div class="panel-footer">
                        <h4>Production Platform Version: </h4>
                        eTRIKS Platform version: 2.0 <br>
                        tranSMART core version: 1.2.2 <br>
                        R version: 3.1.2
                    </div>
                </div>
            </div>
        </div><!--/end of bioaster panel-->

        <div class="col-md-4">
            %{--OncoTrack project panel--}%
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">

                        <a data-toggle="collapse" data-target="#collapseOncotrack">
                            <img class="img-responsive" alt="eTRIKS-brand-logo" src="${resource(dir:'images/projectLogo',file: 'logo-onco-track-trans.png')}" width="80" height="80" >
                        </a>
                    </div>
                </div>
                <div class="panel-collapse collapse" id="collapseOncotrack">
                    <div class="panel-body">
                        <h3>Services : </h3>
                        <li><a  href="https://oncotrack.etriks.org/transmart/datasetExplorer/index" target="_blank">Production tranSMART v1.2</a></li>
                        <li><g:link url="[action:'index',controller:'oncoTrackEndUserSpaceManager']"> Forgot or change your password </g:link>
                    </div><!--/panel content-->
                    <div class="panel-footer">
                        <h4>Production Platform Version: </h4>
                        eTRIKS Platform version: 2.0 <br>
                        tranSMART core version: 1.2.2 <br>
                        R version: 3.1.2
                    </div>
                </div>
            </div>
        </div> <!--/end of oncotrack panel-->
    </div>%{--The end of the second row--}%

</div>
%{--Begin of the eTRIKS platform deployment tools--}%

<div class="well">
    <div class="row">
        <div class="col-md-10">
            <h1>eTRIKS deployment tools for services and applications</h1>
            eTRIKS project provides several ways to deploy applications and services such as tranSMART, etc. Beware, these deployment tools are not designed for data centers that want to host production eTRIKS platform.
            If you are planning to deploy the eTRIKS platform in your data center, please contact us by clicking on the link user support on the navigation bar. We will provide you with more support.<br>
            <br>

        </div>
    </div>
    <div class="row">
        %{--colome for docker--}%
        <div class="col-md-4">
            %{--docker panel--}%
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">

                        <a data-toggle="collapse" data-target="#collapseDocker">
                            <i class="pull-right"><img class="img-responsive" alt="bioaster-logo" src="${resource(dir:'images',file: 'projectLogo/etriks-logo-trans.png')}" width="30" height="30" ></i>
                            <h4> Docker file for eTRIKS-flavour of tranSMART </h4>

                        </a>
                    </div>
                </div>
                <div id="collapseDocker" class="panel-collapse collapse">
                    <div class="panel-body">

                        <h3>Services : </h3>
                        <a href="https://owncloud.etriks.org/index.php/s/99X6ZEEdyZHTfaO/download" target="_blank">Download docker file </a><br>

                        <a href="${createLink(controller:'transmartRelease',action:'transmartDocker')}">How to start?</a><br>


                    </div><!--/panel content-->
                    <div class="panel-footer">
                        This is an all-in-one tranSMART deployment tool for test purpose only. This docker file needs a compute host with Docker engine on it. Please click on how to start link for more information.
                    </div>
                </div>
            </div><!--/end of docker server panel-->
        </div>
        %{--colome for tomcat--}%
        <div class="col-md-4">
            %{--war panel--}%
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">

                        <a data-toggle="collapse" data-target="#collapseWar">
                            <i class="pull-right"><img class="img-responsive" alt="etriks-logo" src="${resource(dir:'images',file: 'projectLogo/etriks-logo-trans.png')}" width="30" height="30" ></i>
                            <h4>eTRIKS-flavour tranSMART Java war file</h4>

                        </a>
                    </div>
                </div>
                <div id="collapseWar" class="panel-collapse collapse">
                    <div class="panel-body">

                        <h3>Services : </h3>
                        <a href="https://owncloud.etriks.org/index.php/s/E6RplJ8Hie3p2kU/download" target="_blank">Download eTRIKS transmart 1.2.2 war file </a><br>

                        %{--<a href="${createLink(controller:'transmartRelease',action:'transmartDocker')}">How to start?</a><br>--}%


                    </div><!--/panel content-->
                    <div class="panel-footer">
                        This is an all-in-one tranSMART deployment tool for test purpose only. This war file needs a compute host with Java application server on it (e.g. tomcat, JBoss, etc.). %{--Please click on how to start link for more information.--}%
                    </div>
                </div>
            </div><!--/end of war panel-->
        </div>
        %{--colone for vm--}%
        <div class="col-md-4">
            %{--vm panel--}%
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">

                        <a data-toggle="collapse" data-target="#collapseVM">

                            <i class="pull-right"><img class="img-responsive" alt="bioaster-logo" src="${resource(dir:'images',file: 'projectLogo/transmart_logo_without_text.png')}" width="30" height="30" ></i>
                            <h4> Virtual Machine for tranSMART  application</h4>
                        </a>
                    </div>
                </div>
                <div id="collapseVM" class="panel-collapse collapse">
                    <div class="panel-body">

                        <h3>Services : </h3>
                        <a href="https://wiki.transmartfoundation.org/display/transmartwiki/Installing+tranSMART+from+a+Virtual+Machine+image" target="_blank">tranSMART Foundation Virtual Machine deployment </a><br>




                    </div><!--/panel content-->
                    <div class="panel-footer">
                        This is an all-in-one tranSMART virtual machine image. This deployment needs a compute host with virtual machine framework on it (e.g. virtualbox, vmware). The linked page will give you more information.
                    </div>
                </div>
            </div><!--/end of vm panel-->
        </div>
    </div>
</div>
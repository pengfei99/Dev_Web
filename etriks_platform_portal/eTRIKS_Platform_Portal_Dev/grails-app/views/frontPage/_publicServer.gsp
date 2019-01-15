<div class="well">
<div class="row ">
    <div class="col-md-8">
        <h1>The public server</h1>

    </div>
</div>

<div class="row row-centered">
<div class="col-md-8">

    %{--Begin of the nav tab version for public server--}%

    %{--   <ul class="nav nav-tabs">
           <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
           <li><a data-toggle="tab" href="#menu1">Available datasets </a></li>
           <li><a data-toggle="tab" href="#menu2">Data origin</a></li>
       </ul>

       <div class="tab-content">
       <div id="home" class="tab-pane fade in active">
           <h4>Etriks public platform</h4>

           <br>
       </div>
           <div id="menu1" class="tab-pane fade">
               <h4>Availabe datasets on the public server</h4>
            <p>Here below is an overview of the patient-centric as well as cell line-focused data we have currently available on our eTRIKS Public server:</p>

               <div class="scollist">
                   <h4>Patient data:</h4>
                      <dl class="dl-horizontal">
                          <dt>Rheumatoid Arthritis:</dt> <dd>23 Studies from GEO</dd>
                          <dt>Parkinson Disease:</dt> <dd>16 Studies from GEO</dd>
                          <dt>Cancer:</dt>

                          <dd>
                              4 Studies from TCGA --}%%{--(e.g.Breast Invasive Carcinoma, Colon Adenocarcinoma, Ovarian Serous Cystadenocarcinoma, Uterine Corpus Endometrial Carcinoma)--}%%{--<br>
                              3 Studies from GEO on Brest Cancer<br>
                              1 Study from GEO on Multiple Myeloma CCLE (1070 cell lines)<br>
                      </dd>

                        <dt>Asthma:</dt><dd>4 Studies from GEO</dd>
                        <dt>Inflammatory Bowel Disease:</dt><dd>2 Studies from GEO</dd>
                          <dt>Lupus Nephritis:</dt><dd>2 Studies from GEO</dd>
                          <dt> Multiple sclerosis:</dt><dd>1 Study from GEO</dd>
                      </dl>
<hr>
                   <h4>Cell line data:</h4>
                   <dl class="dl-horizontal">
                       <dt>CCLE:</dt> <dd>1070 cell lines</dd>
                       <dt>GSK Cell Lines:</dt> <dd>950 cell lines</dd>
                       </dl>
                   </div>
           </div>

           <div id="menu2" class="tab-pane fade">
               <h4>Origin of the public server datasets</h4>
               <p>The public studies we provide access to, encompass demographic, clinical information and microarray gene expression data. Additional omics data types will be added in the future.These studies are from the following Public data repositories:</p>
               <div class="scollist">

                   <dl >
                       <dt>Gene Expression Omnibus (GEO):</dt> <dd>http://www.ncbi.nlm.nih.gov</dd>
                       <dt>The Cancer Genome Atlas (TCGA):</dt> <dd>http://cancergenome.nih.gov/</dd>
                       <dt>GSK Cell Line data</dt>
                       <dd>https://cabig.nci.nih.gov/tools/caArray_GSKdata</dd>

                       <dt>Cancer Cell Line Encyclopedia (CCLE)</dt><dd>http://www.broadinstitute.org/ccle/home</dd>
                   </dl>
               </div>
--}%

    %{--end of the nav tab version for public server--}%



    <div class="row">
        <div class="col-md-12">
            Public server provides you with a large variety of studies ranging from <span style="font-weight: bold;">demographic</span>, <span style="font-weight: bold;">clinical information</span> and <span style="font-weight: bold;"> microarray gene expression data</span>. All this data can be viewed with tranSMART or Galaxy application tools.
        The data is extracted from public data repositories and <span style="font-weight: bold;">curated by eTRIKS</span> before being made available on the public server. Click on the right hand side widget to explore these datasets using the eTRIKS platform.
        </div>
    </div>
    <br>


    <div class="row">

        <div class="col-md-12">
            %{--public server panel--}%
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="panel-title">
                        <a data-toggle="collapse" data-target="#availableDataSet">
                            Want to see which studies are available to you?
                        </a>
                    </div>
                </div>
                <div id="availableDataSet" class="panel-collapse collapse">
                    <div class="panel-body">

                        <p>Here below is an overview of the patient-centric as well as cell line-focused data we have currently available on our eTRIKS Public server. Stay tuned : Additional omics data types will be added in the future.</p>
                        <div class="scollist">
                            <h4>Patient data:</h4>
                            <dl class="dl-horizontal">
                                <dt>Rheumatoid Arthritis:</dt> <dd>23 Studies from GEO</dd>
                                <dt>Parkinson Disease:</dt> <dd>16 Studies from GEO</dd>
                                <dt>Cancer:</dt>

                                <dd>
                                    4 Studies from TCGA <br>
                                    3 Studies from GEO on Brest Cancer<br>
                                    1 Study from GEO on Multiple Myeloma <br>
                                    CCLE (1070 cell lines)<br>
                                </dd>

                                <dt>Asthma:</dt><dd>4 Studies from GEO</dd>
                                <dt>Inflammatory Bowel Disease:</dt><dd>2 Studies from GEO</dd>
                                <dt>Lupus Nephritis:</dt><dd>2 Studies from GEO</dd>
                                <dt> Multiple sclerosis:</dt><dd>1 Study from GEO</dd>
                            </dl>
                            <hr>
                            <h4>Cell line data:</h4>
                            <dl class="dl-horizontal">
                                <dt>CCLE:</dt> <dd>1070 cell lines</dd>
                                <dt>GSK Cell Lines:</dt> <dd>950 cell lines</dd>
                            </dl>
                        </div>
                    </div>


                </div><!--/panel content-->

            </div>
        </div>
    </div>

    <div class="row">

        <div class="col-md-12">
            %{--public server panel--}%
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="panel-title">
                        <a data-toggle="collapse" data-target="#DataOrigin">
                            Want to know from which public repository the studies are originating from?

                        </a>
                    </div>
                </div>
                <div id="DataOrigin" class="panel-collapse collapse">
                    <div class="panel-body">

                        <p>The studies provided in the public server originate from the following Public data repositories:</p>
                        <div class="scollist">

                            <dl >
                                <dt>Gene Expression Omnibus (GEO): <img src="${resource(dir: 'images/projectLogo',file: 'geo_logo.gif')}" alt="Geo logo" style="width: 80px;height: 30px;"/></dt> <dd>http://www.ncbi.nlm.nih.gov</dd>
                                <hr>
                                <dt>The Cancer Genome Atlas (TCGA): <img src="${resource(dir: 'images/projectLogo',file: 'tcga_logo.jpg')}" alt="TCGA logo" style="width: 180px;height: 30px;"/></dt> <dd>http://cancergenome.nih.gov/</dd>
                                <hr>
                                <dt>GSK Cell Line data</dt>
                                <dd>https://cabig.nci.nih.gov/tools/caArray_GSKdata</dd>
                                <hr>
                                <dt>Cancer Cell Line Encyclopedia (CCLE) <img src="${resource(dir: 'images/projectLogo',file: 'CCLE_logo.png')}" alt="TCGA logo" style="width: 180px;height: 30px;"/></dt><dd>http://www.broadinstitute.org/ccle/home</dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    %{--The downloadable documents--}%
 %{--   <div class="row">
        <div class="col-md-12">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="panel-title">
                        <a data-toggle="collapse" data-target="#downloadDocuments">
                            Downloadable documents

                        </a>
                    </div>
                </div>
                <div id="downloadDocuments" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="scollist">

                            <dl >
                                <dt><a href="http://twk.pm/pwestc9ybo">Guide to user registration and password modification</a></dt>
                                <hr>
                                <dt><a href="http://twk.pm/4v1iqafj12" >Introduction to the ticketing system</a></dt>
                                <hr>
                                <dt><a href="http://twk.pm/uqj4uyqwke">Getting started on ETL</a> </dt>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>--}%

</div>






%{--Begin of the public server well--}%
<div class="col-md-4 pull-right">

    %{--public server panel--}%
    <div class="panel panel-primary">
        <div class="panel-heading">
            <div class="panel-title">
                <i class="pull-right"><img class="img-responsive" alt="eTRIKS-brand-logo" src="${resource(dir:'images',file: 'projectLogo/etriks-logo-trans.png')}" width="25" height="15"></i>
                <a href="#" class="coucou" data-toggle="collapse" data-target="#collapsePublicServer">
                    <h2>Public Server </h2>

                </a>
            </div>
        </div>
        <div id="collapsePublicServer" class="panel-collapse collapse">
            <div class="panel-body">

                <h3>Services : </h3>
                <a href="https://public.etriks.org/transmart/" target="_blank">Production tranSMART v1.2</a><br>

                <a href="https://public.etriks.org/awstats/awstats.pl" target="_blank">   Monitoring dashboard <i class="glyphicon glyphicon-lock"></i></a><br>


            </div><!--/panel content-->
            <div class="panel-footer">
                <h4>Production Platform Version: </h4>

                eTRIKS Platform version: 2.0 <br>
                tranSMART core version: 1.2.2 <br>
                R version: 3.0.1
            </div>
        </div>
    </div>
</div>

</div>%{--end of the public server inforamtion row--}%

</div>
<!--/end of public server panel well-->

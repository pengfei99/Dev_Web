<div class="well">

<div class="row ">
    <div class="col-md-8">
        <h1>eTRIKS Labs</h1>

    </div>
</div>

<div class="row row-centered">
    <div class="col-md-10">
        <div class="row">
            <div class="col-md-12">
                With large, complex datasets becoming the norm staging,
                exploring and analyzing translational research data can be challenging and resource intensive.
                eTRIKS is working to change that by providing platforms, services, and guidance for the use and re-use of translational research data.
                <br>
                Our newest development is the creation of an online space, eTRIKS Labs,
                where new projects that are being worked on will be made available for review and feedback.
                The intent is for this to be a means of co-creating tools, services, training and guidelines for translational research.
            </div>
        </div>
        <br>
    </div>


</div>

   <div class="row">
   %{-- SMartR project panel--}%
    <div class="col-md-4" >
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">

                    <a id="smartRToggle" data-toggle="collapse" data-target="#collapseSmartR">
                        <h4>SmartR</h4>
                    </a>
                </div>
            </div>
            <div id="collapseSmartR" class="collapse panel-collapse">
                <div class="panel-body" >
                    <h3>Services : </h3>
                    <li><g:link url="[action:'index',controller:'smartR']"> Access here to the documentation </g:link> </li>
                    <li><a id="smartRAppToggle" href="#" target="_blank" data-toggle="modal" data-target="#disclaimerModal">Access here to the application</a></li>
                </div><!--/panel content-->
                <hr>
            </div>
            <div class="panel-footer-etrikslab">
                <div id="smartRImage" align="center">
                    <br>
                    <img class="img-responsive" alt="SmartR Demo image" src="${resource(dir:'images',file: 'etriksLabs/smartR.png')}" width="300" height="200">

                    <br>
                </div>
                <p style="padding: 10px 15px;">
                    SmartR is aimed to provide a highly dynamic and
                    interactive way of visualizing and analyzing data
                    within tranSMART.
                </p>
            </div>

        </div>
    </div>
    <!--/end of smartR panel-->

   %{-- diseasemap project panel--}%

    <div class="col-md-4" >

        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">

                    <a id="diseaseMapToggle" data-toggle="collapse" data-target="#collapseDiseaseMap">
                        <h4>Disease Map</h4>
                    </a>
                </div>
            </div>
            <div id="collapseDiseaseMap" class="collapse panel-collapse">
                <div class="panel-body">
                    <h3>Services : </h3>
                    <li><a href="https://diseasemap.etriks.org/index.html" target="_blank"> Access here to the documentation </a> </li>
                    %{--<li><a  id="diseaseMapAppToggle" href="https://diseasemap.etriks.org:8080/browser/" target="_blank" data-toggle="modal" data-target="#disclaimerModal">Access here to the application</a></li>--}%
                </div><!--/panel content-->
                <hr>
            </div>

            <div class="panel-footer-etrikslab">
                <div id="diseaseMapImage" align="center">
                    <br>
                    <img class="img-responsive" alt="SmartR Demo image" src="${resource(dir:'images',file: 'etriksLabs/diseasemap300_200.png')}" width="300" height="200">
                    <br>
                </div>
                <p style="padding: 10px 15px;">
                    An integrated knowledge repository to provide biological context to data emerging
                    from experimental studies for selected IMI projects.
                </p>
            </div>
        </div>
    </div>
    <!--/end of diseasemap panel-->


   %{--snf project panel--}%

       <div class="col-md-4" >

           <div class="panel panel-info">
               <div class="panel-heading">
                   <div class="panel-title">

                       <a id="snfToggle" data-toggle="collapse" data-target="#collapsesnf">
                           <h4>SNF</h4>
                       </a>
                   </div>
               </div>
               <div id="collapsesnf" class="collapse panel-collapse">
                   <div class="panel-body">
                       <h3>Services : </h3>
                       <li><g:link url="[action:'index',controller:'snf']"> Access here to the documentation </g:link> </li>
                       <li><a id="snfAppToggle" href="#" target="_blank" data-toggle="modal" data-target="#disclaimerModal">Access here to the application</a></li>
                   </div><!--/panel content-->
                   <hr>
               </div>
               <div class="panel-footer-etrikslab">
                   <div id="snfImage" align="center">
                       <br>
                       <img class="img-responsive" alt="SmartR Demo image" src="${resource(dir:'images',file: 'etriksLabs/snf.png')}" width="300" height="200">
                       <br>
                   </div>
                   <p style="padding: 10px 15px;">
                       SNF is a novel computational method for genomic data integration that was developed by Wang et al.,
                       in the lab of Anna Goldenberg.

                       <span id="snfMoreText">
                       ...
                       </span>

                       <a id="snfToggleButton" onclick="snfToggleText();" href="javascript:void(0);">See More</a>
                   </p>
               </div>
           </div>
           <!--/end of snf panel-->
       </div>


</div>
%{--The end of the second row--}%

%{--The third row for project 4 to 6--}%
<div class="row">
    %{-- Wgcna project panel--}%
    <div class="col-md-4" >
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">

                    <a id="wcgnaToggle" data-toggle="collapse" data-target="#collapseWcgna">
                        <h4>WGCNA</h4>
                    </a>
                </div>
            </div>
            <div id="collapseWcgna" class="collapse panel-collapse">
                <div class="panel-body" >
                    <h3>Services : </h3>
                    <li><g:link url="[action:'index',controller:'wgcna']"> Access here to the documentation </g:link> </li>
                   %{-- <li><a  href="http://etriks1.uni.lu/" target="_blank">Access here to the application</a></li>--}%
                </div><!--/panel content-->
                <hr>
            </div>
            <div class="panel-footer-etrikslab">
                <div id="wcgnaImage" align="center">
                    <br>
                    <img class="img-responsive" alt="SmartR Demo image" src="${resource(dir:'images',file: 'etriksLabs/wgcna.png')}" width="300" height="200">

                    <br>
                </div>
                <p style="padding: 10px 15px;">
                    Weighted Gene Correlation Network Analysis.
                   <Span style="font-weight:bold;font-style: italic;"> Image reference: Jiang et al. BMC Genomics 2014 15:756</Span>
                </p>
            </div>

        </div>
    </div>

    <div class="col-md-4" >
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">

                    <a id="ehsToggle" data-toggle="collapse" data-target="#collapseEhs">
                        <h4>EHS</h4>
                    </a>
                </div>
            </div>
            <div id="collapseEhs" class="collapse panel-collapse">
                <div class="panel-body" >
                    <h3>Services : </h3>
                    <li><g:link url="[action:'index',controller:'ehs']"> Access here to the documentation </g:link> </li>
                     <li><a id="ehsAppToggle" href="#" target="_blank" data-toggle="modal" data-target="#disclaimerModal">Access here to the application</a></li>
                </div><!--/panel content-->
                <hr>
            </div>
            <div class="panel-footer-etrikslab">
                <div id="ehsImage" align="center">
                    <br>
                    <img class="img-responsive" alt="SmartR Demo image" src="${resource(dir:'images',file: 'etriksLabs/ehs.png')}" width="300" height="200">

                    <br>
                </div>
                <p style="padding: 10px 15px;">
                    The eHS addresses the needs of eTRIKS current and prospective users for data
                    harmonization and support
                    <span id="ehsMoreText">
                ...
                </span>

                    <a id="ehsToggleButton" onclick="ehsToggleText();" href="javascript:void(0);">See More</a>
                </p>
            </div>

        </div>
    </div>



        %{-- hidome project panel--}%
         <div class="col-md-4" >
             <div class="panel panel-info">
                 <div class="panel-heading">
                     <div class="panel-title">

                         <a id="hidomeToggle" data-toggle="collapse" data-target="#collapseHidome">
                             <h4>Hi Dome</h4>
                         </a>
                     </div>
                 </div>
                 <div id="collapseHidome" class="collapse panel-collapse">
                     <div class="panel-body" >
                         <h3>Services : </h3>
                         <li><g:link url="[action:'index',controller:'hidome']"> Access here to the documentation </g:link> </li>
                          <li><a id="hidomeAppToggle" href="#" target="_blank" data-toggle="modal" data-target="#disclaimerModal">Access here to the application</a></li>
                     </div><!--/panel content-->
                     <hr>
                 </div>
                 <div class="panel-footer-etrikslab">
                     <div id="hidomeImage" align="center">
                         <br>
                         <img class="img-responsive" alt="SmartR Demo image" src="${resource(dir:'images',file: 'etriksLabs/hidome.png')}" width="300" height="200">

                         <br>
                     </div>
                     <p style="padding: 10px 15px;">
                         The High Dimensional and omics data exploration project is an extension to tranSMART which allows the creation
                         <span id="hidomeMoreText">
                             ...
                         </span>

                         <a id="hidomeToggleButton" onclick="hidomeToggleText();" href="javascript:void(0);">See More</a>

                     </p>
                 </div>

             </div>
        %{--end of hidome panel--}%

    </div>
    </div>
    %{--4th row for etriks lob 7 to 9--}%
    %{--<div class="row">
        --}%%{-- gsva project panel--}%%{--
         <div class="col-md-4" >
             <div class="panel panel-info">
                 <div class="panel-heading">
                     <div class="panel-title">

                         <a id="gsvaToggle" data-toggle="collapse" data-target="#collapseGsva">
                             <h4>GSVA</h4>
                         </a>
                     </div>
                 </div>
                 <div id="collapseGsva" class="collapse panel-collapse">
                     <div class="panel-body" >
                         <h3>Services : </h3>
                         <li><g:link url="[action:'index',controller:'wgcna']"> Access here to the documentation </g:link> </li>
                          <li><a  href="http://etriks1.uni.lu/" target="_blank">Access here to the application</a></li>
                     </div><!--/panel content-->
                     <hr>
                 </div>
                 <div class="panel-footer-etrikslab">
                     <div id="gsvaImage" align="center">
                         <br>
                         <img class="img-responsive" alt="SmartR Demo image" src="${resource(dir:'images',file: 'etriksLabs/wgcna.png')}" width="300" height="200">

                         <br>
                     </div>
                     <p style="padding: 10px 15px;">
                         Gene Set Variation Analysis (GSVA) is a non-parametric, unsupervised method which estimates the relative enrichment of
                         <span id="gsvaMoreText">
                             ...
                         </span>

                         <a id="gsvaToggleButton" onclick="gsvaToggleText();" href="javascript:void(0);">See More</a>
                     </p>
                 </div>

             </div>


    </div>--}%
    %{--Row for disclaimer--}%
    <div class="row">
        <div class="col-md-10">
            <h2> Disclaimer</h2>
        </div>
        <div class="col-md-10">
            <div class="well well-sm" %{--style="background-color: violet;"--}%>
                The eTRIKS Labs modules are offered to the public as freely available resources, for non-commercial research use. Some aspects of these experimental modules may still be under development, and there are no warranties about the completeness, reliability, accuracy, and security of any of the software packages. Please bear this in mind, especially if you wish to analyse personal and/or confidential data. Some of the services provided use public data sources that are available through third parties on the internet – these are identified by links provided under ‘Acknowledgements’ on the respective module pages. If you use or re-distribute these public data for any purposes, you are responsible for adhering to the license requirements of these public data sources.
            </div>
        </div>
    </div>


    <!--/end of wgcna panel-->

%{--End of the third row--}%



%{--modal popup--}%
<!-- Modal -->
<div class="modal fade" id="disclaimerModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">eTRIKS Labs Disclaimer</h4>
            </div>
            <div class="modal-body">
                <p>The eTRIKS Labs modules are offered to the public as freely available resources, for non-commercial research use. Some aspects of these experimental modules may still be under development, and there are no warranties about the completeness, reliability, accuracy, and security of any of the software packages. Please bear this in mind, especially if you wish to analyse personal and/or confidential data. Some of the services provided use public data sources that are available through third parties on the internet – these are identified by links provided under ‘Acknowledgements’ on the respective module pages. If you use or re-distribute these public data for any purposes, you are responsible for adhering to the license requirements of these public data sources.</p>
            </div>
            <div class="modal-footer">
                <button id="agreeDisclimer" type="button" class="btn btn-default" data-dismiss="modal">Agree</button>
                <button id="notAgreeDisclimer" type="button" class="btn btn-default" data-dismiss="modal">Disagree</button>
            </div>
        </div>

    </div>
</div>


%{--Javascript for modal of disclimer--}%

<script>
    $(document).ready(function(){
        var targetUrl='https://portal.etriks.org/Portal/';
        $("#smartRAppToggle").click(function(){
            targetUrl='http://etriks1.uni.lu/';
        });

        $("#diseaseMapAppToggle").click(function(){
            targetUrl='http://etriks1.uni.lu/';
        });

        $("#snfAppToggle").click(function(){
            targetUrl='https://snf-shiny.shinyapps.io/rtest';
        });

        $("#ehsAppToggle").click(function(){
            targetUrl='http://ehs.biospeak.solutions/sandbox/app/';
        });
        $("#hidomeAppToggle").click(function(){
            targetUrl='https://hidome.etriks.org/transmart/datasetExplorer';
        });

        $("#agreeDisclimer").click(function(){
            window.open(targetUrl);
        });
    })

</script>


%{--Javascript for dynamic actions--}%
<script>
    %{--The script for image fade out--}%
    $(document).ready(function(){
        var smartRImageHidden=false;
        var diseaseMapImageHidden=false;
        var snfImageHidden=false;
        var wcgnaImageHidden=false;
        var ehsImageHidden=false;
        var gsvaImageHidden=false;
        var hidomeImageHidden=false;

        /*fade in and out Rules for smartR panel */
        $("#smartRToggle").click(function(){
            if(smartRImageHidden==false){$("#smartRImage").fadeOut();
                smartRImageHidden=true;
            }
            else{$("#smartRImage").fadeIn();smartRImageHidden=false;}
        });
        /*fade in and out Rules for diseasemap panel */
        $("#diseaseMapToggle").click(function(){
            if(diseaseMapImageHidden==false){$("#diseaseMapImage").fadeOut();
                diseaseMapImageHidden=true;
            }
            else{$("#diseaseMapImage").fadeIn();diseaseMapImageHidden=false;}
        });

        /*fade in and out Rules for snf panel */
        $("#snfToggle").click(function(){
            if(snfImageHidden==false){$("#snfImage").fadeOut();
                snfImageHidden=true;
            }
            else{$("#snfImage").fadeIn();snfImageHidden=false;}
        });
        /*fade in and out Rules for wcgna panel */
        $("#wcgnaToggle").click(function(){
            if(wcgnaImageHidden==false){$("#wcgnaImage").fadeOut();
                wcgnaImageHidden=true;
            }
            else{$("#wcgnaImage").fadeIn();wcgnaImageHidden=false;}
        });

        /*fade in and out Rules for ehs panel */
        $("#ehsToggle").click(function(){
            if(ehsImageHidden==false){$("#ehsImage").fadeOut();
                ehsImageHidden=true;
            }
            else{$("#ehsImage").fadeIn();ehsImageHidden=false;}
        });

        /*fade in and out Rules for gsva panel*/
        $("#gsvaToggle").click(function () {
            if(gsvaImageHidden==false){
                $("#gsvaImage").fadeOut();
                gsvaImageHidden=true;
            }
            else{$("#gsvaImage").fadeIn();gsvaImageHidden=false;}
        });
        /*fade in and out Rules for hidome panel*/
        $("#hidomeToggle").click(function () {
            if(hidomeImageHidden==false){
                $("#hidomeImage").fadeOut();
                hidomeImageHidden=true;
            }
            else{$("#hidomeImage").fadeIn();hidomeImageHidden=false;}
        });

    });
    /*End of the image fade in and out section*/

    /*The script for more text in the etriks lab project description*/


    var  snfTextLess=true;

    function snfToggleText()
    {

        var text="SNF constructs patient similarity networks for each of the data types and in a second step iteratively integrates " +
                "them until it converges to a final fused network. Here we present a Shiny web app for SNF, " +
                "where a user has the ability to integrate various data types, adjust the parameters, view the results and " +
                "download network files and group assignments.  \<br\>";

        if (snfTextLess == true) {
            document.getElementById("snfMoreText").innerHTML=text;
            document.getElementById("snfToggleButton").innerText = "See Less";
            snfTextLess = false;
        } else if (snfTextLess == false) {
            document.getElementById("snfMoreText").innerHTML = "...";
            document.getElementById("snfToggleButton").innerText = "See More";
            snfTextLess = true;
        }
    }

    var ehsTextLess=true;
    function ehsToggleText()
    {

        var text="and refinement of patient cohorts based on their values in high dimensional data experiments. \<br\>";

        if (ehsTextLess == true) {
            document.getElementById("ehsMoreText").innerHTML=text;
            document.getElementById("ehsToggleButton").innerText = "See Less";
            ehsTextLess = false;
        } else if (ehsTextLess == false) {
            document.getElementById("ehsMoreText").innerHTML = "...";
            document.getElementById("ehsToggleButton").innerText = "See More";
            ehsTextLess = true;
        }
    }

    var gsvaTextLess=true;
    function gsvaToggleText() {
        var text="a gene set of interest across a sample population. \<br\>";

        if(gsvaTextLess==true){
            document.getElementById("gsvaMoreText").innerHTML=text;
            document.getElementById("gsvaToggleButton").innerHTML="See Less";
            gsvaTextLess=false;
        } else if(gsvaTextLess==false){
            document.getElementById("gsvaMoreText").innerHTML="...";
            document.getElementById("gsvaToggleButton").innerHTML="See More";
            gsvaTextLess = true;
        }
    }


    var hidomeTextLess=true;
    function hidomeToggleText() {
        var text="a gene set of interest across a sample population. \<br\>";

        if(hidomeTextLess==true){
            document.getElementById("hidomeMoreText").innerHTML=text;
            document.getElementById("hidomeToggleButton").innerHTML="See Less";
            hidomeTextLess=false;
        } else if(hidomeTextLess==false){
            document.getElementById("hidomeMoreText").innerHTML="...";
            document.getElementById("hidomeToggleButton").innerHTML="See More";
            hidomeTextLess = true;
        }
    }
    /*End of the text script*/


</script>
%{--End of the script section--}%

</div>

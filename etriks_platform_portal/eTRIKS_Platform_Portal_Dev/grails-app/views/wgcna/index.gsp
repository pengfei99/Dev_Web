<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 10/13/15
  Time: 11:21 AM
--%>

<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 9/23/15
  Time: 11:39 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link href="${request.contextPath}/js/videojs/video-js.css" rel="stylesheet">
    <meta name="layout" content="main"/>
    <title>eTRIKS labs WGCNA</title>
</head>

<body>

<div class="container">
    <div class="row">
        <h1>WGCNA</h1>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>1.What the tool can do for you?</h3>

            This tool focuses on exploring correlation between probesets in gene expression data and compare with clinical data available.
            <br>
            <br>
            Original paper: Horvath et al, BMC Bioinformatics 2008, 9:559 doi:10.1186/1471-2105-9-559 'WGCNA: an R package for weighted correlation network analysis'
            <br>
            <br>

            The following image is from: Jiang et al, BMC Genomics 2014 15:756 doi:10.1186/1471-2164-15-756, 'Transcriptional profiles of bovine in vivo pre-implantation development'
            <br>
            <br>
            <span style="align: center;margin:auto;">
            <img class="img-responsive" alt="SmartR Demo image" src="${resource(dir:'images',file: 'etriksLabs/wgcna_full.jpg')}" width="600" height="400"></span>
        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-md-12">
            <h3>2.How to get the tool</h3>
            No eTRIKS implementation yet, shiny app in development.
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-12">
            <h3>3.Learn how to use the tool</h3>
            WGCNA tutorials (in R):


            <a href="http://labs.genetics.ucla.edu/horvath/CoexpressionNetwork/Rpackages/WGCNA/Tutorials/" target="_blank">http://labs.genetics.ucla.edu/horvath/CoexpressionNetwork/Rpackages/WGCNA/Tutorials/</a>
            <br>
            <br>

        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col-md-12">
            <h3>4.How to get Involved</h3>

            Please contact <a href="mailto:bdemeulder@eisbm.org">bdemeulder@eisbm.org</a> if you want to participate the development of the application.

        </div>
    </div>
    <hr>

    %{--End of container--}%
</div>

%{--import javascript--}%
<script src="${request.contextPath}/js/videojs/video.js"></script>
<script>
    videojs.options.flash.swf = "${request.contextPath}/js/videojs/video-js.swf"
</script>
</body>
</html>
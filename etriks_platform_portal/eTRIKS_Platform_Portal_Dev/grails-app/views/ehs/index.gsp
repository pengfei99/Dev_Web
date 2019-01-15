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
    <title>eTRIKS labs EHS</title>
</head>

<body>

<div class="container">
    <div class="row">
        <h1>EHS</h1>
    </div>

    <div class="row">
        <div class="col-md-12">
<h3>1.What the tool can do for you?</h3>

            The eTRIKS Harmonization Service (eHS) is a new addition to the eTRIKS platform that addresses
            the needs of eTRIKS current and prospective users for data harmonization and support for cross-study
            data investigations across translational research projects.<br>
            <br>
            These extensions include a new standards compliant repository (BioSPEAK-DB) for storing,
            sharing and querying harmonized translational research data based on a new generic semantic data model.
            This model builds upon already existing standard initiatives in clinical and pre- clinical data management,
            namely the CDISC and ISA standards to facilitate standard-based data harmonization.
            This repository and associated interfaces will facilitate the configuration of study designs, preparation
            of study data for system import and transformation of study data into semantically consistent representations.<br>
            <br>
            Complimentary to the data repository, a metadata registry is setup part of the eHS to support data curation
            and harmonization procedures, and a new eHS-tranSMART pipeline that improves data loading into traSMART by
            leveraging study-specific configurations generated within the EHS to extract, transform and load user-selected data
            to tranSMART with minimal curator intervention.<br>
<br>

    </div>
        </div>
    <hr>

<div class="row">
    <div class="col-md-12">
    <h3>2.How to get the tool</h3>
        Tool not available for download, but a sandbox “prototype” version of the current implementation is available at

        <a href="http://ehs.biospeak.solutions/sandbox/app/" target="_blank">http://ehs.biospeak.solutions/sandbox/app/</a>
</div>
    </div>
<hr>
    <div class="row">
        <div class="col-md-12">
            <h3>3.Learn how to use the tool</h3>
            Not Available yet !

        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-md-12">
            <h3>4.How to get Involved</h3>
            Please email feedback and questions to <a href="mailto:i.emam@imperial.ac.uk">i.emam@imperial.ac.uk</a>

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
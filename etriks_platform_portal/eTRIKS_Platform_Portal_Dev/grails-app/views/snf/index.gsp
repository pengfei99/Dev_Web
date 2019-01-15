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
    <title>eTRIKS labs SNF</title>
</head>

<body>

<div class="container">
    <div class="row">
        <h1>SNF</h1>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>1.What the tool can do for you?</h3>

            Similarity Network Fusion is an algorithm developed in the group of Anna Goldenberg (Wang et al., Nature Methods 2014).
            SNF is fusing diverse type of genomics datasets in a cost-efficient manner, analyzing different layers of biology on the same patients,
            clustering patients based on this fused matrix.
            <br>
        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-md-12">
            <h3>2.How to get the tool</h3>
            Access to the online tool through:
            <a href="https://snf-shiny.shinyapps.io/rtest" target="_blank"><h4>https://snf-shiny.shinyapps.io/rtest</h4></a>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-12">
            <h3>3.Learn how to use the tool</h3>

            Documentation on how to use the shiny tool can be found in
            <a href="https://snf-shiny.shinyapps.io/rtest" target="_blank">https://snf-shiny.shinyapps.io/rtest</a>
            <br>
            <br>

            Documentation and download of the algorithm can be found on
           <a href="https://cran.r-project.org/web/packages/SNFtool/index.html" target="_blank"> https://cran.r-project.org/web/packages/SNFtool/index.html</a>
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col-md-12">
            <h3>4.How to get Involved</h3>

            Please contact <a href="mailto:bdemeulder@eisbm.org">bdemeulder@eisbm.org</a> in case you need to more information regarding the shiny app for SNF.

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
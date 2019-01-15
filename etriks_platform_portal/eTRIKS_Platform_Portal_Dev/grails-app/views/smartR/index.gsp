
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
    <title>eTRIKS labs SmartR</title>
</head>

<body>

<div class="container">
    <div class="row">
        <h1>SmartR</h1>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>1.What the tool can do for you?</h3>

            SmartR provides a highly dynamic and interactive way of visualizing and analyzing data within tranSMART.
            Using recent web technologies SmartR generates visual analytics within the web browser rather than making use of static images.
            This provides the user with the possibility to explore and interact with the data while background scripts
            ensure that heavier computations are carried out by the tranSMART server, maintaining a responsive user interface.
            <br>


            <h4>Tool demo</h4>
            <iframe width="560" height="315" src="https://www.youtube.com/embed/_cXvrksMbtA" frameborder="0" allowfullscreen></iframe>
        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-md-12">
            <h3>2.How to get the tool</h3>
            You can use the following link to use a demo version of SmartR. You can also download SmartR and test it on your own environment<br>
            <a href="http://etriks1.uni.lu/" target="_blank"><h4>Go to SmartR</h4></a>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-12">
            <h3>3.Learn how to use the tool</h3>
            <h4>SmartR workflow videos</h4>

        </div>
    </div>

    <div class="row">

        <div class="col-md-4"><iframe width="360" height="230" src="https://www.youtube.com/embed/QG56Azycg8k" frameborder="0" allowfullscreen></iframe>
            <h5>SmartR - Boxplot Analysis</h5>
        </div>
        <div class="col-md-4"><iframe width="360" height="230" src="https://www.youtube.com/embed/OJKj_eqJY6U" frameborder="0" allowfullscreen></iframe>
            <h5>SmartR - Correlation Analysis</h5>
        </div>
        <div class="col-md-4">
            <iframe width="360" height="230" src="https://www.youtube.com/embed/FURhd-yIbYA" frameborder="0" allowfullscreen></iframe>
            <h5>SmartR - Heatmap Analysis</h5>
        </div>
    </div>

    <div class="row">

        <div class="col-md-12"> You can use the following link to get more tutorial videos<br>

            <a href="https://www.youtube.com/channel/UCKUbu0z3CQfi6RcFermONSw/videos" target="_blank">SmartR workflow tutorials</a>
        </div>

    </div>
    <hr>

    <div class="row">
        <div class="col-md-12">
            <h3>4.How to get Involved</h3>
            <ul>
                <li>Code and setup instructions: <a href="https://github.com/transmart/SmartR" target="_blank">https://github.com/transmart/SmartR</a></li>
                <li> Bugreports go here: <a href="http://usersupport.etriks.org" target="_blank">http://usersupport.etriks.org/</a></li>
                <li>Feedback goes here: <a href="mailto:sascha.herzinger@uni.lu">sascha.herzinger@uni.lu</a></li>
            </ul>
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
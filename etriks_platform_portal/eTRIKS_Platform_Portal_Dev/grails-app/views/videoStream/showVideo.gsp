<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 6/4/15
  Time: 5:33 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link href="${request.contextPath}/js/videojs/video-js.css" rel="stylesheet">

    <meta name="layout" content="main">
    <title>Available Videos in portal</title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message
                code="default.home.label" /></a></li>
        <li><a class="list" href="${createLink(uri: '/videoStream')}">Media List
        </a>
        </li>
    </ul>
</div>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>


<div id="page-body">
   The selected video name is ${videoId} !!!
<br>
    <br>
    <video id="example_video_1" class="video-js vjs-default-skin" controls preload="auto" width="640" height="264" data-setup='{"example_option":true}'>
       <source src="${resource(dir:"images/videos",file:videoId)}" type='video/mp4' />
       %{-- <source src="http://video-js.zencoder.com/oceans-clip.webm" type='video/webm' />
        <source src="http://video-js.zencoder.com/oceans-clip.ogv" type='video/ogg' />--}%
        <p class="vjs-no-js">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a></p>
    </video>

</div>

<script src="${request.contextPath}/js/videojs/video.js"></script>
<script>
    videojs.options.flash.swf = "${request.contextPath}/js/videojs/video-js.swf"
</script>

</body>
</html>
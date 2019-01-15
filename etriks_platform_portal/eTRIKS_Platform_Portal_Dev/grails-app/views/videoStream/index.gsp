<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 6/4/15
  Time: 3:47 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Available Videos in portal</title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message
                code="default.home.label" /></a></li>
    </ul>
</div>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<div id="bodyText">
    <h3> This page lists all available videos in the portal. </h3>


    <br>
    <br>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="VideoName" title="Video Name" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${videoList}" status="i" var="video">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="showVideo" id="${video}" params="[videoId:video]">${video}</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>


</div>
</body>
</html>
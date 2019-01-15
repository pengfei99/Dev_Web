<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 12/4/14
  Time: 1:47 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Policy Decision</title>
</head>


<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message
                code="default.home.label" /></a></li>
        <li><a class="home" href="${createLink(uri: '/XACMLTester/index')}"> XACML policy tester</a></li>
    </ul>
</div>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<div id="bodyText">

    <table>
        <tbody>
        <tr class="prop">
            <td valign="top" width="30%"> Decision in xml format: </td>
             <td valign="top" > ${xmlDecision} </td>
        </tr>

        <tr class="prop">
            <td>  Decision in boolean format: </td>
            <td>   ${boolDecision}</td>
        </tr>


        <tr class="prop">
            <td>  Decision in text format: </td>
            <td>    ${textDecision}</td>
        </tr>

        </tbody>
    </table>
</div>
</body>
</html>
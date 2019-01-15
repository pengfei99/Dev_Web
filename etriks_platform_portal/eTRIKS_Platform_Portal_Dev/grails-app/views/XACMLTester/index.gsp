<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 12/3/14
  Time: 3:14 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>XACML policy tester</title>
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
    <h3> This page is for testing the XACML security policy. </h3>


    <br>
    <p>Enter the user id, user role, target id and action in the following form. Then you will receive a decision.

    <br>

    <br>

    <g:form>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name"><label for="userId">Enter the user Id:</label>
                    </td>
                    <td valign="top">
                        <g:textField name="userId" maxlength="40" required=""  value=" "/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><label for="userRole">Enter the user role:</label>
                    </td>
                    <td valign="top">
                        <g:textField name="userRole" maxlength="40" required=""  value=" "/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><label for="targetId">Enter the target Id:</label>
                    </td>
                    <td valign="top">
                        <g:textField name="targetId" maxlength="40" required=""  value=" "/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name"><label for="uaction">Enter the user action:</label>
                    </td>
                    <td valign="top">
                        <g:textField name="uaction" maxlength="40" required=""  value=" "/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <fieldset class="buttons">

            <g:actionSubmit class="save" action="evaluateRequest" value="evaluate"  />
        </fieldset>
    </g:form>

    <br>
    <br>



</div>
</body>
</html>
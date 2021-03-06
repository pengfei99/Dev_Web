<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 7/10/14
  Time: 2:36 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Create a new organization </title>
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><a class="list" href="${createLink(uri: '/bioasterOrganizationManager/')}"> Organization manager </a></li>
    </ul>
</div>
<div class="body">


    <div id="page-body" role="main">
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
    <g:if test="${flash.error}">
        <div class="errors" role="alert">${flash.error}</div>
    </g:if>
<g:form>
    <div class="dialog">
        <table>
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><label for="organizationName">Organization Name:</label>
                </td>

                <td valign="top">
                    <g:textField name="organizationName" maxlength="40" required=""  value="Enter the organization name"/>
                %{--<input type="text" id="organizationName" name="organizationName" value="Enter the organization name"/>--}%
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><label for="organizationDescription">Organization description:</label>
                </td>
                <td valign="top">
                   %{-- <input type="text" id="organizationDescription" name="organizationDescription" value="Enter the organization description" />--}%
                   <g:textField name="organizationDescription" maxlength="80" required=""  value="Enter the organization description"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <fieldset class="buttons">

        <g:actionSubmit class="save" action="createOrganizationLdapEntry" value="submit"  />
    </fieldset>
    </g:form>



    </div>
</div>
</body>
</html>
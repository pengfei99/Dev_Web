<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Bioaster User registration validation</title>
</head>
<body>







<div class="container">

    %{--Flash message and error row--}%

    <div class="row">
        <div class="col-md-10">
            <g:if test="${flash.message}">
                <div class="message" role="alert">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
                <div class="errors" role="alert">${flash.error}</div>
            </g:if>
            <br>
        </div>
    </div>


    <br>

    %{--Form of request attribute for final review--}%

<g:form>
    <div class="row">
    <div class="dialog">
        <table class="table borderless">
            <tbody>
                <tr class="prop">
                    <td valign="top" class="name"><label for="CN">CN:</label>
                    </td>
                    <td valign="top"><input type="text" id="cn" name="cn" value="${bioasterLdapAccountEntry.cn} "/>
							</td>
						</tr>
    <tr class="prop">
        <td valign="top" class="name"><label for="UID">UID:</label>
        </td>
        <td valign="top"><input type="text" id="uid" name="uid" value="${bioasterLdapAccountEntry.uid}" /></td>
    </tr>

    <tr class="prop">
        <td valign="top" class="name"><label for="SN">SN:</label>
        </td>
        <td valign="top"><input type="text" id="sn" name="sn" value="${bioasterLdapAccountEntry.sn}"/>
        </td>
    </tr>

    <tr class="prop">
        <td valign="top" class="name"><label for="GivenName">GivenName:</label>
        </td>
        <td valign="top"><input type="text" id="givenName" name="givenName" value="${bioasterLdapAccountEntry.givenName}"/>
        </td>
    </tr>

    <tr class="prop">
        <td valign="top" class="name"><label for="UserPassword">UserPassword:</label>
        </td>
        <td valign="top"><input type="password" id="userPassword"
                                name="userPassword" value="${bioasterLdapAccountEntry.password}" />
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name"><label for="UidNumber">UidNumber:</label>
        </td>
        <td valign="top"><input type="text" id="uidNumber" name="uidNumber" value="${bioasterLdapAccountEntry.uidNumber}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name"><label for="GidNumber">GidNumber:</label>
        </td>
        <td valign="top"><input type="text" id="gidNumber" name="gidNumber" value="${bioasterLdapAccountEntry.gidNumber}"/> </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name"><label for="HomeDirectory">HomeDirectory:</label>
        </td>
        <td valign="top"><input type="text" id="homeDirectory" name="homeDirectory" value="${bioasterLdapAccountEntry.homeDirectory}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name"><label for="LoginShell">LoginShell:</label>
        </td>
        <td valign="top"><input type="text" id="loginShell" name="loginShell" value="${bioasterLdapAccountEntry.loginShell}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name"><label for="Mail">Mail:</label>
        </td>
        <td valign="top"><input type="text" id="mail" name="mail" value="${bioasterLdapAccountEntry.mail}"/> </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name"><label for="Organization">Organization:</label>
        </td>
        <td valign="top"><input type="text" id="organization" name="organization" value="${bioasterLdapAccountEntry.organization}"/> </td>
    </tr>


    </tbody>
</table>
</div>
</div>
    <div class="row">
        <div class="col-md-6 text-center">
            <fieldset class="buttons">
                <g:hiddenField name="validator" value="${bioasterLdapAccountEntry.validator}" />
                <g:hiddenField name="id" value="${bioasterLdapAccountEntry.requestID}" />
                <g:actionSubmit class="save" action="createLdapEntry" value="submit"  />
            </fieldset>
        </div>
    </div>
</g:form>


</div>


</body>
</html>



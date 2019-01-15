<%@ page import="org.etriks.security.register.AbiriskMemberAccount"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>Abirisk account validation</title>
</head>
<body>
<!-- This block is the navigation block which allows admin users to go to other pages -->

	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/dashBoard/abiriskAdminDashBoard')}">abirisk admin dashboard</a></li>
		</ul>
	</div>

<!-- This block is the form which shows the ldap user account entry view. The admin user can modify it or not, and the submit button allows to create an account at the ldap server -->
<div class="container">
    <g:form>
    <div class="row">

			<div class="dialog">
				<table class="table borderless">
					<tbody>
						<tr class="prop">
							<td valign="top" class="name"><label for="CN">CN:</label>
							</td>
							<td valign="top"><input type="text" id="cn" name="cn" value="${abiriskMemberAccount.cn} "/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="UID">UID:</label>
							</td>
							<td valign="top"><input type="text" id="uid" name="uid" value="${abiriskMemberAccount.uid}" /></td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><label for="SN">SN:</label>
							</td>
							<td valign="top"><input type="text" id="sn" name="sn" value="${abiriskMemberAccount.sn}"/>
							</td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><label for="GivenName">GivenName:</label>
							</td>
							<td valign="top"><input type="text" id="givenName" name="givenName" value="${abiriskMemberAccount.givenName}"/>
							</td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><label for="UserPassword">UserPassword:</label>
							</td>
							<td valign="top"><input type="password" id="userPassword"
								name="userPassword" value="${abiriskMemberAccount.password}"/></td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="UidNumber">UidNumber:</label>
							</td>
							<td valign="top"><input type="text" id="uidNumber" name="uidNumber" value="${abiriskMemberAccount.uidNumber}"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="GidNumber">GidNumber:</label>
							</td>
							<td valign="top"><input type="text" id="gidNumber" name="gidNumber" value="${abiriskMemberAccount.gidNumber}"/> </td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="HomeDirectory">HomeDirectory:</label>
							</td>
							<td valign="top"><input type="text" id="homeDirectory" name="homeDirectory" value="${abiriskMemberAccount.homeDirectory}"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="LoginShell">LoginShell:</label>
							</td>
							<td valign="top"><input type="text" id="loginShell" name="loginShell" value="${abiriskMemberAccount.loginShell}"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="Mail">Mail:</label>
							</td>
							<td valign="top"><input type="text" id="mail" name="mail" value="${abiriskMemberAccount.mail}"/> </td>
						</tr>
                    <tr class="prop">
                        <td valign="top" class="name"><label for="Organization">Organization:</label>
                        </td>
                        <td valign="top"><input type="text" id="organization" name="organization" value="${abiriskMemberAccount.organization}"/> </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name"><label for="Groups">Groups:</label>
                        </td>
                        <td valign="top"><input type="text" id="groups" name="groups" value="${abiriskMemberAccount.groups}"/> </td>
                    </tr>
					</tbody>
				</table>
			</div>
            </div>
        <div class="row">
			<div class="col-md-6 text-center">
            <fieldset class="buttons">
					<g:hiddenField name="validator" value="${abiriskMemberAccount.validator}" />
                    <g:hiddenField name="id" value="${abiriskMemberAccount.accountID}" />
					<g:actionSubmit class="save" action="createLdapEntry" id="${abiriskMemberAccount?.accountID}" value="submit"  />
				</fieldset>
				</div>
        </div>
		</g:form>


</div>

</body>
</html>
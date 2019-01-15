<%@ page import="org.etriks.security.register.BioasterMemberAccount"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">

<title>bioaster account validation</title>
</head>
<body>
<!-- This block is the navigation block which allows admin users to go to other pages -->

	<div class="nav" role="navigation">
		<ul>
            <li><a class="home" href="${createLink(uri: '/dashBoard/bioasterAdminDashBoard')}">bioaster admin dashboard</a></li>
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
							<td valign="top" class="name"><label for="CN">CN:</label></div>
							</td>
							<td valign="top"><input type="text" id="cn" name="cn" value="${bioasterMemberAccount.cn} "/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="UID">UID:</label>
							</td>
							<td valign="top"><input type="text" id="uid" name="uid" value="${bioasterMemberAccount.uid}" /></td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><label for="SN">SN:</label>
							</td>
							<td valign="top"><input type="text" id="sn" name="sn" value="${bioasterMemberAccount.sn}"/>
							</td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><label for="GivenName">GivenName:</label>
							</td>
							<td valign="top"><input type="text" id="givenName" name="givenName" value="${bioasterMemberAccount.givenName}"/>
							</td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><label for="UserPassword">UserPassword:</label>
							</td>
							<td valign="top"><input type="password" id="userPassword"
								name="userPassword" value="${bioasterMemberAccount.password}"/></td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="UidNumber">UidNumber:</label>
							</td>
							<td valign="top"><input type="text" id="uidNumber" name="uidNumber" value="${bioasterMemberAccount.uidNumber}"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="GidNumber">GidNumber:</label>
							</td>
							<td valign="top"><input type="text" id="gidNumber" name="gidNumber" value="${bioasterMemberAccount.gidNumber}"/> </td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="HomeDirectory">HomeDirectory:</label>
							</td>
							<td valign="top"><input type="text" id="homeDirectory" name="homeDirectory" value="${bioasterMemberAccount.homeDirectory}"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="LoginShell">LoginShell:</label>
							</td>
							<td valign="top"><input type="text" id="loginShell" name="loginShell" value="${bioasterMemberAccount.loginShell}"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="Mail">Mail:</label>
							</td>
							<td valign="top"><input type="text" id="mail" name="mail" value="${bioasterMemberAccount.mail}"/> </td>
						</tr>
                    <tr class="prop">
                        <td valign="top" class="name"><label for="Organization">Organization:</label>
                        </td>
                        <td valign="top"><input type="text" id="organization" name="organization" value="${bioasterMemberAccount.organization}"/> </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name"><label for="Groups">Groups:</label>
                        </td>
                        <td valign="top"><input type="text" id="groups" name="groups" value="${bioasterMemberAccount.groups}"/> </td>
                    </tr>
					</tbody>
				</table>
            </div>
			</div>
            <div class="row">
                <div class="col-md-6 text-center">
			<fieldset class="buttons">
					<g:hiddenField name="validator" value="${bioasterMemberAccount.validator}" />
                    <g:hiddenField name="id" value="${bioasterMemberAccount.accountID}" />
					<g:actionSubmit class="save" action="createLdapEntry" id="${bioasterMemberAccount?.accountID}" value="submit"  />
				</fieldset>
            </div>
            </div>
		</g:form>


</div>

</body>
</html>
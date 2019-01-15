<%@ page import="org.etriks.security.register.OncoTrackMemberAccount"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>Oncotrack member account validation</title>
</head>
<body>
<!-- This block is the navigation block which allows admin users to go to other pages -->
	<div class="nav" role="navigation">
		<ul>
            <li><a class="home" href="${createLink(uri: '/dashBoard/oncoTrackAdminDashBoard')}">oncoTrack admin dashboard</a></li>
		</ul>
	</div>

<!-- This block is the form which shows the ldap user account entry view. The admin user can modify it or not, and the submit button allows to create an account at the ldap server -->
<div class="container">
		<g:form>
			<div class="dialog">
                <div class="row">
				<table class="table borderless">
					<tbody>
						<tr class="prop">
							<td valign="top" class="name"><label for="CN">CN:</label>
							</td>
							<td valign="top"><input type="text" id="cn" name="cn" value="${oncoTrackMemberAccount.cn} "/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="UID">UID:</label>
							</td>
							<td valign="top"><input type="text" id="uid" name="uid" value="${oncoTrackMemberAccount.uid}" /></td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><label for="SN">SN:</label>
							</td>
							<td valign="top"><input type="text" id="sn" name="sn" value="${oncoTrackMemberAccount.sn}"/>
							</td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><label for="GivenName">GivenName:</label>
							</td>
							<td valign="top"><input type="text" id="givenName" name="givenName" value="${oncoTrackMemberAccount.givenName}"/>
							</td>
						</tr>
						
						<tr class="prop">
							<td valign="top" class="name"><label for="UserPassword">UserPassword:</label>
							</td>
							<td valign="top"><input type="password" id="userPassword"
								name="userPassword" value="${oncoTrackMemberAccount.password}"/></td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="UidNumber">UidNumber:</label>
							</td>
							<td valign="top"><input type="text" id="uidNumber" name="uidNumber" value="${oncoTrackMemberAccount.uidNumber}"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="GidNumber">GidNumber:</label>
							</td>
							<td valign="top"><input type="text" id="gidNumber" name="gidNumber" value="${oncoTrackMemberAccount.gidNumber}"/> </td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="HomeDirectory">HomeDirectory:</label>
							</td>
							<td valign="top"><input type="text" id="homeDirectory" name="homeDirectory" value="${oncoTrackMemberAccount.homeDirectory}"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="LoginShell">LoginShell:</label>
							</td>
							<td valign="top"><input type="text" id="loginShell" name="loginShell" value="${oncoTrackMemberAccount.loginShell}"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name"><label for="Mail">Mail:</label>
							</td>
							<td valign="top"><input type="text" id="mail" name="mail" value="${oncoTrackMemberAccount.mail}"/> </td>
						</tr>
                    <tr class="prop">
                        <td valign="top" class="name"><label for="Organization">Organization:</label>
                        </td>
                        <td valign="top"><input type="text" id="organization" name="organization" value="${oncoTrackMemberAccount.organization}"/> </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name"><label for="Groups">Groups:</label>
                        </td>
                        <td valign="top"><input type="text" id="groups" name="groups" value="${oncoTrackMemberAccount.groups}"/> </td>
                    </tr>
					</tbody>
				</table>
                </div>
			</div>
            <div class="row">
                <div class="col-md-6 text-center">
			<fieldset class="buttons">
					<g:hiddenField name="validator" value="${oncoTrackMemberAccount.validator}" />
                    <g:hiddenField name="id" value="${oncoTrackMemberAccount.accountID}" />
					<g:actionSubmit class="save" action="createLdapEntry" id="${oncoTrackMemberAccount.accountID}" value="submit"  />
				</fieldset>
            </div>
            </div>
		</g:form>

</div>


</body>
</html>
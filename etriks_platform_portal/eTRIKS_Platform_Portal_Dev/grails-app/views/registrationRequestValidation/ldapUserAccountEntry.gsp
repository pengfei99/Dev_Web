<%@ page import="org.etriks.security.register.EtriksMemberAccount"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">

<title>eTRIKS member account validation</title>

</head>
<body>
<!-- This block is the navigation block which allows admin users to go to other pages -->

	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/dashBoard')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" action="userAccountManager">
					user registration request list
				</g:link></li>

		</ul>
	</div>

<!-- This block is the form which shows the ldap user account entry view. The admin user can modify it or not, and the submit button allows to create an account at the ldap server -->
	<div class="row">
    <div class="col-md-7 text-center">
		<g:form>
            <div class="table-responsive">

                    <table class="table borderless">
                        <tbody>
                            <tr>
                                <td class="text-right"><label for="cn">CN:</label>
                                </td>
                                <td class="text-left"><input type="text" id="cn" name="cn" value="${etriksMemberAccount.cn} "/>
							</td>
						</tr>
						<tr class="prop">
							<td class="text-right"><label for="uid">UID:</label>
							</td>
							<td class="text-left"><input type="text" id="uid" name="uid" value="${etriksMemberAccount.uid}" /></td>
						</tr>
						
						<tr class="prop">
							<td class="text-right"><label for="sn">SN:</label>
							</td>
							<td class="text-left"><input type="text" id="sn" name="sn" value="${etriksMemberAccount.sn}"/>
							</td>
						</tr>
						
						<tr class="prop">
							<td class="text-right"><label for="givenName">GivenName:</label>
							</td>
							<td class="text-left"><input type="text" id="givenName" name="givenName" value="${etriksMemberAccount.givenName}"/>
							</td>
						</tr>
						
						<tr class="prop">
							<td class="text-right"><label for="userPassword">UserPassword:</label>
							</td>
							<td class="text-left"><input type="password" id="userPassword"
								name="userPassword" value="${etriksMemberAccount.password}"/></td>
						</tr>
						<tr class="prop">
							<td class="text-right"><label for="UidNumber">UidNumber:</label>
							</td>
							<td class="text-left"><input type="text" id="uidNumber" name="uidNumber" value="${etriksMemberAccount.uidNumber}"/>
							</td>
						</tr>
						<tr class="prop">
							<td class="text-right"><label for="GidNumber">GidNumber:</label>
							</td>
							<td class="text-left"><input type="text" id="gidNumber" name="gidNumber" value="${etriksMemberAccount.gidNumber}"/> </td>
						</tr>
						<tr class="prop">
							<td class="text-right"><label for="HomeDirectory">HomeDirectory:</label>
							</td>
							<td class="text-left"><input type="text" id="homeDirectory" name="homeDirectory" value="${etriksMemberAccount.homeDirectory}"/>
							</td>
						</tr>
						<tr class="prop">
							<td class="text-right"><label for="LoginShell">LoginShell:</label>
							</td>
							<td class="text-left"><input type="text" id="loginShell" name="loginShell" value="${etriksMemberAccount.loginShell}"/>
							</td>
						</tr>
						<tr class="prop">
							<td class="text-right"><label for="Mail">Mail:</label>
							</td>
							<td class="text-left"><input type="text" id="mail" name="mail" value="${etriksMemberAccount.mail}"/> </td>
						</tr>
                    <tr class="prop">
                        <td class="text-right"><label for="Organization">Organization:</label>
                        </td>
                        <td class="text-left"><input type="text" id="organization" name="organization" value="${etriksMemberAccount.organization}"/> </td>
                    </tr>

                    <tr class="prop">
                        <td class="text-right"><label for="workPackage">Work_Package:</label>
                        </td>
                        <td class="text-left"><input type="text" id="workPackage" name="workPackage" value="${etriksMemberAccount.workPackage}"/> </td>
                    </tr>
					</tbody>
				</table>
			</div>

            %{--Content of the privacy protection confirm box--}%
            <div id="dialog-confirm"></div>

			<fieldset class="buttons">
					<g:hiddenField name="id" value="${etriksMemberAccount?.id}" />
					<g:actionSubmit class="save" action="createLdapEntry"
                                    id="${etriksMemberAccount?.id}" value="submit"  />
				</fieldset>
		</g:form>

</div>
    </div>


</body>
</html>
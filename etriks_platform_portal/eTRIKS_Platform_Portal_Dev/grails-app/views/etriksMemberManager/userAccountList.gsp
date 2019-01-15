<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>All registered user list </title>
</head>
<body>

  <div class="body">

      <div class="row text-center">
          <br>
          <br>
          There are ${userAccountTotal} registered user as eTRIKS project member in eTRIKS LDAP server
          <br>
          <br>
      </div>
      <div class="row">
  <table class="table table-striped">
				<thead>
					<tr>
					
						<g:sortableColumn property="Name" title="User Name" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userCNList}" status="i" var="userCN">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="showUserDetails" id="${userCN}">${userCN}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			%{--<div class="pagination">
				<g:paginate next="Forward" prev="Back" total="${userAccountTotal}" max="8" controller="userAccount" action="userAccountList" maxsteps="0"/>
			</div>--}%
  </div>
      </div>
</body>
</html>
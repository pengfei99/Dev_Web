<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>All User mail list</title>
</head>
<body>
  <div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><a class="list" href="${createLink(uri: '/ldapMemberManager/exportUserList', params: "[foo:'ok']")}"><g:message code="Export as excel file"/></a></li>
			</ul>
		</div>
<br>
<br>
  
  <div id="licenseText">
 %{-- ${mailListStr}--}%

      <g:each var="user" in="${userList}">

          <p>UserName: ${user.getName()}</p>
          <p>UserUid: ${user.getUid()}</p>
          <p>UserMail: ${user.getMail()}</p>
          <br>
      </g:each>
  </div>
</body>
</html>
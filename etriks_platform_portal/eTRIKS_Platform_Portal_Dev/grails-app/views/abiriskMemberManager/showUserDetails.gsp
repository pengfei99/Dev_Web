<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Abirisk member details</title>
</head>
<body>
<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>

        <li><a class="list" href="${createLink(uri: '/abiriskMemberManager/userAccountList')}"> Abirisk member List</a></li>
       </ul>
		</div>


  <div id="page-body" role="main">
  <br>
  <br>
  User Name : ${pcn }
<br>
<br>
  User ID : ${puid }
<br>
<br>
  User Mail : ${mail}
  <br>
      <br>

      User Organization : ${organizationName}
      <br>
      <br>

      User Roles : <g:each in="${userGroups}" var="group">

              ${group.key} (${group.value}),

      </g:each>
  </div>
</body>
</html>
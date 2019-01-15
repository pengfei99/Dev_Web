<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>eTRIKS member details</title>
</head>
<body>
<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>

        <li><a class="list" href="${createLink(uri: '/etriksMemberManager/userAccountList')}"> <g:message code="eTRIKS member List"/></a></li>
       </ul>
		</div>


  <div id="page-body" role="main">
  <br>
  <br>
  UserName : ${pcn }
<br>
<br>
  UserID : ${puid }
<br>
<br>
  UserMail : ${mail}
      <br>
      <br>

      User Organization : ${organizationName}
      <br>
      <br>
  </div>
</body>
</html>
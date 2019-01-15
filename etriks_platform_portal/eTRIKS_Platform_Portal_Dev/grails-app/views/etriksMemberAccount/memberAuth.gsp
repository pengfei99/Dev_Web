<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Member authentication page</title>
</head>
<body>

%{--<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>--}%

  <div id="page-body" role="main">
      <g:if test="${flash.messages}">
          <ul class="errors" role="alert">
              <g:each in="${flash.messages}" var="message">
                  <li>
                      ${message}
                  </li>
              </g:each>
          </ul>
      </g:if>

      <div class="container">
          <div class="row">
              <div class="col-md-12 text-center">

      <p>
          <h4>Welcome to eTRIKS member registration page</h4>
          <br>
      To prevent the abuse or attack of the eTRIKS member registration page.
      <br>
      In the following case, you need to enter a password.
      <br>
      If you do not know this password, ask your work package leader.

          <br>
          <br>
          <br>
        Enter the password:
          <br>
      <g:form action="memberAuth">
          <g:field type="password" name="password" maxlength="40" required=""/>
          <br>
          <br>
          <g:submitButton name="submit" value="Submit" />
      </g:form>
      </p>
      </div>
              </div>

  </div>
</body>
</html>
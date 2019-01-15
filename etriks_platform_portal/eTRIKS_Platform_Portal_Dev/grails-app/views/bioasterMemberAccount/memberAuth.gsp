<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Member authentication page</title>
</head>
<body>

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


          <h4>Welcome to Bioaster member registration page</h4>
          <br>
      To prevent the abuse or attack of the Bioaster member registration page.
      <br>
      In the following case, you need to enter a password.
      <br>
      If you do not know this password,
      <br>
      please contact :
          <br>
          <g:each in="${validatorList}" var="validators">

          <a href="mailto:${validators.value}" target="_top">${validators.key}</a>
            <br>

            </g:each>


          <br>
        Enter the password:
          <br>
      <g:form action="memberAuth">
          <g:field type="password" name="password" maxlength="40" required=""/>
          <br>
          <br>
          <g:submitButton name="submit" value="Submit" />
      </g:form>

      </div>
  </div>
          </div>
</body>
</html>
<%@ page import="org.etriks.security.register.UserAccount" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:javascript library="jquery"/>
		<title>eTRIKS community registration</title>
	</head>
	<body>


		<div id="create-userAccount" class="content scaffold-create" role="main">
			<h1>eTRIKS community registration</h1>
			<br>
            <hr>
			<g:if test="${flash.messages}">
			<ul class="errors" role="alert">
			<g:each in="${flash.messages}" var="message">
			<li>
			${message}
			</li>
			</g:each>
			</ul>
			</g:if>
			<g:form action="save">
                <div class="row">
            %{--<g:form url="[resource:etriksMemberAccountInstance, action:'save']" params="[organizationList:organizationList]">--}%
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-12 text-center">

					<g:submitButton name="submit" class="save" value="Submit" />

                </div>
                </div>
			</g:form>
		</div>
	</body>
</html>

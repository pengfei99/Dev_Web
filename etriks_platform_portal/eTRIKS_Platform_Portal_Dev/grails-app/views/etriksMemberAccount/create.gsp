<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

        <g:javascript library="jquery"/>
		<title>eTRIKS project member registration </title>
	</head>
	<body>

            <a href="#create-etriksMemberAccount" class="skip" tabindex="-1"><g:message default="Skip to content&hellip;"/></a>

    %{--The navigation bar--}%
     %{--       <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                </ul>
            </div>--}%
            <div id="create-etriksMemberAccount" class="content scaffold-create" role="main">
                <h2>eTRIKS project member registration</h2>

        <hr>

            %{--The messages here is a list of message--}%
                <g:if test="${flash.messages}">
                    <ul class="errors" role="alert">
                        <g:each in="${flash.messages}" var="message">
                            <li>
                                ${message}
                            </li>
                        </g:each>
                    </ul>
                </g:if>


            %{--The errors message generate by grails.--}%
		%{--	<g:hasErrors bean="${etriksMemberAccountInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${etriksMemberAccountInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>--}%
			<g:form url="[resource:etriksMemberAccountInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>

                <hr>
                <div class="row">
                  <div class="col-md-12 text-center">
					<g:submitButton name="create" class="save" value="Submit" />
				</div>
                </div>
			</g:form>
		</div>
	</body>
</html>

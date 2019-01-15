<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:javascript library="jquery"/>
		<title>Bioaster member registration</title>
	</head>
	<body>


		<div id="create-bioasterMemberAccount" class="content scaffold-create" role="main">
			<h1>Bioaster member registration</h1>
            <hr>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
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
			<g:form url="[resource:bioasterMemberAccountInstance, action:'save']" params="[bioasterValidatorList:bioasterValidatorList,hostingValidatorList:hostingValidatorList,organizationList:organizationList,groupList:groupList]" >
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

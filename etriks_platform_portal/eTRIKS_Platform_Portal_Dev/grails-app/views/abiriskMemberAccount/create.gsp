<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

        %{--Include the javacript library which the dynamic determination of password's strength need --}%
        <g:javascript library="jquery"/>
		<title>Abirisk project member registration</title>
	</head>
	<body>


		<div id="create-abiriskMemberAccount" class="content scaffold-create" role="main">
			<h2>Abirisk project member registration</h2>
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
			<g:form url="[resource:abiriskMemberAccountInstance, action:'save']" params="[abiriskValidatorList:abiriskValidatorList,hostingValidatorList:hostingValidatorList,organizationList:organizationList,groupList:groupList]">
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

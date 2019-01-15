<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

        <g:javascript library="jquery"/>
		<title>OncoTrack project member registration</title>
	</head>
	<body>

		<div id="create-oncoTrackMemberAccount" class="content scaffold-create" role="main">
			<h1>OncoTrack project member registration</h1>

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

			<g:form url="[resource:oncoTrackMemberAccountInstance, action:'save']" params="[oncotrackValidatorList:oncotrackValidatorList,hostingValidatorList:hostingValidatorList,organizationList:organizationList,groupList:groupList]" >
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

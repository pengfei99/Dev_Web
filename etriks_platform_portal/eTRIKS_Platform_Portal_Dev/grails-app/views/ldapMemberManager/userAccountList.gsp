<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>All registered user list </title>
</head>
<body>
<div class="container">
    %{--row for the navigation--}%
    <div class="row">
        <div class="col-md-12">
            <ul>
                <li><a class="list" href="${createLink(uri: '/ldapMemberManager/')}">Back to the bioaster member manager </a></li>
                <li><a class="list" href="${createLink(uri: '/ldapMemberManager/userMailListWithUserName')}"> ldap member mail List </a></li>
            </ul>
        </div>
        %{--row for the user size--}%
        <div class="row">
            <div class="col-md-12" style="text-align: center;">
                <h4> There are ${userAccountTotal} registered user in Bioaster project LDAP server</h4>
            </div>
        </div>
    </div>
    %{--row for the user list--}%
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="scrollableTable" style="height: 700px;">
                <table class="table table-striped">
                    <thead>
                    <tr>

                        <g:sortableColumn property="Name" title="User Name" />

                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userCNList}" status="i" var="userCN">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                            <td><g:link action="showUserDetails" id="${userCN}">${userCN}</g:link></td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>



</body>

</html>
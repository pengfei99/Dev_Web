<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />

        <title>Welcome to bioaster admin dash board</title>
    </head>
    <body>



    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <!-- This bloc is the navigation bloc  -->
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/bioasterAdminDashBoard')}"><g:message code="default.home.label"/></a></li>
                        <li><a class="list" href="${createLink(uri: '/bioasterGroupManager/')}"> Group manager </a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <h2>Congratulations, you have successfully connected to the BIOASTER user group manager.</h2>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-md-12">
                <h4> BIOASTER Department Groups manager </h4>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'bioasterGroupList',controller:'bioasterGroupManager']">
                    View all groups of BIOASTER Department (e.g. UTEC, UTEM)
                </g:link>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'createDepartmentGroup',controller:'bioasterGroupManager']">
                    Create a new group of BIOASTER Department in OPENLdap server
                </g:link>

            </div>
        </div>

        <br>
        <br>

        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'addUserToDepartmentGroupView',controller:'bioasterGroupManager']">
                    Add users to a group of BIOASTER Department
                </g:link>
            </div>
        </div>
        <br>
        <br>
        <hr>

        <div class="row">
            <div class="col-md-12">
                <h4> BIOASTER Project Groups manager </h4>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'projectGroupList',controller:'bioasterGroupManager']">
                    View all groups of BIOASTER projects (e.g. MOSAIC, Codira2)
                </g:link>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'createProjectGroup',controller:'bioasterGroupManager']">
                    Create a new group of BIOASTER Project in OPENLdap server
                </g:link>

            </div>
        </div>

        <br>
        <br>

        <div class="row">
            <div class="col-md-12">
                <g:link url="[action:'addUserToProjectGroupView',controller:'bioasterGroupManager']">
                    Add users to a group of BIOASTER Project
                </g:link>
            </div>
        </div>

    </div>
    </body>
</html>
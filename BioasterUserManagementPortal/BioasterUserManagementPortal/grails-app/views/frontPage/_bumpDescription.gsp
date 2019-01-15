<div class="well">
    %{--row for title--}%
    <div class="row">
        <div class="col-md-12">
            <H1>BIOASTER User Account Management Portal</H1>
        </div>
    </div>
    %{--end of title row--}%
    <br>

%{--bump description row--}%

<div class="row">
    <div class="col-md-10">
         BUMP (BIOASTER User Account Management Portal) is designed and developed to facilitate the management of user account, password and group in an OPENLdap server.
        These features allow the admin user to control who can access which VMs (Virtual Machines), directories or files in a client server architecture.
         This means we no longer need to do change config file in each VMs to enforce our security policy, we make one change in OPENLdap server, the security policy of all VMs will be
        updated.
    </div>
</div>

    <br>

    %{--row for bump function--}%
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="panel-title">
                        <a href="#" data-toggle="collapse" data-target="#bumpFunction">
                           Wants to know more what BUMP can do for you?
                        </a>
                    </div>
                </div>
                <div id="bumpFunction" class="panel-collapse collapse">
                    <div class="panel-body">

                        <p>Here below is an overview of the BUMP features. You can find more detailed informatin in following sections.</p>
                        <div class="scollist">
                            <h4>User account management:</h4>

                            <dl class="dl-horizontal">
                                <dt>BIOASTER employees :</dt> <dd>They can import user account From AD</dd>
                                <dt>Collaborators :</dt> <dd> They can create account by submitting a registration request</dd>
                            </dl>
                            <hr>
                            <h4>User password management:</h4>
                            <dl class="dl-horizontal">
                                <dt>Change Password:</dt> <dd>If you knows your old password, you can chang it to a new one</dd>
                                <dt>Reset Password:</dt> <dd>If you forgot your password, BUMP can generate a new one and send it to your registered email</dd>
                                <dt>Import AD Password:</dt> <dd>If you want synchronize your password with AD, you can import your AD account password</dd>
                            </dl>

                            <hr>
                            <h4>User group management:</h4>
                            <dl class="dl-horizontal">

                                <dt>Add group to user:</dt> <dd>To allow access of linux server, file or directories, you need to assign user to the allowed group. You can use BUMP to do it easily</dd>
                                <dt>Remove user group:</dt> <dd>To revoke access of linux server, file or directories, you need to remove user from the allowed group. You can use BUMP to do it easily</dd>
                                <dt>Create new Group:</dt> <dd>If you wants create a new access control group, You can use BUMP to do it easily</dd>
                            </dl>
                        </div>
                    </div>


                </div><!--/panel content-->

            </div>

        </div>

    </div>
    %{--End of row bump function--}%

%{--row for which service access--}%
<div class="row">
    <div class="col-md-8">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="panel-title">
                    <a href="#" data-toggle="collapse" data-target="#availableDataSet">
                        Which services you can access with OPENLdap account?
                    </a>
                </div>
            </div>
            <div id="availableDataSet" class="panel-collapse collapse">
                <div class="panel-body">

                    <p>Here below is an list of services which you can access with OPENLdap account.</p>
                    <div class="scollist">
                        <h4>WEB Applications:</h4>

                        <dl class="dl-horizontal">
                            <dt>BUMP :</dt> <dd>You can access BUMP with your OPENLdap account</dd>
                        </dl>
                        <hr>
                        <h4>VMs in cloud:</h4>
                        <dl class="dl-horizontal">
                            <dt>Vm-ware cloud:</dt> <dd>You can access all Linux VMs with your OPENLdap account </dd>
                            <dt>OpenStack cloud:</dt> <dd>You can access all Linux VMs with your OPENLdap account</dd>

                        </dl>

                    </div>
                </div>


            </div><!--/panel content-->

        </div>

    </div>
</div>
%{--End of which service access---}%

</div>
%{--End of well--}%

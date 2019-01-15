<div class="well">
    %{--row for title--}%
    <div class="row">
        <div class="col-md-12">
            <H1>Password Management</H1>
        </div>
    </div>
    %{--end of title row--}%

    <br>
%{--bump description row--}%

<div class="row">
    <div class="col-md-10">
          To facilitate the management of user password, BUMP offers three possible scenarios. If you are a BIOASTER employee, you can synchronize your OPENLdap password with your AD account password.
          If you prefer to have a different password. You can change it. If you forgot your password, you can ask BUMP generates one for you.
          If you are a collaborator of BIOASTER, you can only change or reset your password. You can not synchronize your password with AD.
    </div>
</div>


    <br>
    %{--row for change password--}%
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="panel-title">
                        <a href="#" data-toggle="collapse" data-target="#changPassword">
                          Change Password
                        </a>
                    </div>
                </div>
                <div id="changPassword" class="panel-collapse collapse">
                    <div class="panel-body">

                        <p> </p>
                        <div class="scollist">
                            <h4>Change OPENLdap password:</h4>

                            <dl class="dl-horizontal">
                                <dt>Requirements :</dt> <dd>Your need to know your old password</dd>
                                <dt>Link :</dt> <dd> <a href="${createLink(controller:'endUserAccountManager',action:'changePassword')}">Change Password</a></dd>
                            </dl>
                            <hr>


                        </div>
                    </div>


                </div><!--/panel content-->

            </div>

        </div>

    </div>
    %{--End of row change password--}%

%{--row for forgot password--}%
<div class="row">
    <div class="col-md-8">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="panel-title">
                    <a href="#" data-toggle="collapse" data-target="#resetPassword">
                        Forgot Password
                    </a>
                </div>
            </div>
            <div id="resetPassword" class="panel-collapse collapse">
                <div class="panel-body">

                    <div class="scollist">

                        <h4>BUMP reset password:</h4>
                        <dl class="dl-horizontal">
                            <dt>Requirements :</dt> <dd>Your need to know your organizatinal email and your user ID. The generated password will be sent to your registered email</dd>
                            <dt>Link :</dt> <dd><a href="${createLink(controller:'endUserAccountManager',action:'forgotPassword')}">Registration</a></dd>
                        </dl>
                        <hr>
                    </div>
                </div>


            </div><!--/panel content-->

        </div>

    </div>
</div>
%{--End of forgot password row---}%

%{--row for synch with AD row--}%
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="panel-title">
                        <a href="#" data-toggle="collapse" data-target="#SycPassword">
                            Synchronize with AD Password
                        </a>
                    </div>
                </div>
                <div id="SycPassword" class="panel-collapse collapse">
                    <div class="panel-body">

                        <div class="scollist">

                            <h4>Synchronize with AD password:</h4>
                            <dl class="dl-horizontal">
                                <dt>Requirements :</dt> <dd>Your need to know your AD user ID and password.</dd>
                                <dt>Link :</dt> <dd><a href="${createLink(controller:'endUserAccountManager',action:'synchronizeWithADPassword')}">Registration</a></dd>
                            </dl>
                            <hr>
                        </div>
                    </div>


                </div><!--/panel content-->

            </div>

        </div>
    </div>
    %{--End of forgot password row---}%

</div>
%{--End of well--}%

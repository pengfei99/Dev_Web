<div class="well">
    %{--row for title--}%
    <div class="row">
        <div class="col-md-12">
            <H1>User Account Management</H1>
        </div>
    </div>
    %{--end of title row--}%

    <br>
%{--bump description row--}%

<div class="row">
    <div class="col-md-10">
          To facilitate the management of OPENLdap user account, BUMP offers two possible scenarios. If you are a BIOASTER employee, you can import your AD account into OPENLdap server.
          The account creation is automatic and immediate.
          If you are a collaborator of BIOASTER, you need to submit a registration request. You need to chose a validator who must be a BUMP validator and knows who you are.
          Your account will be created only after the validation of the BUMP validator which you have chosen. It may be refused if the validator consider that you do not have the right.
    </div>
</div>


    <br>
    %{--row for bump function--}%
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="panel-title">
                        <a href="#" data-toggle="collapse" data-target="#bioEmployee">
                          If you are a BIOASTER employee
                        </a>
                    </div>
                </div>
                <div id="bioEmployee" class="panel-collapse collapse">
                    <div class="panel-body">

                        <p> </p>
                        <div class="scollist">
                            <h4>Import AD account into OPENLdap:</h4>

                            <dl class="dl-horizontal">
                                <dt>Requirements :</dt> <dd>Your need to know your AD account Login and password</dd>
                                <dt>Link :</dt> <dd> <a href="${createLink(controller:'importADUser',action:'index')}">Import AD account</a></dd>
                            </dl>
                            <hr>


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
                    <a href="#" data-toggle="collapse" data-target="#bioCollabo">
                        If you are a BIOASTER collaborator
                    </a>
                </div>
            </div>
            <div id="bioCollabo" class="panel-collapse collapse">
                <div class="panel-body">

                    <div class="scollist">

                        <h4>Submit registration request:</h4>
                        <dl class="dl-horizontal">
                            <dt>Requirements :</dt> <dd>Your need to have an organizatinal email and know a BIOASTER validator</dd>
                            <dt>Link :</dt> <dd><a href="${createLink(controller:'bioasterUserRegistrationRequest',action:'index')}">Registration</a></dd>
                        </dl>
                        <hr>
                    </div>
                </div>


            </div><!--/panel content-->

        </div>

    </div>
</div>
%{--End of which service access---}%

</div>
%{--End of well--}%

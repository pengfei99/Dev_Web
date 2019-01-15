<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'bioasterUserRegistrationRequest.label', default: 'BioasterUserRegistrationRequest')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

        %{--Capcha css--}%
        <style>

    #licenseText{
        width: 900px;
        height: 500px;
        background-color: #FCFADD;
        font-size: 1em;
        /* margin-left:auto;
         margin-right:auto;
         width:70%;
         padding: 0.2em 0.4em;*/
        border:5px solid gray;
        overflow: scroll;
    }

    </style>
        %{--End of Capcha css--}%
    </head>
    <body>
    <div class="container">
        %{--Description row--}%
        <div class="row">
            <div class="col-md-10">
                <H3>
                    BIOASTER User Account data privacy agreement </H3>
            </div>
        </div>
        %{--End of Description row--}%


        <br>
        <br>

        <div class="row">

            <div class="col-md-8 col-md-offset-1">

                <div id="licenseText">
                    <p>
                        <span style="color:red">
                            Please read the following carefully to understand our views and practices regarding your
                            personal data and how we will treat it.
                            The data controller is BIOASTER, 40 Avenue Tony Garnier
                            69007 Lyon France
                        </span>
                        <br>
                        <br>
                        <span style="color:red">INFORMATION WE MAY COLLECT FROM YOU</span> <br>
                        We may collect and process the following data about you:
                        Information that you provide by filling in forms on our sites
                        If you contact us, we may keep a record of that correspondence.
                        We may also ask you to complete surveys that we use for research purposes, although you do not have to respond to them.
                        Details of transactions you carry out through our site.
                        Details of your visits to our site including, but not limited to, traffic data, location data, weblogs and other communication data
                        <br>
                        <br>
                        <span style="color:red">IP ADDRESSES</span>
                        <br>
                        We may collect information about your computer, including where available your IP address, operating system and browser type. This is statistical data about our users’ browsing actions and patterns, and does not identify any individual.
                        <br>
                        <br>

                        <span style="color:red"> COOKIES </span>
                        <br>
                        Our website uses cookies to distinguish you from other users of our website. This helps us to provide you with a good experience when you browse our website and also allows us to improve our site.
                        <br>
                        <br>
                        <span style="color:red"> WHERE WE STORE YOUR PERSONAL DATA </span>
                        <br>
                        All information you provide to us is stored on our secure servers. Where we have given you (or where you have chosen) a password which enables you to access certain parts of our site, you are responsible for keeping this password confidential. We ask you not to share a password with anyone.
                        Unfortunately, the transmission of information via the internet is not completely secure. Although we will do our best to protect your personal data, we cannot guarantee the security of your data transmitted to our site; any transmission is at your own risk. Once we have received your information, we will use strict procedures and security features to try to prevent unauthorised access.
                        <br>
                        <br>
                        <span style="color:red"> USES MADE OF THE INFORMATION
                        </span>
                        <br>
                        We use information held about you in the following ways:
                        To ensure that content from our site is presented in the most effective manner for you and for your computer.
                        To provide you with information or services that you request from us or which we feel may interest you, where you have consented to be contacted for such purposes.
                        To carry out our obligations arising from any contracts entered into between you and us.
                        To allow you to participate in interactive features of our service, when you choose to do so.
                        To notify you about changes to our service.
                        <br>
                        <br>
                        <span style="color:red"> DISCLOSURE OF YOUR INFORMATION </span>
                        <br>
                        We may disclose your personal information to any member of our group, which means our subsidiaries, our ultimate holding company and its subsidiaries.
                        <br>
                        <br>
                        <span style="color:red"> YOUR RIGHTS </span>
                        <br>
                        Our site may, from time to time, contain links to and from the websites of our partner networks, advertisers and affiliates.  If you follow a link to any of these websites, please note that these websites have their own privacy policies and that we do not accept any responsibility or liability for these policies.  Please check these policies before you submit any personal data to these websites.
                        <br>
                        <br>
                        <span style="color:red"> ACCESS TO INFORMATION </span>
                        <br>
                        You have right to access information held about you. Your right of access can be exercised in accordance with the Act. Any access request may be subject to a fee of 10 euros to meet our costs in providing you with details of the information we hold about you.
                        <br>
                        <br>
                        <span style="color:red"> CHANGES TO OUR PRIVACY POLICY </span>
                        <br>
                        Any changes we may make to our privacy policy in the future will be posted on this page and, where appropriate, notified to you by e-mail.
                        <br>
                        <br>
                        <span style="color:red"> CONTACT </span>
                        <br>
                        Questions, comments and requests regarding this privacy policy are welcomed and should be addressed to admin@bioaster.org
                    </p>

                </div>
                <br>
                <br>
            </div>
        </div>

        <div class="row">


                <form  action="bioasterRegistrationRequestCreate" method="get">

                    <div class="col-md-6 text-center">
                                <button type="submit">Agree</button>
                    </div>

                    <div class="col-md-6 text-center" >
                                <button type="button" onclick="alert('You have to agree with BIOASTER data privacy agreement to continue!')">Disagree</button>
                    </div>


                </form>










        </div>
    </body>
</html>
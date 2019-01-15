<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Bioaster user account manager</title>
</head>

<body>

<div class="container">

%{--Row for the error messages--}%
    <div class="row">
        <div class="col-md-10">
            <g:if test="${flash.message}">
                <div class="alert alert-info">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
                <div class="alert alert-danger">${flash.error}</div>
            </g:if>
        </div>
    </div>





    <div class="row">
        <h1>Import your Active Directory account to OpenLdap</h1>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>1.Why you need to have account in OpenLdap?</h3>

            If you need to access a Linux Virtual Machine in Cloud (e.g. VMWare or OpenLdap), you need to have an account in OpenLdap<br>

        </div>
    </div>
    <hr>

    %{--User login and password form --}%
    <div class="row">
        <div class="col-md-12">
            <h3>2.What this OpenLdap account can do for you?</h3>

            This OpenLdap account will assign you a unique ID and IDNumber, which is recognized by all Linux VM inside BIOASTER Cloud. But this does not mean you can <br>
            login to every VM. Because, You will also be assigned a list of groups which you belongs to. Based on these groups, VM will decide if you can access the VM or not.
        </div>
    </div>
    <hr>




    %{--User login and password form --}%

    <div class="row">
        <div class="col-md-12">
            <h3>3. How to import your AD account into OPENLdap server?</h3>
            Before you start, you need to be sure you have an account in AD. Then
            enter your login and password in the BIOASTER Active Directory, click on button import to create your OpenLdap account. If you need help, please email feedback and questions to <a href="mailto:pengfei.liu@bioaster.org">pengfei.liu@bioaster.org</a>

        </div>
    </div>
    <hr>

    %{--End of container--}%



    <div class="row">
        <div class="col-md-12">

            <g:form>
                <div class="row">

                    <div class="dialog">
                        <table class="table borderless">
                            <tbody>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="Uid">Uid:</label>
                                </td>
                                <td valign="top"><input type="text" id="uid" name="uid"/>
                                </td>
                            </tr>



                            <tr class="prop">
                                <td valign="top" class="name"><label for="UserPassword">UserPassword:</label>
                                </td>
                                <td valign="top"><input type="password" id="userPassword"
                                                        name="userPassword" /></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 text-center">

                        <fieldset class="buttons">

                            <g:actionSubmit class="save" action="importADEntry" value="Import"  />
                        </fieldset>
                    </div>
                </div>


            </g:form>
        </div>
    </div>



    </div>
%{--End of container--}%



</body>
</html>

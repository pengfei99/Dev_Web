<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 6/12/15
  Time: 1:49 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Transmart docker release</title>
</head>

<body>


<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<div id="bodyText">
    <h1>This page explains how to use the supplied docker file to generate a tranSMART ready to go container</h1>

    <br>
    <br>

   <h3>1 Purpose of this Dockerfile</h3>
    <br>

    Here is a Dockerfile with an emphasis on best practices and performance regarding to tranSMART ecosystem. Pull requests on the gitup are welcome.

    <br>
    <hr>
    <h3>2 Deployment</h3>
    This is an all-in-one tranSMART setup thus it should be used only for the test of eTRIKS platform. In general the database, R, and Tomcat should be split in order to achieve a good througtput.
  <br>
    <br>
    The amount of RAM is the critical part, at least 4GB should be assigned to the container.
    <br>

    <h3>3 Pre-requisites</h3>
    3.1 Minimum requirements :
    <br>
    <br>
    <ul>
    <li>2VCPU</li>
    <li>4GB</li>
    <li>10GB</li>
    </ul>

    3.2 Recommended requirements :
    <br>
    <br>
    <ul>
        <li>4VCPU</li>
        <li>8GB</li>
        <li>15GB</li>
    </ul>

    <br>
    This is a Dockerfile which needs a special compute host with Docker engine installed on it. To proceed to, you can follow few steps described on the official Docker website.
    <br>
    <br>
    <ul>
        <li>For Debian follow : <a href="https://docs.docker.com/installation/debian/">https://docs.docker.com/installation/debian/</a> </li>
        <li>For Ubuntu follow : <a href="https://docs.docker.com/installation/ubuntulinux/">https://docs.docker.com/installation/ubuntulinux/</a></li>
        <li>For CentOS follow : <a href="https://docs.docker.com/installation/centos/">https://docs.docker.com/installation/centos/</a></li>
    </ul>

    <hr>

    <h3>4 Build and run eTRIKS-tranSMART on your own docker container</h3>
    <br>
    <h4>4.1 Download source : <a href="https://owncloud.etriks.org/index.php/s/99X6ZEEdyZHTfaO/download">transmart-docker.zip</a></h4>
    <br>
    <h4>4.2 Clone source from GitHub: </h4>

    <PRE><CODE>
        git clone https://github.com/grumpycatt/transmart-docker.git
        cd transmart-docker
    </CODE></PRE>
<br>
     The transmart-docker folder should contain two files (i.e. Dockerfile and README.md) and a folder (i.e. includes).
<br>
    <br>
    <PRE><CODE>
    -rw-rw-r--.  1 pliu pliu 3.8K Jun 29 14:42 Dockerfile
    drwxrwxr-x.  2 pliu pliu 4.0K Jun 29 14:42 includes
    -rw-rw-r--.  1 pliu pliu 2.2K Jun 29 14:42 README.md
</CODE></PRE>
<br>

    <h4>4.3 Build and run the tranSMART dockerfile:</h4>
    <br>
    <PRE><CODE>
        docker build --rm -t myrepo/transmart .
        docker run -d -p 80:8080 5432:5432 8983:8983 --name transmart myrepo/transmart
    </CODE></PRE>

    <h4>4.4 Access tranSMART:</h4>
    <br>
    <PRE><CODE>
        Launch your favorite browser and go to http://transmartIP
    </CODE></PRE>


    <hr>

    <h3>5 Debug and core components</h3>

   5.1 Check running processes in your docker container :
    <br>
    <br>
    <PRE><CODE>
        docker ps
        docker exec transmart ps axuf
    </CODE></PRE>

    <br>

    There must be at least 4 processes:

    <ul>
    <li>PostgreSQL</li>
    <li>Java/Tomcat</li>
    <li>R</li>
    <li>SolR</li>
    </ul>
<br>
   5.2 Probe your network access :
    <br>
    <br>
    <PRE><CODE>
        docker exec transmart netstat -tlup
        nc localhost 80 -v
    </CODE></PRE>

    <br>
    5.3 Check the logs:

    <br>
    <br>
    <PRE><CODE>
        docker logs transmart
    </CODE></PRE>

    <br>
    5.3 Essential components used
    <br>
    <br>
    <ul>
    <li>PostgreSQL 9.3</li>
    <li>Tomcat7</li>
    <li>Oracle JDK 1.8u51</li>
    <li>tranSMART 1.2.x</li>
    <li>Groovy 2.4.4</li>
        <li>RRO 3.2.0</li>
        <li>Rserve 1.8.x</li>
    </ul>
<hr>
    <h3>6 Other transmart Docker deployments</h3>
    <br>
    There are few versions and perceptions of this setup, here is the list:

    <br>
    <br>
    <ul>
        <li>For Debian follow : <a href="https://github.com/quartzbio/transmart-docker">QuartzBio</a> </li>
        <li>For Ubuntu follow : <a href="https://github.com/io-informatics/transmart-docker">io-informatics</a></li>
    </ul>


</div>
</body>
</html>
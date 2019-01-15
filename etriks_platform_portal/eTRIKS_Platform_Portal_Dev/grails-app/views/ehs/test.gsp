<%--
  Created by IntelliJ IDEA.
  User: pliu
  Date: 10/27/15
  Time: 5:07 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>

<p id="demo">Let ajax chang this text</p>
First operator <input type="text" id="firstO" value=""><br>
Second operator <input type="text" id="secondO" value=""><br>
<button type="button" onclick="addition()">change me</button>

<script>
    function loadDoc(){
    var xhttp=new XMLHttpRequest();
xhttp.onreadystatechange=function(){
    if(xhttp.readyState==4&&xhttp.status==200){
    document.getElementById("demo").innerHTML=xhttp.responseText;
    }
    }
    xhttp.open("GET","../pdf/info.txt",true);
    xhttp.send();
    }
    function addition(){
        var a=document.getElementById("firstO").value;
        var b=document.getElementById("secondO").value;
        $.ajax({
            url:'${g.createLink( controller:'ehs', action:'addition', params:[a:a] )}'
        });

        %{--<g:remoteFunction controller="ehs" action="addition" update="demo" params="'a='+a,'b='+b"/>--}%
       /* document.getElementById("demo").innerHTML=a+b;
*/
    }
</script>

</body>
</html>
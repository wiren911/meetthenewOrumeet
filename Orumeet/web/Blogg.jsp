<%--
    Document   : Utbildning
    Created on : 2018-feb-13, 10:28:38
    Author     : user
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="stylesheet/style.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update blogg</title>
    </head>
    <body>
        <form id="myForm" action="insertBlogg" method="post" enctype="multipart/form-data">
            <label for="description"> Description:</label><textarea maxlength="500" rows="8" cols="75" name="txtdescription" id="txtdescription"></textarea><br><br>
                <br>
               
                <p><strong>Note:</strong> Lägg in bild här.</p>
                <br>
                <input type="file" name="photo" />
                <input type="submit" value="Post"/>
                <input type="reset" value="Clear" />                
        </form>
        <div id="content"><a href="listBloggs">Get</a></div>
    </body>
</html>



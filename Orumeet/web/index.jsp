<%-- 
    Document   : sendMail
    Created on : 2018-feb-01, 14:48:07
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="stylesheet/style.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email</title>
    </head>
    <body>
        <h1>Email</h1>
        <form method="POST" action="EmailServlet">
            <label for="to">TO:</label> <input id="to" name="to" type="text" /><br>
            <label for="subject">Subject:</label><input id="subject" name="subject" type="text" /><br>
            <textarea name="body" cols="60" rows="15"></textarea><br>
            <input type="submit" value="Send" />
            <input type="reset" value="Clear"/>
        </form>
    </body>
</html>

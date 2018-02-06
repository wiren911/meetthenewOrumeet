<%-- 
    Document   : sendMail
    Created on : 2018-feb-01, 14:48:07
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email</title>
    </head>
    <body>
        <h1>Email</h1>
        <form method="POST" action="EmailServlet">
            <label for="to">TO:</label> <input id="to" name="to" type="text" /><br>
            <label for="subject">Subject:</label>Subject:<input id="Subject" name="Subject" type="text" /><br>
            <textarea name="body" cols="60" rows="15"></textarea><br>
            <input type="submit" value="Send" />
            <input type="reset" value="Clear"/>
        </form>
    </body>
</html>

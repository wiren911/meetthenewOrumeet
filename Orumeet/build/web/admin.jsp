<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <link href="stylesheet/style.css" rel="stylesheet" type="text/css"/>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        <nav id="adminMenu">
            <a><img src="img/logotype305x220.png" id="logo" alt="no image was found"/></a>
            <ul class="top-navbar">
                <li><a class="active" href="#main">Home</a></li>
                
                
                <li><a href="#addTeacher">Add Teacher</a></li>
                <li><a class="" href="OruInfo.jsp">Oru HomePage</a></li>
                <li><a> <%=session.getAttribute("fornamn")%> <%=session.getAttribute("efternamn")%></a></li>
            </ul>
        </nav>
        
        <section id="main">
            <br><br><br><br>
            <div>
                <h1>Välkommen, administratör!</h1>
            </div>

        </section>
        <section id="addTeacher">
            <div class="inputTeacher">
                <h2>Add A New User, </h2>
            <form method="post" action="insertServlet" class="newTeacher">
                <label class="teipt">FirstName:</label><input type="text" name="txtname" required="required" class="inputFields"/><br><br>
                <label class="teipt">LastName:</label><input type="text" name="txtlast" class="inputFields"/><br><br>
                <label class="teipt">Email:</label><input type="text" name="txtemail" class="inputFields"/><br><br>
                <label class="teipt">Phone:</label><input type="text" name="txtphone" class="inputFields"/><br><br>
                <label class="teipt">Password:</label><input type="text" name="txtpassword" class="inputFields"/><br><br>
                
                <select name="role" >
                    <option value="-1">Choose role</option>
                    <option value="User">User</option>
                    <option value="Admin">Admin</option>
                </select>
                <br><br>
                <input type="submit" value="Add Data" />
                <input type="reset" value="Clear" />
            </form>
            </div>
        </section>
        <section id="oruinfo"></section>
    </body>
</html>

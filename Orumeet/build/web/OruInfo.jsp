<%-- 
    Document   : OruInfo
    Created on : 2018-feb-12, 15:05:00
    Author     : Anton Söderberg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="stylesheet/style.css" rel="stylesheet" type="text/css"/>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <nav id="menu">
            <a><img src="img/logotype305x220.png" id="logo" alt="no image was found"/></a>
            <ul class="top-navbar">
                <li><a class="active" href="#header">Home</a></li>
                <li><a href="UtbildningServlet">Utbildning</a></li>
                <li><a href="ForskningServlet">Forskning</a></li>
                <li><a href="#larare">Lärare</a></li>
                <li><a href="#mote">Möte</a></li>
                <li><a href="DisplayBloggServlet">Blogg</a></li>
                <li><a href="index.jsp">Send</a></li>
                <li><a> <%=session.getAttribute("fornamn")%> <%=session.getAttribute("efternamn")%></a></li>
                <!--<li><a href="secureUser/pageU.html">Login</a></li>-->
            </ul>
        </nav>
        
        <section id="header">
            <div >
                <div><h2>Informatik - intranät</h2></div>
                Välkommen till Örebro Universitets informatik sida för lärare och annan personal.<br><br>
                
            </div>

        </section>

        </section>
         <section id="mote">
            <div id="searchMeeting">
                <form  method="get" action="getServlet"> 
                    
                    <label for="möte"><b>För att se information om vilka möten som finns<br /></b></label>
                    <label for="valjDatum">Börja med att välja ett datum:</label><br><br>
                    <input type="date" id="valjDatum" name="valjDatum" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" />
                    <span class="validity"></span>
                    <input type="Submit" value="Se möten"/><br><br>
                </form>
            </div>
        <div id="addMeeting">    
            <form method="post" action="postServlet">                
                <label for="nyttMöte"><b>För att lägga till ett möte, fyll i nedan.</b></label><br/>
                <br><br/>
                Titel: <input type="text" name="txttitel" required="required" pattern=".{1,30}"/><br><br>
                Plats: <input type="text" name="txtplats" required="required" pattern=".{1,30}"/><br><br>
                Tid: <input id="appt-time" type="time" name="appt-time" step="2" value="08:00:00" required pattern="[00-23]-[00-59]-[00-59]" />
                <span class="validity"></span><br><br>
                Datum: <input type="date" id="ettDatum" name="ettDatum" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" /><br><br>

                <input type="submit" value="Lägg till möte" />
                <input type="reset" value="Rensa" />
            </form>
        </div>    
        </section>
        <section id="larare">
            <div class="searchTeacher">
                <div>
                    <h2>Här kan du hämta information om vald lärare</h2>
                </div>
                <form method="post" action="LarareServlet">
                    Förnamn: <input type="text" name="txtname" /><br><br>
                    Efternamn : <input type="text" name="txtlast" /><br><br>

                    <input type="submit" value="Hämta info" />
                    <input type="reset" value="Clear" />
                </form>
            </div>
        </section>

    </body>
</html>

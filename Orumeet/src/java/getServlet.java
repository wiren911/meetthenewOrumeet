/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
/**
 *
 * @author Anton Söderberg
 */
@WebServlet(urlPatterns = {"/getServlet"})
public class getServlet extends HttpServlet {
 
    Connection conn;
    Statement stmt;
    Statement stm;
    Statement st;
    Statement s;
    String datum;
    String dburl = "jdbc:mysql://localhost:3306/oru?zeroDateTimeBehavior=convertToNull";
    String Username = "root";
    String PassWord = "";  
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
               
                datum = request.getParameter("valjDatum");
            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(dburl, Username, PassWord);
                stmt = conn.createStatement();
                stm = conn.createStatement();
                st = conn.createStatement();
                s = conn.createStatement();
               
                //SQL-fråga för att jämföra datum från datepicker med databas
               String query = "select * from mote where datum ='"+datum+"'order by tid";
               String newquery = "select * from mote where datum ='"+datum+"'";
               ResultSet rs = stmt.executeQuery(query);
               ResultSet r = s.executeQuery(newquery);
               //Kontrollerar om det finns något möte under valt datum
                if(r.first() == false){
                    out.println("<body style=\"background-color:lightgrey\">");
                    out.println("<h2> Det finns inga möten under detta datum!</h2>");
                    out.println("</body>");
                    r.close();
                }else{
                    out.println("<h1> Här presenteras alla möten under valt datum</h1>");
                    }
                    out.println("<button onclick=goBack()>Gå Tillbaka");
                    out.println("</button>");
                    out.println("<script>");
                    out.println("function goBack() {");
                    out.println("window.history.back()");
                    out.println("}");
                    out.println("</script>");
                //Hämtar ut information från databasen
                while ( rs.next()) {
                    String titel = rs.getString("title");
                    String plats = rs.getString("plats");
                    String tid = rs.getString("tid");
                    String ettDatum = rs.getString("datum");
                    String id = rs.getString("id");
               
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Info om möte</title>");            
                    out.println("</head>");
                    out.println("<body style=\"background-color:lightgrey\">");
                    out.println("<h2>Datum: <Small>"+ettDatum+".</Small> Tid: <Small>"+tid+".</Small> <br>Titel: <Small>"+titel+".</Small> Plats: <Small>"+plats+".</Small></h2>");
 
                String aquery = "select * from larare join larare_mote on larare.id = larare join mote on mote.id = mote where mote.id ='"+id+"'";
                ResultSet rss = stm.executeQuery(aquery);
                ResultSet rt = st.executeQuery(aquery);
               
                //Kontrollerar med databasen
                    if(rt.first() == false){
                        out.println("<p style=\"color:red;\"> Inga personer är uppskrivna på mötet.</p>");
                    }
                    else{
                        out.println("<h3 style=\"color:grey;\">Personer som medverkar under detta möte är:</h3>");
                    }
                    while (rss.next()){
                   
                String fornamn = rss.getString("firstname");
                String efternamn = rss.getString("lastname");
                    out.println("<ul style=\"color:red;\"><li>"+fornamn+" "+efternamn+"</li></ul>");
                    }
                    out.println("</body>");
                    out.println("</html>");
           
                }} catch (Exception e){
                e.printStackTrace();
            }
    }
 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
       
    }
 
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
 
}
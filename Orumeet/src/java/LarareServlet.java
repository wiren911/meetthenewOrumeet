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
 * @author sofiehamilton
 */

@WebServlet(urlPatterns = {"/LarareServlet"})
public class LarareServlet extends HttpServlet {
    Connection conn;
    Statement stmt;
    String firstname;
    String lastname;
    String email;
    int phone;
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
            firstname = request.getParameter("txtname");
            lastname = request.getParameter("txtlast");
           
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(dburl, Username, PassWord);
                stmt = conn.createStatement();
               
    // Execute SQL query
         String query = "SELECT firstname, lastname, email, phone FROM larare where FIRSTNAME = '" + firstname + "' and LASTNAME = '" + lastname + "'";
         ResultSet rs = stmt.executeQuery(query);
         
         
         // Extract data from result set
           if(rs.next()){
            //Retrieve by column name
            String first = rs.getString("firstname");
            String last = rs.getString("lastname");
            String emmail = rs.getString("email");
            String phonne = rs.getString("phone");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<body style=\"background-color:lightgrey\">");
            out.println("<title>Info om lärare</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Info om lärare</h1>");
            out.println("Förnamn: " + first + "<br>");
            out.println("Efternamn: " + last + "<br>");
            out.println("Email: " + emmail + "<br>");
            out.println("Telefonnummer: " + phonne + "<br>");
            out.println("</body>");
            out.println("</html>");
            out.println("<button onclick=goBack()>Gå Tillbaka");
            out.println("</button>");
            out.println("<script>");
            out.println("function goBack() {");
            out.println("window.history.back()");
            out.println("}");
            out.println("</script>");

          }
           else {
           out.println("<!DOCTYPE html>");
           out.println("<html>");
           out.println("<head>");
           out.println("<body style=\"background-color:lightgrey\">");           
           out.println("</head>");
           out.println("<body>");
           out.println("Läraren finns inte, försök igen");
           out.println("</body>");
           out.println("</html>");
           out.println("<br><button onclick=goBack()>Gå Tillbaka");
           out.println("</button>");
           out.println("<script>");
           out.println("function goBack() {");
           out.println("window.history.back()");
           out.println("}");
           out.println("</script>");
           return;
            }
         
            }
            catch (Exception e){
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
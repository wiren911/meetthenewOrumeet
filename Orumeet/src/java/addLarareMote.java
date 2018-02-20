/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.Instant;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anton Söderberg
 */
@WebServlet(urlPatterns = {"/addLarareMote"})
public class addLarareMote extends HttpServlet {
Connection conn;
    Statement stmt, stm;
    String dburl = "jdbc:mysql://localhost:3306/oru?zeroDateTimeBehavior=convertToNull";
    String Username = "root";
    String PassWord = "";  
     String mail;
     String id;
     String datumet;
     String titeln;
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addLarareMote</title>");            
            out.println("</head>");
            out.println("<body style=\"background-color:lightgrey\">");
            out.println("<h1>Välj ett möte i listan</h1>");
            conn = DriverManager.getConnection(dburl, Username, PassWord);
                
                stm = conn.createStatement();
                stmt = conn.createStatement();
               HttpSession session = request.getSession(true);
//                mail = (String) session.getAttribute("name");
//                String query = "select * from larare where email ='"+mail+"'";
//                ResultSet rs = stm.executeQuery(query);
                datumet = (String) session.getAttribute("ettDatum");
                String aquery = "select * from mote where datum = '"+datumet+"'";
                ResultSet rss = stmt.executeQuery(aquery);
                out.println("<select id=\"val\">");
                while(rss.next()){
                     titeln = rss.getString("title");
                    out.println("<option value=>"+titeln+"</option>");
                }
                
                out.println("</select>");
                
                out.println("<input type=\"submit\" value=\"anmäl dig\">");
                
               
                
                
//                while(rs.next()){
//                id = rs.getString("id");
//                }
//               String aquery = "insert into larare_mote values ('"+id+"', '"+moteId+"')"; 
//               stmt.execute(aquery);
            
            
            
            out.println("</body>");
            out.println("</html>");
            
        }catch(Exception e){
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

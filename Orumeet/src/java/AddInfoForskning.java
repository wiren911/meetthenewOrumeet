/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@WebServlet(urlPatterns = {"/AddInfoForskning"})
public class AddInfoForskning extends HttpServlet {
    Connection conn;
    Statement stmt, stm;
    String dburl = "jdbc:mysql://localhost:3306/oru?zeroDateTimeBehavior=convertToNull";
    String Username = "root";
    String PassWord = "";  
    String info;
    String mail;
    String id;
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
           
            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(dburl, Username, PassWord);
                
                stm = conn.createStatement();
                
                HttpSession session = request.getSession(true);
                mail = (String) session.getAttribute("name");
                String aquery = "select * from larare where email ='"+mail+"'";
                ResultSet rs = stm.executeQuery(aquery);
                while(rs.next()){
                id = rs.getString("id");
                
                info = request.getParameter("onecomment");
                stmt = conn.createStatement();
                Date date = new Date(System.currentTimeMillis());
                Time time = new Time(System.currentTimeMillis());
                String query ="insert into forskning (description, larare, datum, tid) values ('"+info+"', '"+id+"', '"+date+"', '"+time+"')";
                stmt.execute(query);
                }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Servlet AddInfoForskning</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Grattis! Du har lagt till info.</h3>");
            out.println("</body>");
            out.println("</html>");
            RequestDispatcher rd = request.getRequestDispatcher("ForskningServlet");
                rd.include(request, response);
                
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

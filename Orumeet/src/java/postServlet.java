/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Time;
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
@WebServlet(urlPatterns = {"/postServlet"})
public class postServlet extends HttpServlet {
    Connection conn;
    Statement stmt;
    String dburl = "jdbc:mysql://localhost:3306/oru?zeroDateTimeBehavior=convertToNull";
    String Username = "root";
    String PassWord = "";
    String titel;
    String plats;
    String tid;
    String datumet;
    
    
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
            
            titel = request.getParameter("txttitel");
            plats = request.getParameter("txtplats");
            tid = request.getParameter("appt-time");
            datumet = request.getParameter("ettDatum");
   
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dburl, Username, PassWord);
            stmt = conn.createStatement();
                String query = "insert into mote(title, plats, datum, tid) values('"+titel+"', '"+plats+"', '"+datumet+"', '"+tid+"')";
                stmt.execute(query);
                
                    out.println("<button onclick=goBack()>Gå Tillbaka");
                    out.println("</button>");
                    out.println("<script>");
                    out.println("function goBack() {");
                    out.println("window.history.back()");
                    out.println("}");
                    out.println("</script>");
//                    RequestDispatcher rd = request.getRequestDispatcher("index.html#mote");
//                      rd.include(request, response);
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet postServlet</title>");            
                        out.println("</head>");
                        out.println("<body style=\"background-color:lightgrey\">");
                        out.println("<h1>Du har lagt in följande möte:<br><br> Titel: <Small>"+titel+"</Small><br> Plats: <Small>"+plats+"</Small><br> Datum: <Small>"+datumet+"</Small><br> Tid: <Small>"+tid+"</Small></h1>");
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

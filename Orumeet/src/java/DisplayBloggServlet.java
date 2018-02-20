/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * @author user
 */
@WebServlet(urlPatterns = {"/DisplayBloggServlet"})
public class DisplayBloggServlet extends HttpServlet {
 
    Connection conn;
    Statement stmt;
    String description;
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
            /* TODO output your page here. You may use following sample code. */        
            RequestDispatcher rd = request.getRequestDispatcher("Blogg.jsp");
            rd.include(request, response);
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
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dburl, Username, PassWord);
            PreparedStatement pss = conn.prepareStatement("select * from blogg where description=?");
            String desc = request.getParameter("txtdescription");
            pss.setString(1,desc );
            ResultSet rss = pss.executeQuery();
            rss.next();
            Blob b = rss.getBlob("photo");
            rss.getString("description");
            response.setContentType("image/jpeg");
            response.setContentLength((int) b.length());
            InputStream is = b.getBinaryStream();
            OutputStream os = response.getOutputStream();
            byte buf[] = new byte[(int) b.length()];
            is.read(buf);
            os.write(buf);
            os.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
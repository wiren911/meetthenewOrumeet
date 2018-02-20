/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
/**
 *
 * @author Anton Söderberg
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(urlPatterns = {"/insertBlogg"})
public class insertBlogg extends HttpServlet {
 
    Connection conn;
    Statement stmt;
    String description;
    String dburl = "jdbc:mysql://localhost:3306/oru?zeroDateTimeBehavior=convertToNull";
    String Username = "root";
    String PassWord = "";
   
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
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
 
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);      
    }
 
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        description = request.getParameter("txtdescription");
        Part filePart =  request.getPart("photo");
        InputStream inputStream = null;
        if(filePart != null){
             System.out.println(filePart.getName());
             System.out.println(filePart.getSize());
             System.out.println(filePart.getContentType());            
             inputStream = filePart.getInputStream();
        }        
        try {
            // Connect to Db
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dburl, Username, PassWord);
            conn.setAutoCommit(false);
 
            PreparedStatement ps = conn.prepareStatement("insert into blogg (description, photo) values(?, ?)");
            ps.setString(1, description);
            if(inputStream != null){
                ps.setBlob(2, inputStream);
            }
            int row = ps.executeUpdate();
            if(row > 0){
                out.println("File uploaded and saved to db!");
            }
            conn.commit();
            conn.close();
            out.println("Blogg Successfully Added.<div><a href='listBloggs'></a>");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {            
            out.close();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
 
}

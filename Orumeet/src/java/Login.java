/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
 
 
/**
 *
 * @author sofiehamilton
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class Login extends HttpServlet {
 
 
    Connection conn;
    Statement stmt, stmmt, st;
    String password, role;
    String email = new String();  
    String dburl = "jdbc:mysql://localhost:3306/oru?zeroDateTimeBehavior=convertToNull";
    String Username = "root";
    String PassWord = "";
    String fornamn;
    String mail;
    String efternamn;
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
            
            
        email = request.getParameter("txtusername");
            password = request.getParameter("txtpassword");
            //role = request.getAttribute("role").toString();
 
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(dburl, Username, PassWord);
    stmt = conn.createStatement();
    stmmt = conn.createStatement();
    st = conn.createStatement();
//    PreparedStatement ps = conn.prepareStatement( "SELECT role FROM LARARE where EMAIL = '" + email + "' and PASSWORD = '" + password + "'");
    String uquery = "SELECT * FROM LARARE where EMAIL = '" + email + "' and PASSWORD = '" + password + "' and role = 'User'";
    String query = "SELECT * FROM LARARE where EMAIL = '" + email + "' and PASSWORD = '" + password + "' and role = 'Admin'";
    String aquery = "SELECT * FROM LARARE where EMAIL = '" + email + "' and PASSWORD = '" + password + "'";
    ResultSet rs = stmmt.executeQuery(query);
    ResultSet r = st.executeQuery(aquery);
    ResultSet rss = stmt.executeQuery(uquery);
    while (r.next()){
        fornamn = r.getString("firstname");
        efternamn = r.getString("lastname");
    }
//    fornamn = rss.getString("firstname");
//    mail = rss.getString("email");
//    String efternamn = rss.getString("lastname");

//    role = rs.getString(role);
    
   
//    if(Role == "Admin"){
//        response.sendRedirect("admin.html");
//    }
//    else if(Role == "User"){
//        response.sendRedirect("oruinfo.html");
//    }
//    if(role.equals("User")){
//        response.sendRedirect("oruinfo.html");
//    }
    if(rss.next()){
        System.out.println("Login successful");
//        ServletContext context=getServletContext(); 
//        String driverName=context.getInitParameter(email);
//        out.println("driver name is="+driverName);  
//  
//        out.close();
//             mail = rss.getString("email");
//        out.print(mail);
        HttpSession session = request.getSession(true);
        request.getSession().setAttribute("fornamn", fornamn);
        request.getSession().setAttribute("efternamn", efternamn);
        request.getSession().setAttribute("name", email);
        response.sendRedirect("OruInfo.jsp");
        

    }
    else if (rs.next()){
        System.out.println("Login successful");
        HttpSession session = request.getSession(true);
        request.getSession().setAttribute("fornamn", fornamn);
        request.getSession().setAttribute("efternamn", efternamn);
        request.getSession().setAttribute("name", email);
        response.sendRedirect("admin.jsp");        
    }
    else {
        out.println("Fel användarnamn eller lösenord, försök igen");
        RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.include(request, response);   
    } 
    }
    catch (Exception ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
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
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
import javax.servlet.http.HttpSession;
 
/**
 *
 * @author Anton Söderberg
 */
@WebServlet(urlPatterns = {"/ForskningServlet"})
public class ForskningServlet extends HttpServlet {
    Connection conn;
    Statement stmt;
    Statement stm;
    Statement st;
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
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><link href=\"stylesheet/style.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<link href=\"stylesheet/style.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<title>Servlet ForskningServlet</title>");            
            out.println("</head>");
            out.println("<body>");

            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(dburl, Username, PassWord);
                stmt = conn.createStatement();
                st= conn.createStatement();
                
                out.println("<button onclick=goBack()>Ga Tillbaka");
                    out.println("</button>");
                    out.println("<script>");
                    out.println("function goBack() {");
                    out.println("window.history.back()");
                    out.println("}");
                    out.println("</script><br>");
                    
                    HttpSession session = request.getSession(true);
                    out.println("Du ar inloggad som: ");
                    out.println(session.getAttribute("fornamn"));
                    out.println(session.getAttribute("efternamn")); 
                    
                    out.println("<h1>Valkommen till forumet for Forskning</h1>");

                String query = "Select * from forskning order by id desc";
                ResultSet rs = stmt.executeQuery(query);
            
                RequestDispatcher rd = request.getRequestDispatcher("Forskning.html");
                rd.include(request, response);
                while(rs.next()){
                    String beskrivning = rs.getString("description");
                    String id = rs.getString("id");
                    String datum = rs.getString("datum");
                    String tid = rs.getString("tid");
 
            
            out.println("<h4>Inlagg: </h4><p>" + beskrivning + "</p>");
            out.println("<h4>Datum: "+datum+" Tid: "+tid+"");
            stm = conn.createStatement();
            String aquery = "Select firstname, lastname from larare join forskning on larare.id = larare where forskning.id = '"+id+"' ";
            ResultSet rset = stm.executeQuery(aquery);
                while(rset.next()){
                    String fornamn = rset.getString("firstname");
                    String efternamn = rset.getString("lastname");
                                out.println("<h4>Forfattare till inlagget: </h4><p>" + fornamn + " "+efternamn+"</p>");
                                out.println("--------------------------------------------------------------");
                }}
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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class insertServlet extends HttpServlet {
    Connection conn;
    Statement stmt;
    String first, last, email, password;
    int phone;
    String dburl = "jdbc:mysql://localhost:3306/oru?zeroDateTimeBehavior=convertToNull";
    String Username = "root";
    String PassWord = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            first = request.getParameter("txtname");
            last = request.getParameter("txtlast");
            email = request.getParameter("txtemail");
            phone = Integer.parseInt(request.getParameter("txtphone"));
            password = request.getParameter("txtpassword");
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(dburl, Username, PassWord);
                stmt = conn.createStatement();
                String query = "insert into larare(firstname, lastname, email, phone, password) values('"+first+"', '"+last+"', '"+email+"', '"+phone+"', '"+password+"');";
                stmt.execute(query);
                System.out.println("Data was inserted");
                
                response.sendRedirect("oruinfo.html");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

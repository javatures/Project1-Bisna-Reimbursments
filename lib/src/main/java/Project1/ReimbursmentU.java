package Project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Reimbu")

public class ReimbursmentU extends HttpServlet{
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();


        String Rstatus="Approved";

        String url = "jdbc:postgresql://localhost:5432/ndemo";
        String username = "ndemo";
        String pass = "npass";

        try {

            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement pStatement = connection.prepareStatement(" update reimbursment set r_status =? where r_id=?");
    
            pStatement.setString(1,Rstatus);
            
            int i = pStatement.executeUpdate();
            out.println("Request Approved ");
            
    
            
        } catch (Exception e) {
            //TODO: handle exception
        }
       



    }
}

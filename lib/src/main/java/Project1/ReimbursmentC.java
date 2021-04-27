package Project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Reimbc")

public class ReimbursmentC extends HttpServlet{
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       

        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        int supid = (int)session.getAttribute("supid");
        int empid = (int)session.getAttribute("empid");
        System.out.println("supid from reim"+supid);
        System.out.println("empid from reim"+empid);


        
        String Ramount=req.getParameter("ramount");
        Float Ramt = Float.parseFloat(Ramount);
        String Rstatus="Submitted";
        String Rremarks=req.getParameter("rremarks");
        String Rimage ="/upload/image123";
       
   

        System.out.println("ramount from reimn"+ Ramt);
        System.out.println("rstatus from reimbu"+Rremarks);

        RequestDispatcher rd1 = req.getRequestDispatcher("/Dashboard.html");
        rd1.include(req, resp);


        String url = "jdbc:postgresql://localhost:5432/ndemo";
        String username = "ndemo";
        String pass = "npass";

        try {

            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO reimbursment(r_amt,r_status,r_remarks,r_image,emp_id_fk,sup_id_fk) values (?,?,?,?,?,?)");
            System.out.println("from inside the reimbursment insert");
            
            pStatement.setFloat(1,Ramt);
            pStatement.setString(2,Rstatus);
            pStatement.setString(3,Rremarks);
            pStatement.setString(4,Rimage);
            pStatement.setInt(5,empid);
            pStatement.setInt(6,supid);
            
        
            
            pStatement.executeUpdate();
           
            
    
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        out.println("Request submitted ");


    }
}

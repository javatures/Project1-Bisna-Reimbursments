package Project1;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Home")
public class Home extends HttpServlet{
    // This class confirms if the user exist
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user, password;
        PrintWriter out = resp.getWriter();
        boolean empst = false;
        int supid = 0;
        // rflag to check if the employee is a supervisor, its revers from login servler logic
        boolean rflag = false;
        // yflag to check if employee is in the database
        boolean yflag = false;
        int empid = 0;
        String uname = "";

        System.out.println("Username is at Home " + req.getParameter("uname"));
        System.out.println("password is at Home " + req.getParameter("pwd"));

        if ((user = req.getParameter("uname")) != null && ((password = req.getParameter("pwd")) != null)) {

            HttpSession session = req.getSession();
            System.out.println("the value of user in Home" + user);
            session.setAttribute("user", user);
            session.setAttribute("password", password);
      

            String url = "jdbc:postgresql://localhost:5432/ndemo";
            String username = "ndemo";
            String pass = "npass";

            try {

                Connection connection = DriverManager.getConnection(url, username, pass);
                // cehcking if supervisor
                PreparedStatement pStatementS = connection.prepareStatement("select * from supervisors where sup_fn=?");
                String supfn = user;
                pStatementS.setString(1, supfn);
                ResultSet sSet = pStatementS.executeQuery();

                while (sSet.next()) {
                    supid = sSet.getInt("sup_id");
                    System.out.println("supervisor id from while loop" + supid);

                

                }

                System.out.println("supervisor id out of while loop" + supid);

                System.out.println("reporting flag before if" + rflag);
                // checking for employee if r flag is true

                

                    PreparedStatement pStatement = connection
                            .prepareStatement("select * from employees where emp_fn=?");
                    String empfn = user;

                    pStatement.setString(1, empfn);
                    ResultSet rSet = pStatement.executeQuery();

                    System.out.println("the value of yflag before while" + yflag);

                    while (rSet.next()) {
                        System.out.println("from inside the reimb loop");

                        empid = rSet.getInt("emp_id");
                        supid = rSet.getInt("sup_id_fk");
                        if (empid != 0) {
                            

                            session.setAttribute("empid", empid);
                            session.setAttribute("supid", supid);

                            RequestDispatcher rd = req.getRequestDispatcher("/Home.html");
                            rd.forward(req, resp);



                        }else 
                        
                        
                        
                        {

                            
                            System.out.println("inside the invalid user");
                            out.print("Invalid User name entered should be an employee");
                            RequestDispatcher rd1 = req.getRequestDispatcher("/loginerror.html");
                            rd1.forward(req, resp);
    
                        }

                    }

        
                
            
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }else{

            System.out.println("inside the invalid user");
                            out.print("Enter username and password");
                            RequestDispatcher rd = req.getRequestDispatcher("/loginerror.html");
                            rd.forward(req, resp);
    
        }

   }


}



        

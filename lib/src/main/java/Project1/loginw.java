package Project1;


    

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/Loginw")
public class loginw extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user, password;
        PrintWriter out = resp.getWriter();
        boolean empst = false;
        int supid = 0;
        // rflag to check if the employee is a supervisor
        boolean rflag = true;
        // yflag to check if employee is in the database
        boolean yflag = false;
        int empid = 0;
        String uname = "";

        System.out.println("Username is " + req.getParameter("uname"));
        System.out.println("password is " + req.getParameter("pwd"));

        HttpSession session = req.getSession();

   //     if ((user = (String)session.getAttribute("user")) != null && ((password = (String)session.getAttribute("password")) != null)) {
            if ((user = req.getParameter("uname")) != null && ((password = req.getParameter("pwd")) != null)) {
        

            System.out.println("the value of user after getting session" + user);
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

                    rflag = false;

                }

                System.out.println("supervisor id out of while loop" + supid);

                System.out.println("reporting flag before if" + rflag);
                // checking for employee if r flag is true

                if (rflag) {

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
                            yflag = true;

                            session.setAttribute("empid", empid);
                            session.setAttribute("supid", supid);

                        }

                    }

 //Display the navigation bar before the table                 
                     // RequestDispatcher rd1 = req.getRequestDispatcher("/Dashboard.html");
                     // rd1.include(req, resp);

                    
                    // after confirming the employee exist
                    if (yflag) {
                        PreparedStatement pStatementR = connection.prepareStatement(
                                "select * from reimbursment inner join employees on( reimbursment.emp_id_fk= ?)");
                        pStatementR.setInt(1, empid);
                        List<Reimbursment> reimball = new ArrayList<>();
                        ResultSet rSetR = pStatementR.executeQuery();
                       resp.setContentType("text/html");
                        out.print("<html><body>");

                        out.print("<table border=3 width=20% height=20%>");
                        out.print("<tr><th>Rimage</th><th>Rremarks</th><th>Rstatus</th><tr>");
                        while (rSetR.next()) {
                            Reimbursment temp = new Reimbursment(rSetR.getString("r_image"),
                                    rSetR.getString("r_remarks"), rSetR.getString("r_status"));

                            out.print("<tr><td>" + rSetR.getString("r_image") + "</td><td>"
                                    + rSetR.getString("r_remarks") + "</td><td>"

                                    + rSetR.getString("r_status") + "</td></tr>");

                            reimball.add(temp);

                        }
                        out.print("</table>");
                        out.print("</body>");
                        out.print("</html>");

                     // resp.setContentType("application/json");
                        resp.getWriter().write(new Gson().toJson(reimball));

                     RequestDispatcher rd = req.getRequestDispatcher("/Dashboard.html");
                     rd.include(req, resp);

                    } else {

                        System.out.println("inside the invalid user");
                        out.print("Invalid User name entered");
                        RequestDispatcher rd = req.getRequestDispatcher("/loginerror.html");
                        rd.forward(req, resp);

                    }

                    System.out.println("supervisor id before else" + supid +yflag) ;

                } else {

                    System.out.println("he/she is a supervisor");
                    System.out.println("supervisor id in elseloop" + supid);

                    session.setAttribute("supid", supid);

                    RequestDispatcher rds = req.getRequestDispatcher("Slogin");
                    rds.forward(req, resp);

                }

                System.out.println("supervisor id after else" + supid +yflag) ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("supervisor id the last" + supid +yflag) ;
        }

    }

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null) {

            int empid=0;
            int supid=0;
            PrintWriter out = resp.getWriter();

            String user = (String) req.getAttribute("user");
            String password = (String) req.getAttribute("password");



            String url = "jdbc:postgresql://localhost:5432/ndemo";
            String username = "ndemo";
            String pass = "npass";

            try {

                Connection connection = DriverManager.getConnection(url, username, pass);
                    PreparedStatement pStatement = connection.prepareStatement("select * from employees where emp_fn=?");
                    String empfn = user;
                    pStatement.setString(1, empfn);
                    ResultSet rSet = pStatement.executeQuery();

                    while (rSet.next()) {
                        System.out.println("from inside the reimb loop");

                        empid = rSet.getInt("emp_id");
                        supid = rSet.getInt("sup_id_fk");

                    }


                    // after confirming the employee exist
                        PreparedStatement pStatementR = connection.prepareStatement(
                                "select * from reimbursment inner join employees on( reimbursment.emp_id_fk= ?)");
                        pStatementR.setInt(1, empid);
                        List<Reimbursment> reimball = new ArrayList<>();
                        ResultSet rSetR = pStatementR.executeQuery();
                        resp.setContentType("text/html");
                        out.print("<html><body>");

                        out.print("<table border=1 width=50% height=50%>");
                        out.print("<tr><th>Rimage</th><th>Rremarks</th><th>Rstatus</th><tr>");
                        while (rSetR.next()) {
                            Reimbursment temp = new Reimbursment(rSetR.getString("r_image"),
                                    rSetR.getString("r_remarks"), rSetR.getString("r_status"));

                            out.print("<tr><td>" + rSetR.getString("r_image") + "</td><td>"
                                    + rSetR.getString("r_remarks") + "</td><td>"

                                    + rSetR.getString("r_status") + "</td></tr>");

                            reimball.add(temp);

                        }
                        out.print("</table>");
                        out.print("</body>");
                        out.print("</html>");

                  //      resp.setContentType("application/json");
                        resp.getWriter().write(new Gson().toJson(reimball));

                        // RequestDispatcher rd = req.getRequestDispatcher("/Dashboard.html");
                        // rd.forward(req, resp);

                    
                    
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
        

            } else

            {
                resp.getWriter().println("Please login");
    
            }
    

        

        }

}


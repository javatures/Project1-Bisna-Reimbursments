package Project1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet("/Elogin")
public class Employeelogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "jdbc:postgresql://localhost:5432/ndemo";
        String username = "ndemo";
        String pass = "npass";
        HttpSession session = req.getSession();
        int supid = (int)session.getAttribute("supid");
        String user = (String)session.getAttribute("user");
        resp.getWriter().println("hello from employee login"+user);
        try {
            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement pStatement = connection.prepareStatement("select * from employees where emp_fn=?");
            String empfn = user;

            pStatement.setString(1, empfn);
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                int empid = rSet.getInt("emp_id");
                System.out.println("employeed id after the  " + empid);
            
            

                PreparedStatement pStatementR = connection.prepareStatement(
                        "select * from reimbursment inner join employees on( reimbursment.emp_id_fk= ?)");

                pStatementR.setInt(1, empid);
                List<Reimbursment> reimball = new ArrayList<>();
                ResultSet rSetR = pStatementR.executeQuery();
                while (rSetR.next()) {

                    Reimbursment temp = new Reimbursment(rSetR.getString("r_image"), rSetR.getString("r_remarks"),
                            rSetR.getString("r_status")

                    );

                    System.out.println("reimbursment remar" + rSetR.getString("r_remarks"));
                    System.out.println("reimbursment image" + rSetR.getString("r_image"));

                    reimball.add(temp);

                }

                resp.setContentType("application/json");
                resp.getWriter().write(new Gson().toJson(reimball));

 //             RequestDispatcher rd = req.getRequestDispatcher("/Employeereim.html");
  //            rd.forward(req, resp);
      

            }

        } catch (Exception e) {

        }

    }
}

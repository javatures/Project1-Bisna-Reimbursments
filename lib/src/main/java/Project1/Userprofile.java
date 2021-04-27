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

@WebServlet("/userprofile")
public class Userprofile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "jdbc:postgresql://localhost:5432/ndemo";
        String username = "ndemo";
        String pass = "npass";
        HttpSession session = req.getSession();
        int supid = (int) session.getAttribute("supid");
        String user = (String) session.getAttribute("user");
        resp.getWriter().println("hello from employee login" + user);
        try {
            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement pStatement = connection.prepareStatement("select * from employees where emp_fn=?");
            String empfn = user;
            List<Employees> employeel = new ArrayList<>();

            pStatement.setString(1, empfn);
            ResultSet eSet = pStatement.executeQuery();
            while (eSet.next()) {
                int empid = eSet.getInt("emp_id");
                System.out.println("employeed id after the  " + empid);

                Employees temp = new Employees(eSet.getString("emp_fn"), eSet.getString("emp_ln"),
                        eSet.getString("emp_dep")

                );

                System.out.println("Employee fn" + eSet.getString("emp_fn"));
                System.out.println("Employee dep" + eSet.getString("emp_dep"));

                employeel.add(temp);

            }

            // resp.setContentType("text/html");
            resp.getWriter().write(new Gson().toJson(employeel));

 //           RequestDispatcher rd = req.getRequestDispatcher("/Employeereim.html");
 //           rd.forward(req, resp);

        } catch (Exception e) {

        }

    }

}

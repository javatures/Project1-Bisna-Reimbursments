package Project1;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/Slogin")
public class Supervisorlogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "jdbc:postgresql://localhost:5432/ndemo";
        String username = "ndemo";
        String pass = "npass";

        HttpSession session = req.getSession();
        int supid = (int)session.getAttribute("supid");
        String user = (String)session.getAttribute("user");

        PrintWriter out = resp.getWriter();
      

        try {

            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement pStatement = connection.prepareStatement("select * from reimbursment where sup_id_fk=?");
            int supfn = supid;
            
            pStatement.setInt(1, supfn);
            ResultSet sSet = pStatement.executeQuery();

            List<Reimbursment> reimburs = new ArrayList<>();
            resp.setContentType("text/html");
            out.print("<html><body>");
            out.print ("<div>" + "Welcome"+ user + "</div>");

            out.print("<table border=3 width=20% height=20%>");
            out.print("<tr><th>Rimage</th><th>Rremarks</th><th>Rstatus</th><tr>");


            while (sSet.next()) {

                Reimbursment temp = new Reimbursment(sSet.getString("r_image"), sSet.getString("r_remarks"),
                        sSet.getString("r_status"));

                        out.print("<tr><td>" + sSet.getString("r_image") + "</td><td>"
                        + sSet.getString("r_remarks") + "</td><td>"

                        + sSet.getString("r_status") + "</td></tr>");


                        

                


                reimburs.add(temp);

            }

            out.print("</table>");
            out.print("</body>");
            out.print("</html>");


 //           resp.setContentType("text/html");
 //           resp.getWriter().write(new Gson().toJson(reimburs));
           RequestDispatcher rd = req.getRequestDispatcher("/Supervisor.html");
           rd.forward(req, resp);

        }

        catch (Exception e) {
            // TODO: handle exception
        }

    }

}

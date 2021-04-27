package Project1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")

public class Logout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/logout.html");
        rd.include(req, resp);
        HttpSession session = req.getSession();
        session.invalidate();
        




    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/index.html");
        rd.include(req, resp);
        




    }
    
}

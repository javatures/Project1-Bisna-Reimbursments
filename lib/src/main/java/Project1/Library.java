package Project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/hello")
public class Library extends HttpServlet{

@Override
protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {

    HttpSession session = req.getSession();
    String user = (String) req.getAttribute("uname");
   /*  if (session !=null){

        if (user !=null && user.equals("admin")){

   //      resp.getWriter().println("Welcome admin");
     
        }else
     
  //      {resp.getWriter().println("hello from helloservlet");}
        
    }*/
    
    Supervisordao supdao = new Supervisordao();
    
    List<Supervisor> suplist;

    try {
        suplist = supdao.getAll();

        //Issue : the server is sending text/html data even though the headerare made toapplication/json and fetch fails at resp.json
        // when accessing the url everthing works its giving json data , but when it reaches fetch it is changin to html !
// Tested with Jackson did not work

      /*   ObjectMapper mapper = new ObjectMapper();
         String jsonString = mapper.writeValueAsString(suplist);
        resp.setContentType("application/json");
        resp.getWriter().print(jsonString) */;
  
  //-------------------------------------
  
  //tried with Gson did not work
     
     //   resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
   //       out.print((new Gson().toJson(suplist)));
    //       resp.getWriter().println((new Gson().toJson(suplist)));

    //need the .write function for the fetch to work
  //         resp.getWriter().write((new Gson().toJson(suplist)));
           out.print((new Gson().toJson(suplist)));
        
     
    // -- to trim the [] bracket off the json array - wrong solution       

   //     String temp = new Gson().toJson(suplist);
   //     String newStr = temp.substring(1, temp.length()-1);
   //     resp.getWriter().write(newStr);
        
    } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }


 

    //do not use forward , include or redirect in this servlet
  //resp.sendRedirect("testb.html");

}



   
   

   
}   




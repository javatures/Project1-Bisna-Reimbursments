package Project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Supervisordao {

String url = "jdbc:postgresql://localhost:5432/ndemo";
String username = "ndemo";
String password = "npass";
Connection connection;

    public Supervisordao(Connection connection) {
    }

    public Supervisordao() {
    }

    public List<Supervisor> getAll() throws SQLException {
        
        Connection connection = DriverManager.getConnection(url, username, password);


        List<Supervisor> supervisorall = new ArrayList<>();

        try {
            PreparedStatement pStatement = connection.prepareStatement("select * from supervisors");
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next())
            {
                Supervisor temp = new Supervisor(
                                                            
                                rSet.getInt("sup_id"),
                                rSet.getString("sup_fn"),
                                rSet.getString("sup_ln")
                                
                );

                supervisorall.add(temp);
          
            }
        } catch(SQLException e) {
         e.printStackTrace();
        }
        return supervisorall;

        
    }
    public List<Supervisor> getOne() throws SQLException {
        
        Connection connection = DriverManager.getConnection(url, username, password);


        List<Supervisor> supervisorall = new ArrayList<>();

        try {
            PreparedStatement pStatement = connection.prepareStatement("select * from supervisors where sup_fn=?");
            String supfn ="";
            pStatement.setString(1, supfn);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next())
            {
                Supervisor temp = new Supervisor(
                                                            
                                rSet.getInt("sup_id"),
                                rSet.getString("sup_fn"),
                                rSet.getString("sup_ln")
                                
                );

                supervisorall.add(temp);
          
            }
        } catch(SQLException e) {
         e.printStackTrace();
        }
        return supervisorall;

        
    }


    
}




package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {

    public Connection getconnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Record";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Connect");
        }
        catch (Exception ae)
        {
            System.out.println("Not Connected");
        }

        return conn;
    }

}
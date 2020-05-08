package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class NewDatabaseCreation
{
    public static void createNewDatabase()
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/";
            connection = DriverManager.getConnection(url, "root", "12345");
            //System.out.println("Connected");
            statement = connection.createStatement();

            try
            {
                statement.executeUpdate("drop database Record");
            }
            catch (Exception ex)
            {
                System.out.println("Database not present.");
            }

            try
            {
                statement.executeUpdate("create database Record");
                statement.executeUpdate("use Record");
                statement.executeUpdate("create table EMAIL(email varchar(50) not null, password varchar(50) not null)");
                statement.executeUpdate("create table WEBSITE(name varchar(50) not null, password varchar(50) not null)");
                statement.executeUpdate("create table NOTE(header varchar(50) not null, note mediumtext)");
                System.out.println("Database and Table are Successfully Created!");
            }
            catch (Exception ex)
            {
                System.out.println("Database not created!");
            }
        }
        catch (Exception ae)
        {
            System.out.println("Not Connected");
        }
    }

}

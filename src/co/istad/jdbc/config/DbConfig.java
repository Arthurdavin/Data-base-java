package co.istad.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
//    singleton pattern : we need put keyword "static" for meke it to be singleton
    private static Connection conn;
    public static Connection getInstance(){
        return conn;
    }
//    initialize connection object
    public static void init(){
        if(conn==null){
//            start jdbc foundation steps
//            Step 1. load the driver
            try{
                Class.forName("org.postgresql.Driver");
            }catch (ClassNotFoundException e){
                System.out.println("driver load failed: "+e.getMessage());
            }
//            step 2 . define connection url
            final String URL = "jdbc:postgresql://localhost:5432/davin";
            final String USER = "postgres";
            final String PASSWORD ="vin1205";
//            step 3 establish connection
            try{
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }catch (SQLException e){
                System.out.println("Error SQL: "+ e.getMessage());
            }
        }else {
            System.out.println("Connection is already initialize");
        }
    }
}

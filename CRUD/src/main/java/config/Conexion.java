package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static final String username = "root";
    public static final String password = "Tomas.010716";
    public static final String database = "colegio";
    public static final String url = "jdbc:mysql://localhost:3306/" +database;
    
    public static Connection getConnection(){
        Connection cn = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, username, password);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("Conexión establecida");
        return cn;
    }
    
}

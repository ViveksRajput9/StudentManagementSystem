/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Abhishek Rajput
 */
public class Database {
    //    "E:\mysql-connector-java-8.0.28.jar"
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Studentsrecord";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "V@#r9128";
    
    private static Connection conn;
        static {
        try{
            conn =  DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
           
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static Connection conn()throws SQLException{
        if(conn == null){
            JOptionPane.showMessageDialog(null, "database connection is empty");
        }
        return conn;
    }
}
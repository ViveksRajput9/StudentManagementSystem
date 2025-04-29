/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import studentmanagementsystem.utility.Util;

/**
 *
 * @author Abhishek Rajput
 */
public class StudentLoginDao {

    public static final StudentLoginDao Dao = new StudentLoginDao();
    private static final String studentLoginTable = "studentLogin";

    private StudentLoginDao() {
        createStudentLogin();
    }

    private void createStudentLogin() {
        String createTable = "CREATE TABLE IF NOT EXISTS " + studentLoginTable + " ("
                + "id INT NOT NULL UNIQUE, "
                + "email VARCHAR(60) NOT NULL UNIQUE, "
                + "password VARCHAR(255) NOT NULL);";
        try (PreparedStatement pstm = Database.conn().prepareStatement(createTable)) {
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(createTable);
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // check email exists
    public boolean isEmailExists(String email) throws SQLException {
        String getAllEmailQuery = "SELECT email from " + studentLoginTable + " WHERE email=?";
        PreparedStatement pstm = Database.conn().prepareStatement(getAllEmailQuery);
        pstm.setString(1, email);

        ResultSet rs = pstm.executeQuery();
        return rs.next();
    }

    // add login details
    public boolean addNewUser(int id, String email, String password) throws SQLException {
        password = Util.hashPassword(password);
        String AddUserQuery = "INSERT into " + studentLoginTable + "(id,email,password) VALUES(?,?,?)";
        PreparedStatement pstm = Database.conn().prepareStatement(AddUserQuery);
        pstm.setInt(1, id);
        pstm.setString(2, email);
        pstm.setString(3, password);
        return pstm.executeUpdate() > 0;
    }

    // update existing password
    public boolean updatePassword(String email, String password) throws SQLException {
        password = Util.hashPassword(password);
        String updatePasswordQuery = "UPDATE " + studentLoginTable + " SET password=? where email=?";
        PreparedStatement pstm = Database.conn().prepareStatement(updatePasswordQuery);
        pstm.setString(1, password);
        pstm.setString(2, email);
        return pstm.executeUpdate() > 0;
    }

    // checking user valid 
    public boolean isUserValid(String email, String password) throws SQLException {
        password = Util.hashPassword(password);
        String query = "SELECT * FROM " + studentLoginTable + " WHERE email=?&&password=?";
        PreparedStatement pstm = Database.conn().prepareStatement(query);
        pstm.setString(1, email);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        return rs.next();
    }
    public boolean isUserValid(int id, String password) throws SQLException {
        password = Util.hashPassword(password);
        String query = "SELECT * FROM " + studentLoginTable + " WHERE id=?&&password=?";
        PreparedStatement pstm = Database.conn().prepareStatement(query);
        pstm.setInt(1, id);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        return rs.next();
    }
}

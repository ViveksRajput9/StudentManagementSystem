/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import studentmanagementsystem.utility.Util;
import static studentmanagementsystem.utility.Util.hashPassword;

/**
 *
 * @author Abhishek Rajput
 */
public class AdminDao {
    private static final String adminTableName = "adminLogin" ;
    public static final AdminDao DAO = new AdminDao();

    private AdminDao() {
    }
    static{
        // creating admin table
        String q = "CREATE TABLE IF NOT EXISTS "+adminTableName+""
                + " (Id int AUTO_INCREMENT PRIMARY KEY "
                + ",adminName VARCHAR(255) NOT NULL"
                + ",email VARCHAR(255) NOT NULL UNIQUE"
                + " ,password VARCHAR(255) NOT NULL"
                +",mobileNo VARCHAR(20) NOT NULL UNIQUE"
                +") AUTO_INCREMENT = 100000";
        
        try {
            Database.conn().prepareStatement(q).executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public boolean isAdminValid(int id, String password) {
        password = Util.hashPassword(password);
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM "+adminTableName+" WHERE id = ? && password=?";
            PreparedStatement pstm; 
            pstm = Database.conn().prepareStatement(query);
            pstm.setInt(1, id);
            pstm.setString(2, password);
            rs = pstm.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean updatePassword(String email, String password) throws SQLException {
                password = Util.hashPassword(password);

        String updateQuery = "UPDATE "+adminTableName+" SET password = ? WHERE email=?";
        PreparedStatement pstm = Database.conn().prepareStatement(updateQuery);
        pstm.setString(1, password);
        pstm.setString(2, email);

        return pstm.executeUpdate() > 0;
    }
    public boolean addNewUser(String adminName,String email,String password,String mobileNo) throws SQLException{
        password = hashPassword(password);
        String addUserQuery = "INSERT "+adminTableName+"("
                + "adminName,email,password,mobileNo)"
                + " VALUES(?,?,?,?)";
        PreparedStatement pstm= Database.conn().prepareStatement(addUserQuery);
        pstm.setString(1, adminName);
        pstm.setString(2, email);
        pstm.setString(3, password);
        pstm.setString(4, mobileNo);
        return pstm.executeUpdate()>0;
    }
            
    public boolean isEmailExists(String email) throws SQLException {
        String findEmailQuery = "SELECT email FROM "+adminTableName+" WHERE email = ?";
        PreparedStatement pstm = Database.conn().prepareStatement(findEmailQuery);
        pstm.setString(1, email);
        ResultSet rs = pstm.executeQuery();
        return rs.next();
    }
    public String getadminNameByEmail(String email){
        String q = "SELECT adminName FROM "+adminTableName+" WHERE Email=? LIMIT 1";
        try {
            PreparedStatement p = Database.conn().prepareStatement(q);
            p.setString(1, email);
            ResultSet rs =  p.executeQuery();
            while(rs.next()){
              return  rs.getString("adminName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int getId(String email){
        

    String q = "SELECT id FROM " + adminTableName + " WHERE Email=? LIMIT 1";
        try {
            PreparedStatement p = Database.conn().prepareStatement(q);
            p.setString(1, email);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
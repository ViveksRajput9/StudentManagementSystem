/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import studentmanagementsystem.model.Student;
import studentmanagementsystem.utility.Util;

/**
 *
 * @author vivek
 */
public class StudentDao {

    public static final StudentDao DAO = new StudentDao();
    private static final String studentTable = "StudentRecords";

    private StudentDao() {
        createTableStudentRecord();
    }

    private boolean isTableExists(String tableName) {
        try {
            ResultSet rs = Database.conn().getMetaData().getTables(null, null, tableName, null);
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    private boolean createTableStudentRecord() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + studentTable + " ("
                + "id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, "
                + "StudentName VARCHAR(30) NOT NULL, "
                + "DateOfBirth Date NOT NULL, "
                + "Gender VARCHAR(30) NOT NULL, "
                + "Email VARCHAR(80)  NOT NULL UNIQUE, "
                + "CollegeCourse VARCHAR(60) NOT NULL, "
                + "CollegeYear INT NOT NULL, "
                + "StudentMobileNo VARCHAR(16) NOT NULL, "
                + "LocalAddress VARCHAR(255), "
                + "FatherName VARCHAR(30) NOT NULL, "
                + "FatherAadhaarNo VARCHAR(60) NOT NULL, "
                + "FatherOccupation VARCHAR(50) NOT NULL, "
                + "FatherIncome DECIMAL(10,2) NOT NULL, "
                + "FatherMobileNo VARCHAR(16) NOT NULL, "
                + "MotherName VARCHAR(30) NOT NULL, "
                + "MotherOccupation VARCHAR(30), "
                + "State VARCHAR(30) NOT NULL, "
                + "City VARCHAR(30) NOT NULL, "
                + "Address VARCHAR(255), "
                + "Batch VARCHAR(100), "
                + "Total_leave INT, "
                + "Status ENUM('active', 'complete', 'disqualified','inProgress'), "
                + "StudentAadhaarNo VARCHAR(60) NOT NULL UNIQUE, "
                + "CONSTRAINT chk_FatherIncome CHECK (FatherIncome < 300000)"
                + ")AUTO_INCREMENT = 20000;";
        String alterQuery = "ALTER TABLE StudentRecords AUTO_INCREMENT = 1000";

        try {
            PreparedStatement p1 = Database.conn().prepareStatement(createTableQuery);
            PreparedStatement p2 = Database.conn().prepareStatement(alterQuery);
            int row = p1.executeUpdate();

            if (row > 0) {
                p2.executeUpdate();
            }
            return row > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean addStudent(Student student) {

        String query = "INSERT INTO " + studentTable + " (StudentName, DateOfBirth, Gender, Email, CollegeCourse, "
                + "CollegeYear, StudentMobileNo, LocalAddress, FatherName, FatherAadhaarNo, "
                + "FatherOccupation, FatherIncome, FatherMobileNo, MotherName, MotherOccupation, "
                + "State, City, Address,status, StudentAadhaarNo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = Database.conn().prepareStatement(query)) {

            pstmt.setString(1, student.getStudentName());

            pstmt.setDate(2, student.getDateOfBirth());

            pstmt.setString(3, student.getGender());

            pstmt.setString(4, student.getEmail());

            pstmt.setString(5, student.getCollegeCourse());

            pstmt.setInt(6, student.getCollegeYear());

            pstmt.setString(7, student.getStudentMobileNo());

            pstmt.setString(8, student.getLocalAddress());

            pstmt.setString(9, student.getFatherName());

            pstmt.setString(10, student.getFatherAadhaarNo());

            pstmt.setString(11, student.getFatherOccupation());

            pstmt.setDouble(12, student.getFatherIncome());

            pstmt.setString(13, student.getFatherMobileNo());

            pstmt.setString(14, student.getMotherName());

            pstmt.setString(15, student.getMotherOccupation());

            pstmt.setString(16, student.getState());

            pstmt.setString(17, student.getCity());

            pstmt.setString(18, student.getAddress());

            pstmt.setString(19, "inProgress");

            pstmt.setString(20, student.getStudentAadhaarNo()); // Ensure this is set correctly

            int rowsInserted = pstmt.executeUpdate();

            return rowsInserted > 0; // Return true if the insert was successful

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
            return false; // Return false if there was an error
        }
    }

    private List<Student> getStudentList(ResultSet rs) throws SQLException {
        ArrayList<Student> studentsRecord = new ArrayList<>();

        while (rs.next()) {

            // Add the student object to the list
            studentsRecord.add(getStudent(rs));
        }
        return studentsRecord;
    }

    private Student getStudent(ResultSet rs) throws SQLException {
        //     Create a new Student object using the second constructor with all parameters
        return new Student(
                rs.getInt("id"),
                rs.getString("batch"),
                rs.getString("status"),
                rs.getInt("Total_leave"),
                rs.getString("StudentName"),
                rs.getDate("dateOfBirth"),
                rs.getString("Gender"),
                rs.getString("Email"), // Added Email field
                rs.getString("CollegeCourse"),
                rs.getInt("CollegeYear"),
                rs.getString("StudentMobileNo"),
                rs.getString("LocalAddress"),
                rs.getString("FatherName"),
                rs.getString("FatherAadhaarNo"),
                rs.getString("FatherOccupation"),
                rs.getDouble("FatherIncome"),
                rs.getString("FatherMobileNo"),
                rs.getString("MotherName"),
                rs.getString("MotherOccupation"),
                rs.getString("State"),
                rs.getString("City"),
                rs.getString("Address"),
                rs.getString("StudentAadhaarNo")
        );

    }

    // fetch all student
    public List<Student> getAllStudentsRecord() {
        if (isTableExists(studentTable)) {
            try {
                String query = "SELECT * FROM " + studentTable; // Ensure the table name matches
                PreparedStatement pstmt = Database.conn().prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                return getStudentList(rs);

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "no table found");
        }
        return null;
    }

    //fetch all student from database by name
    public List<Student> getAllStudentRecordByName(String name) {
        try {
            String query = "SELECT * FROM " + studentTable + " WHERE StudentName LIKE '?%'";
            PreparedStatement pstm = Database.conn().prepareStatement(query);
            pstm.setString(1, name);
            ResultSet rs = pstm.executeQuery();
            return getStudentList(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    // fetch all Student from database by id 
    public List<Student> getAllStudentRecordById(int id) {
        try {
            String query = "SELECT * FROM " + studentTable + " WHERE id LIKE '" + id + "%'";
            PreparedStatement pstm = Database.conn().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            return getStudentList(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    // fetch Student from database by id 

    public Student getStudentRecordById(int id) {
        try {
            String query = "SELECT * FROM " + studentTable + " WHERE id  ? ";
            PreparedStatement pstm = Database.conn().prepareStatement(query);

            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            return getStudent(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    // fetch Student from database by id 

    public Student getStudentRecordByEmail(String email) {
        try {
            String query = "SELECT * FROM " + studentTable + " WHERE email = ?"; // Correct syntax
            PreparedStatement pstm = Database.conn().prepareStatement(query);
            pstm.setString(1, email); // Set the email parameter
            ResultSet rs = pstm.executeQuery();

            // Ensure the ResultSet has data before passing it to getStudent()
            if (rs.next()) {
                return getStudent(rs); // Call method to retrieve and map the student
            } else {
                JOptionPane.showMessageDialog(null, "No student found with the provided email.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
            return null;
        }
    }
    // get student leave 

    public int getTotalLeave(int id) {
        String q = "SELECT Total_leave FROM " + studentTable + " WHERE id = ?";
        try {
            PreparedStatement p = Database.conn().prepareStatement(q);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total_leave");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    // update leave 
        public boolean updateTotalLeave(int id ,int Total_leave) {
        String q = "UPDATE  " + studentTable + " SET Total_leave =? WHERE id = ?";
        try {
            PreparedStatement p = Database.conn().prepareStatement(q);
            p.setInt(1, Total_leave);
            p.setInt(2, id);
            return p.executeUpdate()>0;
          
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // get student name
    public String getStudentNameByEmail(String email) {
        String q = "SELECT StudentName FROM " + studentTable + " WHERE Email=? LIMIT 1";
        try {
            PreparedStatement p = Database.conn().prepareStatement(q);
            p.setString(1, email);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                return rs.getString("StudentName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    


    // get student id 
    public int getId(String email) {
        String q = "SELECT id FROM " + studentTable + " WHERE Email=? LIMIT 1";
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

    public boolean updateStudent(Student student) {
        String query = "UPDATE " + studentTable + " SET "
                + "StudentName = ?, DateOfBirth = ?, Gender = ?, Email = ?, "
                + "CollegeCourse = ?, CollegeYear = ?, StudentMobileNo = ?, LocalAddress = ?, "
                + "FatherName = ?, FatherAadhaarNo = ?, FatherOccupation = ?, FatherIncome = ?, "
                + "FatherMobileNo = ?, MotherName = ?, MotherOccupation = ?, State = ?, "
                + "City = ?, Address = ?, Batch = ?, Total_leave = ?, Status = ? "
                + "WHERE id = ?"; // Or use StudentAadhaarNo = ? instead of id

        try (PreparedStatement pstmt = Database.conn().prepareStatement(query)) {
            pstmt.setString(1, student.getStudentName());
            pstmt.setDate(2, student.getDateOfBirth());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getCollegeCourse());
            pstmt.setInt(6, student.getCollegeYear());
            pstmt.setString(7, student.getStudentMobileNo());
            pstmt.setString(8, student.getLocalAddress());
            pstmt.setString(9, student.getFatherName());
            pstmt.setString(10, student.getFatherAadhaarNo());
            pstmt.setString(11, student.getFatherOccupation());
            pstmt.setDouble(12, student.getFatherIncome());
            pstmt.setString(13, student.getFatherMobileNo());
            pstmt.setString(14, student.getMotherName());
            pstmt.setString(15, student.getMotherOccupation());
            pstmt.setString(16, student.getState());
            pstmt.setString(17, student.getCity());
            pstmt.setString(18, student.getAddress());
            pstmt.setString(19, student.getBatch());
            pstmt.setInt(20, student.getTotalLeave());
            pstmt.setString(21, student.getStatus());
            pstmt.setInt(22, student.getId()); // Or use StudentAadhaarNo with pstmt.setString(22, student.getStudentAadhaarNo());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Return true if the update was successful

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
            return false; // Return false if there was an error
        }
    }

    public boolean updateStudent(int id, String status, String batch) {
        try {
            String query = "UPDATE " + studentTable + " SET status = ? , batch =? WHERE id = ?";
            PreparedStatement pstm = Database.conn().prepareStatement(query);

            pstm.setString(1, status);
            pstm.setString(2, batch);
            pstm.setInt(3, id);

            return pstm.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateStudentStatus(int id, String status) {
        try {
            String query = "UPDATE " + studentTable + " SET status = ?  WHERE id = ?";
            PreparedStatement pstm = Database.conn().prepareStatement(query);

            pstm.setString(1, status);
            pstm.setInt(2, id);

            return pstm.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateStudentBatch(int id, String batch) {
        try {
            String query = "UPDATE " + studentTable + " SET batch = ?  WHERE id = ?";
            PreparedStatement pstm = Database.conn().prepareStatement(query);

            pstm.setString(1, batch);
            pstm.setInt(2, id);

            return pstm.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        try {
            String query = " DELETE FROM " + studentTable + "WHERE id =?";
            PreparedStatement pstm = Database.conn().prepareStatement(query);

            pstm.setInt(1, id);

            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

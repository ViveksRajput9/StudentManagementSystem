/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Abhishek Rajput
 */
public class BatchDao {

    public static final BatchDao DAO = new BatchDao();

    private final String BATCH_TABLE_NAME = "Batches";

    private BatchDao() {
        createTableBatches();
    }

    private boolean createTableBatches() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + BATCH_TABLE_NAME + " ("
                + "BatchID INT AUTO_INCREMENT PRIMARY KEY, "
                + "BatchName VARCHAR(100) NOT NULL UNIQUE, "
                + "StartDate DATE NOT NULL, "
                + "EndDate DATE, "
                + "CHECK (EndDate = DATE_ADD(StartDate, INTERVAL 1 YEAR))"
                + ")";
        try {
            Database.conn().prepareStatement(createTableSQL).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    public boolean addBatch(String batchName, String startDate) {
        PreparedStatement insertStatement = null;
        try {
            String INSERT_QUERY = "INSERT " + BATCH_TABLE_NAME
                    + " (batchName,startDate)"
                    + "VALUES(?,?)";
            insertStatement = Database.conn().prepareStatement(INSERT_QUERY);
            insertStatement.setString(1, batchName);
            insertStatement.setDate(2, Date.valueOf(startDate));
            return insertStatement.executeUpdate() > 0;
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
            return false;
        }

    }

    public ArrayList<String> getBatchNames() {
        String query = "SELECT BatchName FROM " + BATCH_TABLE_NAME;
        ArrayList<String> batchNames = new ArrayList<>();
        try {
            PreparedStatement pstm = Database.conn().prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                batchNames.add(rs.getString("BatchName"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
        return batchNames;
    }
    public boolean isBatchExists(String batchName){
        String q = "SELECT BatchName FROM " + BATCH_TABLE_NAME + " WHERE BatchName = ?";
        try {
            PreparedStatement pstm = Database.conn().prepareStatement(q);
            
            pstm.setString(1,batchName);
            ResultSet rs = pstm.executeQuery();
            return rs.next();

        } catch (Exception e) {
            return false;
        }
    }
}

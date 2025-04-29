/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.service;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import studentmanagementsystem.dao.*;

import studentmanagementsystem.model.Student;

/**
 *
 * @author vivek
 */
public class Services {

    private static final Services instance = new Services();

    private Services() {
    }

    
    
    public static Services getService() {
        return instance;
    }

    public List<Student> getAllStudentsRecord() {
        return StudentDao.DAO.getAllStudentsRecord();
    }

    public List<Student> getAllStudentRecordByName(String name) {
        return StudentDao.DAO.getAllStudentRecordByName(name);
    }

    public List<Student> getAllStudentRecordById(int id) {
        return StudentDao.DAO.getAllStudentRecordById(id);
    }

    public Student getStudentRecordById(int id) {
        return StudentDao.DAO.getStudentRecordById(id);
    }
    public Student getStudentRecordByEmail(String email) {
        return StudentDao.DAO.getStudentRecordByEmail(email);
    }

    public String getStudentNameByEmail(String email) {

        return StudentDao.DAO.getStudentNameByEmail(email);
    }
    public int getStudentId(String email){
        return StudentDao.DAO.getId(email);
    }
    public int getTotalLeave(int id){
        return StudentDao.DAO.getTotalLeave(id);
    }
    public boolean deleteStudent(int id){
        return StudentDao.DAO.deleteStudent(id);
    }
    
    
  // Update methods 
    public boolean addStudent(Student student) {
        return StudentDao.DAO.addStudent(student);
    }

    public boolean updateStudent(int id, String status, String batch) {
        return StudentDao.DAO.updateStudent(id, status, batch);
    }

    public boolean updateStudentStatus(int id, String status) {
        return StudentDao.DAO.updateStudentStatus(id, status);
    }

    public boolean updateStudentBatch(int id, String batch) {
        return StudentDao.DAO.updateStudentBatch(id, batch);
    }
    public boolean updateTotalLeave(int id, int totalLeave){
        return StudentDao.DAO.updateTotalLeave(id, totalLeave);
    }


    
    
    
    
    // admin dao methods 
    public boolean isAdminValid(int id, String password) {
        return AdminDao.DAO.isAdminValid(id, password);
    }

    public boolean addAdmin(String adminName, String mobileNo, String email, String password) throws SQLException {
        return AdminDao.DAO.addNewUser(adminName, email, password, mobileNo);
    }

    public String getAdminNameByEmail(String email) {
        return AdminDao.DAO.getadminNameByEmail(email);
    }

    public boolean updateAdminPassword(String email, String password) throws SQLException {
        return AdminDao.DAO.updatePassword(email, password);
    }

    public boolean isAdminEmailExists(String email) throws SQLException {
        return AdminDao.DAO.isEmailExists(email);
    }
    
    public int getAdminId(String email){
        return AdminDao.DAO.getId(email);
    }

    
    
    
    
    // student Login Dao methods 
    public boolean isStudentEmailExists(String email) throws SQLException {
        return StudentLoginDao.Dao.isEmailExists(email);
    }

    public boolean updateStudentPassword(String email, String password) throws SQLException {
        return StudentLoginDao.Dao.updatePassword(email, password);
    }

    public boolean addStudentLoginDetail(int id,String email, String password) throws SQLException {
        return StudentLoginDao.Dao.addNewUser(id,email, password);
    }

    public boolean isStudentValid(String email, String password) throws SQLException {
        return StudentLoginDao.Dao.isUserValid(email, password);
    }
    public boolean isStudentValid(int id, String password) throws SQLException {
        return StudentLoginDao.Dao.isUserValid(id, password);
    }

    
    
    // Batch Dao methods
    public ArrayList<String> getBatchesName() {
        return BatchDao.DAO.getBatchNames();
    }
    public boolean addBatch(String batchName,String startDate){
        return BatchDao.DAO.addBatch(batchName, startDate);
    }
    public boolean isBatchExist(String batchName){
        return BatchDao.DAO.isBatchExists(batchName);
    }
}
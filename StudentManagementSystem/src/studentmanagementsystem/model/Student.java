/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.model;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author vivek
 */
 public class Student {
    private int id;
    private String studentName;
    private Date dateOfBirth;
    private String gender;
    private String collegeCourse;
    private int collegeYear;
    private String studentMobileNo;
    private String localAddress;
    private String fatherName;
    private String fatherAadhaarNo;
    private String fatherOccupation;
    private double fatherIncome;
    private String fatherMobileNo;
    private String motherName;
    private String motherOccupation;
    private String state;
    private String city;
    private String address;
    private String batch;
    private String status; // 'active', 'complete', 'disqualified'
    private String email;
    private int totalLeave;
    private  String studentAadhaarNo;
    // Constructor
public Student(String studentName, Date dateOfBirth, String gender, String email, 
                      String collegeCourse, int collegeYear, String studentMobileNo, 
                      String localAddress, String fatherName, String fatherAadhaarNo, 
                      String fatherOccupation, double fatherIncome, String fatherMobileNo, 
                      String motherName, String motherOccupation, String state, 
                      String city, String address, String studentAadhaarNo) {
    this.studentName = studentName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.email = email;
    this.collegeCourse = collegeCourse;
    this.collegeYear = collegeYear;
    this.studentMobileNo = studentMobileNo;
    this.localAddress = localAddress;
    this.fatherName = fatherName;
    this.fatherAadhaarNo = fatherAadhaarNo;
    this.fatherOccupation = fatherOccupation;
    this.fatherIncome = fatherIncome;
    this.fatherMobileNo = fatherMobileNo;
    this.motherName = motherName;
    this.motherOccupation = motherOccupation;
    this.state = state;
    this.city = city;
    this.address = address;
    this.studentAadhaarNo = studentAadhaarNo;
}
public Student(int id, String batch, String status, int totalLeave, 
                      String studentName, Date dateOfBirth, String gender, String email, 
                      String collegeCourse, int collegeYear, String studentMobileNo, 
                      String localAddress, String fatherName, String fatherAadhaarNo, 
                      String fatherOccupation, double fatherIncome, String fatherMobileNo, 
                      String motherName, String motherOccupation, String state, 
                      String city, String address, String studentAadhaarNo  ) {
    // Using the original constructor to initialize most parameters
    this(studentName, dateOfBirth, gender, email, collegeCourse, collegeYear, studentMobileNo, 
         localAddress, fatherName, fatherAadhaarNo, fatherOccupation, fatherIncome, 
         fatherMobileNo, motherName, motherOccupation, state, city, address, studentAadhaarNo);
    
    // Initialize the remaining fields separately
    this.id = id;
    this.batch = batch;
    this.status = status;
    this.totalLeave = totalLeave;
}

    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getCollegeCourse() {
        return collegeCourse;
    }

    public int getCollegeYear() {
        return collegeYear;
    }

    public String getStudentMobileNo() {
        return studentMobileNo;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getFatherAadhaarNo() {
        return fatherAadhaarNo;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public double getFatherIncome() {
        return fatherIncome;
    }

    public String getFatherMobileNo() {
        return fatherMobileNo;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getBatch() {
        return batch;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalLeave() {
        return totalLeave;
    }

    public String getStudentAadhaarNo() {
        return studentAadhaarNo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCollegeCourse(String collegeCourse) {
        this.collegeCourse = collegeCourse;
    }

    public void setCollegeYear(int collegeYear) {
        this.collegeYear = collegeYear;
    }

    public void setStudentMobileNo(String studentMobileNo) {
        this.studentMobileNo = studentMobileNo;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setFatherAadhaarNo(String fatherAadhaarNo) {
        this.fatherAadhaarNo = fatherAadhaarNo;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public void setFatherIncome(double fatherIncome) {
        this.fatherIncome = fatherIncome;
    }

    public void setFatherMobileNo(String fatherMobileNo) {
        this.fatherMobileNo = fatherMobileNo;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setTotalLeave(int totalLeave) {
        this.totalLeave = totalLeave;
    }

    public void setStudentAadhaarNo(String studentAadhaarNo) {
        this.studentAadhaarNo = studentAadhaarNo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.fatherAadhaarNo);
        hash = 47 * hash + Objects.hashCode(this.studentAadhaarNo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.collegeYear != other.collegeYear) {
            return false;
        }
        if (Double.doubleToLongBits(this.fatherIncome) != Double.doubleToLongBits(other.fatherIncome)) {
            return false;
        }
        if (this.batch != other.batch) {
            return false;
        }
        if (this.totalLeave != other.totalLeave) {
            return false;
        }
        if (!Objects.equals(this.studentName, other.studentName)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.collegeCourse, other.collegeCourse)) {
            return false;
        }
        if (!Objects.equals(this.studentMobileNo, other.studentMobileNo)) {
            return false;
        }
        if (!Objects.equals(this.localAddress, other.localAddress)) {
            return false;
        }
        if (!Objects.equals(this.fatherName, other.fatherName)) {
            return false;
        }
        if (!Objects.equals(this.fatherAadhaarNo, other.fatherAadhaarNo)) {
            return false;
        }
        if (!Objects.equals(this.fatherOccupation, other.fatherOccupation)) {
            return false;
        }
        if (!Objects.equals(this.fatherMobileNo, other.fatherMobileNo)) {
            return false;
        }
        if (!Objects.equals(this.motherName, other.motherName)) {
            return false;
        }
        if (!Objects.equals(this.motherOccupation, other.motherOccupation)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.studentAadhaarNo, other.studentAadhaarNo)) {
            return false;
        }
        if (!Objects.equals(this.studentAadhaarNo, other.studentAadhaarNo)) {
            return false;
        }
        return Objects.equals(this.dateOfBirth, other.dateOfBirth);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", studentName=" + studentName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", collegeCourse=" + collegeCourse + ", collegeYear=" + collegeYear + ", studentMobileNo=" + studentMobileNo + ", localAddress=" + localAddress + ", fatherName=" + fatherName + ", fatherAadhaarNo=" + fatherAadhaarNo + ", fatherOccupation=" + fatherOccupation + ", fatherIncome=" + fatherIncome + ", fatherMobileNo=" + fatherMobileNo + ", motherName=" + motherName + ", motherOccupation=" + motherOccupation + ", state=" + state + ", city=" + city + ", address=" + address + ", batch=" + batch + ", status=" + status + ", email=" + email + ", totalLeave=" + totalLeave + ", studentAadhaarNo=" + studentAadhaarNo + '}';
    }

 }
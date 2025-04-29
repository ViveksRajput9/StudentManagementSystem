/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.ui;

import studentmanagementsystem.ui.forms.StudentRegistrationForm;
import studentmanagementsystem.ui.admin.StudentDetail;
import studentmanagementsystem.ui.admin.HomePage;
import studentmanagementsystem.ui.Login.*;
import studentmanagementsystem.ui.verification.EmailDialog;

/**
 *
 * @author Abhishek Rajput
 */

public class UiController {
    //Login instances
  private static final AdminLogin adminLogin = new AdminLogin();
 private  static final HomePage homePage = new HomePage();
  private static final Login login = new Login();
    
    public static AdminLogin getAdminLogin() {
        return adminLogin;
    }

   public static HomePage getHome() {
        return homePage;
    }

  public  static Login getLogin() {
        return login;
    }
    
    //form instances
//    StudentRegistrationForm studentRegistrationForm = new StudentRegistrationForm();
    
//    static StudentRegistrationForm getStudentRegistrationForm() {
//        return studentRegistrationForm;
//    }
    
    
    // student details instance
 private  static final StudentLogin studentLogin = new StudentLogin();
 private  static final StudentDetail studentDetail = new StudentDetail();
    
    
  public  static StudentLogin getStudentLogin() {
        return studentLogin;
    }

  public  static StudentDetail getStudentDetail() {
        return studentDetail;
    }
    
   // email dialog instances 
    private static EmailDialog ed;
   public static EmailDialog getEmailDialog(Object obj){
       if(ed!=null){
           ed.dispose();
       }
       return new EmailDialog(obj);
       
   } 
}
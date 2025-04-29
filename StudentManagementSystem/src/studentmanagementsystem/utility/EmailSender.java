/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.utility;

/**
 *
 * @author Abhishek Rajput
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private String otp;

    public EmailSender() {
    }
    // Method to send OTP

    private boolean sendMail(String recipientEmail, String subject, String msg) {

        // Sender's email ID and password (should be stored securely)
        final String senderEmail = "viveksingh70499"; // Replace with your email
        final String senderPassword = "wppf dgug ejqk gyct"; // Replace with your email password
        final String senderName = "Student Management Portal"; // Sender name
        // Mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Mail session
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderName + " <" + senderEmail + ">"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(msg);

            // Send message
            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public  void main(String[] args) {
//        // Example usage
//        String recipientEmail = "vivekraj60271@gmail.com"; // replace with recipient's email
//        String otp = generateOTP(6); // generate a 6-digit OTP
//        String subject = "Student Verification ";
//        sendOTP(recipientEmail, otp,subject);
//    }
    // Method to generate a random OTP
    private String generateOTP() {
        int length = 6;
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append((int) (Math.random() * 10)); // Append a random digit (0-9)
        }
        return otp.toString();
    }

    // method for verify otp
    public boolean verifyOtp(String otp) {

        return otp.equals(this.otp);
    }
    
    

    public boolean sendVerificationMail(String recipientEmail, String name) {
        this.otp = generateOTP(); // Generate OTP
        String subject = "Verification";

        String verificationMessage = "Dear " + name + ",\n\n"
                + "Thank you for registering with the Student Management Portal! To complete your email verification, please use the One-Time Password (OTP) provided below:\n\n"
                + "OTP: [" + otp + "]\n\n"
                + "This OTP is valid for [time limit, e.g., 10 minutes]. Please enter this code on the portal to verify your email address. If you didn't request this email, please ignore it.\n\n"
                + "Feel free to reach out to us at viveksingh70499@gmail.com if you encounter any issues.\n\n"
                + "Best regards,\n"
                + "Student Management Portal Team";

        return sendMail(recipientEmail, subject, verificationMessage);
    }

    public boolean sendResetMail(String recipientEmail, String name) {

        this.otp = generateOTP();
        // Define the subject for the OTP email
        String subject = "Password Reset";

// Define the message for the email
        String resetMessage = "Dear " + name + ",\n\n"
                + "Your one-time password (OTP) for resetting your password is: " + otp + "\n\n"
                + "Please enter this OTP on the password reset page to proceed.\n\n"
                + "If you did not request this, please ignore this email.\n\n"
                + "Best regards,\n"
                + "Support Team";

        return sendMail(recipientEmail, subject, resetMessage);

    }

    public boolean sendRegistrationSuccessMail(String recipientEmail, String name, int userId) {
        String subject = "Successful Registration - Student Management Portal";
        String successMessage = "Dear " + name + ",\n\n"
                + "Congratulations! You have successfully registered with the Student Management Portal.\n\n"
                + "Your User ID for logging in is: " + userId + "\n\n"
                + "We are excited to have you on board. Please take a moment to log in to your account and explore the features available to you.\n\n"
                + "If you have any questions or need assistance, feel free to reach out to us at viveksingh70499@gmail.com.\n\n"
                + "Best regards,\n"
                + "Student Management Portal Team";

        // Call the sendMail method and return its result
        return sendMail(recipientEmail, subject, successMessage);
    }

    public boolean sendStatusEmail(String recipientEmail, String name, String status) {
        String subject;
        String message;

        switch (status) {
            case "Active" -> {
                subject = "Congratulations! You Have Cleared the Exam";
                message = "Dear " + name + ",\n\n"
                        + "Congratulations! You have successfully cleared the exam and your status is now Active. "
                        + "You can continue to the next steps in your academic journey.\n\n"
                        + "Best regards,\n"
                        + "Student Management Portal Team";
            }

            case "Complete" -> {
                subject = "Course Completion Confirmation";
                message = "Dear " + name + ",\n\n"
                        + "Congratulations! You have successfully completed your course. "
                        + "Thank you for being a part of the Student Management Portal. We wish you all the best in your future endeavors.\n\n"
                        + "Best regards,\n"
                        + "Student Management Portal Team";
            }

            case "Disqualified" -> {
                subject = "Important: Disqualification Notice";
                message = "Dear " + name + ",\n\n"
                        + "We regret to inform you that you are no longer able to continue the course due to disqualification. "
                        + "If you have any questions or would like to discuss this further, please contact us.\n\n"
                        + "Best regards,\n"
                        + "Student Management Portal Team";
            }

            default -> {
                subject = "Important: Batch Update Notice";
                message = "Dear " + name + ",\n\n"
                        + "We have an update regarding the batch you enrolled in: " + status + ". Please check your account for more details.\n\n"
                        + "Best regards,\n"
                        + "Student Management Portal Team";
            }
        }

        // Call your email sending method here
        return sendMail(recipientEmail, subject, message);
    }
}

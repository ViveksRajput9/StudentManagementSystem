/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem.utility;

/**
 *
 * @author Abhishek Rajput
 */
import java.awt.Color;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Util {

    private static Timer timer = new Timer();
    private static TimerTask task;

    public static boolean isInternetAvailable() {
        try {
            // Try connecting to a reliable website (e.g., Google)
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(3000); // Timeout in milliseconds
            connection.setReadTimeout(3000);
            connection.connect();

            // If response code is 200, internet is available
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            // Any exception means the internet is not available
            return false;
        }
    }

    //  allow character or space only
    public static void allowLetterOrSpace(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume();
        }
    }

    // allow digits only without space
    public static void allowDigit(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == ' ') {
            evt.consume();
        }
    }

    // disable space
    public static void disableSpace(java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();
        if (c == ' ') {
            evt.consume();
        }
    }

    // disable SpecialChar
    public static void disableSpecialChar(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isLetter(c)) {
            evt.consume();
        }
    }

    public static boolean isMobileNoValid(String mobileNo) {

        String[] n = {"9", "8", "7", "6"};
        for (String n1 : n) {
            if (mobileNo.startsWith(n1) && mobileNo.length() == 10) {
                return true;
            }
        }
        return false;
    }

    //check email valid
    public static boolean isEmailValid(String email) {
        if (email.length() < 8) {
            return false;
        }
        String[] mailId = email.split("@");
        if (mailId.length != 2) {
            return false;
        }

        String[] domains = {"@gmail.com", " @outlook.com", "@yahoo.com"};
        for (String domain : domains) {
            if (email.endsWith(domain)) {
                if (Character.isLetter(email.charAt(0))) {
                    return true;
                }
            }
        }
        return false;
    }

// check password pattern
    public static boolean isPasswordValidPattern(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean upperCase = false;
        boolean Digit = false;
        boolean lowerCase = false;
        boolean specialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upperCase = true;
            } else if (Character.isDigit(c)) {
                Digit = true;
            } else if (Character.isLowerCase(c)) {
                lowerCase = true;
            } else if (!Character.isLetterOrDigit(c)) {
                specialChar = true;
            }
        }

        return upperCase && Digit && lowerCase && specialChar;
    }

    // Method to check if the input format is valid
    public static boolean isValidDate(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Disable lenient parsing

        try {
            sdf.parse(dob); // Try parsing the input string
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return false;
        }
    }

    // Method to check if the user is at least 18 years old
    public static boolean isAgeBetween18And30(String dob) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdf.parse(dob); // Parse the date

            Calendar today = Calendar.getInstance();
            Calendar birthDay = Calendar.getInstance();
            birthDay.setTime(birthDate);

            // Calculate age
            int age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);

            // Adjust for the birthday not having occurred yet this year
            if (today.get(Calendar.MONTH) < birthDay.get(Calendar.MONTH)
                    || (today.get(Calendar.MONTH) == birthDay.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < birthDay.get(Calendar.DAY_OF_MONTH))) {
                age--;
            }

            return age >= 18 && age <= 30; // Return true if the age is 18 or more
        } catch (ParseException e) {
            // This should not happen if the format is already validated
            System.out.println("Error parsing date during age validation.");
            return false;
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            System.out.println(sb.toString());
            return sb.toString(); // Returns the hashed password as a hexadecimal string
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

   public static void setLabelStatus(javax.swing.JTextField field, javax.swing.JLabel label, boolean result) {
        if (result) {
            field.setForeground(Color.GREEN);
            label.setText("✅");
            label.setForeground(Color.GREEN);
        } else {
            field.setForeground(Color.RED);
            label.setText("❌");
            label.setForeground(Color.RED);
        }
    }

    public static void setLabelStatus(javax.swing.JComboBox<String> field, javax.swing.JLabel label, boolean result) {
        if (result) {
            field.setForeground(Color.GREEN);
            label.setText("✅");
            label.setForeground(Color.GREEN);
        } else {
            field.setForeground(Color.RED);
            label.setText("❌");
            label.setForeground(Color.RED);
        }
    }

    public static void isPassValid(javax.swing.JTextField pass1, javax.swing.JLabel pass1Label, java.awt.event.KeyEvent evt) {
        int len1 = pass1.getText().length() + 1;
        if (len1 >= 16) {
            evt.consume();
        }
        if (len1 <= 0) {
            pass1.setBackground(Color.GRAY);
            pass1Label.setText("");
        }

        if (len1 < 8) {
            // light red color
            pass1.setBackground(new Color(255, 102, 102));
            pass1Label.setForeground(Color.RED);
            pass1Label.setText("Not Valid! ❌");

        } else {
            boolean flag = isPasswordValidPattern(pass1.getText());
            
            
            if (flag) {
                // light green color
                pass1.setBackground(new Color(144, 238, 144));
                pass1Label.setForeground(Color.GREEN);
                pass1Label.setText("Secure ✅");

            } else {
                pass1.setBackground(new Color(255, 102, 102));
                pass1Label.setForeground(Color.RED);

                pass1Label.setText("condtion not meat!");

            }
        }
    }

    public static boolean isPassMatched(javax.swing.JTextField pass1, javax.swing.JTextField pass2, javax.swing.JLabel pass2Label, java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int len1 = pass2.getText().length() + 1;
        if (len1 >= pass1.getText().length() + 1) {
            evt.consume();
        }
        System.out.println(pass2.getText());
        if (pass1.getText().equals(pass2.getText())) {
            // light green color
            pass2.setBackground(new Color(144, 238, 144));
            pass2Label.setForeground(Color.GREEN);
            pass2Label.setText("Matched ✅");
            return true;
        } else {
            // light red color
            pass2.setBackground(new Color(255, 102, 102));
            pass2Label.setForeground(Color.RED);
            pass2Label.setText("Not matched!");
            return false;
        }
    }



    // Timer 
    public static void timer(javax.swing.JLabel timerLabel,javax.swing.JButton verifyButton,javax.swing.JButton resendButton ) {
        if (timer != null) {
            timer.cancel(); // stops the timer 
        }
        int delay = 0;          // Start immediately
        int period = 1000;      // 1 second (1000 milliseconds)
        final int[] secondsLeft = {180}; // 3 minutes = 180 seconds
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (secondsLeft[0] > 0) {
                    int minutes = secondsLeft[0] / 60; // Calculate minutes
                    int seconds = secondsLeft[0] % 60; // Calculate remaining seconds
                    timerLabel.setText(minutes + ":" + seconds);
                    secondsLeft[0]--;
                } else {
                    
                    verifyButton.setEnabled(false);
                    resendButton.setEnabled(true);
                    timer.cancel(); // Stops the timer
                }
            }
        };

        // Schedule the task to run every second
        timer.scheduleAtFixedRate(task, delay, period);
    }
    
    
}

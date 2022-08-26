package CS347;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.File;

public class Auth {
    private String userID = null;
    private String userPWD = null;
    private boolean admin;
    public Auth() {
        try {
            File logfile = new File("CS347/logfile.txt");
            Scanner s = new Scanner(logfile);
            if(s.hasNextLine()) {
                userID = s.nextLine();
                userPWD = s.nextLine();
            }
        } catch(Exception e) {
            userID = "default_user";
            userPWD = "password";
        }
    }
    public boolean auth() {
        return userID != null && userPWD != null;
    }
    public boolean login(String usr, String pwd) {
        if(usr.equals("admin") && pwd.equals("adminpassword")) {
            admin = true;
            return true;
        }
        return userID .equals(usr) && userPWD.equals(pwd);
    }
    public boolean logout() {
        if(admin) {
            admin = false;
            return true;
        }
        if(!auth())
            return false;
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("logfile.txt"), "UTF-8"));
            out.write(userID+"\n");
            out.write(userPWD+"\n");
            out.close();
        } catch(Exception e) {
            System.err.println("Error: Could not write to logfile");
            return false;
        }
        return true;
    }
    public String getLog() {
        return userID + "\n"+userPWD+"\n";
    }
}
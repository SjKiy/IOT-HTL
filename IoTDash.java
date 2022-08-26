package CS347;

import CS347.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
public class IoTDash extends JPanel implements ActionListener {
    private int[] data_pack_processed;
    Logger log;
    Auth auth;
    Analytics analytics;
    JTextField[]authField;
    JButton loginButton;
    JButton[]menu;
    int page=-1;
    JFrame frame;
    boolean failed = false;
    boolean cc = false;
    String message = "";
    public IoTDash() {
        frame = new JFrame("IoT Dashboard");
        frame.setLayout(new BorderLayout());
        auth = new Auth();
        analytics = new Analytics();
        log = new Logger();
        authField = new JTextField[2];
        authField[0] = new JTextField();
        authField[1] = new JTextField();

        authField[0].setBounds(100, 200, 100, 25);
        authField[0].addActionListener(this);
        frame.add(authField[0]);

        authField[1].setBounds(100, 235, 100, 25);
        authField[1].addActionListener(this);
        frame.add(authField[1]);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 265, 80, 25);
        loginButton.addActionListener(this);
        frame.add(loginButton);

        menu = new JButton[5];
        menu[0] = new JButton("Show Log");
        menu[1] = new JButton("Get Analytics");
        menu[2] = new JButton("Cruise Control Enable");
        menu[3] = new JButton("Cruise Control Disable");
        menu[4] = new JButton("Logout");
        for(int x=0;x<5;x++){
            menu[x].setBounds(100, 265+50*x, 200, 40);
            menu[x].addActionListener(this);
            menu[x].setVisible(false);
            frame.add(menu[x]);
        }
        frame.add(this);
        frame.resize(950, 750);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getDataPack() {
        data_pack_processed = analytics.sendDataPack();
    }

    public String getLog() {
        return log.getLog();
    }

    public static void main(String[] args) {
        JPanel canvas = new IoTDash();
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = ((Graphics2D)g);
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0,950,750);
        g2.setColor(Color.BLACK);
        if(page == -1){
            if(failed)
                g2.drawString("Login failed, please try again.", 100, 150);
            else
                g2.drawString("Login.", 100, 150);
        }else{
            g2.drawString("Choose an option",100,150);
            if(page == 1) {
                message = log.getLog();
            } else if(page == 2) {
                message = "Risk Assessed = "+ analytics.getLog() + " with sensor data [ ";
                for(int x: analytics.sendDataPack()) {
                    message += x+" ";
                }
                message+="]";
            }
            g.drawString(message, 300, 150);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            if(auth.login(authField[0].getText(), authField[1].getText())) {
                page=0;
                authField[0].setVisible(false);
                authField[1].setVisible(false);
                authField[0].setText("");
                authField[1].setText("");
                loginButton.setVisible(false);
                failed = false;
                System.out.println("login successful");
                log.logData(auth.getLog());
                for(int x=0;x<5;x++){
                    menu[x].setVisible(true);
                }
            } else {
                failed = true;
                System.out.println("login failed");
                authField[0].setVisible(true);
                authField[1].setVisible(true);
                loginButton.setVisible(true);
            }
        } else if(e.getSource() == menu[4]) {
            page = -1;
            auth.logout();
            for(int x=0;x<5;x++){
                menu[x].setVisible(false);
            }
            authField[0].setVisible(true);
            authField[1].setVisible(true);
            loginButton.setVisible(true);
            log.storeLog();
        } else if(e.getSource() == menu[0]) {
            for(int x=0;x<5;x++){
                menu[x].setVisible(false);
                menu[x].setVisible(true);//refresh output
            }
            page = 1;
        } else if(e.getSource() == menu[1]) {
            for(int x=0;x<5;x++){
                menu[x].setVisible(false);
                menu[x].setVisible(true);//refresh output
            }
            page = 2;
        } else if(e.getSource() == menu[2]) {
            for(int x=0;x<5;x++){
                menu[x].setVisible(false);
                menu[x].setVisible(true);//refresh output
            }
            page = 3;
            if(!cc)
                message = "Cruise Control enabled";
            else
                message = "Error: Feature already enabled";
            cc = true;
        } else if(e.getSource() == menu[3]) {
            for(int x=0;x<5;x++){
                menu[x].setVisible(false);
                menu[x].setVisible(true);//refresh output
            }
            page = 4;
            if(cc)
                message = "Cruise Control disabled";
            else
                message = "Error: Feature already disabled";
            cc = false;
        }
        repaint();
    }
}
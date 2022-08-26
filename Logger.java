package CS347;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.File;

public class Logger {
    File logfile;
    private ArrayList<String> log;

    public Logger() {
        log = new ArrayList<String>();
        logfile = new File("CS347/logfile.txt");
        try {
            Scanner s = new Scanner(logfile);
            while(s.hasNextLine())
                log.add(s.nextLine());
        } catch(Exception e) {
            System.err.println("Error: Could not get logfile");
        }
    }
    public void rmlog(int a) {
        if (a >= log.size())
            throw new IllegalArgumentException("Invalid Log Index");
        log.remove(a);
    }
    public void storeLog() {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("logfile.txt"), "UTF-8"));
            while(log.size() > 0)
                out.write(log.remove(0)+"\n");
            out.close();
        } catch(Exception e) {
            System.err.println("Error: Could not store log");
        }
    }
    public void logData(String data) {
        log.add(data);
    }
    public String getLog() {
        String s = "";
        for(int x=2;x<log.size();x++)
            s+=log.get(x)+"\n";
        return s;
    }
}
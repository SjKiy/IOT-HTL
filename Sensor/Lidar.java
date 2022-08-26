package CS347.Sensor;

public class Lidar implements Sensor {
    
    private int lreading;

    public Lidar() {
        lreading = 0;
    }

    public int getData() {
        return lreading;
    }

    public String getLog() {
        return lreading+"\n";
    }

    public void lidarRead(int x) {
        lreading = x;
    }
}
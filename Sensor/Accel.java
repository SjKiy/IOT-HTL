package CS347.Sensor;

public class Accel implements Sensor {
    private int speed;
    public Accel() {
        speed=0;
    }

    public int getData() {
        return speed;
    }

    public String getLog() {
        return speed+"\n";
    }

    public void setSpeed(int s) {
        speed = s;
    }
}
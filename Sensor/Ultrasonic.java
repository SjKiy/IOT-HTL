package CS347.Sensor;

public class Ultrasonic implements Sensor {

    private int freq;

    public Ultrasonic() {
        freq = 0;
    }

    public int getData() {
        return freq;
    }

    public String getLog() {
        return freq+"\n";
    }

    public void setFreq(int f) {
        freq = f;
    }
}
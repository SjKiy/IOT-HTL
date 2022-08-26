package CS347.Sensor;

public class Radar implements Sensor {
    
    public int[][]area;

    public Radar() {
        area = new int[10][10];
    }

    public int getData() {
        int n=0;
        for(int r = 0; r < 10; r++)
            for(int c = 0; c < 10; c++)
                n+=area[r][c];
        return n;
    }

    public String getLog() {
        return getData()+"\n";
    }
}
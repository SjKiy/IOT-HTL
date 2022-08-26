package CS347;
import CS347.Sensor.*;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
/*
From directory Sensor:
run the file 'runthis.bat'
it will compile and properly run the code
*/
public class Analytics {
    private Sensor[] sens;
    private int risk_assessed;
    private int[] data_pack_processed;
    private boolean cc;
    public Analytics() {
        cc = false;
        sens = new Sensor[100];//4 cameras, 2 of all other sensors
        for(int i=0;i<2;i++) {
            sens[i] = new Camera();
            sens[i+2] = new Camera();
            sens[i+6] = new Lidar();
            sens[i+4] = new Accel();
            sens[i+8] = new Radar();
        }
        for(int i=10; i<38;i++) {
            sens[i] = new Radar();
            sens[i+32] = new Lidar();
            if(i+64 < 100)
            sens[i+64] = new Ultrasonic();
        }
        data_pack_processed = new int[100];
    }
    private void processData() {
        for(int i=0;i<12;i++)
            data_pack_processed[i] = sens[i].getData();
    }
    /** TODO: Use Tensorflow to assess risk on PSO model
    * Ultimately, this cannot be tested until a dataset is created different risks calculated by humans to
    * properly utilize machine learning.
    */
    public int assessRisk() {
        int r = 0;
        for(int i=0;i<100;i++)
            r+=data_pack_processed[i];
        risk_assessed = r/100;
        return r/100;
    }
    public int[] sendDataPack() {
        return data_pack_processed;
    }
    public String getLog() {
        return risk_assessed+"\n";
    }

    public String enableCC() {
        if(cc) {
            return "Error: Feature already enabled";
        }
        cc = true;
        return "Cruise Control enabled"; 
    }

    public String disableCC() {
        if(!cc) {
            return "Error: Feature already disabled";
        }
        cc = false;
        return "Cruise Control disabled"; 
    }
}
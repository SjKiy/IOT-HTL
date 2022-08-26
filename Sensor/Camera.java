package CS347.Sensor;

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
/*
From directory Sensor:
javac -cp ../libtensorflow_jni-cpu-windows-x86_64-1.3.0-rc1/libtensorflow-1.3.0-rc1.jar Camera.java Accel.java Lidar.java Radar.java Sensor.java Ultrasonic.java ../Analytics.java ../Auth.java ../IoTDash.java ../Logger.java
Will compile with TensorFlow Library
*/
public class Camera implements Sensor {
    private Tensor t;
    private int object_found;


    public Camera() {
        //t = Tensor.create();
    }

    /** TODO: Use Tensorflow to get data 
    * Ultimately, this cannot be tested until a dataset is created with images of people and other objects to
    * properly utilize machine learning.
    */
    public int getData() {
        return object_found;
    }

    public String getLog() {
        return object_found+"\n";
    }
}
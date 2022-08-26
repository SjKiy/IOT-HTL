package CS347;

import CS347.*;

public class IoTTest {
    private int[] data_pack_processed;
    Logger log;
    Auth auth;
    Analytics analytics;
    public IoTTest() {
        auth = new Auth();
        analytics = new Analytics();
        log = new Logger();
    }

    public void getDataPack() {
        data_pack_processed = analytics.sendDataPack();
    }

    public String getLog() {
        return log.getLog();
    }

    private void wait(int n) {
        try{Thread.sleep(n);} catch(Exception e){}
    }

    public static void main(String[] args) {
        IoTTest test = new IoTTest();

        System.out.println("Test 1: Enable Cruise Control While Disabled");
        System.out.println("Output: "+test.analytics.enableCC());

        test.wait(1000);

        System.out.println("Test 2: Enable Cruise Control While Enabled");
        System.out.println("Output: " + test.analytics.enableCC());

        test.wait(1000);

        System.out.println("Test 3: Disable Cruise Control While Enabled");
        System.out.println("Output: " + test.analytics.disableCC());

        test.wait(1000);

        System.out.println("Test 4: Disable Cruise Control While Disabled");
        System.out.println("Output: " + test.analytics.disableCC());

        test.wait(1000);

        System.out.println("Test 5: Login to IoT");
        System.out.println("Login Successful? " + test.auth.login("msanchez","password"));

        test.wait(1000);

        System.out.println("Test 6: Get Risk Assessment");
        System.out.println("Risk Assessed=" + test.analytics.assessRisk());

        test.wait(1000);

        System.out.println("Test 7: Login to IoT as Admin");
        System.out.println("Login Successful? " + test.auth.login("admin","adminPassword"));

        test.wait(5000);
    }
}
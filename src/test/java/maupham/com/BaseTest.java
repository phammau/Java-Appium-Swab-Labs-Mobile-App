package maupham.com;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {
    protected AndroidDriver driver;

    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
            .setPlatformName("Android")
            .setDeviceName("SM_A127F")
            .setUdid("R58RA3Q6A5E")
            .setAutomationName("UiAutomator2")
            .setAppPackage("com.swaglabsmobileapp")
            .setAppActivity("com.swaglabsmobileapp.MainActivity"); // phai mo SplashActivity đe dieu huong vao LoginActivity

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Thread.sleep(10000);// doi load
    }
}

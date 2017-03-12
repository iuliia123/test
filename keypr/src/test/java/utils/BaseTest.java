package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yulka on 3/12/17.
 */
public class BaseTest {
    public static AppiumDriver driver;

    @BeforeClass
    public static void setUpSuite() {
        startServer();
        prepareDevice();
    }

    @AfterClass
    public static void tearDownSuite() {
        stopServer();
    }

    private static void prepareDevice(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "deviceName");

        String platform = System.getenv("PLATFORM");
        String deviceID = System.getenv("DEVICEID");

        //mandatory capabilities
        capabilities.setCapability("deviceName", "emulator");
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("dontStopAppOnReset", true);
        capabilities.setCapability("deviceId", deviceID);
        capabilities.setCapability("appPackage", "com.android.settings");
        capabilities.setCapability("appActivity", ".Settings");

        //other capabilities
        capabilities.setCapability("FullReset", false);
        capabilities.setCapability("noReset", true);
        try {
            if (platform.equals("android")) {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            } else if (platform.equals("ios")) {
                driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            } else {
                throw new Exception("Unable to read device platform.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startServer() {
        CommandLine command = new CommandLine("appium");
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);

        try {
            executor.execute(command, resultHandler);
            Thread.sleep(10000);
            System.out.println("Appium server started.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void stopServer() {
        CommandLine command = new CommandLine("killall");
        command.addArgument("node");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);

        try {
            executor.execute(command, resultHandler);
            Thread.sleep(5000);
            System.out.println("Appium server stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

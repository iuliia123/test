package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by yulka on 3/12/17.
 */
public class AddNetworkPopup {
    private AppiumDriver driver;

    public AddNetworkPopup(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    @AndroidFindBy(id = "ssid")
    @iOSFindBy(id = "ssid")  ///fake
    private MobileElement ssidEditField;

    @AndroidFindBy(id = "security")
    @iOSFindBy(id = "security") //fake
    private MobileElement securitySelect;

    @AndroidFindBy(id = "button1")
    @iOSFindBy(id = "button1") //fake
    private MobileElement saveButton;

    @AndroidFindBy(id = "password_layout")
    @iOSFindBy(id = "password_layout") //fake
    private MobileElement passwordField;

    @AndroidFindBy(uiAutomator ="new UiSelector().text(\"None\")")
    @iOSFindBy(uiAutomator ="new UiSelector().text(\"None\")") //fake
    private MobileElement securityNone;

    @AndroidFindBy(uiAutomator ="new UiSelector().text(\"WEP\")")
    @iOSFindBy(uiAutomator ="new UiSelector().text(\"WEP\")") //fake
    private MobileElement securityWEP;

    @AndroidFindBy(uiAutomator ="new UiSelector().text(\"WPA/WPA2 PSK\")")
    @iOSFindBy(uiAutomator ="new UiSelector().text(\"WPA/WPA2 PSK\")") //fake
    private MobileElement securityWPA;

    @AndroidFindBy(uiAutomator ="new UiSelector().text(\"802.1x EAP\")")
    @iOSFindBy(uiAutomator ="new UiSelector().text(\"802.1x EAP\")") //fake
    private MobileElement securityEAP;

    public AddNetworkPopup enterNetworkName(String networkName) {
        ssidEditField.click();
        driver.getKeyboard().pressKey(networkName);
        return this;
    }

    public AddNetworkPopup setSecurity(String security) {
        securitySelect.click();
        switch (security) {
            case "None":
                securityNone.click();
                break;
            case "WEP":
                securityWEP.click();
                break;
            case "WPA/WPA2 PSK":
                securityWPA.click();
                break;
            case "802.1x EAP":
                securityEAP.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid security type: " + security);
        }
        return this;
    }

    public AddNetworkPopup setPassword(String password) {
        passwordField.click();
        driver.getKeyboard().pressKey(password);
        return this;
    }

    public SettingsPage saveNetwork() {
        saveButton.click();
        return new SettingsPage(driver);
    }

}

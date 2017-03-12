package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by yulka on 3/11/17.
 */
public class SettingsPage {
    private AppiumDriver driver;

    public SettingsPage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Wi‑Fi\")")
    @iOSFindBy(uiAutomator = "new UiSelector().text(\"Wi‑Fi\")") //fake
    private MobileElement itemWiFi;

    @AndroidFindBy(id = "switch_widget")
    private MobileElement switcher;

    @AndroidFindBys({
            @AndroidFindBy(id = "action_bar_container"),
            @AndroidFindBy(className = "android.widget.LinearLayout"),
            @AndroidFindBy(className = "android.widget.ImageButton")
    })
    @iOSFindBy(id = "menu_item") //fake
    private MobileElement menu;

    @AndroidFindBy(id = "list")
    @iOSFindBy(id= "list") //fake
    private MobileElement listOfNetworks;

    public void clickOnWiFiItem() {
        itemWiFi.click();
    }

    public boolean isWiFiTurnedOn() {
        return switcher.getText().equals("ON");
    }

    public void turnOnWiFi() {
        switcher.click();
    }

    public Menu clickOnMenu() {
        menu.click();
        return new Menu(driver);
    }

    public boolean isNetworkAdded(String networkName) {
        for (MobileElement element : listOfNetworks.findElements(By.id("title"))) {
            if (element.getText().equals(networkName))
                return true;
        }
        return false;
    }
}

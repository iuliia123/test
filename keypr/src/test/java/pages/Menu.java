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
public class Menu {
    private AppiumDriver driver;

    @AndroidFindBy(uiAutomator ="new UiSelector().text(\"Add network\")")
    @iOSFindBy(uiAutomator ="new UiSelector().text(\"Add network\")") //fake
    private MobileElement addNetworkItem;

    @AndroidFindBy(uiAutomator ="new UiSelector().text(\"Saved networks\")")
    @iOSFindBy(uiAutomator ="new UiSelector().text(\"Saved networks\")") //fake
    private MobileElement savedNetworksItem;


    public Menu(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public AddNetworkPopup addNewNetwork(){
        addNetworkItem.click();
        return new AddNetworkPopup(driver);
    }

    public SettingsPage savedNetworks(){
        savedNetworksItem.click();
        return new SettingsPage(driver);
    }
}

package scenarios;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import pages.SettingsPage;
import utils.BaseTest;

/**
 * Created by yulka on 3/12/17.
 */
public class SettingsTest extends BaseTest {
    SettingsPage settingsPage = new SettingsPage(driver);
    public static final String NETWORK_NAME = "test1";

    @Before
    public void setUp() {
        settingsPage.clickOnWiFiItem();
        if (!settingsPage.isWiFiTurnedOn()) {
            settingsPage.turnOnWiFi();
        }
    }

    @Test
    public void test1_AddNewWiFi() {
        settingsPage.clickOnMenu().addNewNetwork()
                .enterNetworkName(NETWORK_NAME)
                .setSecurity("WEP")
                .setPassword("12345678")
                .saveNetwork();
        settingsPage.clickOnMenu().savedNetworks();
        TestCase.assertEquals("Network isn't added", true, settingsPage.isNetworkAdded(NETWORK_NAME));
    }
}

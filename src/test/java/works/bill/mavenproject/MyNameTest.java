/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.mavenproject;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import java.net.URL;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author bill
 */
@RunWith(ConcurrentParameterized.class)
public class MyNameTest implements SauceOnDemandSessionIdProvider {
    
    public String username = System.getenv("SAUCE_USERNAME");
    public String accesskey = System.getenv("SAUCE_ACCESS_KEY");
    
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accesskey);
    
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
    
    @Rule public TestName name = new TestName() {
        @Override
        public String getMethodName() {
            return String.format("%s : (%s %s %s)", super.getMethodName(), os, browser, version);
        };
    };
    
    /**
     * Represents the browser to be used as part of the test run.
     */
    private String browser;
    /**
     * Represents the operating system to be used as part of the test run.
     */
    private String os;
    /**
     * Represents the version of the browser to be used as part of the test run.
     */
    private String version;
    /**
     * Represents the deviceName of mobile device
     */
    private String deviceName;
    /**
     * Represents the device-orientation of mobile device
     */
    private String deviceOrientation;
    /**
     * Instance variable which contains the Sauce Job Id.
     */
    private String sessionId;
    
    /**
     * The {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private WebDriver driver;
    
    public MyNameTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super();
        this.os = os;
        this.version = version;
        this.browser = browser;
        this.deviceName = deviceName;
        this.deviceOrientation = deviceOrientation;
    }
    
    @ConcurrentParameterized.Parameters
    public static LinkedList browserStrings() {
        LinkedList browsers = new LinkedList();
        browsers.add(new String[]{"Windows 7", "41", "chrome", null, null});
        
        return browsers;
    }
    
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        if (browser != null) capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) capabilities.setCapability(CapabilityType.VERSION, version);
        if (deviceName != null) capabilities.setCapability("deviceName", deviceName);
        if (deviceOrientation != null) capabilities.setCapability("device-orientation", deviceOrientation);
        
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        
        String methodName = name.getMethodName();
        capabilities.setCapability("name", methodName);
        
        this.driver = new RemoteWebDriver(
            new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() +
                    "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
                
        String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", this.sessionId, methodName);
        System.out.println(message);
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Test
    public void verifyBelkHompage() throws Exception {
        driver.get("http://www.belk.com");
        WebDriverWait wait = new WebDriverWait(driver, 10); // wait for a maximum of 5 seconds
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".primary-nav")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".promo-utility")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".logo")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#shoppingBagPlaceHolder")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#global_search_box")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".container_24")));

        assertTrue(driver.getTitle().equals("Home - belk.com - Belk.com"));
    }
}

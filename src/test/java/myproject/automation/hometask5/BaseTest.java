package myproject.automation.hometask5;


import myproject.automation.hometask5.utils.DriverFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest extends DriverFactory{
    protected RemoteWebDriver driver;
    protected GeneralActions actions;
    protected boolean isMobileTesting;


    @BeforeClass
    @Parameters({"selenium.browser", "selenium.grid"})
    public void setUp(@Optional("chrome") String browser, @Optional("") String gridUrl) {

        try {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(new URL(gridUrl),
                capabilities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SkipException("Unable to create RemoteWebDriver instance!");
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        if (!isMobileTesting(browser))
            driver.manage().window().maximize();

        isMobileTesting = isMobileTesting(browser);

        actions = new GeneralActions(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private boolean isMobileTesting(String browser) {
        switch (browser) {
            case "android":
                return true;
            case "firefox":
            case "ie":
            case "internet explorer":
            case "chrome":
            case "phantomjs":
            default:
                return false;
        }
    }
}

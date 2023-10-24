package ui;

import driver.DriverRepository;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private final DriverRepository driverRepository = new DriverRepository();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        driverRepository.downloadWebDriver();
    }

    @BeforeClass(alwaysRun = true)
    public void instantiateBrowser() {
        driverRepository.instantiateBrowser();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driverRepository.quitBrowser();
    }
}

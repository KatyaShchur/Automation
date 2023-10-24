package general;

import driver.DriverRepository;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseGeneralPage {

    protected RemoteWebDriver driver;

    public BaseGeneralPage() {
        driver = DriverRepository.DRIVERS.get();
        PageFactory.initElements(driver, this);
    }
}

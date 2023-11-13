package general;

import driver.DriverRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseGeneralPage {

    private static final int WAIT_TWO_SEC = 2;

    protected RemoteWebDriver driver;

    protected WebDriverWait wait;

    public BaseGeneralPage() {
        driver = DriverRepository.DRIVERS.get();
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TWO_SEC));
        PageFactory.initElements(driver, this);
    }

    public void clickJS(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    public void scrollIntoView(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public By.ByXPath getByText(String text) {
        return new By.ByXPath(".//*[contains(text(), '" + text + "')]");
    }
}

package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

public class DriverRepository {
    public static final ThreadLocal<RemoteWebDriver> DRIVERS = new ThreadLocal<>();

    public static String getBrowserType() {
        return System.getProperty(LogType.BROWSER, Browser.FIREFOX.browserName());
    }

    public static String getDownloadFolder() {
        return System.getProperty("user.dir", "C:\\Users\\Kateryna_Shchur\\Desktop\\SQE\\Automation");
    }

    public void downloadWebDriver() {
        final String browserType = getBrowserType();
        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
        }
    }

    public void instantiateBrowser() {
        final String browserType = getBrowserType();
        RemoteWebDriver driver;
        switch (browserType) {
            case "chrome":
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("browser.download.folderList", 2);
                firefoxOptions.addPreference("browser.download.dir", System.getProperty("user.dir"));
                firefoxOptions.addPreference("browser.download.useDownloadDir", true);
                firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
                firefoxOptions.addPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                driver = new ChromeDriver();
        }
        DRIVERS.set(driver);
    }

    public void quitBrowser() {
        if (DRIVERS.get() != null) {
            DRIVERS.get().quit();
        }
    }

}

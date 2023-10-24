package general;

import general.BaseGeneralPage;
import org.openqa.selenium.Dimension;

public class NavigationPO extends BaseGeneralPage {

    public void navigateToUrl(String url) {
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1600, 1200));
    }
}

package ui;

import com.epam.pageobject.EpamAboutPage;
import com.epam.pageobject.EpamHomePage;
import general.NavigationPO;
import utils.FileUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class FirstTaskTest extends BaseTest {

    @BeforeMethod
    public void openMainSite() {
        NavigationPO navigation = new NavigationPO();
        navigation.navigateToUrl("https://epam.com");
    }

    @Test
    public void checkTitle() {
        EpamHomePage epamHomePage = new EpamHomePage();
        assertEquals(epamHomePage.getTitle(),
                "EPAM | Software Engineering & Product Development Services",
                "Title is incorrect");
    }

    @Test
    public void testThemeSwitcher() {
        EpamHomePage epamHomePage = new EpamHomePage();
        if (epamHomePage.isDarkThemeMode()) {
            epamHomePage.clickThemeSwitcher();
            assertFalse(epamHomePage.isDarkThemeMode());
        } else {
            epamHomePage.clickThemeSwitcher();
            assertTrue(epamHomePage.isDarkThemeMode());
        }
    }

    @Test
    public void testUALanguageSelection() {
        EpamHomePage epamHomePage = new EpamHomePage();
        epamHomePage.selectLanguage("uk", "https://careers.epam.ua/");
        assertEquals(epamHomePage.getTitle(),
                "EPAM Ukraine - найбільша ІТ-компанія в Україні | Вакансії",
                "Title is incorrect");
    }

    @Test
    public void checkPoliciesList() {
        EpamHomePage epamHomePage = new EpamHomePage();
        assertEqualsNoOrder(epamHomePage.getPoliciesList(),
                List.of("INVESTORS",
                "COOKIE POLICY",
                "OPEN SOURCE",
                "APPLICANT PRIVACY NOTICE",
                "PRIVACY POLICY",
                "WEB ACCESSIBILITY"), "Incorrect policies list");
    }

    @Test
    public void checkLocationList() {
        EpamHomePage epamHomePage = new EpamHomePage();
        assertEqualsNoOrder(epamHomePage.getLocationList(),
                List.of("AMERICAS", "EMEA", "APAC"), "Incorrect location list");
        epamHomePage.clickLocation("AMERICAS");
        assertEquals(epamHomePage.getCountryList().size(), 5);
        assertEqualsNoOrder(epamHomePage.getCountryList(),
                List.of("canada", "colombia", "dominican republic", "mexico", "usa"),
                "Incorrect country list");
        epamHomePage.clickLocation("APAC");
        assertEquals(epamHomePage.getCountryList().size(), 8);
        assertEqualsNoOrder(epamHomePage.getCountryList(),
                List.of("australia", "china", "hong kong sar", "india", "japan", "malaysia", "singapore", "vietnam"),
                "Incorrect country list");
        epamHomePage.clickLocation("EMEA");
        assertEquals(epamHomePage.getCountryList().size(), 30);
    }

    @Test
    public void checkSearchFunctionality() {
        EpamHomePage epamHomePage = new EpamHomePage();
        String text = "AI";
        String searchResultsCounter = epamHomePage.searchByText(text);
        assertTrue(searchResultsCounter.contains("RESULTS FOR \"" + text + "\""));
    }

    @Test
    public void checkCompanyLogoRedirection() {
        NavigationPO navigation = new NavigationPO();
        navigation.navigateToUrl("https://www.epam.com/about");
        EpamHomePage epamHomePage = new EpamHomePage();
        epamHomePage.checkLogoRedirection("https://www.epam.com/");
    }

    @Test
    public void checkDownloadReport() {
        NavigationPO navigation = new NavigationPO();
        navigation.navigateToUrl("https://www.epam.com/about");
        EpamAboutPage epamAboutPage = new EpamAboutPage();
        epamAboutPage.clickDownloadButton();
        assertTrue(FileUtil.isFileDownloaded("EPAM_Corporate_Overview_2023.pdf"));
    }

    @Test
    public void checkMandatoryFields() {
        NavigationPO navigation = new NavigationPO();
        navigation.navigateToUrl("https://www.epam.com/about/who-we-are/contact");
        EpamAboutPage epamAboutPage = new EpamAboutPage();
        epamAboutPage.clickSubmitButton();
        assertEquals(epamAboutPage.getFirstNameValidationMessage(), "This is a required field");
        assertEquals(epamAboutPage.getLastNameValidationMessage(), "This is a required field");
        assertEquals(epamAboutPage.getEmailValidationMessage(), "This is a required field");
        assertEquals(epamAboutPage.getPhoneValidationMessage(), "This is a required field");
    }
}

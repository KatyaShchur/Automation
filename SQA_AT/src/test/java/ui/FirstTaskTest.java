package ui;

import com.epam.pageobject.EpamAboutPage;
import com.epam.pageobject.EpamHomePage;
import general.NavigationPO;
import utils.FileUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static assertion.MandatoryFieldsAssertion.verifyMandatoryFields;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class FirstTaskTest extends BaseTest {

    private static final String EPAM_URL = "https://www.epam.com/" ;
    private static final String EPAM_ABOUT_URL = "https://www.epam.com/about";
    private static final String EPAM_ABOUT_CONTACT_URL = "https://www.epam.com/about/who-we-are/contact";
    private static final String EPAM_CAREER_URL = "https://careers.epam.ua/";

    @BeforeMethod
    public void openMainSite() {
        NavigationPO navigation = new NavigationPO();
        navigation.navigateToUrl(EPAM_URL);
    }

    @Test
    public void checkTitle() {
        EpamHomePage epamHomePage = new EpamHomePage();
        assertThat(epamHomePage.getTitle())
                .as("Has title")
                .isEqualTo("EPAM | Software Engineering & Product Development Services");
    }

    @Test
    public void testThemeSwitcher() {
        EpamHomePage epamHomePage = new EpamHomePage();
        if (epamHomePage.isDarkThemeMode()) {
            epamHomePage.clickThemeSwitcher();
            assertThat(epamHomePage.isDarkThemeMode()).isFalse();
        } else {
            epamHomePage.clickThemeSwitcher();
            assertThat(epamHomePage.isDarkThemeMode()).isTrue();
        }
    }

    @Test
    public void testUALanguageSelection() {
        EpamHomePage epamHomePage = new EpamHomePage();
        epamHomePage.selectLanguage("uk", EPAM_CAREER_URL);
        assertThat(epamHomePage.getTitle())
                .as("Has title")
                .isEqualTo("EPAM Ukraine - найбільша ІТ-компанія в Україні | Вакансії");
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
        navigation.navigateToUrl(EPAM_ABOUT_URL);
        EpamHomePage epamHomePage = new EpamHomePage();
        epamHomePage.checkLogoRedirection(EPAM_URL);
    }

    @Test
    public void checkDownloadReport() {
        NavigationPO navigation = new NavigationPO();
        navigation.navigateToUrl(EPAM_ABOUT_URL);
        EpamAboutPage epamAboutPage = new EpamAboutPage();
        epamAboutPage.clickDownloadButton();
        assertTrue(FileUtil.isFileDownloaded("EPAM_Corporate_Overview_Q3_october.pdf"));
    }

    @Test
    public void checkMandatoryFields() {
        NavigationPO navigation = new NavigationPO();
        navigation.navigateToUrl(EPAM_ABOUT_CONTACT_URL);
        EpamAboutPage epamAboutPage = new EpamAboutPage();
        epamAboutPage.clickSubmitButton();
        verifyMandatoryFields(epamAboutPage);
    }
}

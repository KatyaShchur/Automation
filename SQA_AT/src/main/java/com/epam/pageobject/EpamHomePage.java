package com.epam.pageobject;

import general.BaseGeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class EpamHomePage extends BaseGeneralPage {

    private static final String CLASS_ATTR = "class";
    private static final String DARK_MODE_ATTR = "dark-mode";
    private static final String DATA_COUNTRY_ATTR = "data-country";

    @FindBy(css = ".header__content > .theme-switcher-ui .switch")
    private WebElement themeSwitcher;

    @FindBy(css = "[class*=fonts-loaded]")
    private WebElement modeType;

    @FindBy(css = ".location-selector__button .location-selector__button-language")
    private WebElement languageButton;

    @FindBy(css = "[class*=policies] .links-item")
    private List<WebElement> policiesList;

    @FindBy(css = "[class*=js-tabs-links-list] > div")
    private List<WebElement> locationList;

    @FindBy(css = "[class*='js-tabs-item active'] [class*=country-details]")
    private List<WebElement> countryList;

    @FindBy(css = "span[class*=search-icon]")
    private WebElement searchButton;

    @FindBy(id = "new_form_search")
    private WebElement searchInput;

    @FindBy(css = ".custom-search-button")
    private WebElement findSearchButton;

    @FindBy(css = ".search-results__counter")
    private WebElement searchResultsCounter;

    @FindBy(css = ".desktop-logo")
    private WebElement logo;

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickThemeSwitcher() {
        themeSwitcher.click();
    }

    public boolean isDarkThemeMode() {
        return modeType.getAttribute(CLASS_ATTR).contains(DARK_MODE_ATTR);
    }

    public void selectLanguage(String language, String redirectUrl) {
        languageButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(languageLocator(language)));
        driver.findElement(languageLocator(language)).click();
        wait.until(ExpectedConditions.urlToBe(redirectUrl));
    }

    public List<String> getPoliciesList() {
        scrollIntoView(policiesList.get(0));

        return policiesList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getLocationList() {
        scrollIntoView(locationList.get(0));

        return locationList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickLocation(String locationName) {
        clickJS(driver.findElement(getByText(locationName)));
    }

    public List<String> getCountryList() {
        scrollIntoView(countryList.get(0));
        return countryList.stream()
                .map(t -> t.getAttribute(DATA_COUNTRY_ATTR))
                .collect(Collectors.toList());
    }

    public String searchByText(String text) {
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(text);
        findSearchButton.click();
        wait.until(ExpectedConditions.visibilityOf(searchResultsCounter));
        return searchResultsCounter.getText();
    }

    public void checkLogoRedirection(String redirectUrl) {
        logo.click();
        wait.until(ExpectedConditions.urlToBe(redirectUrl));
    }

    private By.ByCssSelector languageLocator(String language) {
        return new By.ByCssSelector("[class=location-selector__link][lang=" + language + "]");
    }
}

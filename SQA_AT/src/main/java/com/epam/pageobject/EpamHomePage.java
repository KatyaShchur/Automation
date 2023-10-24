package com.epam.pageobject;

import general.BaseGeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class EpamHomePage extends BaseGeneralPage {

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
        return modeType.getAttribute("class").contains("dark-mode");
    }

    public void selectLanguage(String language, String redirectUrl) {
        languageButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector("[class=location-selector__link][lang=" + language + "]")));
        driver.findElement(new By.ByCssSelector("[class=location-selector__link][lang=" + language + "]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.urlToBe(redirectUrl));
    }

    public List<String> getPoliciesList() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", policiesList.get(0));

        return policiesList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getLocationList() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locationList.get(0));

        return locationList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickLocation(String locationName) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(new By.ByXPath(".//*[contains(text(), '" + locationName + "')]")));
    }

    public List<String> getCountryList() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countryList.get(0));
        return countryList.stream()
                .map(t -> t.getAttribute("data-country"))
                .collect(Collectors.toList());
    }

    public String searchByText(String text) {
        searchButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(text);
        findSearchButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(searchResultsCounter));
        return searchResultsCounter.getText();
    }

    public void checkLogoRedirection(String redirectUrl) {
        logo.click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.urlToBe(redirectUrl));
    }
}

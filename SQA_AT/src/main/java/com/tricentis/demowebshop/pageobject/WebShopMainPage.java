package com.tricentis.demowebshop.pageobject;

import general.BaseGeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class WebShopMainPage extends BaseGeneralPage {

    private static final String HREF_ATTR = "href";

    @FindBy (css = ".ico-login")
    private WebElement loginButton;

    @FindBy (css = ".header-links .account")
    private WebElement accountHeaderName;

    @FindBy (css = ".ico-logout")
    private WebElement logoutButton;

    @FindBy (css = ".top-menu a[href*='computers'] ~ .sublist li a")
    private List<WebElement> computersGroupList;

    @FindBy (css = ".item-box")
    private List<WebElement> itemList;

    @FindBy (css = ".header-links .ico-wishlist")
    private WebElement wishlistCounter;

    @FindBy (css = ".header-links .ico-cart")
    private  WebElement shoppingCartCounter;

    public WebShopLoginPage clickLoginButton() {
        loginButton.click();
        return new WebShopLoginPage();
    }

    public String getCurrentLoginAccountName() {
        return accountHeaderName.getText();
    }

    public void logout() {
        logoutButton.click();
    }

    public List<String> getComputersGroupListName() {
        List<String> list = computersGroupList.stream()
                .map(f -> f.getAttribute(HREF_ATTR))
                .collect(Collectors.toList());
        list = list.stream()
                .map(g -> g.substring(g.indexOf("com/") + 4))
                .collect(Collectors.toList());
        return list;
    }

    public void clickOnHeaderMenuItem(String itemName) {
        driver.findElement(topMenuItemNameLocator(itemName)).click();
    }

    public WebShopItemPage clickOnItemByIndex(int index) {
        itemList.get(index).click();
        return new WebShopItemPage();
    }

    public WebShopCartPage clickWishlistCounter() {
        wishlistCounter.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new WebShopCartPage();
    }

    public WebShopCartPage clickShoppingCartCounter() {
        shoppingCartCounter.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new WebShopCartPage();
    }

    private By.ByCssSelector topMenuItemNameLocator(String itemName) {
        return new By.ByCssSelector(".top-menu a[href*='" + itemName + "']");
    }
}

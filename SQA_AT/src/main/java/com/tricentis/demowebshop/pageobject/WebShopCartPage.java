package com.tricentis.demowebshop.pageobject;

import general.BaseGeneralPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebShopCartPage extends BaseGeneralPage {

    @FindBy (css = ".order-summary-content")
    private WebElement orderSummaryContent;

    @FindBy (css = ".cart-item-row")
    private List<WebElement> cartItem;

    @FindBy (css = "[name=removefromcart]")
    private WebElement removeFromCartCheckbox;

    @FindBy (css = "[name=updatecart]")
    private WebElement updateCartButton;

    @FindBy (id = "termsofservice")
    private WebElement termsOfServiceCheckbox;

    @FindBy (id = "checkout")
    private WebElement checkoutButton;

    public String getOrderSummaryContentMessage() {
         return orderSummaryContent.getText().trim();
    }

    public int getCartItemNumber() {
        return cartItem.size();
    }

    public void removeCartItem() {
        removeFromCartCheckbox.click();
        updateCartButton.click();
    }

    public void checkCheckoutItem() {
        termsOfServiceCheckbox.click();
        checkoutButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/onepagecheckout"));
    }
}

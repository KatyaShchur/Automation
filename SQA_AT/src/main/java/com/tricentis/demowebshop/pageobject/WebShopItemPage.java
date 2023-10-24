package com.tricentis.demowebshop.pageobject;

import general.BaseGeneralPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class WebShopItemPage extends BaseGeneralPage {

    @FindBy (id = "products-pagesize")
    private WebElement productsPageSizeSelect;

    @FindBy (id = "products-orderby")
    private WebElement productOrderBySelect;

    @FindBy (css = ".item-box")
    private List<WebElement> itemList;

    @FindBy (css = "[id*=add-to-wishlist-button]")
    private WebElement addToWishlistButton;

    @FindBy (css = "[id*=add-to-cart-button]")
    private WebElement addToCartButton;

    @FindBy (css = ".actual-price")
    private List<WebElement> itemPriceList;

    public int selectPageSize(int pageSize) {
        new Select(productsPageSizeSelect).selectByVisibleText(String.valueOf(pageSize));
        return itemList.size();
    }

    public List<String> selectOrderBy(String orderBy) {
        new Select(productOrderBySelect).selectByVisibleText(orderBy);
        return itemPriceList.stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickAddToWishListButton() {
        addToWishlistButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }
}

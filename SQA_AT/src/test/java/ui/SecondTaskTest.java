package ui;

import com.google.common.collect.Ordering;
import com.tricentis.demowebshop.models.LoginModel;
import com.tricentis.demowebshop.pageobject.*;
import general.NavigationPO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SecondTaskTest extends BaseTest {

    private static final String COMPUTERS = "computers";
    private static final String APPAREL_SHOES = "apparel-shoes";
    private static final String BASE_URL = "https://demowebshop.tricentis.com/";
    private static final String CHECKOUT_URL = "https://demowebshop.tricentis.com/onepagecheckout";

    @BeforeMethod
    public void openMainSite() {
        NavigationPO navigation = new NavigationPO();
        navigation.navigateToUrl(BASE_URL);
    }

    @Test(enabled = false)
    public void registerUser() {
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        WebShopLoginPage webShopLoginPage = webShopMainPage.clickLoginButton();
        WebShopRegisterPage webShopRegisterPage = webShopLoginPage.clickRegisterButton();
        String firstName = "john";
        String lastName = "smit";
        String email = "john_smit2@gmail.com";
        String password = "Qwerty123!";
        webShopRegisterPage.registerUser(firstName, lastName, email, password);
        new NavigationPO().navigateToUrl(BASE_URL);
        assertEquals(new WebShopMainPage().getCurrentLoginAccountName(),
                email, "Incorrect email name");
    }

    @Test
    public void loginUser() {
        String email = "john_smit@gmail.com";
        String password = "Qwerty123!";
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        WebShopLoginPage webShopLoginPage = webShopMainPage.clickLoginButton();
        webShopMainPage = webShopLoginPage.login(new LoginModel(email, password));
        assertEquals(webShopMainPage.getCurrentLoginAccountName(),
                email, "Incorrect email name");
    }

    @Test
    public void verifyComputersGroupListName() {
        loginUser();
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        List<String> list = webShopMainPage.getComputersGroupListName();
        assertEquals(list.size(), 3);
        assertEqualsNoOrder(list,
                List.of("desktops", "notebooks", "accessories"),
                "Incorrect computers group list");
    }

    @Test
    public void verifyChangingNumberOfItemsOnPage() {
        loginUser();
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        webShopMainPage.clickOnHeaderMenuItem(COMPUTERS);
        WebShopItemPage webShopItemPage = webShopMainPage.clickOnItemByIndex(0);
        int numberOfItemsOnPage = webShopItemPage.selectPageSize(4);
        assertEquals(numberOfItemsOnPage, 4);
        numberOfItemsOnPage = webShopItemPage.selectPageSize(8);
        assertEquals(numberOfItemsOnPage, 6);
    }

    @Test
    public void verifySortingItems() {
        loginUser();
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        webShopMainPage.clickOnHeaderMenuItem(COMPUTERS);
        WebShopItemPage webShopItemPage = webShopMainPage.clickOnItemByIndex(0);
        int numberOfItemsOnPage = webShopItemPage.selectPageSize(4);
        assertEquals(numberOfItemsOnPage, 4);
        List<String> itemList = webShopItemPage.selectOrderBy("Price: Low to High");
        List<Double> priceList = itemList.stream()
                .map(Double::parseDouble)
                .toList();
        assertTrue(Ordering.natural().isOrdered(priceList));
        itemList = webShopItemPage.selectOrderBy("Price: High to Low");
        priceList = itemList.stream()
                .map(Double::parseDouble)
                .toList();
        assertTrue(Ordering.natural().reverse().isOrdered(priceList));
    }

    @Test
    public void addItemToWishlist() {
        loginUser();
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        webShopMainPage.clickOnHeaderMenuItem(APPAREL_SHOES);
        WebShopItemPage webShopItemPage = webShopMainPage.clickOnItemByIndex(0);
        webShopItemPage.clickAddToWishListButton();
        WebShopCartPage webShopCartPage = webShopMainPage.clickWishlistCounter();
        assertEquals(webShopCartPage.getCartItemNumber(), 1);
    }

    @Test
    public void addToCart() {
        loginUser();
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        webShopMainPage.clickOnHeaderMenuItem(APPAREL_SHOES);
        WebShopItemPage webShopItemPage = webShopMainPage.clickOnItemByIndex(0);
        webShopItemPage.clickAddToCartButton();
        WebShopCartPage webShopCartPage = webShopMainPage.clickShoppingCartCounter();
        assertEquals(webShopCartPage.getCartItemNumber(), 1);
    }

    @Test
    public void removeFromCart() {
        loginUser();
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        webShopMainPage.clickOnHeaderMenuItem(APPAREL_SHOES);
        WebShopItemPage webShopItemPage = webShopMainPage.clickOnItemByIndex(0);
        webShopItemPage.clickAddToCartButton();
        WebShopCartPage webShopCartPage = webShopMainPage.clickShoppingCartCounter();
        webShopCartPage.removeCartItem();
        assertEquals(webShopCartPage.getOrderSummaryContentMessage(), "Your Shopping Cart is empty!");
    }

    @Test
    public void checkCheckoutItem() {
        loginUser();
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        webShopMainPage.clickOnHeaderMenuItem(APPAREL_SHOES);
        WebShopItemPage webShopItemPage = webShopMainPage.clickOnItemByIndex(0);
        webShopItemPage.clickAddToCartButton();
        WebShopCartPage webShopCartPage = webShopMainPage.clickShoppingCartCounter();
        assertEquals(webShopCartPage.getCartItemNumber(), 1);
        webShopCartPage.checkCheckoutItem(CHECKOUT_URL);
    }

    @AfterMethod
    public void logout() {
        WebShopMainPage webShopMainPage = new WebShopMainPage();
        webShopMainPage.logout();
    }
}

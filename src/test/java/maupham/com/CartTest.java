package maupham.com;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @BeforeMethod
    public void beforeTest() throws Exception {
        setUp();
    }

    @Test
    public void testAddProductToCartSuccessfully() throws InterruptedException {
        var loginPage = new LoginPage(driver);
        loginPage.loginAuto();
        var productPage = new ProductPage(driver);
        var productItems = productPage.getProductItem();

        for (int i = 0; i < (productItems.size() - 2); i++) {
            var productItemPage = productItems.get(i);
            var expectedName = productItemPage.getName();
            var expectedPrice = productItemPage.getPrice();

            productItemPage.clickBtnAdd();
            productPage.clickShoppingCart();

            var cartPage = new CartPage(driver);
            var cartItems = cartPage.getCartItem();
            var cartItemPage = cartItems.get(0);

            var actualName = cartItemPage.getName();
            var actualPrice = cartItemPage.getPrice();

            Assert.assertEquals(actualPrice, expectedPrice);
            Assert.assertEquals(actualName, expectedName);

           // cartItemPage.removeShoppingCart();
           // Thread.sleep(1000);
            cartPage.clickContinueShopping();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

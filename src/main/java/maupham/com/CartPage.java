package maupham.com;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CartPage extends BasePage {
   
    private final By byBtncontinueShopping;
    private final By byCartItems;
    private final By byShoppingCart;
    private final By byYourCart;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.byBtncontinueShopping = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");
        this.byCartItems = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='test-Item'])");
        this.byShoppingCart = AppiumBy.xpath("//android.widget.TextView");
        this.byYourCart = AppiumBy.xpath("//android.widget.TextView[@text='YOUR CART']");
    }

    public void clickContinueShopping() {
        scrollDown();
        driver.findElement(byBtncontinueShopping).click();
    }

    public int getShoppingCart() {
        try {
            var number = driver.findElement(byShoppingCart).getText();
            return Integer.parseInt(number);
        } catch (Exception e) {
            return 0;
        }
    }

    public void isDisplayedOk() {
        driver.findElement(byYourCart).isDisplayed();
    }

    public List<CartItemPage> getCartItem() {
        var elements = driver.findElements(byCartItems);
        if (elements.isEmpty()) {
            return new ArrayList<>();
        }
        var cartItems = new ArrayList<CartItemPage>();
        for (int i = 0; i < elements.size(); i++) {
            var cartItemPage = new CartItemPage(driver, i);
            cartItems.add(cartItemPage);
        }
        return cartItems;
    }
}

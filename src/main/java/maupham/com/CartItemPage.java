package maupham.com;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CartItemPage extends BasePage {
    private final By byName;
    private final By byPrice;
    private final By byBtnRemove;

    public CartItemPage(AndroidDriver driver, int idex) {
        super(driver);
        this.byName = AppiumBy.androidUIAutomator("new UiSelector().description(\"test-Description\").childSelector(new UiSelector().className(\"android.widget.TextView\"))");
        this.byPrice = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"$\")");
        this.byBtnRemove = AppiumBy.accessibilityId("test-REMOVE");
    }

    public String getName() {
        return driver.findElement(byName).getText();
    }

    public Float getPrice() {
        var price = (driver.findElement(byPrice).getText()).replaceAll("[^0-9.]", "");
        return Float.parseFloat(price);
    }
    
    public void removeShoppingCart() {
        driver.findElement(byBtnRemove).click();
    }
}

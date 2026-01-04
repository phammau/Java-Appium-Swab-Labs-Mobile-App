package maupham.com;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ProductItemPage extends BasePage {
    private final By byImage;
    private final By byName;
    // private final By byBtnAdd;
    private final By byPrice;

    public ProductItemPage(AndroidDriver driver, int index) {
        super(driver);
        index ++;
        this.byImage = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='test-Item'])/android.view.ViewGroup/android.widget.ImageView");
        this.byName = AppiumBy.accessibilityId("test-Item title");
        this.byPrice = AppiumBy.accessibilityId("test-Price");
        // this.byBtnAdd = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])");
    }

    public String getImage() {
        return driver.findElement(byImage).getAttribute("class");
    }

    public String getName() {
        return driver.findElement(byName).getText();
    }

    public Float getPrice() {
        var price = (driver.findElement(byPrice).getText()).replaceAll("[^0-9.]", "");
        return Float.parseFloat(price);
    }

    public void scrollToAdd() {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().text(\"ADD TO CART\"))"));
    }

    public void clickBtnAdd() {
       driver.findElement(
            AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"ADD TO CART\"))"
        )
        ).click();
    }

    public void clickProductItem(int index) {
        driver.findElement(byImage).click();
    }

}

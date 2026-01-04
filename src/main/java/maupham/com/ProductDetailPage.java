package maupham.com;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ProductDetailPage extends BasePage {
    private final By byImage;
    private final By byName;
    private final By byBtnAdd;
    private final By byPrice;
    private final By byBtnBackToProduct;

    public ProductDetailPage(AndroidDriver driver) {
        super(driver);
        this.byImage = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Image Container']/android.widget.ImageView");
        this.byName = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView)[1]");
        this.byPrice = AppiumBy.accessibilityId("test-Price");
        this.byBtnAdd = AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']");
        this.byBtnBackToProduct = AppiumBy.accessibilityId("test-BACK TO PRODUCTS");
    }
    public String getImage() {
        return driver.findElement(byImage).getAttribute("class");
    }

    public String getName() throws InterruptedException {
        Thread.sleep(500);
        return driver.findElement(byName).getText();
    }

    public Float getPrice() {
        var price = (driver.findElement(byPrice).getText()).replaceAll("[^0-9.]", "");
        return Float.parseFloat(price);
    }

    public void clickBtnAdd() {
        if (driver.findElement(byBtnAdd) != null) {
            driver.findElement(byBtnAdd).click();
        }
    }
    public void clickBackToProduct() {
       driver.findElement(byBtnBackToProduct).click();
    }
}

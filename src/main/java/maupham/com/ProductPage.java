package maupham.com;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ProductPage extends BasePage {
    private final By byTitle;
    private final By byShoppingCart;
    private final By byProductItem;
    private final By byBtnAdd;
    private final By byBtnRemove;
    private final By byBtnListView;
    private final By byBtnGridView;
    private final By bySortNameZToA;
    private final By bySortPriceLowToHigh;
    private final By bySortPriceHighToLow;
    private final By byBtnFilter;

    public ProductPage(AndroidDriver driver) {
        super(driver);
        this.byTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]"); //xpath phai khai bao By
        this.byShoppingCart = AppiumBy.xpath("//android.widget.TextView");
        this.byProductItem = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Item']");
        this.byBtnAdd = AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']");
        this.byBtnRemove = AppiumBy.xpath("//android.widget.TextView[@text='REMOVE']");
        this.byBtnListView = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Toggle\"]/android.widget.ImageView");
        this.byBtnGridView = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Toggle\"]/android.widget.ImageView");
        this.bySortNameZToA = AppiumBy.xpath("//android.widget.TextView[@text='Name (Z to A)']");
        this.bySortPriceLowToHigh = AppiumBy.xpath("//android.widget.TextView[@text='Price (low to high)']");
        this.bySortPriceHighToLow = AppiumBy.xpath("//android.widget.TextView[@text='Price (high to low)']");
        this.byBtnFilter = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Modal Selector Button']/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView");
       
    }

    public boolean isDisplayedOk() {
        return driver.findElement(byTitle).isDisplayed();
    }

    public int getShoppingCart() {
        try {
            var number = driver.findElement(byShoppingCart).getText();
            return Integer.parseInt(number);
        } catch (Exception e) {
            return 0;
        }
        
    }

    // public boolean scrollDown() {
    //     try {
    //         driver.findElement(
    //             AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
    //         return true;
    //     } catch (Exception e) {
    //         return false;
    //     }
    // }
    
    public boolean scrollUp() {
        try {
            driver.findElement(
                AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void addAllProductsToCart() {
        int lastCartCount = -1;

        while (true) {
            List<WebElement> addButtons = driver.findElements(byBtnAdd);
            for (WebElement btn : addButtons) {
                if (btn.isDisplayed()) {
                    btn.click();
                }
            }

            int currentCart = getShoppingCart();
            if (currentCart == lastCartCount) {
                break; 
            }

            lastCartCount = currentCart;
            scrollDown();
        }
    }

    public void removeAllProductToCart() {
        int lastCartCount = -1;

        while (true) {
            List<WebElement> removeButtons = driver.findElements(byBtnRemove);
            if (removeButtons.isEmpty()) {
                break;
            }

            for (WebElement btn : removeButtons) {
                if (btn.isDisplayed()) {
                    btn.click();
                }
            }

            int currentCart = getShoppingCart();
            if (currentCart == lastCartCount || currentCart == 0) {
                break;
            }

            lastCartCount = currentCart;
            scrollUp();
        }
    }

    public void clickBtnListView() {
        driver.findElement(byBtnListView).click();
    }

    public void clickBtnGridView() {
        driver.findElement(byBtnGridView).click();
    }

    public boolean isDisplayedGridViewOk() {
        return driver.findElement(byBtnGridView).isDisplayed();
    }

    //android khong co dung Select
    public void sortProductByNameZToA() throws InterruptedException {
        driver.findElement(byBtnFilter).click();
        Thread.sleep(500);
        driver.findElement(bySortNameZToA).click();
        Thread.sleep(500);
    }

    public void sortProductByPriceLowToHigh() throws InterruptedException {
        driver.findElement(byBtnFilter).click();
        Thread.sleep(500);
        driver.findElement(bySortPriceLowToHigh).click();
    }

    public void sortProductByPriceHighToLow() {
        driver.findElement(byBtnFilter).click();
        driver.findElement(bySortPriceHighToLow).click();
    }

    public List<ProductItemPage> getProductItem() {
        var elements = driver.findElements(byProductItem);
        if (elements.isEmpty()) {
            return new ArrayList<>();
        }
        var productItems = new ArrayList<ProductItemPage>();
        for (int i = 0; i < elements.size(); i++) {
            var productItemPage = new ProductItemPage(driver, i);// co the dung index thay cho element
            productItems.add(productItemPage);
        }
        return productItems;
    }

    public void clickShoppingCart() {
        driver.findElement(byShoppingCart).click();
    }
}

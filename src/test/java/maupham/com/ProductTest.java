package maupham.com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @BeforeMethod
    public void beforeTest() throws Exception {
        setUp();
    }

    @Test
    public void testAddToCart() throws InterruptedException {
        var loginPage = new LoginPage(driver);
        loginPage.loginAuto();

        var productPage = new ProductPage(driver);
        Thread.sleep(5000);
        productPage.addAllProductsToCart();
        Assert.assertEquals(productPage.getShoppingCart(), 6);
    }

    @Test
    public void testRemoveCart() throws InterruptedException {
        var loginPage = new LoginPage(driver);
        loginPage.loginAuto();
        var productPage = new ProductPage(driver);
        productPage.addAllProductsToCart();

        productPage.removeAllProductToCart();
        Assert.assertEquals(productPage.getShoppingCart(), 0);
    }

    @Test
    public void testDisplayProductGridView() throws InterruptedException {
        var loginPage = new LoginPage(driver);
        loginPage.loginAuto();
        var productPage = new ProductPage(driver);
        productPage.clickBtnListView();
        Assert.assertTrue(productPage.isDisplayedGridViewOk());
    }

    @Test
    public void testDisplayProductListView() throws InterruptedException {
        var loginPage = new LoginPage(driver);
        loginPage.loginAuto();
        var productPage = new ProductPage(driver);
        productPage.clickBtnListView();
        productPage.clickBtnGridView();
        Assert.assertTrue(productPage.isDisplayedOk());
    }

    @Test
    public void testSortProductByPriceLowToHigh() throws InterruptedException {
        var loginPage = new LoginPage(driver);
        loginPage.loginAuto();
        var productPage = new ProductPage(driver);
        var productItems = productPage.getProductItem();
        var expected = new ArrayList<>(productItems.stream().map(ProductItemPage::getPrice).collect(Collectors.toList()));

        productPage.sortProductByPriceLowToHigh();
        var actualProductItems = productPage.getProductItem();// lay lai ds sau khi sort
        var actual = new ArrayList<>(actualProductItems.stream().map(ProductItemPage::getPrice).collect(Collectors.toList()));

        Assert.assertFalse(actual.equals(expected));

        expected.sort(Comparator.naturalOrder());// low to high
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void testSortProductByPriceHighToLow() throws InterruptedException {
        var loginPage = new LoginPage(driver);
        loginPage.loginAuto();
        var productPage = new ProductPage(driver);
        var productItems = productPage.getProductItem();
        var expected = productItems.stream().map(ProductItemPage::getPrice).collect(Collectors.toList());

        productPage.sortProductByPriceHighToLow();
        var actualProductItems = productPage.getProductItem();
        var actual = new ArrayList<>(actualProductItems.stream().map(ProductItemPage::getPrice).collect(Collectors.toList()));

        Assert.assertFalse(actual.equals(expected));

        expected.sort(Comparator.reverseOrder());
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void testSortProductByNameZToA() throws InterruptedException {
        var loginPage = new LoginPage(driver);
        loginPage.loginAuto();
        var productPage = new ProductPage(driver);
        var productItems = productPage.getProductItem();//lay ds ban dau
        var expected = new ArrayList<>(productItems.stream().map(ProductItemPage::getName).collect(Collectors.toList()));

        productPage.sortProductByNameZToA();// sort tren UI
        var actual = new ArrayList<>(productItems.stream().map(ProductItemPage::getName).collect(Collectors.toList()));

        Assert.assertFalse(actual.equals(expected));

        expected.sort(Comparator.reverseOrder());
        Assert.assertTrue(actual.equals(expected));
    }
   
    @Test
    public void testProductInformation() throws InterruptedException {
        var loginPage = new LoginPage(driver);
        loginPage.loginAuto();
        var productPage = new ProductPage(driver);
        var totalProducts = productPage.getProductItem();

        for (int i = 0; i < totalProducts.size(); i++) {
            var productItems = productPage.getProductItem();
            var productItemPage = productItems.get(i);
            
            var expectedName = productItemPage.getName();
            var expectedImage = productItemPage.getImage();
            var expectedPrice = productItemPage.getPrice();

            productItemPage.clickProductItem(i);
            var productDetailPage = new ProductDetailPage(driver);

            var actualName = productDetailPage.getName();
            var actualImage = productDetailPage.getImage();
            var actualPrice = productDetailPage.getPrice();

            Assert.assertEquals(actualPrice, expectedPrice);
            Assert.assertEquals(actualImage, expectedImage);
            Assert.assertEquals(actualName, expectedName);

            productDetailPage.clickBackToProduct();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

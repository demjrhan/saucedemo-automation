package smoke;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.Collections;
import java.util.stream.Collectors;

public class HomePageSmokeTest extends BaseTest {


    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Product list should have at least one product.")
    public void thereMustBeAtLeastOneProduct() {
        var loginPage = new LoginPage(driver);

        var result = loginPage.open()
                .loginHappyPath()
                .getProducts();
        Assert.assertFalse(result.isEmpty(), "There should be at least one product");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Product has a image.")
    public void productShouldHaveImage() {
        var loginPage = new LoginPage(driver);

        var homePage = loginPage.open()
                .loginHappyPath();
        var firstProduct = homePage.getFirstProduct();
        var result = homePage.productHasImage(firstProduct);

        Assert.assertTrue(result, firstProduct + " should have an image.");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("First product should have a description.")
    public void firstProductShouldHaveDescription() {
        var loginPage = new LoginPage(driver);

        var homePage = loginPage.open()
                .loginHappyPath();
        var firstProduct = homePage.getFirstProduct();
        var result = homePage.productHasDescription(firstProduct);

        Assert.assertTrue(result, firstProduct + " should have an description.");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("First product description is longer than 5 character.")
    public void firstProductDescriptionShouldBeLongerThan5Characters() {
        var loginPage = new LoginPage(driver);

        var homePage = loginPage.open()
                .loginHappyPath();
        var firstProduct = homePage.getFirstProduct();
        var result = homePage.getProductDescription(firstProduct);

        Assert.assertTrue(result.length() > 5, firstProduct + " should have at least 5 character long description.");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Every product should have a description.")
    public void everyProductShouldHaveDescription() {
        var loginPage = new LoginPage(driver);

        var homePage = loginPage.open()
                .loginHappyPath();
        homePage.getProducts().forEach(product -> {
            Assert.assertFalse(homePage.getProductDescription(product).isEmpty(), product + " should have an description.");
        });
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Every product has add to cart button.")
    public void addToCartButtonExistsForAllProducts() {
        var loginPage = new LoginPage(driver);

        var homePage = loginPage.open()
                .loginHappyPath();
        homePage.getProducts().forEach(product -> {
            Assert.assertFalse(homePage.isProductHasAddToCartButton(product), product + " should have an description.");
        });
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add to cart button works for products.")
    public void clickingAddToCartButtonWorks() {
        var loginPage = new LoginPage(driver);

        var homePage = loginPage.open()
                .loginHappyPath();
        homePage.getProducts().forEach(homePage::clickAddToCartButton);
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sorting from A-Z to Z-A should work as expected.")
    public void sortingFromAZShouldBringProductsInAscendingOrder() {
        var loginPage = new LoginPage(driver);

        var homePage = loginPage.open()
                .loginHappyPath();

        var products = homePage.getProducts();
        var productTitles = products.stream().map(homePage::getProductTitle).toList();

        var productsAfterSortZA = homePage.clickProductSortZA().getProducts();
        var productTitlesAfterSortZA = productsAfterSortZA.stream().map(homePage::getProductTitle).toList();

        Assert.assertNotEquals(productTitles,productTitlesAfterSortZA, "Sorting from A-Z to Z-A did not work as expected.");
    }
}

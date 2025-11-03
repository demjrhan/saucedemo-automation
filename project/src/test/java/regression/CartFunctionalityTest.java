package regression;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class CartFunctionalityTest extends BaseTest {

    @Test(groups = "regression")
    @Story("Shopping cart functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Cart badge should display correct count when items are added to cart.")
    public void cartBadgeShowsCorrectCount() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();

        // Add 2 products to cart
        var product1 = homePage.getFirstProduct();
        homePage.clickAddToCartButton(product1);

        var product2 = homePage.getRandomProduct();
        homePage.clickAddToCartButton(product2);

        // Verify cart badge is visible and shows correct count
        Assert.assertTrue(homePage.hasCartBadge(), "Cart badge should be visible after adding items.");
        String badgeCount = homePage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, "2", "Cart badge should show 2 items.");
    }

    @Test(groups = "regression")
    @Story("Shopping cart functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Cart badge count should increase when adding multiple items.")
    public void cartBadgeIncrementsCorrectly() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();

        // Add first product and verify badge appears with count 1
        homePage.clickAddToCartButton(homePage.getFirstProduct());
        Assert.assertTrue(homePage.hasCartBadge(), "Cart badge should appear after first item.");
        Assert.assertEquals(homePage.getCartBadgeCount(), "1", "Cart should show 1 item.");

        // Add second product and verify badge count increases to 2
        homePage.clickAddToCartButton(homePage.getRandomProduct());
        Assert.assertEquals(homePage.getCartBadgeCount(), "2", "Cart should show 2 items.");

        // Add third product if available and verify badge count increases to 3
        var products = homePage.getProducts();
        if (products.size() >= 3) {
            homePage.clickAddToCartButton(products.get(2));
            Assert.assertEquals(homePage.getCartBadgeCount(), "3", "Cart should show 3 items.");
        }
    }

    @Test(groups = "regression")
    @Story("Shopping cart functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Cart badge should not appear when cart is empty.")
    public void cartBadgeNotVisibleWhenEmpty() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();

        // Verify cart badge is not visible initially
        Assert.assertFalse(homePage.hasCartBadge(), "Cart badge should not be visible when cart is empty.");
    }

    @Test(groups = "regression")
    @Story("Shopping cart functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Remove button should appear after adding item to cart.")
    public void removeButtonAppearsAfterAddToCart() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();

        var product = homePage.getFirstProduct();
        
        // Verify remove button not visible initially
        Assert.assertFalse(homePage.isProductHasRemoveFromCartButton(product),
                "Remove button should not be visible before adding to cart.");

        // Add to cart
        homePage.clickAddToCartButton(product);

        // Verify remove button is now visible
        Assert.assertTrue(homePage.isProductHasRemoveFromCartButton(product),
                "Remove button should be visible after adding to cart.");
    }
}


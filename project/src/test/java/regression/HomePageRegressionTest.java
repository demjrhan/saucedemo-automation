package regression;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;


public class HomePageRegressionTest extends BaseTest {

    @Test(groups = "regression")
    @Story("User logs in with valid user credential and adds a item to cart.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Right credentials login successfully, homepage contains items and addable to cart.")
    public void loginAndClickAddToCartForRandomItem() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();
        Assert.assertTrue(homePage.atHomePage(), "Home page should be opened.");
        var product = homePage.getRandomProduct();
        homePage.clickAddToCartButton(product);
        Assert.assertTrue(homePage.isProductHasRemoveFromCartButton(product), String.format("Product %s is not added to the cart.", product));
    }
}

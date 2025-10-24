package smoke;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageSmokeTest extends BaseTest {


    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Product list should have at least one product.")
    public void thereMustBeAtLeastOneProduct(){
        var result = loginPage.open().loginHappyPath().getProducts();
        Assert.assertFalse(result.isEmpty(), "There should be at least one product");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Product has a image.")
    public void productShouldHaveImage(){
        var homePage = loginPage.open().loginHappyPath();
        var firstProduct = homePage.getFirstProduct();
        var result = homePage.productHasImage(firstProduct);

        Assert.assertTrue(result, firstProduct + " should have an image.");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Product has a description.")
    public void productShouldHaveDescription(){
        var homePage = loginPage.open().loginHappyPath();
        var firstProduct = homePage.getFirstProduct();
        var result = homePage.productHasDescription(firstProduct);

        Assert.assertTrue(result, firstProduct + " should have an description.");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Products description is longer than 5 character.")
    public void productsDescriptionShouldBeLongerThan5Characters(){
        var homePage = loginPage.open().loginHappyPath();
        var firstProduct = homePage.getFirstProduct();
        var result = homePage.getProductDescription(firstProduct);

        Assert.assertTrue(result.length() > 5, firstProduct + " should have at least 5 character long description.");
    }
}

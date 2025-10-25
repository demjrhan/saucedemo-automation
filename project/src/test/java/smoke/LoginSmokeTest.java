package smoke;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginSmokeTest extends BaseTest {


    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that username field is visible.")
    public void usernameFieldExists() {
        var loginPage = new LoginPage(driver);
        var result = loginPage.open()
                .isUserNameFieldVisible();

        Assert.assertTrue(result, "Username field should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that password field is visible.")
    public void passwordFieldExists() {
        var loginPage = new LoginPage(driver);
        var result = loginPage.open()
                .isPasswordFieldVisible();

        Assert.assertTrue(result, "Password field should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that login button is visible.")
    public void loginButtonExists() {
        var loginPage = new LoginPage(driver);
        var result = loginPage.open()
                .isLoginButtonVisible();

        Assert.assertTrue(result, "Login button should be visible.");
    }


    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Website should provide at least 1 correct username.")
    public void getCorrectUsernames() {
        var loginPage = new LoginPage(driver);
        var result = loginPage.open()
                        .getCorrectUsernames();

        Assert.assertFalse(result.isEmpty(), "Accepted usernames should not be empty.");
    }
    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Website should provide at least 1 correct password.")
    public void getCorrectPasswords() {
        var loginPage = new LoginPage(driver);
        var result = loginPage.open()
                .getCorrectPassword();

        Assert.assertFalse(result.isEmpty(), "Accepted passwords should not be empty.");
    }
}

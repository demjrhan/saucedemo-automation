package smoke;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSmokeTest extends BaseTest {


    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that username field is visible.")
    public void usernameFieldExists() {
        var result = loginPage.open()
                .isUserNameFieldVisible();

        Assert.assertTrue(result, "Username field should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that password field is visible.")
    public void passwordFieldExists() {
        var result = loginPage.open()
                .isPasswordFieldVisible();

        Assert.assertTrue(result, "Password field should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that login button is visible.")
    public void loginButtonExists() {
        var result = loginPage.open()
                .isLoginButtonVisible();

        Assert.assertTrue(result, "Login button should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in without username and password should cause error..")
    public void emptyCredentialsCausesError() {
        var result = loginPage.open()
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in without username field should cause error.")
    public void emptyPasswordFieldCausesError() {
        var result = loginPage.open()
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in without username field should cause error.")
    public void emptyUsernameFieldCausesError() {
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCorrectUsername())
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Website should provide at least 1 correct username.")
    public void getCorrectUsernames() {
        var result = loginPage.open()
                        .getCorrectUsernames();

        Assert.assertFalse(result.isEmpty(), "Accepted usernames should not be empty.");
    }
    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Website should provide at least 1 correct password.")
    public void getCorrectPasswords() {
        var result = loginPage.open()
                .getCorrectPassword();

        Assert.assertFalse(result.isEmpty(), "Accepted passwords should not be empty.");
    }
}

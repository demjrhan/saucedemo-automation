package negative;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginNegativeTest extends BaseTest {

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in without username and password should cause error..")
    public void emptyCredentialsCausesError() {
        var result = loginPage.open()
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "negative")
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

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in without password field should cause error.")
    public void emptyUsernameFieldCausesError() {
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCorrectUsername())
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with correct username, incorrect password field should cause error.")
    public void correctUsernameIncorrectPasswordCausesError() {
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCorrectUsername())
                .writePassword(loginPage.getRandomCredential(5))
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertTrue(result.contains("password"), "Error message should not be empty.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with correct username, incorrect password field should cause error.")
    public void correctPasswordIncorrectUsernameCausesError() {
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCredential(5))
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertTrue(result.contains("username"), "Error message should not be empty.");
    }

}

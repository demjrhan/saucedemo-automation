package negative;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginNegativeTest extends BaseTest {

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in without username and password should cause error..")
    public void emptyCredentialsCausesError() {
        var loginPage = new LoginPage(driver);
        
        // Try to login without entering any credentials
        var result = loginPage.open()
                    .clickLoginButtonRaw()
                    .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in without username field should cause error.")
    public void emptyPasswordFieldCausesError() {
        var loginPage = new LoginPage(driver);
        
        // Try to login with password only (no username)
        var result = loginPage.open()
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in without password field should cause error.")
    public void emptyUsernameFieldCausesError() {
        var loginPage = new LoginPage(driver);
        
        // Try to login with username only (no password)
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCorrectUsername())
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with correct username, incorrect password field should cause error.")
    public void correctUsernameIncorrectPasswordCausesError() {
        var loginPage = new LoginPage(driver);
        
        // Try to login with correct username but incorrect password
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCorrectUsername())
                .writePassword(loginPage.getRandomCredential(5))
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message mentions password
        Assert.assertTrue(result.contains("password"), "Error message should contain 'password'.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with correct username, incorrect password field should cause error.")
    public void correctPasswordIncorrectUsernameCausesError() {
        var loginPage = new LoginPage(driver);
        
        // Try to login with incorrect username but correct password
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCredential(5))
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message mentions username
        Assert.assertTrue(result.contains("username"), "Error message should contain 'username'.");
    }

}

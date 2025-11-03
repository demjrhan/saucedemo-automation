package negative;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataProviders;

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

    @Test(dataProvider = "correctCredentials", dataProviderClass = DataProviders.class ,groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login should not work with username that has leading/trailing spaces (if trimmed).")
    public void loginWithSpacesAroundUsername(String username, String password) {
        var loginPage = new LoginPage(driver);

        // Open login page and try to login with spaces around username
        var result = loginPage.open()
                .writeUsername(" " + username + " ")
                .writePassword(password)
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify login succeeds (if username is trimmed)
        Assert.assertFalse(result.isEmpty(), "Login should not work if username spaces are trimmed.");
    }

    @Test(dataProvider = "correctCredentials", dataProviderClass = DataProviders.class, groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in without password field should cause error.")
    public void emptyUsernameFieldCausesError(String username, String password) {
        var loginPage = new LoginPage(driver);

        // Try to login with username only (no password)
        var result = loginPage.open()
                .writeUsername(username)
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(dataProvider = "correctUsernames", dataProviderClass = DataProviders.class, groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with correct username, incorrect password field should cause error.")
    public void correctUsernameIncorrectPasswordCausesError(String username) {
        var loginPage = new LoginPage(driver);

        // Try to login with correct username but incorrect password
        var result = loginPage.open()
                .writeUsername(username)
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

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Trying to log in with locked out user should cause error.")
    public void lockedOutUserCausesError() {
        var loginPage = new LoginPage(driver);

        // Try to login with locked out user credentials
        var result = loginPage.open()
                .writeUsername("locked_out_user")
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed for locked out user
        Assert.assertFalse(result.isEmpty(), "Error message should be displayed for locked out user.");
        Assert.assertTrue(result.contains("locked") || result.contains("sorry"),
                "Error message should indicate user is locked out.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with username containing special characters should cause error.")
    public void specialCharactersInUsernameCausesError() {
        var loginPage = new LoginPage(driver);

        // Try to login with username containing special characters
        var result = loginPage.open()
                .writeUsername("user@#$%")
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with very long username should cause error.")
    public void veryLongUsernameCausesError() {
        var loginPage = new LoginPage(driver);

        // Try to login with very long username (more than 100 characters)
        String longUsername = "a".repeat(150);
        var result = loginPage.open()
                .writeUsername(longUsername)
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(dataProvider = "correctUsernames", dataProviderClass = DataProviders.class, groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with very long password should cause error.")
    public void veryLongPasswordCausesError(String username) {
        var loginPage = new LoginPage(driver);

        // Try to login with very long password (more than 100 characters)
        String longPassword = "a".repeat(150);
        var result = loginPage.open()
                .writeUsername(username)
                .writePassword(longPassword)
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with username containing only spaces should cause error.")
    public void usernameWithOnlySpacesCausesError() {
        var loginPage = new LoginPage(driver);

        // Try to login with username containing only spaces
        var result = loginPage.open()
                .writeUsername("   ")
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(dataProvider = "correctUsernames", dataProviderClass = DataProviders.class, groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with password containing only spaces should cause error.")
    public void passwordWithOnlySpacesCausesError(String username) {
        var loginPage = new LoginPage(driver);

        // Try to login with password containing only spaces
        var result = loginPage.open()
                .writeUsername(username)
                .writePassword("   ")
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

    @Test(groups = "negative")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Trying to log in with both incorrect username and password should cause error.")
    public void bothIncorrectCredentialsCausesError() {
        var loginPage = new LoginPage(driver);

        // Try to login with both incorrect username and password
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCredential(8))
                .writePassword(loginPage.getRandomCredential(10))
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message is displayed
        Assert.assertFalse(result.isEmpty(), "Error message should not be empty.");
    }

}

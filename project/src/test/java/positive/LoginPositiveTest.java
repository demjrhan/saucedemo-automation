package positive;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataProviders;

public class LoginPositiveTest extends BaseTest {

    @Test(groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Correct password doesn't create error message.")
    public void correctPasswordDoesntCauseError() {
        var loginPage = new LoginPage(driver);
        
        // Open login page and enter correct password only (no username)
        var result = loginPage.open()
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message doesn't mention password (username error is expected)
        Assert.assertFalse(result.contains("password"), "Error message should not contain 'password'.");
    }

    @Test(groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Correct username doesn't create error message.")
    public void correctUsernameDoesntCauseError() {
        var loginPage = new LoginPage(driver);
        
        // Open login page and enter correct username only (no password)
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCorrectUsername())
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message doesn't mention username (password error is expected)
        Assert.assertFalse(result.contains("username"), "Error message should not contain 'username'.");
    }

    @Test(groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Correct username and password logins correctly.")
    public void correctPasswordAndCorrectUsernameLogins(){
        var loginPage = new LoginPage(driver);
        
        // Login with correct username and password
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCorrectUsername())
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButtonExpectingSuccess();

        // Verify user is redirected to home page
        Assert.assertTrue(result.atHomePage(), "Should be in home page.");
    }


    @Test(dataProvider = "credentials", dataProviderClass = DataProviders.class ,groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Correct username and password from data provider should work correctly.")
    public void correctUsernameAndPasswordFromDataProvider(String username, String password){
        var loginPage = new LoginPage(driver);
        
        // Login with credentials from data provider (tests all valid usernames)
        var result = loginPage.open()
                .writeUsername(username)
                .writePassword(password)
                .clickLoginButtonExpectingSuccess();

        // Verify user is redirected to home page for all valid credentials
        Assert.assertTrue(result.atHomePage(), "Should be in home page.");
    }
}

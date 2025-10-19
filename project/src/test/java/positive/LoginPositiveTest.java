package positive;

import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPositiveTest extends BaseTest {

    @Test(groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Correct password doesn't create error message.")
    public void correctPasswordDoesntCauseError() {
        var result = loginPage.open()
                .writePassword(loginPage.getCorrectPassword())
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertFalse(result.contains("password"), "Error message should not be empty.");
    }

    @Test(groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Correct username doesn't create error message.")
    public void correctUsernameDoesntCauseError() {
        var result = loginPage.open()
                .writeUsername(loginPage.getRandomCorrectUsername())
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertFalse(result.contains("username"), "Error message should not be empty.");
    }
}

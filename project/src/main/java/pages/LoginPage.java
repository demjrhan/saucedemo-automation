package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CredentialsUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LoginPage extends BasePage<LoginPage> {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By loginCredentials = By.cssSelector("[data-test='login-credentials']");
    private By passwordCredentials = By.cssSelector("[data-test='login-password']");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        waitUntilElementVisibility(usernameField);
        return this;
    }

    /* Split and skip first element is because of dom structure.
     * First element is `Accepted usernames are:` text */
    public String getRandomCorrectUsername() {
        var usernames = getText(loginCredentials);
        Random rand = new Random();
        String[] usernamesSplit = usernames.split("\n");
        var usernameIndex = rand.nextInt(usernamesSplit.length - 1) + 1;
        return usernamesSplit[usernameIndex];
    }

    public List<String> getCorrectUsernames() {
        var usernames = getText(loginCredentials);
        var usernamesSplit = usernames.split("\n");
        return Arrays.stream(usernamesSplit)
                .toList()
                .subList(0, usernamesSplit.length - 1);
    }

    public String getRandomCredential(int length) {
        return CredentialsUtils.getPassword(length);
    }

    public String getRandomCorrectUsernameUPPERCASE() {
        var correctUsername = getRandomCorrectUsername();
        return correctUsername.toUpperCase();
    }

    /* Split and last element access is because of dom structure.
     * First element is `Password for all users:` text */
    public String getCorrectPassword() {
        var password = getText(loginCredentials);
        String[] passwordSplit = password.split("\n");
        return passwordSplit[passwordSplit.length - 1];
    }

    public LoginPage writeUsername(String username) {
        sendKeys(usernameField, username);
        return this;
    }

    public LoginPage writePassword(String password) {
        sendKeys(passwordField, password);
        return this;
    }

    public LoginPage loginHappyPath() {
        writeUsername(getRandomCorrectUsername());
        writePassword(getCorrectPassword());
        clickLoginButton();
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessage).toLowerCase();
    }

    public LoginPage clickLoginButton() {
        click(loginButton);
        return this;
    }

    public boolean isUserNameFieldVisible() {
        return isVisible(usernameField);
    }

    public boolean isPasswordFieldVisible() {
        return isVisible(passwordField);
    }

    public boolean isLoginButtonVisible() {
        return isVisible(loginButton);
    }


}

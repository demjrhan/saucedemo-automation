package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    public String selectRandomCorrectUsername() {
        var usernames = getText(loginCredentials);
        Random rand = new Random();
        String[] usernamesSplit = usernames.split("\n");
        var usernameIndex = rand.nextInt(usernamesSplit.length - 1) + 1;
        return usernamesSplit[usernameIndex];
    }

    public String selectRandomIncorrectUsername() {
        var correctUsername = selectRandomCorrectUsername();
        return correctUsername + ".";
    }
    public String selectRandomCorrectUsernameUPPERCASE(){
        var correctUsername = selectRandomCorrectUsername();
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
        writeUsername(selectRandomCorrectUsername());
        writePassword(getCorrectPassword());
        clickLoginButton();
        return this;
    }

    public String getErrorMessage() {
        return findElementPresence(errorMessage).getText();
    }

    public LoginPage clickLoginButton() {
        waitUntilElementPresence(loginButton).scrollToElementSmooth(loginButton).click(loginButton);
        return this;
    }


}

package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage<LoginPage> {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By loginCredentials = By.cssSelector("[data-test='username']");
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
    public LoginPage writeUsername(String username) {
       sendKeys(usernameField,username);
       return this;
    }
    public LoginPage writePassword(String password) {
        sendKeys(passwordField,password);
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

package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.time.Duration;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    protected void clearTextField(By locator) {
        WebElement element = findElementVisibility(locator);
        String value = element.getAttribute("value");
        if (value != null && !value.isEmpty()) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        }
    }
    protected void clearTextField(WebElement element) {
        String value = element.getAttribute("value");
        if (value != null && !value.isEmpty()) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        }
    }
    protected void sendKeys(By locator, String text) {
        var element = findElementVisibility(locator);
        clearTextField(element);
        element.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element,"value"));
    }
    protected void sendEnter(By locator){
        var element = findElementVisibility(locator);
        element.sendKeys(Keys.ENTER);
    }
    protected void sendTab(By locator){
        var element = findElementVisibility(locator);
        element.sendKeys(Keys.TAB);
    }
    protected void sendSpace(By locator){
        var element = findElementVisibility(locator);
        element.sendKeys(Keys.SPACE);
    }
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    protected WebElement findElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    protected WebElement findElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected List<WebElement> findElementsPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    protected List<WebElement> findElementsVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    protected void waitUntilElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void waitUntilElementInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    protected void waitUntilElementPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    protected void scrollToElement(By locator) {
        var element = findElementsPresence(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    protected void scrollToElementSmooth(By locator) {
        var element = findElementsPresence(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
    }
    protected void scrollToElementSmooth(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
    }

}

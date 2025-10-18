package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    protected <T> T clearTextField(By locator) {
        WebElement element = findElementVisibility(locator);
        String value = element.getAttribute("value");
        if (value != null && !value.isEmpty())
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        return (T) this;
    }
    protected <T> T clearTextField(WebElement element) {
        String value = element.getAttribute("value");
        if (value != null && !value.isEmpty())
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);

        return (T) this;
    }
    protected <T> T sendKeys(By locator, String text) {
        var element = findElementVisibility(locator);
        clearTextField(element);
        element.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element,"value"));
        return (T) this;
    }
    protected <T> T sendEnter(By locator){
        var element = findElementVisibility(locator);
        element.sendKeys(Keys.ENTER);
        return (T) this;
    }
    protected <T> T sendTab(By locator){
        var element = findElementVisibility(locator);
        element.sendKeys(Keys.TAB);
        return (T) this;
    }
    protected <T> T sendSpace(By locator){
        var element = findElementVisibility(locator);
        element.sendKeys(Keys.SPACE);
        return (T) this;
    }
    protected <T> T click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        return (T) this;
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
    protected <T> T waitUntilElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return (T) this;
    }
    protected <T> T waitUntilElementInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return (T) this;
    }
    protected <T> T waitUntilElementPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return (T) this;
    }
    protected <T> T scrollToElement(By locator) {
        var element = findElementsPresence(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return (T) this;
    }
    protected <T> T scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return (T) this;
    }
    protected <T> T scrollToElementSmooth(By locator) {
        var element = findElementsPresence(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
        return (T) this;
    }
    protected <T> T scrollToElementSmooth(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
        return (T) this;
    }
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
    }
    protected String getTextInside(WebElement parent, By childLocator) {
        return findInside(parent, childLocator).getText().trim();
    }
    protected WebElement findInside(WebElement parent, By childLocator) {
        return wait.until(_ -> parent.findElement(childLocator));
    }
    protected boolean isEmpty(By locator) {
        WebElement el = findElementVisibility(locator);
        String value = el.getTagName().equalsIgnoreCase("input") || el.getTagName().equalsIgnoreCase("textarea")
                ? el.getAttribute("value")
                : el.getText();
        return value == null || value.isEmpty();
    }
    protected <T> T refresh() {
        driver.navigate().refresh();
        return (T) this;
    }

}

package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage<T extends BasePage<T>> {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
    }

    protected T clearTextField(By locator) {
        WebElement element = findElementVisibility(locator);
        String value = element.getAttribute("value");
        if (value != null && !value.isEmpty())
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        return (T) this;
    }

    protected T clearTextField(WebElement element) {
        String value = element.getAttribute("value");
        if (value != null && !value.isEmpty())
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);

        return (T) this;
    }

    protected T sendKeys(By locator, String text) {
        var element = findElementVisibility(locator);
        clearTextField(element);
        element.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
        return (T) this;
    }

    protected T sendEnter(By locator) {
        var element = findElementVisibility(locator);
        element.sendKeys(Keys.ENTER);
        return (T) this;
    }

    protected T sendTab(By locator) {
        var element = findElementVisibility(locator);
        element.sendKeys(Keys.TAB);
        return (T) this;
    }

    protected T sendSpace(By locator) {
        var element = findElementVisibility(locator);
        element.sendKeys(Keys.SPACE);
        return (T) this;
    }

    protected T click(By locator) {
        scrollToElementSmooth(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        return (T) this;
    }
    protected T click(WebElement element) {
        scrollToElementSmooth(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return (T) this;
    }

    protected T dragAndDrop(By from, By to) {
        var fromElement = findElementVisibility(from);
        var toElement = findElementVisibility(to);
        actions.dragAndDrop(fromElement, toElement).release().perform();
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

    protected T waitUntilElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return (T) this;
    }

    protected T waitUntilElementInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return (T) this;
    }

    protected T waitUntilElementPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return (T) this;
    }

    protected T scrollToElement(By locator) {
        var element = findElementPresence(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return (T) this;
    }

    protected T scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return (T) this;
    }

    protected T scrollToElementSmooth(By locator) {
        var element = findElementPresence(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
        return (T) this;
    }

    protected T scrollToElementSmooth(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
        return (T) this;
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText().trim();
    }

    protected String getTextInside(WebElement parent, By childLocator) {
        return findInside(parent, childLocator).getText().trim();
    }

    protected WebElement findInside(WebElement parent, By childLocator) {
        return wait.until(driver -> parent.findElement(childLocator));
    }

    protected boolean isEmpty(By locator) {
        WebElement el = findElementVisibility(locator);
        String value = el.getTagName().equalsIgnoreCase("input") || el.getTagName().equalsIgnoreCase("textarea")
                ? el.getAttribute("value")
                : el.getText();
        return value == null || value.isEmpty();
    }

    protected T refresh() {
        driver.navigate().refresh();
        return (T) this;
    }

    protected boolean isVisible(By locator) {
        return findElementVisibility(locator).isDisplayed();
    }
    protected boolean isEnabled(By locator) {
        return findElementVisibility(locator).isEnabled();
    }
    protected boolean isSelected(By locator) {
        return findElementVisibility(locator).isSelected();
    }
    protected boolean isExists(By locator) {
        return findElementPresence(locator).isDisplayed();
    }

}

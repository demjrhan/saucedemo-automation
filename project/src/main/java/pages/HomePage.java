package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage<HomePage> {

    private By inventoryContainer = By.id("inventory_list");
    private By firstProduct = By.cssSelector(".inventory_list:nth-child(1)");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean atHomePage() {
        return isVisible(inventoryContainer);
    }
    public List<WebElement> getProducts() {
        return findElementsPresence(inventoryContainer);
    }
    public WebElement getFirstProduct() {
        return findElementPresence(firstProduct);
    }
    public boolean productHasImage(WebElement product) {
        return findInside(product,By.tagName("img")).isDisplayed();
    }
    public boolean productHasDescription(WebElement product) {
        return findInside(product,By.cssSelector("[data-test='inventory-item-desc']")).isDisplayed();
    }
    public String getProductDescription(WebElement product) {
        return getTextInside(product,By.cssSelector("[data-test='inventory-item-desc']"));
    }

}

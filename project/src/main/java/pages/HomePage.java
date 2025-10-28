package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage<HomePage> {

    private By inventoryContainer = By.className("inventory_list");
    private By inventoryProducts = By.cssSelector(".inventory_list .inventory_item");
    private By firstProduct = By.cssSelector(".inventory_list:nth-child(1)");
    private By addToCartButtonGeneric = By.cssSelector("[data-test*='add-to-cart']");
    private By removeFromCartButtonGeneric = By.cssSelector("[data-test*='remove']");
    private By itemPrice = By.cssSelector("[data-test='inventory-item-price']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean atHomePage() {
        return isVisible(inventoryContainer);
    }

    public List<WebElement> getProducts() {
        return findElementsVisibility(inventoryProducts);
    }

    public WebElement getFirstProduct() {
        return findElementsPresence(inventoryProducts).getFirst();
    }

    public WebElement getRandomProduct() {
        var products = getProducts();
        Random random = new Random();
        return products.get(random.nextInt(products.size()));
    }

    public boolean productHasImage(WebElement product) {
        return findInside(product, By.tagName("img")).isDisplayed();
    }

    public boolean productHasDescription(WebElement product) {
        return findInside(product, By.cssSelector("[data-test='inventory-item-desc']")).isDisplayed();
    }

    public String getProductDescription(WebElement product) {
        return getTextInside(product, By.cssSelector("[data-test='inventory-item-desc']"));
    }

    public boolean isProductHasAddToCartButton(WebElement element) {
        return findInside(element, addToCartButtonGeneric).isDisplayed();
    }
    public boolean isProductHasRemoveFromCartButton(WebElement element) {
        return findInside(element, removeFromCartButtonGeneric).isDisplayed();
    }

    public void clickAddToCartButton(WebElement element) {
        click(findInside(element, addToCartButtonGeneric));
    }

    public String getItemPrice(WebElement product) {
        return getTextInside(product, itemPrice);
    }

}

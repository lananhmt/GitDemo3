package anhlan.pageObjects;

import anhlan.abstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class productCatalogue extends abstractComponent {
    private WebDriver driver;

    public productCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> productList;

    @FindBy(css = "b")
    WebElement productName;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productListBy = By.cssSelector(".mb-3");
    By addToCartBy = By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productListBy);
        return productList;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement product = getProductByName(productName);
        product.findElement(addToCartBy).click();
        waitForElementToAppear(By.cssSelector(".toast-container"));
        waitForElementToDisppear(spinner);
    }
}

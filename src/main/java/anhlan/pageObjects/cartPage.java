package anhlan.pageObjects;

import anhlan.abstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class cartPage extends abstractComponent {
    private WebDriver driver;

    public cartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[contains(@class,'ng-star-inserted')]")
    List<WebElement> cartList;

    public List<WebElement> getCartList() {
        return cartList;
    }

    public boolean verifyProductByName(String productName) {
        return getCartList().stream()
                .anyMatch(product -> product.findElement(By.xpath(".//div[@class='cartSection']/h3")).getText().equalsIgnoreCase(productName));
    }
}

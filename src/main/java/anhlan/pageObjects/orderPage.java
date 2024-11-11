package anhlan.pageObjects;

import anhlan.abstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class orderPage extends abstractComponent {
    private WebDriver driver;

    public orderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tr[@class='ng-star-inserted']")
    List<WebElement> orderList;

    public List<WebElement> getOrderList() {
        return orderList;
    }

    public boolean verifyOrderDisplay(String productName) {
        return getOrderList().stream()
                .anyMatch(product -> product.findElement(By.xpath("//tr[@class='ng-star-inserted']/td[2]")).getText().equalsIgnoreCase(productName));

    }
}

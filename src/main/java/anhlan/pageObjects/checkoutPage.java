package anhlan.pageObjects;

import anhlan.abstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class checkoutPage extends abstractComponent {
    private WebDriver driver;

    public checkoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countrySelect;

    public void selectIndia() {
        countrySelect.sendKeys("Ind");
        waitForElementToAppear(By.xpath("//section[contains(@class,'ta-results')]"));
        String key = Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER);
        countrySelect.sendKeys(key);
    }
}

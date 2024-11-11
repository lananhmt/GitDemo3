package anhlan.pageObjects;

import anhlan.abstractComponents.abstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class thanksPage extends abstractComponent {
    private WebDriver driver;

    public thanksPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement thanksTitle;

    public String getThanksTitle() {
        return thanksTitle.getText();
    }

    public boolean verifyThanksTitle() {
        return thanksTitle.getText().equalsIgnoreCase("Thankyou for the order.");
    }
}

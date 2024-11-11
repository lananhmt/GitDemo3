package anhlan.pageObjects;

import anhlan.abstractComponents.abstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage extends abstractComponent {
    private WebDriver driver;

    public landingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement loginBtn;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMsg;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public productCatalogue loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();
        return new productCatalogue(driver);
    }

    public String getErrorMsg() {
        waitForElementToAppear(errorMsg);
        return errorMsg.getText();
    }
}

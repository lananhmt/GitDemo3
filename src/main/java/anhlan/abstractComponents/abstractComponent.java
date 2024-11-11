package anhlan.abstractComponents;

import anhlan.pageObjects.cartPage;
import anhlan.pageObjects.checkoutPage;
import anhlan.pageObjects.orderPage;
import anhlan.pageObjects.thanksPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class abstractComponent {
        private WebDriver driver;

        public abstractComponent(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
        WebElement cartBtn;

        @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
        WebElement orderBtn;

        @FindBy(xpath = "//li[@class='totalRow']/button")
        WebElement checkoutBtn;

        @FindBy(xpath = "//a[contains(@class,'action__submit')]")
        WebElement placeOrderBtn;

        public void waitForElementToAppear(By findBy) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
        }

        public void waitForElementToAppear(WebElement element) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(element));
        }

        public void waitForElementToDisppear(WebElement element) throws InterruptedException {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//            wait.until(ExpectedConditions.invisibilityOf(element));
            Thread.sleep(2000);
        }

        public cartPage goToCartPage() {
            cartBtn.click();
            return new cartPage(driver);
        }

        public orderPage goToOrderPage() {
            orderBtn.click();
            return new orderPage(driver);
        }

        public checkoutPage goToCheckoutPage() {
            checkoutBtn.click();
            return new checkoutPage(driver);
        }

        public thanksPage goToThanksPage() {
            placeOrderBtn.click();
            return new thanksPage(driver);
        }
}

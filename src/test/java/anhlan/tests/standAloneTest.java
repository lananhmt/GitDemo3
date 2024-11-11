package anhlan.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class standAloneTest {
    public static void main(String[] args) {
        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().driverVersion("130.0.6723.70").setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();

        // Open web + login
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("anhlan.selenium@email.com");
        driver.findElement(By.id("userPassword")).sendKeys("Anh.lan@123");
        driver.findElement(By.id("login")).click();

        // Get list product + click product "ZARA COAT 3"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream()
                .filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
        product.findElement(By.cssSelector(".card-body button:nth-of-type(2)")).click();

        // Check if adding successfully + click button "Cart"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".toast-message")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        // Get list product in Cart + check if having the added product
        List<WebElement> carts = driver.findElements(By.xpath("//li[contains(@class,'ng-star-inserted')]"));
        boolean matchProduct = carts.stream().anyMatch(s -> s.findElement(By.xpath(".//div[@class='cartSection']/h3")).getText().equalsIgnoreCase(productName));
        Assert.assertTrue(matchProduct);

        // Click button "Checkout"
        driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=' Payment Method ']")));

        // Choose country "India"
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));
        String key = Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER);
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(key);

        // Click button "Place order" + verify title
        driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
        String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

        driver.quit();
    }
}

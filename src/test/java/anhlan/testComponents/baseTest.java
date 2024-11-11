package anhlan.testComponents;

import anhlan.pageObjects.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class baseTest {
    public WebDriver driver;
    public landingPage landingPg;

    public WebDriver initializeDriver() throws IOException {
        // properties class
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//anhlan//resources//globalData.properties");
        properties.load(inputStream);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            // Chrome open
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900)); // full screeen mode
        }
        else if (browserName.equalsIgnoreCase("Firefox")) {
            // Firefox open
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("Edge")) {
            // Edge open
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod(groups = {"login"})
    public landingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPg = new landingPage(driver);
        landingPg.goTo();
        return landingPg;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

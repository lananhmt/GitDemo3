package anhlan.tests;

import anhlan.data.dataReader;
import anhlan.pageObjects.*;
import anhlan.testComponents.baseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class submitOrderTest extends baseTest {
    private String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String, String> data) throws InterruptedException, IOException {
        // Login
        productCatalogue productCata = landingPg.loginApplication(data.get("email"), data.get("password"));

        // Get list product + click product "ZARA COAT 3" + verify if adding successfully
        List<WebElement> productList = productCata.getProductList();
        productCata.addProductToCart(data.get("product"));

        // Click button "Cart"
        cartPage cartPage = productCata.goToCartPage();

        // Get list product in Cart + check if having the added product
        Assert.assertTrue(cartPage.verifyProductByName(data.get("product")));

        // Click button "Checkout"
        checkoutPage checkoutPage = cartPage.goToCheckoutPage();

        // Choose country "India"
        checkoutPage.selectIndia();

        // Click button "Place order" + verify title
        thanksPage thanksPage = checkoutPage.goToThanksPage();
        Assert.assertTrue(thanksPage.verifyThanksTitle());
    }

    @Test(dependsOnMethods = "submitOrder")
    public void orderHistoryTest() {
        productCatalogue productCata = landingPg.loginApplication("anhlan.selenium@email.com", "Anh.lan@123");
        orderPage orderPg = productCata.goToOrderPage();
        Assert.assertTrue(orderPg.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        dataReader dataReader = new dataReader();
        List<HashMap<String, String>> data = dataReader.readJsonToHashMap(System.getProperty("user.dir") + "//src//test//java//anhlan//data//loginAcc.json");

        return new Object[][] {{data.get(0)}, {data.get(1)}};

//        HashMap<String, String> data1 = new HashMap<String, String>();
//        data1.put("email", "anhlan.selenium@email.com");
//        data1.put("password", "Anh.lan@123");
//        data1.put("product", "ZARA COAT 3");
//        HashMap<String, String> data2 = new HashMap<String, String>();
//        data2.put("email", "shetty@gmail.com");
//        data2.put("password", "Iamking@000");
//        data2.put("product", "ADIDAS ORIGINAL");
//        return new Object[][] {{data1}, {data2}};

//        return new Object[][] {{"anhlan.selenium@email.com", "Anh.lan@123", "ZARA COAT 3"},
//                {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
    }

    public String takeScreenShot(String testcaseName, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "//src//test//java//anhlan//screenshots//" + testcaseName + ".png";
        FileUtils.copyFile(source, new File(filePath));
        return filePath;
    }
}

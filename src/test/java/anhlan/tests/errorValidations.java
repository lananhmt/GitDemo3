package anhlan.tests;

import anhlan.pageObjects.cartPage;
import anhlan.pageObjects.productCatalogue;
import anhlan.testComponents.baseTest;
import anhlan.testComponents.retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class errorValidations extends baseTest {
    @Test(groups = {"login"}, retryAnalyzer = retry.class)
    public void loginErrorValidation() throws InterruptedException, IOException {
        productCatalogue productCata = landingPg.loginApplication("anhlan.selenium@email.com", "helloWorld");
        Assert.assertEquals("Incorrect email or password.", landingPg.getErrorMsg());
    }

    @Test
    public void productErrorValidation() throws InterruptedException, IOException {
        String productName = "ZARA COAT 3";
        productCatalogue productCata = landingPg.loginApplication("anhlan.selenium@email.com", "Anh.lan@123");
        productCata.addProductToCart(productName);
        cartPage cartPage = productCata.goToCartPage();
        Assert.assertFalse(cartPage.verifyProductByName("ZARA COAT 33"));
    }
}

package anhlan.cucumber.stepDefinitions;

import anhlan.pageObjects.*;
import anhlan.testComponents.baseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class stepDefinitionImpl extends baseTest {
    public landingPage landingPage;
    public productCatalogue productCatalogue;
    public thanksPage thanksPage;

    @Given("I landed on Ecommerce page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Log in with username (.+) and password (.+)$")
    public void log_in_with_username_and_password(String userName, String passWord) {
        productCatalogue = landingPage.loginApplication(userName, passWord);
    }

    @When("^I add the product (.+) from Cart$")
    public void i_add_the_product_from_cart(String productName) throws InterruptedException {
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_product_and_submit(String productName) {
        cartPage cartPage = productCatalogue.goToCartPage();
        cartPage.verifyProductByName(productName);
        checkoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectIndia();
        thanksPage = checkoutPage.goToThanksPage();
    }

    @Then("{string} success message is displayed")
    public void check_message_on_thank_page(String expectedThanksTitle) {
        Assert.assertTrue(thanksPage.getThanksTitle().equalsIgnoreCase(expectedThanksTitle));
        driver.close();
    }

    @Then("{string} error message is displayed")
    public void check_error_message(String expectedErrorMessage) {
        Assert.assertTrue(landingPage.getErrorMsg().equalsIgnoreCase(expectedErrorMessage));
        driver.close();
    }
}

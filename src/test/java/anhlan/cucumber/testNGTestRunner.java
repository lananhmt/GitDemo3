package anhlan.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/anhlan/cucumber",
        glue = "anhlan.cucumber.stepDefinitions",
        monochrome = true, // print results in readable format
        plugin = {"html:target/cucumber.html"},
        tags = "@errorValidation") // generate report
public class testNGTestRunner extends AbstractTestNGCucumberTests {

}

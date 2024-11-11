package anhlan.testComponents;

import anhlan.resources.extentReportNG;
import anhlan.tests.submitOrderTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class listener implements ITestListener {
    WebDriver driver;
    ExtentReports reports;
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe

    @Override
    public void onTestStart(ITestResult result) {
        extentReportNG reportNG = new extentReportNG();
        reports = reportNG.getReportObject();
        test = reports.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // unique Thread id(ErrorValidationTest) -> test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        extentTest.get().fail(result.getThrowable());
        String filePath = null;
        // Screenshot + add to report
        submitOrderTest submitOrderTest = new submitOrderTest();
        try {
            filePath = submitOrderTest.takeScreenShot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        reports.flush();
    }
}

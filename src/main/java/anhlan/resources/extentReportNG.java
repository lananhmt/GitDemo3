package anhlan.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReportNG {
    public ExtentReports getReportObject() {
        // Create where to save file
        String filePath = System.getProperty("user.dir") + "//src//test//java//anhlan//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
        // Config report: title, name, ...
        reporter.config().setDocumentTitle("Test results");
        reporter.config().setReportName("Web automation results");

        // Create report to attach to upper file
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        // Define some info
        reports.setSystemInfo("Tester", "Amy");

        return reports;
    }
}

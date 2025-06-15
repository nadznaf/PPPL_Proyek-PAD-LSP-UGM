package steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;
import utils.TestContext; // Pastikan import ini ada

public class Hooks {

    private final TestContext context;
    private static final ExtentReports extent = ExtentReportManager.getInstance();
    private ExtentTest test;

    /**
     * INI BAGIAN KUNCI #1:
     * Constructor ini akan dipanggil oleh Cucumber/PicoContainer
     * untuk menyuntikkan instance TestContext yang sama ke semua kelas (Hooks, Steps, dll).
     */
    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setup(Scenario scenario) {
        // Membuat test report untuk skenario ini
        test = extent.createTest(scenario.getName());
        context.setTest(test); // Menyimpan test ke dalam context agar bisa dipakai di steps lain

        // Membuat driver baru untuk setiap skenario
        context.setDriver(new ChromeDriver()); // <-- INI BAGIAN KUNCI #2: Driver dimasukkan ke dalam context!
        context.getDriver().manage().window().maximize();

        test.log(Status.INFO, "Browser launched and maximized.");
    }

    @After
    public void teardown(Scenario scenario) {
        String statusText;
        String suffix;

        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario Failed: " + scenario.getName());
            statusText = "Failed";
            suffix = "_failed";
        } else {
            test.log(Status.PASS, "Scenario Passed.");
            statusText = "Passed";
            suffix = "_passed";
        }

        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
        String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fullScreenshotName = scenarioName + suffix + "_" + timestamp;

        if (context.getDriver() != null) {
            String path = ScreenshotUtil.takeScreenshot(context.getDriver(), fullScreenshotName);
            try {
                test.addScreenCaptureFromPath(path, statusText + " Screenshot");
            } catch (Exception e) {
                test.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
            }
        }

        if (context.getDriver() != null) {
            context.getDriver().quit();
            test.log(Status.INFO, "Browser closed.");
        }

        extent.flush();
    }
}
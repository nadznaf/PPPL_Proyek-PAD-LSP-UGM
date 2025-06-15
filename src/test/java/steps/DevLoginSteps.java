package steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.*;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.DashboardAdminPage;
import pages.DevLoginPage;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DevLoginSteps {

    private WebDriver driver;
    private DevLoginPage loginPage;
    private DashboardAdminPage dashboardPage;
    private ExtentReports extent;
    private ExtentTest test;

    @Before
    public void setup(Scenario scenario) {
        extent = ExtentReportManager.getInstance();
        test = extent.createTest(scenario.getName());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        test.log(Status.INFO, "Browser launched and maximized.");

        loginPage = new DevLoginPage(driver);
    }

    @Given("the user is on the developer login page")
    public void userIsOnLoginPage() {
        loginPage.goToLoginPage();
        test.log(Status.INFO, "Navigated to login page.");
    }

    @When("the user enters email {string} and password {string}")
    public void userEntersValidCredentials(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        test.log(Status.INFO, "Entered email: " + email + " and password: [HIDDEN]");
    }

    @And("the user clicks the login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
        test.log(Status.INFO, "Clicked login button.");
    }

    @Then("the system doing authentification and user redirected to the dashboard")
    public void userRedirectedToDashboard() {
        dashboardPage = new DashboardAdminPage(driver);
        boolean isOnDashboard = dashboardPage.isAtDashboard();
        assertTrue(isOnDashboard, "Expected dashboard elements to be present.");
        test.log(Status.PASS, "Dashboard elements verified successfully.");
    }

    @Then("the login process should be failed and user stay on login page displaying an alert")
    public void loginShouldFail() {
        boolean failed = loginPage.isLoginErrorDisplayed();
    }

    @After
    public void teardown(Scenario scenario) {
        String screenshotName = scenario.getName().replaceAll(" ", "_");

        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, screenshotName + "_failed");
            try {
                test.addScreenCaptureFromPath(screenshotPath, "Failed Screenshot");
            } catch (Exception e) {
                test.log(Status.WARNING, "Could not attach failed screenshot: " + e.getMessage());
            }
        } else {
            test.log(Status.PASS, "Scenario passed: " + scenario.getName());
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, screenshotName + "_passed");
            try {
                test.addScreenCaptureFromPath(screenshotPath, "Passed Screenshot");
            } catch (Exception e) {
                test.log(Status.WARNING, "Could not attach passed screenshot: " + e.getMessage());
            }
        }

        if (driver != null) {
            driver.quit();
            test.log(Status.INFO, "Browser closed.");
        }

        extent.flush();
    }
}

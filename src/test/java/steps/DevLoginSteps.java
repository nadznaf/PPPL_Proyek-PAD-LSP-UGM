package steps;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.DashboardAdminPage;
import pages.DevLoginPage;
import utils.TestContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DevLoginSteps {

    private final WebDriver driver;
    private final DevLoginPage loginPage;
    private final DashboardAdminPage dashboardPage;
    private final TestContext context;

    public DevLoginSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver(); // Mengambil driver dari context

        // Inisialisasi semua page objects yang dibutuhkan oleh kelas ini di satu tempat.
        this.loginPage = new DevLoginPage(driver);
        this.dashboardPage = new DashboardAdminPage(driver);
    }

    @Given("the user is on the developer login page")
    public void userIsOnLoginPage() {
        loginPage.goToLoginPage();
        context.getTest().log(Status.INFO, "Navigated to the developer login page.");
    }

    @When("the user enters email {string} and password {string}")
    public void userEntersValidCredentials(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        // Demi keamanan, jangan log password asli
        context.getTest().log(Status.INFO, "Entered email: " + email + " and password: [HIDDEN]");
    }

    @And("the user clicks the login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
        context.getTest().log(Status.INFO, "Clicked the login button.");
    }

    @Then("the system doing authentification and user redirected to the dashboard")
    public void userRedirectedToDashboard() {
        // Inisialisasi dashboardPage sudah dilakukan di constructor, tidak perlu diulang
        boolean isOnDashboard = dashboardPage.isAtDashboard();
        assertTrue(isOnDashboard, "Verification failed: User was not redirected to the dashboard.");
        context.getTest().log(Status.PASS, "Successfully authenticated and redirected to the dashboard.");
    }

    @Then("the login process should be failed and user stay on login page displaying an alert")
    public void loginShouldFail() {
        boolean isErrorDisplayed = loginPage.isLoginErrorDisplayed();
        assertTrue(isErrorDisplayed, "Verification failed: Login error message was not displayed.");
        context.getTest().log(Status.PASS, "Login failed as expected, and an error alert was displayed.");
    }
}
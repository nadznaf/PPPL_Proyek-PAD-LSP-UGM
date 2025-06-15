package steps;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardAdminPage;
import pages.DevLoginPage;
import utils.TestContext; // Import kelas TestContext yang akan kita buat

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutSteps {

    // Variabel untuk menyimpan page objects dan context
    private final DashboardAdminPage dashboardPage;
    private final DevLoginPage loginPage;
    private final TestContext context;

    /**
     * Constructor ini digunakan untuk Dependency Injection.
     * Cucumber akan secara otomatis menyediakan instance 'TestContext' yang sama
     * untuk setiap kelas Step Definition dalam satu skenario.
     * @param context Shared context yang berisi WebDriver dan ExtentTest.
     */
    public LogoutSteps(TestContext context) {
        this.context = context;
        // Inisialisasi Page Objects menggunakan WebDriver dari shared context
        this.dashboardPage = new DashboardAdminPage(context.getDriver());
        this.loginPage = new DevLoginPage(context.getDriver());
    }

    @When("the user clicks the logout button")
    public void userClicksLogoutButton() {
        dashboardPage.clickLogoutButton();
        // Mengambil test instance dari context untuk logging
        context.getTest().log(Status.INFO, "Clicked logout button.");
    }

    @Then("the user should be redirected to the login page")
    public void userRedirectedToLoginPage() {
        assertTrue(loginPage.isAtLoginPage(), "Expected to be on the login page after logout.");
        context.getTest().log(Status.PASS, "Successfully redirected to login page after logout.");
    }
}
package steps;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import pages.CompetencyUnitCreatePage;
import pages.CompetencyUnitPage;
import pages.DashboardAdminPage;
import utils.TestContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class CompetencyUnitSteps {

    // Page objects bisa di-final jika diinisialisasi sekali saja
    private final TestContext context;
    private DashboardAdminPage dashboardPage; //
    private final CompetencyUnitPage unitPage;
    private final CompetencyUnitCreatePage createPage;

    public CompetencyUnitSteps(TestContext context) {
        this.context = context;
        this.dashboardPage = new DashboardAdminPage(context.getDriver());
        this.unitPage = new CompetencyUnitPage(context.getDriver());
        this.createPage = new CompetencyUnitCreatePage(context.getDriver());
    }

    @When("the admin clicks the {string} menu")
    public void theAdminClicksTheMenu(String menuName) {
        switch (menuName) {
            case "Competency Unit Management":
                dashboardPage.clickCompetencyUnitButton();
                context.getTest().log(Status.INFO, "Clicked menu: " + menuName);
                break;
            case "Schema Management":
                dashboardPage.clickSchemaButton();
                context.getTest().log(Status.INFO, "Clicked menu: " + menuName);
                break;
            default:
                context.getTest().log(Status.FAIL, "Unknown menu: " + menuName);
                throw new IllegalArgumentException("Menu not recognized: " + menuName);
        }
    }

    @Then("the admin should be redirected to the competency unit management page")
    public void shouldBeRedirectedToCompetencyUnitPage() {
        assertTrue(unitPage.isAtCompetencyUnit(), "Expected to be at the Competency Unit page.");
        context.getTest().log(Status.PASS, "Redirected to Competency Unit Management page.");
    }

    @And("the admin clicks the {string} button")
    public void theAdminClicksButton(String buttonName) {
        switch (buttonName) {
            case "Add Competency Unit":
                unitPage.clickAddCompetencyUnit();
                context.getTest().log(Status.INFO, "Clicked Add Competency Unit button.");
                break;
            case "Save Competency Unit":
                // Memanggil method yang benar dan menangkap object halaman berikutnya.
                this.dashboardPage = createPage.clickSaveButtonAndRedirectToDashboard();
                context.getTest().log(Status.INFO, "Clicked Save Competency Unit button and redirected to dashboard.");
                break;
            case "Save Competency Unit with Error":
                createPage.clickSaveButtonExpectingError();
                context.getTest().log(Status.INFO, "Clicked Save Competency Unit button, expecting an error.");
                break;
            default:
                context.getTest().log(Status.FAIL, "Unknown button: " + buttonName);
                throw new IllegalArgumentException("Button not recognized: " + buttonName);
        }
    }

    @And("fills out the competency unit form with the following data:")
    public void fillsOutTheCompetencyUnitForm(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> formData = dataTable.asLists(String.class);
        createPage.fillFormWithData(formData);
        context.getTest().log(Status.INFO, "Filled out competency unit form with provided data.");
    }

    @Then("a success notification {string} appears")
    public void successNotificationAppears(String expectedMessage) {
        // Memanggil method yang benar dari DashboardAdminPage POM
        String actualMessage = dashboardPage.getSuccessNotificationText();
        assertTrue(actualMessage.contains(expectedMessage), "Success message mismatch. Expected to contain '" + expectedMessage + "', but got '" + actualMessage + "'.");
        context.getTest().log(Status.PASS, "Success message appeared: " + actualMessage);
    }

    @Then("an error message {string} appears")
    public void errorMessageAppears(String expectedMessage) {
        String actualMessage;
        
        // Menentukan jenis error message berdasarkan expected message
        if (expectedMessage.contains("Kode unit kompetensi sudah digunakan.")) {
            actualMessage = createPage.getCodeFieldValidationError();
        } else if (expectedMessage.contains("Minimal harus ada satu elemen unit kompetensi")) {
            actualMessage = createPage.getElementValidationError();
        } else {
            // Default untuk pesan error lainnya
            actualMessage = createPage.getCodeFieldValidationError();
        }

        assertTrue(actualMessage.contains(expectedMessage), 
            "Error message mismatch. Expected: '" + expectedMessage + "', Actual: '" + actualMessage + "'");
        context.getTest().log(Status.PASS, "Validation error appeared: " + actualMessage);
    }
}
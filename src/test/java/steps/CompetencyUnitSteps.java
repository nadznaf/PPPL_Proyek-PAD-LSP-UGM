package steps;

import com.aventstack.extentreports.Status;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.*;
import utils.TestContext;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

// FILE INI SEKARANG MENJADI PUSAT UNTUK SEMUA FITUR
public class CompetencyUnitSteps {

    private final TestContext context;
    private DashboardAdminPage dashboardPage;
    private  CompetencyUnitPage unitPage;
    private final CompetencyUnitCreatePage competencyUnitCreatePage;
    private  SchemaManagementPage schemaManagementPage;
    private final SchemaCreatePage schemaCreatePage;


    public CompetencyUnitSteps(TestContext context) {
        this.context = context;
        this.dashboardPage = new DashboardAdminPage(context.getDriver());
        this.unitPage = new CompetencyUnitPage(context.getDriver());
        this.competencyUnitCreatePage = new CompetencyUnitCreatePage(context.getDriver());
        this.schemaManagementPage = new SchemaManagementPage(context.getDriver());
        this.schemaCreatePage = new SchemaCreatePage(context.getDriver());
    }

    @When("the admin clicks the {string} menu")
    public void theAdminClicksTheMenu(String menuName) {
        switch (menuName) {
            case "Competency Unit Management":
                dashboardPage.clickCompetencyUnitButton();
                break;
            case "Schema Management":
                dashboardPage.clickSchemaButton();
                break;
            default:
                throw new IllegalArgumentException("Menu not recognized: " + menuName);
        }
        context.getTest().log(Status.INFO, "Clicked menu: " + menuName);
    }

    @And("the admin clicks the {string} button")
    public void theAdminClicksButton(String buttonName) {
        switch (buttonName) {
            case "Add Competency Unit":
                unitPage.clickAddCompetencyUnit();
                break;
            case "Add Schema":
                schemaManagementPage.klikTombolTambahSkemaBaru();
                break;
            case "Save Competency Unit":
                this.unitPage = competencyUnitCreatePage.clickSaveButtonAndRedirectToUnitPage(); // Panggil method yang benar
                context.getTest().log(Status.INFO, "Clicked Save Competency Unit button.");
                break;
            case "Save Certification":
                this.dashboardPage = schemaCreatePage.clickSaveButton();
                break;
            case "Save Certification with Error Schema":
                schemaCreatePage.clickSaveButtonExpectingError();
                break;
            case "Save Competency Unit with Error":
                competencyUnitCreatePage.clickSaveButtonExpectingError();
                break;
            default:
                throw new IllegalArgumentException("Button not recognized: " + buttonName);
        }
        context.getTest().log(Status.INFO, "Clicked button: " + buttonName);
    }

    @Then("the admin should be redirected to the {string} page")
    public void theAdminShouldBeRedirectedToThePage(String pageName) {
        boolean isAtPage = switch (pageName.toLowerCase()) {
            case "competency unit management" -> unitPage.isAtCompetencyUnit();
            case "schema management" -> schemaManagementPage.isAtSchemaPage();
            default -> throw new IllegalArgumentException("Page name not recognized: " + pageName);
        };
        assertTrue(isAtPage, "Expected to be at the " + pageName + " page.");
        context.getTest().log(Status.PASS, "Redirected to " + pageName + " page.");
    }

    @Then("a success notification {string} appears")
    public void successNotificationAppears(String expectedMessage) {
        String actualMessage; // Inisialisasi dihilangkan
        String currentUrl = context.getDriver().getCurrentUrl();

        if (currentUrl != null && currentUrl.contains("/skema") && !currentUrl.contains("/create")) {
            actualMessage = schemaManagementPage.getSuccessNotificationText();
        } else if (currentUrl != null && currentUrl.contains("/unit-kompetensi") && !currentUrl.contains("/create")) {
            actualMessage = unitPage.getSuccessNotificationText();
        } else {
            actualMessage = dashboardPage.getSuccessNotificationText();
        }
        assertTrue(actualMessage.contains(expectedMessage), "Success message mismatch.");
        context.getTest().log(Status.PASS, "Success message appeared: " + actualMessage);
    }

    // === STEPS SPESIFIK UNIT KOMPETENSI ===
    @And("fills out the competency unit form with the following data:")
    public void fillsOutTheCompetencyUnitForm(DataTable dataTable) {
        List<List<String>> formData = dataTable.asLists(String.class);
        competencyUnitCreatePage.fillFormWithData(formData);
        context.getTest().log(Status.INFO, "Filled out competency unit form with provided data.");
    }

    @Then("an error message {string} appears")
    public void errorMessageAppears(String expectedMessage) {
        String actualMessage;

        // Menentukan jenis error message berdasarkan expected message
        if (expectedMessage.contains("Kode unit kompetensi sudah digunakan.")) {
            actualMessage = competencyUnitCreatePage.getCodeFieldValidationError();
        } else if (expectedMessage.contains("Minimal harus ada satu elemen unit kompetensi")) {
            actualMessage = competencyUnitCreatePage.getElementValidationError();
        } else {
            // Default untuk pesan error lainnya
            actualMessage = competencyUnitCreatePage.getCodeFieldValidationError();
        }
    }

    @Then("the admin returns to the dashboard")
    public void theAdminReturnsToTheDashboard() {
        dashboardPage.returnToDashboard();
        assertTrue(dashboardPage.isAtDashboard(), "Failed to return to the dashboard.");
        context.getTest().log(Status.PASS, "Successfully returned to and verified the dashboard.");
    }

}
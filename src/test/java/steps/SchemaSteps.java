package steps;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import org.testng.asserts.Assertion;
import pages.SchemaCreatePage;
import utils.TestContext;

public class SchemaSteps {

    private final TestContext context;
    private final SchemaCreatePage schemaCreatePage;

    public SchemaSteps(TestContext context) {
        this.context = context;
        this.schemaCreatePage = new SchemaCreatePage(context.getDriver());
    }

    //untuk step step umumnya disatukan di file competencyunitsteps agar tidak tabrakan

    @And("fills out the schema form with the following details:")
    public void fillsOutTheSchemaFormWithDetails(DataTable dataTable) {
        schemaCreatePage.fillFormDetails(dataTable.asLists(String.class));
        context.getTest().log(Status.INFO, "Filled out schema form details.");
    }

    @And("adds the following basic requirements:")
    public void addsTheFollowingBasicRequirements(String requirements) {
        schemaCreatePage.fillRequirements(requirements);
        context.getTest().log(Status.INFO, "Added basic requirements for the schema.");
    }

    @Then("an error message should be shown for empty competency units")
    public void errorMessageEmptyCompetencyUnits() {
        String error = schemaCreatePage.getCompetencyUnitError();
        Assertions.assertTrue(error.contains("Minimal 1 Unit Kompetensi harus dipilih."));
    }

    @Then("an error message should be shown for empty SKKNI document")
    public void errorMessageEmptySKKNIDocument() {
        String error = schemaCreatePage.getSKKNIDocumentError();
        Assertions.assertTrue(error.contains("The dokumen skkni field is required."));
    }

}
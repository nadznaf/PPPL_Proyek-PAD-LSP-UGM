package steps;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
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
}
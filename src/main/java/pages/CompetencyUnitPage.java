package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompetencyUnitPage {
    private WebDriver driver;

    public CompetencyUnitPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By competencyUnitPageHeader = By.xpath("//h2[contains(text(),'Manajemen Unit Kompetensi')]");
    private final By addCompetencyUnitButton = By.xpath("/html/body/main/div/div/div[1]/div/div[2]/a");

    public boolean isAtCompetencyUnit() {
        boolean dashboardHeaderExist = driver.findElements(competencyUnitPageHeader).size() > 0;
        return dashboardHeaderExist;
    }

    public void clickAddCompetencyUnit() {
        driver.findElement(addCompetencyUnitButton).click();
    }
}

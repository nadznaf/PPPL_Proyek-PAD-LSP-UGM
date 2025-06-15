package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompetencyUnitCreatePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By competencyUnitCodeInput = By.id("kode_uk");
    private final By competencyUnitNameInput = By.id("nama_uk");
    private final By competencyUnitFieldSelect = By.id("id_bidang");
    private final By standardTypeInput = By.id("jenis_standar");
    private final By competencyElementInputs = By.cssSelector("input[name='elemen_uk[]']");
    private final By addElementButton = By.id("tambah-elemen-btn");
    private final By saveButton = By.xpath("//button[contains(text(), 'Simpan Unit Kompetensi')]");

    private final By codeFieldValidationError = By.xpath("//input[@id='kode_uk']/following-sibling::p");

    public CompetencyUnitCreatePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillCompetencyUnitCode(String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(competencyUnitCodeInput)).sendKeys(code);
    }

    public void fillCompetencyUnitName(String name) {
        driver.findElement(competencyUnitNameInput).sendKeys(name);
    }

    public void fillStandardType(String standardType) {
        driver.findElement(standardTypeInput).sendKeys(standardType);
    }

    public void selectCompetencyUnitFieldByVisibleText(String visibleText) {
        Select select = new Select(driver.findElement(competencyUnitFieldSelect));
        select.selectByVisibleText(visibleText);
    }

    public void selectCompetencyUnitFieldByValue(String valueAttribute) {
        Select select = new Select(driver.findElement(competencyUnitFieldSelect));
        select.selectByValue(valueAttribute);
    }

    public void fillCompetencyUnitElements(List<String> elements) {
        if (elements == null || elements.isEmpty()) {
            return;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(competencyElementInputs)).sendKeys(elements.get(0));

        for (int i = 1; i < elements.size(); i++) {
            int currentNumberOfInputs = driver.findElements(competencyElementInputs).size();
            driver.findElement(addElementButton).click();
            wait.until(d -> d.findElements(competencyElementInputs).size() > currentNumberOfInputs);
            List<WebElement> allInputs = driver.findElements(competencyElementInputs);
            allInputs.get(allInputs.size() - 1).sendKeys(elements.get(i));
        }
    }

    public DashboardAdminPage clickSaveButtonAndRedirectToDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        return new DashboardAdminPage(driver);
    }

    public void clickSaveButtonExpectingError() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public String getCodeFieldValidationError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(codeFieldValidationError)).getText();
    }

    public void fillFormWithData(List<List<String>> formData) {
        List<String> competencyElements = formData.stream()
                .filter(row -> row.get(0) != null && row.get(0).trim().equalsIgnoreCase("Competency Unit Elements"))
                .map(row -> row.get(1))
                .collect(Collectors.toList());

        for (List<String> row : formData) {
            if (row.get(0) == null) {
                continue;
            }
            String field = row.get(0).trim();
            String value = row.get(1);

            if (field.equalsIgnoreCase("Competency Unit Elements") || Objects.equals(value, null) || value.equalsIgnoreCase("(empty)") || value.equalsIgnoreCase("(dikosongkan)")) {
                continue;
            }

            switch (field) {
                case "Competency Unit Code":
                    fillCompetencyUnitCode(value);
                    break;
                case "Competency Unit Name":
                    fillCompetencyUnitName(value);
                    break;
                case "Competency Unit Field":
                    selectCompetencyUnitFieldByValue(value);
                    break;
                case "Standard Type":
                    fillStandardType(value);
                    break;
            }
        }

        if (!competencyElements.isEmpty()) {
            fillCompetencyUnitElements(competencyElements);
        }
    }
}
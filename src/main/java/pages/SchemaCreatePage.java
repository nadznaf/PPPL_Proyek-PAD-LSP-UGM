package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class SchemaCreatePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locator tidak berubah
    private final By schemaNumberInput = By.id("nomor_skema");
    private final By schemaNameInput = By.id("nama_skema");
    private final By skkniDocumentInput = By.id("dokumen_skkni");
    private final By scienceFieldSelect = By.id("id_bidang");
    private final By competencyUnitSelect = By.id("id_uk");
    private final By addCompetencyUnitButton = By.id("tambahBtn");
    private final By selectedUnitsTableBody = By.id("ukTableBody");
    private final By requirementsTextArea = By.id("persyaratan_skema");
    private final By saveButton = By.xpath("/html/body/main/div/div/div[2]/div[2]/form/div/div[6]/button");

    public SchemaCreatePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillFormDetails(List<List<String>> formData) {
        for (List<String> row : formData) {
            String field = row.get(0).trim();
            String value = row.get(1);
            if (Objects.equals(value, null) || value.isEmpty()) continue;
            switch (field) {
                case "Schema Number":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(schemaNumberInput)).sendKeys(value);
                    break;
                case "Schema Name":
                    driver.findElement(schemaNameInput).sendKeys(value);
                    break;
                case "SKKNI Document":
                    String absolutePath = new File(value).getAbsolutePath();
                    driver.findElement(skkniDocumentInput).sendKeys(absolutePath);
                    break;
                case "Science Field":
                    Select selectField = new Select(driver.findElement(scienceFieldSelect));
                    selectField.selectByVisibleText(value);
                    break;
                case "Competency Units":
                    addCompetencyUnits(value);
                    break;
            }
        }
    }

    // Method ini ada di dalam kelas SchemaCreatePage.java
    private void addCompetencyUnits(String units) {
        String[] unitsToAdd = units.split(",\\s*");

        for (String unit : unitsToAdd) {
            String unitToSelect = unit.trim();
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(String.format("//select[@id='id_uk']/option[normalize-space()='%s']", unitToSelect))
            ));
            Select selectUnit = new Select(driver.findElement(competencyUnitSelect));
            selectUnit.selectByVisibleText(unitToSelect);
            driver.findElement(addCompetencyUnitButton).click();
            String unitCode = unitToSelect.split(" - ")[0].trim();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(selectedUnitsTableBody, unitCode));

            // Jeda singkat untuk stabilitas
            try {
                Thread.sleep(500); // Tunggu setengah detik untuk stabilitas
            } catch (InterruptedException e) {
                // Praktik terbaik untuk menangani interupsi
                Thread.currentThread().interrupt();
            };
        }
    }

    public void fillRequirements(String requirements) {
        driver.findElement(requirementsTextArea).sendKeys(requirements);
    }

    public DashboardAdminPage clickSaveButton() {
        // Pertama, kita pastikan elemennya ada di halaman
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(saveButton));

        // Gunakan JavascriptExecutor untuk solusi yang paling andal
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1. Scroll ke tombol agar terlihat di layar
        js.executeScript("arguments[0].scrollIntoView(true);", button);

        // 2. Klik tombol menggunakan JavaScript
        js.executeScript("arguments[0].click();", button);

        return new DashboardAdminPage(driver);
    }
    public void clickSaveButtonExpectingError() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public String getCompetencyUnitError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div/div/div[2]/div[2]/div/ul/li"))).getText();
    }

    public String getSKKNIDocumentError() {
        return driver.findElement(By.xpath("/html/body/main/div/div/div[2]/div[2]/div/ul/li")).getText();
    }

}
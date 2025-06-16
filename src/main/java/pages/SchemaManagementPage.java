package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SchemaManagementPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By tambahSkemaButton = By.xpath("//a[contains(., 'Tambah Skema Baru')]");
    private final By pageTitle = By.xpath("//h2[contains(., 'Manajemen Skema Sertifikasi')]");

    private final By successNotification = By.cssSelector("div.bg-green-100 p");

    public SchemaManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void klikTombolTambahSkemaBaru() {
        wait.until(ExpectedConditions.elementToBeClickable(tambahSkemaButton)).click();
    }

    public boolean isAtSchemaPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public String getSuccessNotificationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification)).getText();
    }

}
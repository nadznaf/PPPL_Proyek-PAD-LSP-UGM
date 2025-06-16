package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CompetencyUnitPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public CompetencyUnitPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By pageTitle = By.xpath("//h2[contains(text(),'Manajemen Unit Kompetensi')]");
    private final By addCompetencyUnitButton = By.xpath("/html/body/main/div/div/div[1]/div/div[2]/a");

    private final By successNotification = By.cssSelector("div.bg-green-100 p");

    public String getSuccessNotificationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification)).getText();
    }

    public boolean isAtCompetencyUnit() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public void clickAddCompetencyUnit() {
        driver.findElement(addCompetencyUnitButton).click();
    }

}

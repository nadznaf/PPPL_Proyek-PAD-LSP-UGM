package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardAdminPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardAdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By dashboardHeader = By.xpath("//h1[contains(text(),'Dashboard Admin')]");
    private final By logoutButton = By.xpath("//*[@id=\"navbar\"]/form/button");
    private final By competencyUnitButton = By.xpath("//*[@id=\"navbar\"]/a[5]");
    private final By schemaButton = By.xpath("//*[@id=\"navbar\"]/a[4]");
    private final By successNotification = By.cssSelector("div.bg-green-100");
    private final By dashboardLink = By.xpath("//a[.//span[text()='Dashboard']]");

    public boolean isAtDashboard() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void clickCompetencyUnitButton() {
        driver.findElement(competencyUnitButton).click();
    }

    public void clickSchemaButton() {
        driver.findElement(schemaButton).click();
    }

    public String getSuccessNotificationText() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification));
        return notification.findElement(By.tagName("p")).getText();
    }

    public void returnToDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardLink)).click();
    }
}
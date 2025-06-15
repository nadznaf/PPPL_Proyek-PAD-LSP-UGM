package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardAdminPage {
    private WebDriver driver;

    // Konstruktor
    public DashboardAdminPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By dashboardHeader = By.xpath("//h1[contains(text(),'Dashboard Admin')]");
    private final By logoutButton = By.xpath("//*[@id=\"navbar\"]/form/button");

    public boolean isAtDashboard() {
        boolean dashboardHeaderExist = driver.findElements(dashboardHeader).size() > 0;
        return dashboardHeaderExist;
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}

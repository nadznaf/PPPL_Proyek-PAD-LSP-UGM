package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DevLoginPage {
    private WebDriver driver;

    // Konstruktor yang benar
    public DevLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Lokator elemen halaman login
    private final By emailField = By.name("email");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");

    // Metode-metode interaksi halaman
    public void goToLoginPage() {
        driver.get("http://127.0.0.1:8000/dev/login");
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginErrorDisplayed() {
        try {
            return driver.findElement(By.className("swal2-container")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

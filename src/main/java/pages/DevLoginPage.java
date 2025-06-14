package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DevLoginPage {
    private WebDriver driver;

    // Lokator input dan tombol dari halaman HTML yang kamu berikan
    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");

    public DevLoginPage(WebDriver driver) {
        this.driver = driver;
    }

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
}
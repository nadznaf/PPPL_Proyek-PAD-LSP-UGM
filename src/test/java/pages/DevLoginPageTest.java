package pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DevLoginPageTest {
    private WebDriver driver;

    // Lokator elemen halaman login
    private final By emailField = By.name("email");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginToDashboard() {
        goToLoginPage();
        enterEmail("admin@mail.ugm.ac.id");     // Ganti dengan email UGM yang valid
        enterPassword("AdminNafa");         // Ganti dengan password yang valid
        clickLoginButton();

        // Validasi sederhana: pastikan berhasil masuk dashboard
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("dashboard")
                || driver.getPageSource().toLowerCase().contains("dashboard"));
    }

    @AfterEach
    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
        System.out.println("Login test berhasil dijalankan");
    }

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

}

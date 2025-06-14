import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginToDashboard() {
        loginPage.goToLoginPage();
        loginPage.enterEmail("admin@ugm.ac.id ");  // Ganti dengan akun yang valid
        loginPage.enterPassword("AdminDwi");          // Ganti dengan password valid
        loginPage.clickLoginButton();

        // Validasi sederhana setelah login, bisa diubah jika kamu tahu URL/elemen dashboard
        assertTrue(driver.getCurrentUrl().contains("dashboard")
                || driver.getPageSource().toLowerCase().contains("dashboard"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

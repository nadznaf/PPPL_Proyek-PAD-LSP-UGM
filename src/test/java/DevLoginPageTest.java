import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DevLoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DevLoginPageTest {
    private WebDriver driver;
    private DevLoginPage devLoginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        devLoginPage = new DevLoginPage(driver);
    }

    @Test
    public void testLoginToDashboard() {
        devLoginPage.goToLoginPage();
        devLoginPage.enterEmail("admin@ugm.ac.id ");  // Ganti dengan akun yang valid
        devLoginPage.enterPassword("AdminDwi");          // Ganti dengan password valid
        devLoginPage.clickLoginButton();

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

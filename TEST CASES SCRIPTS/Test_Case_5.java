import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Test_Case_5 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAFSAN\\libs\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("http://schoolapps.free.nf/login.php");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("uname")));
            WebElement usernameField = driver.findElement(By.name("uname"));
            WebElement passwordField = driver.findElement(By.name("pass"));
            WebElement roleField = driver.findElement(By.name("role"));
            usernameField.sendKeys("elias");
            passwordField.sendKeys("123");
            roleField.sendKeys("Admin");
            WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
            loginButton.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".container.mt-5")));
            WebElement registrarOfficeButton = driver.findElement(By.cssSelector("a[href='registrar-office.php']"));
            registrarOfficeButton.click();
            wait.until(ExpectedConditions.titleContains("Registrar Office"));
            WebElement deleteButton = driver.findElement(By.cssSelector("a.btn.btn-danger"));
            deleteButton.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert-info, .alert-danger")));
            WebElement successMessage = driver.findElement(By.cssSelector(".alert-info"));
            if (successMessage != null) {
                System.out.println("User successfully deleted.");
            } else {
                WebElement errorMessage = driver.findElement(By.cssSelector(".alert-danger"));
                if (errorMessage != null) {
                    System.out.println("Failed to delete user: " + errorMessage.getText());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // driver.quit();
        }
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Test_Case_4 {
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
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL after clicking registrar office: " + currentUrl);
            WebElement editUserButton = driver.findElement(By.cssSelector("a[href*='registrar-office-edit.php']"));
            editUserButton.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("fname")));
            currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL after clicking 'Edit': " + currentUrl);
            WebElement firstNameField = driver.findElement(By.name("fname"));
            firstNameField.clear();
            firstNameField.sendKeys("JOHN WALMER");
            WebElement form = driver.findElement(By.tagName("form"));
            form.submit();
            wait.until(ExpectedConditions.urlContains("registrar-office.php"));
            currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL after updating: " + currentUrl);
            System.out.println("Test completed: First name updated successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

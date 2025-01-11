import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Test_Case_2 {
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

            WebElement sectionButton = driver.findElement(By.cssSelector("a[href='section.php']"));
            sectionButton.click();

            wait.until(ExpectedConditions.titleContains("Admin - Section"));

            WebElement editButton = driver.findElement(By.cssSelector("a[href^='section-edit.php?section_id=']"));
            editButton.click();

            wait.until(ExpectedConditions.titleContains("Edit Section"));

            WebElement sectionField = driver.findElement(By.name("section"));
            sectionField.clear();
            sectionField.sendKeys("X");

            WebElement updateButton = driver.findElement(By.cssSelector("button[type='submit']"));
            updateButton.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert.alert-success")));
            WebElement successMessage = driver.findElement(By.cssSelector(".alert.alert-success"));

            if (successMessage.getText().contains("success")) {
                System.out.println("TEST PASSED : Section updated successfully!");
            } else {
                System.out.println("TEST FAILED : Section update failed!");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

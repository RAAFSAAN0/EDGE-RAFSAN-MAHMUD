import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class Test_Case_3 {
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

            WebElement addNewUserButton = driver.findElement(By.cssSelector("a[href='registrar-office-add.php']"));
            addNewUserButton.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));

            WebElement fnameField = driver.findElement(By.name("fname"));
            WebElement lnameField = driver.findElement(By.name("lname"));
            WebElement unameField = driver.findElement(By.name("username"));
            WebElement passField = driver.findElement(By.name("pass"));
            WebElement addressField = driver.findElement(By.name("address"));
            WebElement employeeNumberField = driver.findElement(By.name("employee_number"));
            WebElement phoneNumberField = driver.findElement(By.name("phone_number"));
            WebElement qualificationField = driver.findElement(By.name("qualification"));
            WebElement emailField = driver.findElement(By.name("email_address"));
            WebElement dobField = driver.findElement(By.name("date_of_birth"));

            fnameField.sendKeys("John");
            lnameField.sendKeys("Doe");
            unameField.sendKeys("john_doe_2");
            passField.sendKeys("randompassword123");
            addressField.sendKeys("1234 Street Name, City");
            employeeNumberField.sendKeys("12345");
            phoneNumberField.sendKeys("9876543210");
            qualificationField.sendKeys("Masters in Computer Science");
            emailField.sendKeys("john.doe@example.com");

            dobField.sendKeys("1990-05-15");

            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);

            try {
                addButton.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
            }

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert.alert-success")));

            WebElement successMessage = driver.findElement(By.cssSelector(".alert.alert-success"));
            System.out.println("Success Message: " + successMessage.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

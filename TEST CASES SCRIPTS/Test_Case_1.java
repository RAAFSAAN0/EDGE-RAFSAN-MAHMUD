import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Test_Case_1 {
    public static void main(String[] args) {
        // Set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAFSAN\\libs\\chromedriver-win64\\chromedriver.exe");

        // Set up Chrome options for the WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);

        // WebDriver wait for synchronization
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open the login page
            driver.get("http://schoolapps.free.nf/login.php");

            // Login to the system
            loginToSystem(driver, wait);

            // Navigate to the "Add Student" page
            navigateToAddStudent(driver, wait);

            // Fill out the student information form
            fillAndSubmitStudentForm(driver, wait);

            // Verify the successful submission
            verifySubmission(driver, wait);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser after the test, whether passed or failed
           // driver.quit();
        }
    }

    private static void loginToSystem(WebDriver driver, WebDriverWait wait) {
        try {
            // Wait and locate elements
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("uname")));
            WebElement passwordField = driver.findElement(By.name("pass"));
            WebElement roleField = driver.findElement(By.name("role"));
            WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-primary"));

            // Enter login details and submit form
            usernameField.sendKeys("james");
            passwordField.sendKeys("123");
            roleField.sendKeys("Registrar Office");
            loginButton.click();

            // Wait for the dashboard page to load
            wait.until(ExpectedConditions.urlToBe("http://schoolapps.free.nf/RegistrarOffice/index.php"));

            // Verify successful login
            if (!driver.getCurrentUrl().equals("http://schoolapps.free.nf/RegistrarOffice/index.php")) {
                System.out.println("Login failed. Current URL: " + driver.getCurrentUrl());
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
          //  driver.quit();
        }
    }

    private static void navigateToAddStudent(WebDriver driver, WebDriverWait wait) {
        try {
            // Wait for the All Students button and click it
            WebElement studentButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.col.btn.btn-dark.m-2.py-3[href='student-add.php']")));
            studentButton.click();

            // Wait for the student add form to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("fname")));
        } catch (Exception e) {
            System.out.println("Navigation to 'Add Student' failed: " + e.getMessage());
           // driver.quit();
        }
    }

    private static void fillAndSubmitStudentForm(WebDriver driver, WebDriverWait wait) {
        try {
            // Fill in the student details
            driver.findElement(By.name("fname")).sendKeys("John");
            driver.findElement(By.name("lname")).sendKeys("Doe");
            driver.findElement(By.name("address")).sendKeys("123 Main St, Springfield, IL");
            driver.findElement(By.name("email_address")).sendKeys("johndoe@example.com");

            // Fill additional details like DOB and gender
            driver.findElement(By.name("date_of_birth")).sendKeys("2000-01-01");
            driver.findElement(By.id("male")).click(); // Select gender as male

            // Select grade and section using dropdowns
            Select gradeDropdown = new Select(driver.findElement(By.name("grade")));
            gradeDropdown.selectByVisibleText("Grade 1");

            Select sectionDropdown = new Select(driver.findElement(By.name("section")));
            sectionDropdown.selectByVisibleText("A");

            // Fill other required fields for username, password, etc.
            driver.findElement(By.name("username")).sendKeys("johndoe123");
            driver.findElement(By.name("pass")).sendKeys("Password123");
            driver.findElement(By.name("parent_fname")).sendKeys("Jane");
            driver.findElement(By.name("parent_lname")).sendKeys("Doe");
            driver.findElement(By.name("parent_phone_number")).sendKeys("1234567890");

            // Wait for and click submit button
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit']")));
            submitButton.click();
        } catch (Exception e) {
            System.out.println("Error filling the form: " + e.getMessage());
          //  driver.quit();
        }
    }

    private static void verifySubmission(WebDriver driver, WebDriverWait wait) {
        try {
            // Wait for the successful submission confirmation URL or message
            wait.until(ExpectedConditions.urlContains("http://schoolapps.free.nf/RegistrarOffice/student-add.php"));
            
            // Check if URL contains success indication
            String currentPageUrl = driver.getCurrentUrl();
            if (currentPageUrl.contains("status=success")) {
                System.out.println("Test Passed: Student added successfully.");
            } else {
                System.out.println("Test Failed: Form not submitted successfully.");
                System.out.println("Current Page URL: " + currentPageUrl);
            }
        } catch (Exception e) {
            System.out.println("Submission verification failed: " + e.getMessage());
            System.out.println("Test Failed: Error in verifying submission.");
        } finally {
            // Add debugging logs to ensure quit is triggered
            System.out.println("Closing the browser.");
           // driver.quit();
        }
    }}


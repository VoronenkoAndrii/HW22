import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class testingOfAuthorization {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;
    String correctLogin = "1303";
    String getCorrectPass = "Guru99";

    @BeforeMethod
    public void actionBeforeTest() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @AfterClass
    public void actionAfterTest() {
        driver.quit();
    }


    @Test
    public void login() {
        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");

        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("password")).sendKeys(getCorrectPass);
        driver.findElement(By.name("btnLogin")).click();
        element = wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));
        assertTrue(element.getText().contains("Log out"));

    }


    @Test
    public void emptyTest() {
        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");
        driver.findElement(By.name("btnLogin")).click();
        driver.switchTo().alert().accept();
        element = wait.until(presenceOfElementLocated(By.name("btnLogin")));
        assertTrue(element.isDisplayed());

    }

    @Test
    public void negativePass() {
        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");
        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("password")).sendKeys(" 123123a");
        driver.findElement(By.name("btnLogin")).click();
        driver.switchTo().alert().accept();
        element = wait.until(presenceOfElementLocated(By.name("btnLogin")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void negativeLogin() {
        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");
        driver.findElement(By.name("uid")).sendKeys("asda");
        driver.findElement(By.name("password")).sendKeys(getCorrectPass);
        driver.findElement(By.name("btnLogin")).click();
        driver.switchTo().alert().accept();
        element = wait.until(presenceOfElementLocated(By.name("btnLogin")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void emptyLogin() {
        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");
        driver.findElement(By.name("password")).sendKeys(getCorrectPass);
        driver.findElement(By.name("btnLogin")).click();
        driver.switchTo().alert().accept();
        element = wait.until(presenceOfElementLocated(By.name("btnLogin")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void emptyPass() {
        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");
        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("btnLogin")).click();
        driver.switchTo().alert().accept();
        element = wait.until(presenceOfElementLocated(By.name("btnLogin")));
        assertTrue(element.isDisplayed());
    }
    @Test
    public void emptyPassNegativeLogin() {
        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");
        driver.findElement(By.name("uid")).sendKeys("asda");
        driver.findElement(By.name("btnLogin")).click();
        driver.switchTo().alert().accept();
        element = wait.until(presenceOfElementLocated(By.name("btnLogin")));
        assertTrue(element.isDisplayed());
    }
}

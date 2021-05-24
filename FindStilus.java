import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;
import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FindStilus {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void initialActions() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void closeChrome() {
        driver.quit();
    }

    @BeforeMethod
    public void openGoogle() {
        driver.get("https://www.google.com/");
    }

    @Test
    public void testSearch() {
        driver.findElement(By.name("q")).sendKeys("iphone kyiv buy" + Keys.ENTER);
        WebElement stats = wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));
        int currentPage = 1;
        while (currentPage<=5){
            List<WebElement> allElements= driver.findElements(By.xpath("//cite"));
            Optional<WebElement> findedStylus =allElements.stream().filter(o->o.getText().contains("stylus.ua")).findAny();
            if(findedStylus.isPresent()){
                System.out.println("STYLUS.UA found " + currentPage + "page");
                break;
            }else {
                driver.findElements(By.xpath("//a[@id='pnnext']")).get(0).click();
                currentPage++;

            }
            System.out.println("STYLUS.UA not found on first 5 pages");
        }
    }
}

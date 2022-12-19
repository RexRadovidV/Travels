import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTest {
    @Test
    public void  searchHotel() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo");

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        String lastName="Rachoń";
        int randomNumbers=(int) (Math.random()*1000);
        String email="tester"+randomNumbers+"@gmail.com";

        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.name("firstname")).sendKeys("Wojciech");
        driver.findElement(By.name("lastname")).sendKeys("Rachoń");
        driver.findElement(By.name("phone")).sendKeys("111111111");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(),"Hi, Wojciech Rachoń");

    }
}
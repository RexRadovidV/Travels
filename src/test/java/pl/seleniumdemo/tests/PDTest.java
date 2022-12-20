package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;

public class PDTest extends BaseTest {

    @Test
    public void noResultsFoundTest(){


        driver.findElement(By.name("checkin")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='23']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.name("checkout")).clear();
        driver.findElement(By.name("checkout")).sendKeys("25/12/2022");
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Search']")).click();

        List<String>noResultsFound= driver.findElements(By.xpath("//h2[@class='text-center' and text()='No Results Found']"))
                .stream()
                .map(el -> el.getAttribute("textContent")).toList();

        Assert.assertEquals("No Results Found",noResultsFound.get(0));


    }
}

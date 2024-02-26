import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SeleniumTest {

    public static WebDriver driver = new ChromeDriver();

    @BeforeTest
    void setup(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    void stepTest(){
        String userName = "standard_user";
        String userPassword = "secret_sauce";
        String expectedHeader = "Swag Labs";
        Integer expectedListInventoryItems = 6;

        driver.findElement(By.cssSelector("input[data-test=\"username\"]")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[data-test=\"password\"]")).sendKeys(userPassword);
        driver.findElement(By.cssSelector("input[data-test=\"login-button\"]")).click();

        WebElement headerElement = driver.findElement(By.cssSelector("div.header_label"));
        WebElement shoppingCartLink = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        List<WebElement> listInventoryItems = driver.findElements(By.cssSelector("div.inventory_item"));

        Assert.assertEquals(headerElement.getText(), expectedHeader);
        Assert.assertTrue(shoppingCartLink.isDisplayed());
        Assert.assertEquals(listInventoryItems.size(), expectedListInventoryItems);
    }

    @AfterTest
    void tearDown(){
        driver.close();
    }
}

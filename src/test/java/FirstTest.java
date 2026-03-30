import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FirstTest {
    //1 открыть браузер
    //2 зайти на сайт
    //3 поиск элементов и внесение данных
    //4 проверка


    @Test
    public void checkLogin() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.xpath("//*[@id='user-name']")).sendKeys("standard_user");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("[id=login-button]")).click();
        String title = browser.findElement(By.cssSelector("[data-test='title']")).getText();

        assertEquals(title,"Products");

        browser.quit();

    }
}

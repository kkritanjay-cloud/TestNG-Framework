package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By username = By.name("username");
    By password = By.name("password"); // ✔ FIXED
    By loginBtn = By.xpath("//button[@type='submit']");

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);   // ✔ use parameter
        driver.findElement(password).sendKeys(pass);   // ✔ use parameter
        driver.findElement(loginBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }
}
package Pages;

import Tests.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPage {
    WebDriver driver;
    ConfigReader configReader=new ConfigReader();
    public LoginPage(WebDriver driver) throws IOException {
        this.driver=driver;
        configReader.Read();
    }
    public void ValidLogin(){
        driver.findElement(By.id("menu-toggle")).click();
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        driver.findElement(By.id("txt-username")).sendKeys(configReader.getUserName());
        driver.findElement(By.id("txt-password")).sendKeys(configReader.getPassword());
        driver.findElement(By.id("btn-login")).click();
    }
    public void LogOut(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menu=wait.until(ExpectedConditions.elementToBeClickable(By.id("menu-toggle")));
        menu.click();
        WebElement log= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"authenticate.php?logout\"]")));
        log.click();
        assertTrue(configReader.getUrl().contains("https://katalon-demo-cura.herokuapp.com/"));
    }
    public void InvalidLogin()  {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get(configReader.getLoginUrl());
        WebElement pass=driver.findElement(By.id("txt-username"));
        pass.sendKeys(configReader.getInvalidUser());
        wait.until(ExpectedConditions.textToBePresentInElementValue(pass,configReader.getInvalidUser()));
        WebElement password=driver.findElement(By.id("txt-password"));
        password.sendKeys(configReader.getInvalidPassword());
        wait.until(ExpectedConditions.textToBePresentInElementValue(password,configReader.getInvalidPassword()));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-login"))).click();
        assertTrue(driver.findElement(By.className("text-danger")).getText().contains("Login failed! Please ensure the username and password are valid."));
    }
    public void VerifyingRedirecttoLoginpage(){
        driver.findElement(By.id("btn-make-appointment")).click();
        assertEquals("https://katalon-demo-cura.herokuapp.com/profile.php#login",driver.getCurrentUrl());
    }
}

package Pages;

import Tests.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class ProfilePage {
    WebDriver driver;
    ConfigReader configReader=new ConfigReader();
    public ProfilePage(WebDriver driver) throws IOException {
        this.driver=driver;
        configReader.Read();
    }
    public void ViewProfile(){
        driver.get(configReader.getProfile());
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu-toggle"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='profile.php#profile']"))).click();
    }
}

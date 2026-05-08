package Pages;

import Tests.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryPage {
    WebDriver driver;
    ConfigReader configReader=new ConfigReader();
    public HistoryPage(WebDriver driver) throws IOException {
        this.driver=driver;
        configReader.Read();
    }
    public void History() throws InterruptedException {
        driver.get(configReader.getHistory());
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu-toggle"))).click();
        WebElement buton= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='history.php#history']")));
        Thread.sleep(2000);
        buton.click();
        Thread.sleep(2000);
        String Date=driver.findElement(By.xpath("//div[@class='panel-heading']")).getText();
        String Facility=driver.findElement(By.xpath("//p[@id='facility']")).getText();
        assertEquals("05/06/2026",Date);
        assertEquals("Seoul CURA Healthcare Center",Facility);
    }
    public void MultipleHistory() throws InterruptedException{
        driver.get(configReader.getHistory());
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu-toggle"))).click();
        WebElement buton= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='history.php#history']")));
        Thread.sleep(2000);
        buton.click();
        Thread.sleep(2000);
    }

}

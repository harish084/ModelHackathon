package Pages;

import Tests.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppointmentPage {
    WebDriver driver;
    ConfigReader configReader=new ConfigReader();
        public AppointmentPage(WebDriver driver) throws IOException {
            this.driver=driver;
            configReader.Read();
        }
        public void AppointmentBook(){
            driver.get(configReader.getMakeAppointment());
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element=driver.findElement(By.id("combo_facility"));
            element.click();
            Select select=new Select(element);
            select.selectByValue("Seoul CURA Healthcare Center");
            driver.findElement(By.xpath("//div[@class='input-group-addon']")).click();
            driver.findElement(By.xpath("//div[@class='datepicker-days']//table[@class='table-condensed']//thead//tr[2]//th[3]")).click();
            driver.findElement(By.xpath("//table[@class='table-condensed']//tbody//tr[1]//td[6]")).click();
            driver.findElement(By.id("txt_comment")).sendKeys("I have HeadAche from past 10 days");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-book-appointment"))).click();
            assertTrue(driver.findElement(By.tagName("h2")).getText().contains("Appointment Confirmation"));
        }

        public void MultipleAppointment(String facility,String MonthandYear,String date,String comment){
            driver.get(configReader.getMakeAppointment());
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element=driver.findElement(By.id("combo_facility"));
            element.click();
            Select select=new Select(element);
            select.selectByValue(facility);
            driver.findElement(By.xpath("//div[@class='input-group-addon']")).click();
            driver.findElement(By.xpath(MonthandYear)).click();
            driver.findElement(By.xpath(date)).click();
            driver.findElement(By.id("txt_comment")).sendKeys(comment);
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-book-appointment"))).click();
            assertTrue(driver.findElement(By.tagName("h2")).getText().contains("Appointment Confirmation"));
            driver.findElement(By.xpath("//p[@class='text-center']//a[@class='btn btn-default']")).click();
            driver.get(configReader.getMakeAppointment());
        }

}

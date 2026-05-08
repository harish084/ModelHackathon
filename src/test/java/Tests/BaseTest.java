package Tests;

import Pages.AppointmentPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    ConfigReader configReader=new ConfigReader();
    WebDriver driver;
    @BeforeTest
    void SetUp() throws IOException {
        configReader.Read();
        driver=new EdgeDriver();
        driver.get(configReader.getUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @BeforeMethod
    void Login() throws IOException{
        LoginPage page=new LoginPage(driver);
        page.ValidLogin();
    }
    @Test(priority = 1)
    void CheckUserAuthentication() throws IOException{
        LoginPage page=new LoginPage(driver);
        page.LogOut();
        page.InvalidLogin();
        page.VerifyingRedirecttoLoginpage();
    }
    @Test(priority = 2)
    void CheckAppointment() throws IOException{
        HomePage page1=new HomePage(driver);
        AppointmentPage page=new AppointmentPage(driver);
        page.AppointmentBook();
        page1.GetHomePage();
    }
    @AfterTest
    void Post(){
        driver.quit();
    }
}

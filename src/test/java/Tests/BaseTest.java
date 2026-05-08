package Tests;

import Pages.AppointmentPage;
import Pages.HistoryPage;
import Pages.LoginPage;
import Pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    ConfigReader configReader=new ConfigReader();
    WebDriver driver;
    int c=0;
    @BeforeTest
    void SetUp() throws IOException {
        configReader.Read();
        driver=new EdgeDriver();
        driver.get(configReader.getUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @BeforeMethod
    void Login(Method method) throws IOException{
        if(method.getName().contains("skipTest") || (method.getName().contains("CheckMultipleAppointment") && c>0)){
            return;
        }
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
        AppointmentPage page=new AppointmentPage(driver);
        page.AppointmentBook();
    }
    @Test(priority = 3)
    void skipTest() throws IOException,InterruptedException{
        HistoryPage page=new HistoryPage(driver);
        page.History();
        LoginPage page1=new LoginPage(driver);
        page1.LogOut();
    }
    @Test(priority = 4,dataProvider = "AppointmentData",dataProviderClass = Dataprovider.class)
    void CheckMultipleAppointment(String facility,String MonthandYear,String date,String comment) throws IOException,InterruptedException{
        c++;
        AppointmentPage page=new AppointmentPage(driver);
        page.MultipleAppointment(facility,MonthandYear,date,comment);
        HistoryPage page1=new HistoryPage(driver);
        page1.MultipleHistory();
    }
    @AfterClass
    void Post(){
        driver.quit();
    }
}

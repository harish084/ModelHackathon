package Pages;

import Tests.ConfigReader;
import org.openqa.selenium.WebDriver;


import java.io.IOException;

public class HomePage {
    WebDriver driver;
    ConfigReader configReader=new ConfigReader();
    public HomePage(WebDriver driver) throws IOException {
        this.driver=driver;
        configReader.Read();
    }
    public void GetHomePage(){
        driver.get(configReader.getUrl());
    }

}

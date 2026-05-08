package Tests;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public  class ConfigReader  {
    Properties prop;
    public void Read() throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//Testbase//config.properties");
        prop = new Properties();
        prop.load(file);
    }
    public String getUrl() {
        return prop.getProperty("url");
    }
    public String getLoginUrl(){
        return prop.getProperty("LoginUrl");
    }
    public  String getMakeAppointment(){
        return prop.getProperty("MakeAppointment");
    }
    public String getHistory(){
        return prop.getProperty("History");
    }
    public String getProfile(){
        return prop.getProperty("Profile");
    }
    public String getUserName(){
        return prop.getProperty("UserName");
    }
    public String getPassword(){
        return prop.getProperty("Password");
    }
    public String getInvalidUser(){
        return prop.getProperty("InvalidUser");
    }public String getInvalidPassword(){
        return prop.getProperty("InvalidPassword");
    }
}

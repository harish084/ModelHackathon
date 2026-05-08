package Tests;

import org.testng.annotations.DataProvider;

public class Dataprovider {
    @DataProvider(name = "AppointmentData")
    public Object[][] data(){
        return new Object[][]{
                {"Hongkong CURA Healthcare Center","//div[@class='datepicker-days']//table[@class='table-condensed']//thead//tr[2]//th[3]","//table[@class='table-condensed']//tbody//tr[1]//td[6]","I have HeadAche from past 10 days"},
                {"Seoul CURA Healthcare Center","//div[@class='datepicker-days']//table[@class='table-condensed']//thead//tr[2]//th[3]","//table[@class='table-condensed']//tbody//tr[1]//td[7]","I have Fever from past 10 days"},
        };
    }
}

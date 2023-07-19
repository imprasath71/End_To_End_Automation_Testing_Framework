package testComponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import page_object_model.Landing_page;

public class BaseTest {
    public WebDriver driver;
    public Landing_page landing_page;
	public WebDriver initializeDriver() throws IOException{

        //property class can read the global properties file 
        //File is being read by FileInputStream 
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\GlobalData.properties");
        properties.load(fileInputStream);
        String Browser = properties.getProperty("browser");
        if(Browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            
        }
        else if(Browser.equalsIgnoreCase("Firefox")){
            //Firefox integration Code
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            //Edge integration Code
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;

}

    @BeforeMethod (alwaysRun=true)
    public Landing_page LaunchApplication() throws IOException{
        driver = initializeDriver();
        landing_page = new Landing_page(driver);
        landing_page.goTo();
        return landing_page;
    }

    @AfterMethod (alwaysRun=true)
    public void tearDown(){
        driver.close();
    }
}
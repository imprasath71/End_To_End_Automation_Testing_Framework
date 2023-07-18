package packagestandalonetest;

import static org.junit.Assume.assumeNoException;
import org.junit.*;     
import org.junit.Assert.*;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstract_components.Abstract_Components;
import page_object_model.Landing_page;

public class Stand_Alone_Test  {
    public static void main(String[] args) throws InterruptedException{
        WebDriver driver = new ChromeDriver();
        String productName = "ZARA COAT 3";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Landing_page landing_page = new Landing_page(driver);
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.findElement(By.id("userEmail")).sendKeys("johnwick123@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Johnwick123");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));  
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List <WebElement> products =  driver.findElements(By.cssSelector(".mb-3"));
        System.out.println(products);
        WebElement prod = products.stream().filter(product->
        product.findElement(By.cssSelector("b"))
        .getText().equals(productName)).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating - class name.

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        Thread.sleep(3000);
        //driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS) ;
        driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

        List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
        org.junit.Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();
        WebElement ddropdown = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
        // Select select = new Select(ddropdown);
        // select.selectByVisibleText("India");;
        Thread.sleep(3000);
//        ddropdown.sendKeys("India");
        

//        driver.findElement(By.xpath("//span[normalize-space()='']")).click();
        
        

        Actions actions = new Actions(driver);
        actions.sendKeys(ddropdown,"india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
        driver.findElement(By.cssSelector(".actions a ")).click();
        
        String actual_msg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(actual_msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


        
        
    }
    
    

}

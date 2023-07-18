package abstract_components;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page_object_model.CartPage;
import page_object_model.OrderPage;

public class Abstract_Components {
    //wait statements

    //Trying myself
    //After Change - Trying myself
    @FindBy (css = "[routerlink*=cart]")
    WebElement cartButton;

    //myorders

    @FindBy (css = "[routerlink*=myorders]")
    WebElement ordersButton;

    WebDriver driver;
    public Abstract_Components(WebDriver driver) {
        this.driver = driver;
        
    }

    public void waitforElementtoAppear(By by){
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));  
     wait.until(ExpectedConditions.visibilityOfElementLocated((By) by));
    }

    public void waitforWebElementtoAppear(WebElement element){
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));  
     wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitforElementtoDisappear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));  
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    //Trying myself 
    public CartPage cartNavigation(){
        cartButton.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public  OrderPage orderPageNavigation(){
        ordersButton.click();
        OrderPage orderPage  = new OrderPage(driver);
        return orderPage;
    }


}

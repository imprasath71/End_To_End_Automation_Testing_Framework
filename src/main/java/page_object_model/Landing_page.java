package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_components.Abstract_Components;

public class Landing_page extends Abstract_Components{
    WebDriver driver;

    //using constructor we can fetch the original driver props.
    public Landing_page(WebDriver driver){

        //super keyword used to send variable to parent class. 
        
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        

    }

    //WebElement user_email = driver.findElement(By.id("userEmail"));
    //page object model focuses on only actions and init, there should be no hardcoding.

    //page factory used to reduce syntax in declarations 

    @FindBy(id = "userEmail")
    WebElement user_email;

    @FindBy(id = "userPassword")
    WebElement user_password;

    @FindBy(id = "login")
    WebElement login_button;

    @FindBy(css = "[class*=flyInOut]")
    WebElement incorrectMessage;


    public Product_Catalog  login_application(String email,String password){
        user_email.sendKeys(email);
        user_password.sendKeys(password);
        login_button.click();
        Product_Catalog product_Catalog = new Product_Catalog(driver);
        return product_Catalog;

    }

    public String getErrorMessage(){
        waitforWebElementtoAppear(incorrectMessage);
        String actual_message = incorrectMessage.getText();
        return actual_message;
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }



}

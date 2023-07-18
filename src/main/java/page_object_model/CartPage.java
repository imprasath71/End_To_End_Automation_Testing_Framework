package page_object_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import abstract_components.Abstract_Components;

public class CartPage extends Abstract_Components {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        
        
    }

    //After change - Trying myself
    @FindBy (css = ".cartSection h3")
    List <WebElement> cartProductslist;

    @FindBy (css = ".totalRow button")
    WebElement checkoutButton;

    public Boolean cartProductverification(String productName){
        Boolean match = cartProductslist.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage cartCheckOut(){
        checkoutButton.click();
        return new CheckoutPage(driver);
        
    }


	

	

}

package page_object_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import abstract_components.Abstract_Components;

public class OrderPage extends Abstract_Components {
    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        
        
    }

    @FindBy (css = "tr td:nth-child(3)")
    List <WebElement> orderProductList;

    public Boolean orderProductverification(String productName){
        Boolean match = orderProductList.stream().anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    }


	

	


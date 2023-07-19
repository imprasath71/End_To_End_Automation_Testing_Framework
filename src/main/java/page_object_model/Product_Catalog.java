package page_object_model;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import abstract_components.Abstract_Components;

public class Product_Catalog extends Abstract_Components{
    WebDriver driver;

    //using constructor we can fetch the original driver props.
    public Product_Catalog(WebDriver driver){

        //every child should serve parent class. 
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        

    }
    //Page Factory for Driver.findelement 
    @FindBy(css  = ".mb-3")
    List<WebElement> products ;
    //Page Factory for Driver.findelement 
    @FindBy(css = ".ng-animating")
    WebElement spinner;

     


    By products_by = By.cssSelector(".mb-3");
    By addtoCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage  = By.cssSelector("#toast-container");

    public List<WebElement> getProductList(){
        
        waitforElementtoAppear(products_by);
        return products;

    }

    public WebElement getProductbyName(String productName){


        WebElement prod = getProductList().stream().filter(product->
        product.findElement(By.cssSelector("b"))
        .getText().equals(productName)).findFirst().orElse(null);
        return prod;

    }

    public void addProducttoCart(String productName){
        WebElement prod = getProductbyName(productName);
        prod.findElement(addtoCart).click();
        waitforElementtoAppear(toastMessage);
        waitforElementtoDisappear(spinner);

    }


}

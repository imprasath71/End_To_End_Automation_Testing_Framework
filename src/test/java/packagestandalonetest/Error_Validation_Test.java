package packagestandalonetest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import page_object_model.CartPage;
import page_object_model.CheckoutPage;
import page_object_model.ConfirmationPage;
import page_object_model.Product_Catalog;
import testComponents.BaseTest;

public class Error_Validation_Test extends BaseTest {
 
       @Test (groups =  {"ErrorHandling"})
       public void LoginErrorValidation() throws IOException, InterruptedException{
        String productName = "ZARA COAT 3";
        //give different email address for different tests. 
        landing_page.login_application("johnwik123@gmail.com", "Johnwick123");
        Assert.assertEquals("Incorrect email or password.",landing_page.getErrorMessage());

       }

       @Test 
       public void Product_Validation() throws IOException, InterruptedException{
        String productName = "ZARA COAT 3";
        Product_Catalog product_Catalog = landing_page.login_application("jdmaster@gmail.com", "JohnDurai#123");
        List<WebElement> products = product_Catalog.getProductList();
        product_Catalog.addProducttoCart(productName);
        CartPage cartPage = product_Catalog.cartNavigation();
        Boolean match = cartPage.cartProductverification("ZARA COAT 33");
        Assert.assertFalse(match);

       }



}

package packagestandalonetest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_model.CartPage;
import page_object_model.CheckoutPage;
import page_object_model.ConfirmationPage;
import page_object_model.Landing_page;
import page_object_model.OrderPage;
import page_object_model.Product_Catalog;
import testComponents.BaseTest;

public class Submit_Order_Test extends BaseTest {
       String productName = "ZARA COAT 3";
 
       @Test
       public void submitOrder() throws IOException, InterruptedException{
        
        Product_Catalog product_Catalog = landing_page.login_application("johnwick123@gmail.com", "Johnwick123");
        List<WebElement> products = product_Catalog.getProductList();
        product_Catalog.addProducttoCart(productName);
        //Thread.sleep(2000);
        CartPage cartPage = product_Catalog.cartNavigation();
        Boolean match = cartPage.cartProductverification(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.cartCheckOut();
        checkoutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

       }

       @Test (dependsOnMethods = {"submitOrder"})
       public void OrderHistory(){
              
              Product_Catalog product_Catalog = landing_page.login_application
              ("johnwick123@gmail.com", "Johnwick123");
              OrderPage orderPage = product_Catalog.orderPageNavigation(); 
              Assert.assertTrue(orderPage.orderProductverification(productName));

       }
}

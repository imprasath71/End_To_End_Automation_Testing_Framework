package packagestandalonetest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import page_object_model.CartPage;
import page_object_model.CheckoutPage;
import page_object_model.ConfirmationPage;
import page_object_model.OrderPage;
import page_object_model.Product_Catalog;
import testComponents.BaseTest;

public class Submit_Order_Test extends BaseTest {
       String productName = "ZARA COAT 3";
 
       @Test(dataProvider = "getData",groups = {"Purchase"})
       public void submitOrder(HashMap<String,String> inputHashMap) throws IOException, InterruptedException{
        
        Product_Catalog product_Catalog = landing_page.login_application(inputHashMap.get("email"),inputHashMap.get("password"));
        List<WebElement> products = product_Catalog.getProductList();
        product_Catalog.addProducttoCart(inputHashMap.get("productName"));
        //Thread.sleep(2000);
        CartPage cartPage = product_Catalog.cartNavigation();
        Boolean match = cartPage.cartProductverification(inputHashMap.get("productName"));
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

       @DataProvider 
       public Object[][] getData(){
              //in the before method we used 2-d array to send test-data. 
              //now Hashmap is used. 
              HashMap <String,String> hashMap = new HashMap<String,String>();
              hashMap.put("email", "johnwick123@gmail.com");
              hashMap.put("password", "Johnwick123");
              hashMap.put("productName", "ZARA COAT 3");

              HashMap <String,String> hashMap_1  = new HashMap<String,String>();
              hashMap_1.put("email", "jdmaster@gmail.com");
              hashMap_1.put("password", "JohnDurai#123");
              hashMap_1.put("productName", "ADDIDAS ORIGINAL");

              return new Object[][]{{hashMap},{hashMap_1}};

       }

}

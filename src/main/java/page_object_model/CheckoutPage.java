package page_object_model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_components.Abstract_Components;

public class CheckoutPage extends Abstract_Components {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy (xpath  = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy (xpath  = "//button[contains(@class, 'ta-item')][2]")
    WebElement selectCountry;

    @FindBy (css  = ".actions a ")
    WebElement actionSubmit;

    public void selectCountry(String countryName){
        Actions actions = new Actions(driver);
        actions.sendKeys(country,countryName).build().perform();
        waitforElementtoAppear(By.cssSelector(".ta-results"));
        selectCountry.click();
    }
    public ConfirmationPage submitOrder(){
        actionSubmit.click();
        return new ConfirmationPage(driver);
    }


}

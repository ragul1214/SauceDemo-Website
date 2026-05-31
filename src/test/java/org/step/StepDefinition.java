package org.step;

import org.base.baseclass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends baseclass {

    @Given("the user is on the Sauce Demo Page")
    public void the_user_is_on_the_sauce_demo_page() {
        launchURL("https://www.saucedemo.com/");
    }

     @When("user logs in with the correct credentials")
    public void user_logs_in_with_the_correct_credentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        page.getUser("standard_user");
        page.getPass("secret_sauce");
        page.getButton();
        
    }

    @When("adds items to the cart and clicks checkout")
    public void adds_items_to_the_cart_and_clicks_checkout() {
    	try {
    	    Alert alert = driver.switchTo().alert();
    	    alert.accept(); // clicks OK
    	} catch (NoAlertPresentException e) {
    	    System.out.println("No alert present.");
    	}
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(page.getAddToCartBtn()));
        addToCartBtn.click();
        
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(page.getAddToCartBtn()));
        cartLink.click();
        
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutBtn.click();
    }

    @When("enters checkout information with first name {string} last name {string} and zip code {string}")
    public void enters_checkout_information(String firstName, String lastName, String zipCode) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement fNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        fNameField.sendKeys(firstName);
        
        WebElement lNameField = findid("last-name");
        lNameField.sendKeys(lastName);
        
        WebElement zipField = findid("postal-code");
        zipField.sendKeys(zipCode);
        
        
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();
        
        
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();
    }

    @Then("the order confirmation message {string} should be displayed")
    public void the_order_confirmation_message_should_be_displayed(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmationHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        
        String actualMessage = confirmationHeader.getText();
        
        System.out.println (actualMessage);
    }
}

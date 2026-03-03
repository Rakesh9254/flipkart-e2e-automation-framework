package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerConfig;
import utils.SeleniumUtils;

import java.util.List;

public class AddToCartPage {

    public AddToCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='KzDlHZ' or @class='_4rR01T']")
    private List<WebElement> productResults;

    @FindBy(xpath = "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cart')]")
    private WebElement addToCartBtn;

    @FindBy(id = "pincodeInputId")
    private WebElement pincodeInput;

    @FindBy(xpath = "//span[text()='Check']")
    private WebElement checkPincodeBtn;

    public void printAllResults() {
        LoggerConfig.info("Total products found: " + productResults.size());
        if (!productResults.isEmpty()) {
            SeleniumUtils.getAllText(productResults).forEach(LoggerConfig::info);
        }
    }

    public void clickOnProduct(int index) {
        SeleniumUtils.pause(3);
        if (index < productResults.size()) {
            SeleniumUtils.click(productResults.get(index));
            SeleniumUtils.switchToNewWindow();
        } else {
            LoggerConfig.error("Product index " + index + " is out of range.");
        }
    }

    public void enterPincode(String pincode) {
        try {
            SeleniumUtils.scrollToElement(pincodeInput);
            SeleniumUtils.enterValue(pincodeInput, pincode);
            SeleniumUtils.click(checkPincodeBtn);
            SeleniumUtils.pause(2);
        } catch (Exception e) {
            LoggerConfig.warn("Pincode field not available, skipping.");
        }
    }

    public void verifyProductTitle() {
        String title = SeleniumUtils.getTitle();
        if (title.contains("Buy")) {
            LoggerConfig.info("Product page title looks correct.");
        } else {
            LoggerConfig.warn("Unexpected product page title: " + title);
        }
    }

    public void clickAddToCart() {
        try {
            SeleniumUtils.scrollToElement(addToCartBtn);
            SeleniumUtils.click(addToCartBtn);
        } catch (Exception e) {
            LoggerConfig.warn("Add to Cart button not available.");
        }
    }
}

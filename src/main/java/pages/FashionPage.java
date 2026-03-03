package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerConfig;
import utils.SeleniumUtils;

public class FashionPage {

    public FashionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'Fashion')] | //div[contains(text(),'Fashion')]")
    private WebElement fashionMenu;

    @FindBy(xpath = "//*[contains(text(),'Kids')]")
    private WebElement kidsMenu;

    @FindBy(xpath = "//a[contains(text(),'Girls Dresses')] | //a[contains(text(),'Girls')]")
    private WebElement girlsDresses;

    public void hoverOverFashion() {
        SeleniumUtils.mouseOver(fashionMenu);
    }

    public void hoverOverKids() {
        SeleniumUtils.mouseOver(kidsMenu);
    }

    public void clickGirlsDresses() {
        try {
            SeleniumUtils.moveToElementAndClick(girlsDresses);
        } catch (Exception e) {
            LoggerConfig.warn("Could not click Girls Dresses.");
        }
    }
}

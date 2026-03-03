package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerConfig;
import utils.SeleniumUtils;

public class FilterPage {

    public FilterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//select)[1]")
    private WebElement minPriceDropdown;

    @FindBy(xpath = "(//select)[2]")
    private WebElement maxPriceDropdown;

    @FindBy(xpath = "//div[text()='Brand']")
    private WebElement brandSection;

    @FindBy(xpath = "//div[text()='Apple']/preceding-sibling::div")
    private WebElement appleBrandCheckbox;

    @FindBy(xpath = "//div[text()='RAM']")
    private WebElement ramSection;

    @FindBy(xpath = "//div[text()='4 GB']/preceding-sibling::div")
    private WebElement ramCheckbox;

    public void selectMinPrice(String price) {
        try {
            SeleniumUtils.scrollToElement(minPriceDropdown);
            SeleniumUtils.selectByVisibleText(minPriceDropdown, price);
        } catch (Exception e) {
            LoggerConfig.warn("Could not set min price filter.");
        }
    }

    public void selectMaxPrice(String price) {
        try {
            SeleniumUtils.selectByVisibleText(maxPriceDropdown, "₹" + price);
        } catch (Exception e) {
            LoggerConfig.warn("Could not set max price filter.");
        }
    }

    public void selectBrand() {
        SeleniumUtils.scrollToElement(brandSection);
        SeleniumUtils.click(appleBrandCheckbox);
    }

    public void selectRam() {
        SeleniumUtils.scrollToElement(ramSection);
        SeleniumUtils.click(ramCheckbox);
    }
}

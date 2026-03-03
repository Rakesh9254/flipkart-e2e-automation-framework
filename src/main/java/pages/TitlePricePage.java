package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerConfig;
import utils.SeleniumUtils;

import java.util.List;

public class TitlePricePage {

    public TitlePricePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='KzDlHZ' or @class='_4rR01T']")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//div[@class='Nx9bqj _4b5DiR' or @class='_30jeq3 _1_WHN1']")
    private List<WebElement> productPrices;

    public void extractTitlesAndPrices() {
        List<String> titles = SeleniumUtils.getAllText(productTitles);
        List<String> prices = SeleniumUtils.getAllText(productPrices);
        int count = Math.min(titles.size(), prices.size());

        for (int i = 0; i < count; i++) {
            LoggerConfig.info("Product " + (i + 1) + ": " + titles.get(i) + " | Price: " + prices.get(i));
        }

        if (count == 0) {
            LoggerConfig.warn("No products found on this page.");
        }
    }
}

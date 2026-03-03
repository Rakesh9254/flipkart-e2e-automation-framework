package utils;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SeleniumUtils extends BaseTest {

    private static final int WAIT_TIMEOUT = 15;

    private static WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    public static void click(WebElement element) {
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            LoggerConfig.warn("Normal click failed, using JS click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public static void enterValue(WebElement element, String value) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

    public static String getTitle() {
        return driver.getTitle();
    }

    public static void scrollToElement(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void mouseOver(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        new Actions(driver).moveToElement(element).perform();
    }

    public static void moveToElementAndClick(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        new Actions(driver).moveToElement(element).click().perform();
    }

    public static void selectByVisibleText(WebElement dropdown, String text) {
        getWait().until(ExpectedConditions.visibilityOf(dropdown));
        new Select(dropdown).selectByVisibleText(text);
    }

    public static void selectByIndex(WebElement dropdown, int index) {
        getWait().until(ExpectedConditions.visibilityOf(dropdown));
        new Select(dropdown).selectByIndex(index);
    }

    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) {
        }
    }

    public static void navigateBack() {
        driver.navigate().back();
    }

    public static void switchToNewWindow() {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public static List<String> getAllText(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();
        for (WebElement el : elements) {
            try {
                texts.add(el.getText());
            } catch (Exception ignored) {
            }
        }
        return texts;
    }
}

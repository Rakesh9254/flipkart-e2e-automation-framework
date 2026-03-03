package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.LoggerConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    protected static WebDriver driver;
    protected static Properties prop;

    static {
        try {
            prop = new Properties();
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static void initializeBrowser() {
        LoggerConfig.info("Launching Chrome browser...");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Run headless in CI environments
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);

        int implicitWait = Integer.parseInt(prop.getProperty("implicitWait", "10"));
        int pageLoadTimeout = Integer.parseInt(prop.getProperty("pageLoadTimeout", "30"));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));

        String url = prop.getProperty("url");
        LoggerConfig.info("Opening URL: " + url);
        driver.get(url);

        dismissLoginPopup();
    }

    private static void dismissLoginPopup() {
        try {
            Thread.sleep(3000);
            List<WebElement> closeButtons = driver.findElements(
                    By.xpath("//*[text()='✕'] | //button[text()='✕'] | //span[text()='✕']"));

            if (!closeButtons.isEmpty()) {
                closeButtons.get(0).click();
                LoggerConfig.info("Login popup dismissed.");
            } else {
                driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
                LoggerConfig.info("Popup closed via Escape key.");
            }
        } catch (Exception e) {
            LoggerConfig.info("No login popup found, continuing...");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            LoggerConfig.info("Browser closed.");
        }
    }
}

package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.LoggerConfig;

public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        LoggerConfig.info("--- Starting: " + scenario.getName() + " ---");
        BaseTest.initializeBrowser();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerConfig.error("FAILED: " + scenario.getName());
            try {
                byte[] screenshot = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "FailureScreenshot");
            } catch (Exception e) {
                LoggerConfig.error("Could not capture screenshot.");
            }
        } else {
            LoggerConfig.info("PASSED: " + scenario.getName());
        }

        BaseTest.tearDown();
        LoggerConfig.info("--- Finished: " + scenario.getName() + " ---");
    }
}

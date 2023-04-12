package framework.browser;

import framework.util.config.ConfigManager;
import framework.util.logger.LoggerManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
    private static ChromeDriver getChromeInstance(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(ConfigManager.getStringValueOf("chromeWindowSize"));
        return new ChromeDriver(chromeOptions);
    }
    private static FirefoxDriver getFirefoxInstance(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments(ConfigManager.getStringValueOf("firefoxWindowHeight"),
                ConfigManager.getStringValueOf("firefoxWindowWidth"));
        return new FirefoxDriver(firefoxOptions);
    }
    protected static WebDriver getBrowser(String browserName){
        switch (browserName){
            case "chrome":
                LoggerManager.logInfo("Chrome is chosen");
                return getChromeInstance();
            case "firefox":
                LoggerManager.logInfo("Firefox is chosen");
                return getFirefoxInstance();
            default:
                throw new RuntimeException("Incorrect BrowserName");
        }
    }
}

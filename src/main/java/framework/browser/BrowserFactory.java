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
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }
    private static FirefoxDriver getFirefoxInstance(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
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

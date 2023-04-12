package framework.browser;

import framework.util.config.ConfigManager;
import framework.util.logger.LoggerManager;
import org.openqa.selenium.WebDriver;

public class BrowserManager {
    private static WebDriver instanceOfDriver = null;

    public static WebDriver getDriver(){
        if(instanceOfDriver == null){
            LoggerManager.logInfo("Driver is initialised");
            return instanceOfDriver = BrowserFactory.getBrowser(ConfigManager.getStringValueOf("browser"));
        }
        return instanceOfDriver;
    }
    public static void quitDriver(){
        LoggerManager.logInfo("Driver is quited");
        getDriver().quit();
        instanceOfDriver = null;
    }
}

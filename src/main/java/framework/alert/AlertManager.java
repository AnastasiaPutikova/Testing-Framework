package framework.alert;

import framework.browser.BrowserManager;
import framework.util.logger.LoggerManager;
import framework.wait.Wait;
import org.openqa.selenium.NoAlertPresentException;

public class AlertManager {
    public static void accept(){
        Wait.waitUntilAlertIsPresent().accept();
    }
    public static String getAlertText(){
        return Wait.waitUntilAlertIsPresent().getText();
    }
    public static void switchToAlert(){
        BrowserManager.getDriver().switchTo().alert();
    }
    public static void sendKeysToAlert(String text){
        LoggerManager.logInfo(String.format("Send keys to alert %s", text));
        Wait.waitUntilAlertIsPresent().sendKeys(text);
    }
    public static boolean isAlertPresent(){
        try {
            switchToAlert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}

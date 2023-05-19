package framework.wait;

import framework.browser.BrowserManager;
import framework.util.config.ConfigManager;
import framework.util.logger.LoggerManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {
    public static WebDriverWait getWait() {
        return new WebDriverWait(BrowserManager.getDriver(),
                Duration.ofSeconds(ConfigManager.getIntValueOf("explicitWait")));
    }
    public static boolean isElementDisplayed(By locator) {
        try {
            getWait().until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
            return true;
        } catch (NullPointerException e) {
            LoggerManager.logError(e.toString());
            return false;
        }
    }
    public static void waitUntilElementToBeClickable(By locator) {
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            LoggerManager.logError(e.toString());
        }
    }
    public static void waitUntilElementIsDisappear(By locator) {
        try {
            getWait().until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(locator)));
        } catch (TimeoutException e) {
            LoggerManager.logError(e.toString());
        }
    }
    public static void waitUntilPresenceOfElement(By locator) {
        try {
            getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            LoggerManager.logError(e.toString());
        }
    }
    public static void waitUntilInvisibilityOfElement(By locator) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public static Alert waitUntilAlertIsPresent() {
        return getWait().until(ExpectedConditions.alertIsPresent());
    }
}

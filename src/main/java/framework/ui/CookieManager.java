package framework.ui;

import framework.browser.BrowserManager;
import framework.util.logger.LoggerManager;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class CookieManager {
    public static void addCookie(String key, String value) {
        LoggerManager.logInfo(String.format("Add cookie: key = %s, value = %s", key, value));
        BrowserManager.getDriver().manage()
                .addCookie(new Cookie("key", "value"));
    }
    public static Cookie getCookieNamed(String key) {
        LoggerManager.logInfo(String.format("Get cookie named %s", key));
        return BrowserManager.getDriver().manage().getCookieNamed(key);
    }
    public static Set<Cookie> getAllCookies() {
        LoggerManager.logInfo("Get all cookies");
        return BrowserManager.getDriver().manage().getCookies();
    }
    public static void deleteCookieNamed(String key) {
        LoggerManager.logInfo(String.format("Delete cookie named %s", key));
        BrowserManager.getDriver().manage().deleteCookieNamed(key);
    }
    public static void deleteCookie(Cookie cookie) {
        LoggerManager.logInfo(String.format("Delete cookie %s", cookie.getName()));
        BrowserManager.getDriver().manage().deleteCookie(cookie);
    }
    public static void deleteAllCookies() {
        LoggerManager.logInfo("Delete all cookies");
        BrowserManager.getDriver().manage().deleteAllCookies();
    }
}

package framework.browser;

import framework.util.logger.LoggerManager;
import framework.wait.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BrowserInteractions {
    public static String getTitle(){
        LoggerManager.logInfo("Getting a title");
        return BrowserManager.getDriver().getTitle();
    }
    public static String getCurrentUrl(){
        LoggerManager.logInfo("Getting a current url");
        return BrowserManager.getDriver().getCurrentUrl();
    }
    public static void openUrl(String url){
        LoggerManager.logInfo("Page is opened, url = " + url);
        BrowserManager.getDriver().get(url);
    }
    public static void closeTab(){
        LoggerManager.logInfo("Tab is closed");
        BrowserManager.getDriver().close();
    }
    public static String getWindow(){
        return BrowserManager.getDriver().getWindowHandle();
    }
    public static void switchToOriginTab(String originWindow){
        LoggerManager.logInfo("Switch to origin tab");
        BrowserManager.getDriver().switchTo().window(originWindow);
    }
    public static void switchToFrame(By locator){
        LoggerManager.logInfo("Switch to frame");
        BrowserManager.getDriver().switchTo().frame(BrowserManager.getDriver().findElement(locator));
    }
    public static void switchToDefaultContent(){
        LoggerManager.logInfo("Switch to default content");
        BrowserManager.getDriver().switchTo().defaultContent();
    }
    public static void switchToAnotherTab() {
        LoggerManager.logInfo("Switch to another tab");
        String originalWindow = getWindow();
        Wait.getWait().until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : BrowserManager.getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                BrowserManager.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }
}

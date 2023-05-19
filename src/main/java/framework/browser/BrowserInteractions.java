package framework.browser;

import framework.util.logger.LoggerManager;
import framework.wait.Wait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;

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
    public static void back() {
        LoggerManager.logInfo("Pressing the browser’s back button");
        BrowserManager.getDriver().navigate().back();
    }
    public static void forward() {
        LoggerManager.logInfo("Pressing the browser’s forward button");
        BrowserManager.getDriver().navigate().forward();
    }
    public static void refresh() {
        LoggerManager.logInfo("Refresh the current page");
        BrowserManager.getDriver().navigate().refresh();
    }
    public static void openNewTab(){
        LoggerManager.logInfo("New tab is opened");
        BrowserManager.getDriver().switchTo().newWindow(WindowType.TAB);
    }
    public static void openNewWindow(){
        LoggerManager.logInfo("New window is opened");
        BrowserManager.getDriver().switchTo().newWindow(WindowType.WINDOW);
    }
    public static void closeTab(){
        LoggerManager.logInfo("Tab is closed");
        BrowserManager.getDriver().close();
    }
    public static void switchToOriginTab(String originWindow){
        LoggerManager.logInfo("Switch to origin tab");
        BrowserManager.getDriver().switchTo().window(originWindow);
    }
    public static void switchToFrame(By locator){
        LoggerManager.logInfo("Switch to frame");
        BrowserManager.getDriver().switchTo().frame(BrowserManager.getDriver().findElement(locator));
    }
    public static void leaveFrame(){
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
    public static String getWindow(){
        return BrowserManager.getDriver().getWindowHandle();
    }
    public static void maximizeWindow(){
        LoggerManager.logInfo("Window is maximized");
        BrowserManager.getDriver().manage().window().maximize();
    }
    public static void minimizeWindow(){
        LoggerManager.logInfo("Window is minimized");
        BrowserManager.getDriver().manage().window().minimize();
    }
    public static void fullscreenWindow(){
        LoggerManager.logInfo("Fullscreen is set");
        BrowserManager.getDriver().manage().window().fullscreen();
    }
    public static void takeScreenshot(String imagePathname) {
        LoggerManager.logInfo("Screenshot is taken and saved in " + imagePathname);
        try {
            File scrFile = ((TakesScreenshot)BrowserManager.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(imagePathname));
        }
        catch (IOException e) {
            LoggerManager.logInfo("Image path is wrong: " + e.getMessage());
        }
    }
}

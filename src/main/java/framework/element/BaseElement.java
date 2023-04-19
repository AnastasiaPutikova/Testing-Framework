package framework.element;

import framework.browser.BrowserManager;
import framework.util.logger.LoggerManager;
import framework.wait.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class BaseElement {
    private final By locator;
    private final String name;

    protected BaseElement(By locator, String name){
        this.locator = locator;
        this.name = name;
    }

    protected WebElement findElement(){
        Wait.waitUntilPresenceOfElement(locator);
        try{
            return BrowserManager.getDriver().findElement(locator);
        } catch (NoSuchElementException e){
            LoggerManager.logError("Unable to locate element " + name);
            return null;
        }
    }
    protected void click(){
        LoggerManager.logInfo(String.format("Click on %s", name));
        Wait.waitUntilElementToBeClickable(locator);
        findElement().click();
    }
    protected String  getText(){
        return findElement().getText();
    }
    protected boolean isElementDisappeared(){
        try {
            Wait.waitUntilElementIsDisappear(locator);
            return true;
        } catch (TimeoutException e) {
            LoggerManager.logError(e.toString());
            return false;
        }
    }
    protected boolean isElementPresence(){
        try {
            Wait.waitUntilPresenceOfElement(locator);
            return true;
        } catch (TimeoutException e) {
            LoggerManager.logError(e.toString());
            return false;
        }
    }
    protected boolean isElementDisplayed(){
        try {
            Wait.waitUntilInvisibilityOfElement(locator);
            return false;
        } catch (TimeoutException e) {
            LoggerManager.logError(e.toString());
            return true;
        }
    }
}

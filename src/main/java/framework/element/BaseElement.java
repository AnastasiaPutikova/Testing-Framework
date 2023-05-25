package framework.element;

import framework.browser.BrowserManager;
import framework.util.logger.LoggerManager;
import framework.wait.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BaseElement {
    private final By locator;
    private final String name;

    public BaseElement(By locator, String name){
        this.locator = locator;
        this.name = name;
    }

    public WebElement findElement(){
        Wait.waitUntilPresenceOfElement(locator);
        try{
            return BrowserManager.getDriver().findElement(locator);
        } catch (NoSuchElementException e){
            LoggerManager.logError("Unable to locate element " + name);
            return null;
        }
    }
    public void click(){
        LoggerManager.logInfo(String.format("Click on %s", name));
        WebElement element = findElement();
        new Actions(BrowserManager.getDriver())
                .scrollToElement(element)
                .perform();
        element.click();
    }
    public void type(String text){
        LoggerManager.logInfo(String.format("Type in %s", name));
        Wait.isElementDisplayed(locator);
        findElement().sendKeys(text);
    }
    public void clear(){
        LoggerManager.logInfo(String.format("Clear %s", name));
        Wait.isElementDisplayed(locator);
        findElement().clear();
    }
    public String  getText(){
        return findElement().getText();
    }
    public String  getAttribute(String attributeName){
        return findElement().getAttribute(attributeName);
    }
    public String  getCssValue(String propertyName){
        return findElement().getCssValue(propertyName);
    }
    public String  getTagName(){
        return findElement().getTagName();
    }
    public boolean isElementDisappeared(){
        try {
            Wait.waitUntilElementIsDisappear(locator);
            return true;
        } catch (TimeoutException e) {
            LoggerManager.logError(e.toString());
            return false;
        }
    }
    public boolean isElementPresence(){
        try {
            Wait.waitUntilPresenceOfElement(locator);
            return true;
        } catch (TimeoutException e) {
            LoggerManager.logError(e.toString());
            return false;
        }
    }
    public boolean isElementDisplayed(){
        try {
            Wait.waitUntilInvisibilityOfElement(locator);
            return false;
        } catch (TimeoutException e) {
            LoggerManager.logError(e.toString());
            return true;
        }
    }
}

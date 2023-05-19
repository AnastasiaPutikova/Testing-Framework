package framework.page;

import framework.util.logger.LoggerManager;
import framework.wait.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class BasePage {
    private final By uniqueElement;
    private final String name;

    public BasePage(By uniqueElement, String name){
        this.uniqueElement = uniqueElement;
        this.name = name;
    }

    public boolean isPageOpened(){
        LoggerManager.logInfo(String.format("%s is opened", name));
        try{
            return Wait.isElementDisplayed(uniqueElement);
        } catch (TimeoutException e){
            LoggerManager.logError(e.toString());
            return false;
        }
    }
}

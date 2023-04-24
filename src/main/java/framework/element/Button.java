package framework.element;

import org.openqa.selenium.By;

public class Button extends BaseElement{

    protected Button(By locator, String name) {
        super(locator, name);
    }

    @Override
    public void click() {
        super.click();
    }
}

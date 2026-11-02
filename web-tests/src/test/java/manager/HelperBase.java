package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By locator) {
        try {
            manager.driver.findElement(locator).click();
        } catch (StaleElementReferenceException e) {
            manager.driver.findElement(locator).click();
        }
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    //нужен для скролла до нужного элемента, подключает умение исполнять js
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) manager.driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

}

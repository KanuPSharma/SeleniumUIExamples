
package utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

public abstract class Page {
	
	FluentWait<WebDriver> wait;	

	protected abstract String getPageTitle();

	protected abstract void sleep(int milliseconds) throws InterruptedException;

	protected abstract void scrollDown() ;

	protected abstract void jsExecuteScriptClick(WebElement el);

	protected abstract WebElement getElementBy(By locator);

	protected abstract List<WebElement> getElementsByList(By locator);
	
	protected abstract void implicitWait(int duration);
	
	protected abstract void setWindowHandle(String windowHandle);
	
	protected abstract void fluentWaitUntilElementDisplayed(WebElement ele,boolean condition);
	
	protected abstract void switchToFrame(WebElement ele);

	
}

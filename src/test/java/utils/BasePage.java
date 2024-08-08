package utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
public class BasePage extends Page {
	public static WebDriver driver;

	public BasePage(WebDriver driver) throws IOException {
		this.driver = driver;
	}
	@Override
	protected String getPageTitle() {
	   return driver.getTitle();
	}
	@Override
	protected void sleep(int milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}
	@Override
	protected void jsExecuteScriptClick(WebElement el) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].click();", el);
	}
	@Override
	protected WebElement getElementBy(By locator) {
		return driver.findElement(locator);
	}
	@Override
	protected List<WebElement> getElementsByList(By locator) {
		return driver.findElements(locator);
	}
	@Override
	protected void scrollDown() {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("scroll(0,1000);");
	}
	protected void implicitWait(int duration) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
	}
	
	@Override
	protected void fluentWaitUntilElementDisplayed(WebElement ele,boolean condition) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(Constants.FLUENT_WAIT_VAL))
				.pollingEvery(Duration.ofSeconds(Constants.POOLING)).ignoring(ElementNotInteractableException.class);
		wait.until(d -> {
			return condition;
		});
	}
	@Override
	protected void setWindowHandle(String windowHandle) {
		for(String w: driver.getWindowHandles()) {
			if (w != windowHandle) {
				driver.switchTo().window(w);
			}
		}
	}
	protected String getCurrentWindow() {
		String windowHandle = driver.getWindowHandle();
		return windowHandle;
	}
	@Override
	protected  void switchToFrame(WebElement ele) {
		driver.switchTo().frame(ele);
	}
	protected  void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	protected  void switchToFrame(String fname) {
		driver.switchTo().frame(fname);
	}
}

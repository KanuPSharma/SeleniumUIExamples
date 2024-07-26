package utils;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage extends Page{
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

}

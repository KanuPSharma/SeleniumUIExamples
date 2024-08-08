package uipages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BasePage;

public class IndexPage extends BasePage {
	WebDriver driver;
	private By lnkNewWindow = By.linkText("Open new window");
	private By txtNewWindow = By.xpath("//h1[text()='Almost empty']");
	private By inputBtn=By.xpath("//input[@type='image']");
	private By lnkSwichToFrame=By.name("sameWindow");
	public IndexPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver=driver;
	}
	@Override
	public String getPageTitle() {
		return super.getPageTitle();
	}
	public void navigateToNewWindow() {
		String window = getCurrentWindow();
		getElementBy(lnkNewWindow).click();
		setWindowHandle(window);
		fluentWaitUntilElementDisplayed(getElementBy(txtNewWindow),true);
		Assert.assertTrue(getElementBy(txtNewWindow).isDisplayed());
		
	}
	public void switchToFrame() {
		getElementBy(lnkSwichToFrame).click();
		switchToFrame("iframe1-name");
		
	}
}

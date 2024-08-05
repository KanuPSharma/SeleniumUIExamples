package tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uipages.IndexPage;
import utils.Constants;

public class IndexPageTest extends BaseTest {
	HomePageTest home;
	IndexPage index;

	@BeforeTest(alwaysRun = true)
	public void setUp() throws IOException {
		super.testSetUp("");
		home = new HomePageTest();
		home.navigateToIndexPage();
		takeScreenshot("step1");
		index = new IndexPage(driver);
		String title = index.getPageTitle();
		Assert.assertEquals(title, Constants.INDEX_PAGE_TITLE);
		
	}

	@Test
	public void openNewWindow() throws IOException {
		super.testSetUp("https://www.selenium.dev/selenium/web/xhtmlTest.html");
		index.navigateToNewWindow();
	}
	
	@Test
	public void openNewFrame() throws IOException {
		super.testSetUp("https://www.selenium.dev/selenium/web/xhtmlTest.html");
		index.switchToFrame();
	}
	
}

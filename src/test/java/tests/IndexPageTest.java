package tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uipages.IndexPage;
import utils.Constants;
/*
 * Test Implementation class for IndexPage
 */
public class IndexPageTest extends BaseTest {
	HomePageTest home;
	IndexPage index;

	@BeforeTest(alwaysRun = true)
	public void setUp() throws IOException {
		super.testSetUp("");
		home = new HomePageTest();
		home.navigateToIndexPage();
		index = new IndexPage(driver);
		String title = index.getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.INDEX_PAGE_TITLE);

	}

	@Test
	public void openNewWindow() throws IOException {
		super.testSetUp(Constants.INDEXPAGE);
		index.navigateToNewWindow();
	}

	@Test
	public void openNewFrame() throws IOException {
		super.testSetUp(Constants.INDEXPAGE);
		index.switchToFrame();
	}
}

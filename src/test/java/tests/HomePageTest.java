package tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import uipages.IndexPage;
import utils.Constants;

public class HomePageTest extends BaseTest {
	IndexPage iPage;

	@BeforeTest(alwaysRun =true)
	public void setUp() throws IOException {
		super.testSetUp("");
	}
	@Test(dataProvider= "testData")
	public void enterUserData(String userId, String password, String text) throws IOException {
	
		String title = form.getPageTitle();
		Assert.assertEquals(title, Constants.FORM_PAGE_TITLE);
		System.out.println("WebForm is " + form.getlinkToIndexPage());
		form.enterUserData(userId, password, text);
	}
	@Test(priority = 1)
	public void checkDisabledInput() {
		Assert.assertTrue(form.isInputDisabled(), "Input field is enabled, test failed");

	}

	@Test(priority = 1)
	public void selecDate() {
		form.selectDate(5);
	}

	@Test(priority = 1)
	public void selectListOption() {
		form.selectListOption("Two");
	}

	@Test(priority = 1)
	public void checkBoxFunction() {
		form.clickCheckboxes();
	}

	@DataProvider(name = "testData")
	public Object[][] testData() {

		return new Object[][] { { "123", "password1", "Entering Form Data" } };
	}

	public IndexPage navigateToIndexPage() {
		try {
			iPage = form.navigateToIndexPage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iPage;

	}

}

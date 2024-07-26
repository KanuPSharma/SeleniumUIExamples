package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends Base{
	
	
	@Test(dataProvider="testData")
	public void setUp(String userId,String password,String text) {
		System.out.println("I am in Test class");
		System.out.println("WebForm is " + form.getlinkToIndexPage());
		form.enterUserData(userId, password, text);
		//form.enterFormOptions();
	}

	@Test
	public void selecDate() {
		form.selectDate(5);
	}
	
	@Test
	public void selectListOption() {
		form.selectListOption("Two");
	}
	
	@Test
	public void checkBoxFunction() {
		form.clickCheckboxes();
	}
	
	
	@DataProvider(name = "testData")
	public Object[][] testData() {

		return new Object[][] { { "123", "password1", "Entering Form Data"}

		};
	}

}

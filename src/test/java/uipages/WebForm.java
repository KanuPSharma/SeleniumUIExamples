package uipages;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.BasePage;

public class WebForm extends BasePage {

	WebDriver driver;
	private By id = By.id("my-text-id");
	private By password = By.name("my-password");
	private By textArea = By.name("my-textarea");
	private By disabledInputField = By.name("my-disabled");
	private By selectOption = By.name("my-select");
	private By date = By.name("my-date");
	private By btnRadio = By.id("my-radio-2");
	private By linkToIndexPage = By.linkText("Return to index");
	private IndexPage page;
	

	public WebForm(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		
	}
	@Override
	public String getPageTitle() {
		return super.getPageTitle();
		 
	}
	public WebElement gettextId() {
		return driver.findElement(id);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement gettextArea() {
		return driver.findElement(textArea);
	}
	
	public boolean isInputDisabled() {
		if(!getElementBy(disabledInputField).isEnabled())
			return true;
		else
		return false;
	}

	public void selectListOption(String str) {
		Random random = new Random();
		int num =1+random.nextInt(3);
		System.out.println("Select value is " + num);
		switch(num) {
			case 1:
				str="One";
				break;
			
			case 2:
				str="Two";
				break;
			
			case 3:
				str="Three";
				break;
			
			default:
			str="Two";
		}
		Select selects = new Select(driver.findElement(selectOption));
		
		selects.selectByVisibleText(str);
	}

	
	public void selectDate(int dateVal) {
		getElementBy(date).click();
		String datePickerStr ="//td[text()='"+ dateVal +"']";
		System.out.println(datePickerStr);
		By datePicker=By.xpath(datePickerStr);
		getElementBy(datePicker).click();
	}

	public WebElement getBtnRadio() {
		return driver.findElement(btnRadio);
	}

	public WebElement getlinkToIndexPage() {
		return driver.findElement(linkToIndexPage);
	}

	public void enterUserData(String username, String pass, String TextArea) {
		gettextId().sendKeys(username);
		getPassword().sendKeys(pass);
		gettextArea().sendKeys(TextArea);

	}

	public void enterFormOptions() {
		getElementBy(btnRadio).click();
	}
	
	public void clickCheckboxes() {
	 List<WebElement> chkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
	 
	 for(int i= 0;i<chkBoxes.size();i++) {
		 if(!chkBoxes.get(i).isSelected())
		 chkBoxes.get(i).click();
		 //Assert.assertTrue(chkBoxes.get(i).isSelected());
	 }
	 
	 
	}
	
	public IndexPage navigateToIndexPage() throws IOException {
		getElementBy(linkToIndexPage).click();
		page= new IndexPage(driver);
		return page;
	}
	
}
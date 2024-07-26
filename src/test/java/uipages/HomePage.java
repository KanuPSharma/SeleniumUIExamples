
package uipages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;
import utils.BasePage;

public class HomePage extends BasePage {
	WebDriver driver;
	public HomePage(WebDriver driver) throws IOException {
		super(driver);
	}

	@FindBy(id = "my-text-id")
	private WebElement id;

	@FindBy(id = "my-password")
	private WebElement password;

	@FindBy(name = "my-textarea")
	private WebElement textArea;

	@FindBys({ @FindBy(name = "my-select") })
	private Select selections;

	@FindBys({ @FindBy(name = "my-datalist") })
	private List<WebElement> datalistoptions;

	@FindBy(name = "my-date")
	private WebElement date;

	@FindBy(xpath = "//td[text()='3']")
	private WebElement datePicker;

	@FindBy(id = "my-radio-2")
	private WebElement btnRadio;

	@FindBy(linkText = "Return to index")
	private WebElement linkToIndexPage;
	
	public WebElement gettextId() {return id;}
    public WebElement getTextPassword() {return password;}
    public WebElement getTextArea(){return textArea;}
    public WebElement getdatePicker(){return datePicker;}
    public List<WebElement> getDataList() {return datalistoptions;}
    public WebElement getRadioButton() {return btnRadio;}
	public void enterFieldsData(WebDriver driver) {
		id.sendKeys("abc");
		password.sendKeys("abc");
		textArea.sendKeys("def");
		selections.selectByIndex(1);
		for(int i= 0;i<datalistoptions.size();i++) {
			if(datalistoptions.get(i).equals("New York")) {
				datalistoptions.get(i).click();
			}
		}
	}

}

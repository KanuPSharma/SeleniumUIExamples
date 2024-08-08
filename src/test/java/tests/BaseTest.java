package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import uipages.IndexPage;
import uipages.WebForm;

public class BaseTest {
	WebDriver driver;
	FluentWait<WebDriver> wait;
	String BASE_URL;
	String BROWSER;
	public static WebForm form;
	public static IndexPage index;
	Properties prop;

	public WebDriver getDriver() {
		return driver;

	}

	public void getTestData() {
		Properties prop = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream("SeleniumUIExamples/src/main/resources/env.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(prop.getProperty("URL"));
	}

	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome ..");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\Gecko34\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	public void testSetUp(String BASE_URL) throws IOException {
		// WebDriverManager.chromedriver().setup();
		prop = new Properties();
		System.out.println(prop.getProperty(BASE_URL, "https://www.selenium.dev/selenium/web/web-form.html"));
		FileInputStream in = null;
		try {
			in = new FileInputStream("src/main/resources/env.properties");
			prop.load(in);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		if (BASE_URL == "")
			BASE_URL = prop.getProperty("URL");
		System.out.println("URL Is " + BASE_URL);
		BROWSER = prop.getProperty("BROWSER");
		System.out.println("BROWSER is " + BROWSER);
		setDriver(BROWSER, BASE_URL);
		wait = new FluentWait<WebDriver>(driver);
		Assert.assertEquals(BASE_URL, driver.getCurrentUrl(), "Base URL is successfully launched");
		form = new WebForm(driver);
		index = new IndexPage(driver);

	}

	public void switchWindows() throws Exception {
		String parent = driver.getWindowHandle();

		for (String handler : driver.getWindowHandles()) {
			if (!handler.equals(parent)) {
				driver.switchTo().window(handler);
				driver.switchTo().window(parent);
			}
		}
	}

	@AfterMethod
	public void takeScreenshot(ITestResult result) {
		String testClassName = getTestClassName(result.getInstanceName()).trim();
		String testMethodName = result.getName().toString().trim();
		for (String s : ITestResult.finalStatuses()) {
			if (s.contains("FAILURE") || s.contains("SKIP")) {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				String path = "test-output/FailedTestsScreenshots/";
				String destination = testClassName + testMethodName+ timestamp + "image.png";
				File dest = new File(path + destination);
				try {
					FileUtils.copyFile(source, dest);
				} catch (IOException e) {
					System.out.println("Exception");
					e.printStackTrace();
				}
			}
		}
	}

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		System.out.println("Quit the browser");
	}

}

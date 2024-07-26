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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import uipages.WebForm;


public class Base {
	WebDriver driver;
	FluentWait<WebDriver> wait;
	String BASE_URL;
	String BROWSER;
	public static WebForm form;
	Properties prop;

	public WebDriver getDriver() {
		return driver;

	}

	public void getTestData() {
		Properties prop = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(
					"C:\\Users\\NS-2\\eclipse-workspace\\SeleniumUIExamples\\src\\main\\resources\\env.properties");
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
		// System.setProperty("webdriver.chrome.driver", driverPath
		// + "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@BeforeTest(alwaysRun = true)
	public void testSetUp() throws IOException {
		// WebDriverManager.chromedriver().setup();
		prop = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(
					"C:\\Users\\NS-2\\eclipse-workspace\\SeleniumUIExamples\\src\\main\\resources\\env.properties");
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
		// System.setProperty("webdriver.chrome.driver",resourcesRoot+"drivers\\chromedriver_126.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		BASE_URL = prop.getProperty("URL");
		System.out.println("URL Is " + BASE_URL);
		BROWSER = prop.getProperty("BROWSER");
		System.out.println("BROWSER is " + BROWSER);
		setDriver(BROWSER, BASE_URL);
		wait = new FluentWait<WebDriver>(driver);
		Assert.assertEquals(BASE_URL, driver.getCurrentUrl(), "Test Passed");
		form = new WebForm(driver);
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

	public void takeScreenshot(String stepName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String destination = System.getProperty("user.dir") + "/screenshots/" + stepName + "_" + timestamp + ".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		//driver.quit();
	}

}

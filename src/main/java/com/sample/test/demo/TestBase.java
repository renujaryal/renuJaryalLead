package com.sample.test.demo;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

	private Configuration config;
	protected WebDriver driver;
	protected String url;

	@BeforeClass(alwaysRun = true)
	public void init() throws Throwable {
		config = new Configuration();
		url = config.getUrl();
		initializelDriver();
		navigateToSite();
	}

	public WebDriver getDriver() {
		return driver;
	}

	private void navigateToSite() {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			driver.quit();

		} catch (Exception e) {
		}
	}

	private void initializelDriver() {
		if (config.getBrowser().equalsIgnoreCase("chrome")) {
			if (config.getPlatform().equalsIgnoreCase("mac")) {
				System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/mac/chromedriver2");
			} else {
				System.setProperty("webdriver.chrome.driver",
						"src/test/resources/chromedriver/windows/chromedriver.exe");
			}
			driver= new ChromeDriver();
		}
		else {
			fail("Unsupported bfrowser " + config.getBrowser());
		}

	}
}

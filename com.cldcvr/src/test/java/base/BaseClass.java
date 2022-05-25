package base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;

public class BaseClass {

	public static WebDriver driver;
	public static HomePage homepage;
	public String APIURI;

	// The homepage is the first page so the object is initialized. Objects for rest
	// of the class will be invoked by page class.
	@BeforeSuite
	public void intObjVariables() {
		homepage = new HomePage();
	}

	@Parameters({ "Browser", "EnvironmentURL" })
	@BeforeMethod
	public void initBeforeNewTest(String browser, String URL) throws IOException {
		// invokes the browser and navigate to application url. The paramters are being
		// fetched from testng.xml
		invokeBrowser(browser, URL);
	}

	// Depending upon the browsers passed from testng.xml file, the browser will be
	// opened. For API case, no browser will be invoked.
	public void invokeBrowser(String browsers, String url) {

		if (browsers.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browsers.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		if (!browsers.equalsIgnoreCase("API")) {
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
		} else {
			APIURI = url;
		}
	}

	// This method will close the browser after the execution is complete.
	@AfterMethod
	public void tearDown() {
		// If driver is not null then driver session is terminated.
		if (driver != null) {
			driver.quit();
		}
	}

}

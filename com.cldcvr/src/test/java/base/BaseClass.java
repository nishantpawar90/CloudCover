package base;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
	public HomePage homepage;

	@BeforeSuite
	public void intObjVariables() {
		homepage = new HomePage();
	}

	@Parameters({ "Browser", "EnvironmentURL" })
	@BeforeMethod
	public void initBeforeNewTest(String browser, String URL) throws IOException {
		// invokes the browser and navigate to application url.
		System.out.println(browser);
		System.out.println(URL);
		invokeBrowser(browser, URL);
	}

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
		}
	}

	@AfterMethod
	public void tearDown() {
		// If driver is not null then driver session is terminated.
		if (driver != null) {
			driver.quit();
		}
	}

}

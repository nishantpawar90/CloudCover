package reusables;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;

import base.BaseClass;

public class PageAction extends BaseClass {

	public void validateTitle(String title) {
		try {
			Assert.assertEquals(driver.getTitle(), title);
			Reporter.log("Title Validated to " + title);
		} catch (Exception e) {
			Reporter.log("Exception :: validateTitle" + e);
		}
	}

	public void clickElement(String webElement) {
		try {
			driver.findElement(By.xpath(webElement)).click();
			Reporter.log("Clicked on WebElement " + webElement);
		} catch (Exception e) {
			Reporter.log("Exception :: clickElement" + e);
		}

	}
}

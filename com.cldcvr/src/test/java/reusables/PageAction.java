package reusables;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;

import base.BaseClass;

//Class contains all the reusable methods for UI testcases.
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

			String[] arr = webElement.split("\\|");
			String xpath = arr[0];
			driver.findElement(By.xpath(xpath)).click();
			Reporter.log("Clicked on WebElement - " + webElement.split("\\|")[1]);
		} catch (Exception e) {
			Reporter.log("Exception :: clickElement" + e);
		}

	}
}

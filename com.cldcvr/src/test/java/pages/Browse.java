package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import reusables.PageAction;

public class Browse extends PageAction {

	// Xpath for the Browse Page. Further optimization can be done by moving these
	// xpath to external file.
	String tags_xpath = "//div[contains(text(), 'Tags')]|Tags";
	String nameTab_xpath = "//a[contains(text(), 'Name')]|Name tab";
	String numberOfQues = "//div[@class='flex--item' and contains(text(),'questions')]";
	String tagForLargestQues = "//div[@class='flex--item' and contains(text(),'+largest+')]//parent::div//preceding-sibling::div[2]//div//a";

	//clicks on browse tag
	public void browseTags() {
		try {
			clickElement(tags_xpath);
		} catch (Exception e) {
			Reporter.log("Exception :: browseTags" + e);
		}
	}

	//this method clicks on Sort depending upon the parameters passed.
	public void sortBy(String sort) {
		try {
			if (sort.equals("Name")) {
				clickElement(nameTab_xpath);
			}
		} catch (Exception e) {
			Reporter.log("Exception :: sortBy" + e);
		}

	}

	//this method gets the tag with highest number of questions.
	public String getTagWithMaxQue() {
		String tagWithLargestQue = null;
		try {
			List<Long> questions = new ArrayList<Long>();
			List<WebElement> element = driver.findElements(By.xpath(numberOfQues));
			for (int i = 0; i < element.size(); i++) {
				questions.add(Long.parseLong(element.get(i).getText().replace(" questions", "")));
			}

			long largest = 0l;
			for (long longNum : questions) {
				if (longNum > largest) {
					largest = longNum;
				}
			}
			tagWithLargestQue = driver
					.findElement(By.xpath(tagForLargestQues.replace("+largest+", Long.toString(largest)))).getText();
		} catch (Exception e) {
			Reporter.log("Exception :: getTagWithMaxQue" + e);
		}
		return tagWithLargestQue;
	}
}

package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import reusables.PageAction;

public class Browse extends PageAction {

	String tags_xpath = "//div[contains(text(), 'Tags')]";
	String nameTab_xpath = "//a[contains(text(), 'Name')]";
	String numberOfQues = "//div[@class='flex--item' and contains(text(),'questions')]";
	String tagForLargestQues = "//div[@class='flex--item' and contains(text(),'+largest+')]//parent::div//preceding-sibling::div[2]//div//a";

	public void browseTags() {
		try {
			clickElement(tags_xpath);
		} catch (Exception e) {
			Reporter.log("Exception :: browseTags" + e);
		}

	}

	public void sortBy(String sort) {
		try {
			if (sort.equals("Name")) {
				clickElement(nameTab_xpath);
			}
		} catch (Exception e) {
			Reporter.log("Exception :: sortBy" + e);
		}

	}

	public String getTagWithMaxQue() {
		String tagWithLargestQue = null;
		try{List<Long> questions = new ArrayList<Long>();
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
		}catch (Exception e) {
			Reporter.log("Exception :: getTagWithMaxQue" + e);
		}
		return tagWithLargestQue;
	}
}

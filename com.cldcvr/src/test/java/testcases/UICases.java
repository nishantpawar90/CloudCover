package testcases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.Browse;
import reusables.PageAction;

public class UICases extends PageAction {

	// THis method navigates to the page mentioned in the problem statement and
	// fetches the tag with highest number of questions.
	@Test
	public void getTagWithHighestQue() throws InterruptedException {
		validateTitle("Stack Overflow - Where Developers Learn, Share, & Build Careers");
		Browse br = homepage.navigateSearchContent();
		br.browseTags();
		validateTitle("Tags - Stack Overflow");
		br.sortBy("Name");
		Reporter.log(br.getTagWithMaxQue());
	}
}

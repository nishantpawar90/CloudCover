package pages;

import org.testng.Reporter;

import reusables.PageAction;

public class HomePage extends PageAction {
	// Xpath for the Browse Page. Further optimization can be done by moving these
	// xpath to external file.
	String searchContent = "//a[text()='search content'][1]|Search Content";

	// Navigates to Search page by clicking on Homepage and method returns the
	// Browse page object.
	public Browse navigateSearchContent() {
		try {
			clickElement(searchContent);
		} catch (Exception e) {
			Reporter.log("Exception :: navigateSearchContent" + e);
		}
		return new Browse();
	}

}

package pages;

import org.testng.Reporter;

import reusables.PageAction;

public class HomePage extends PageAction {
	String searchContent = "//a[text()='search content'][1]|Search Content";

	public Browse navigateSearchContent() {
		try {
			clickElement(searchContent);
		} catch (Exception e) {
			Reporter.log("Exception :: navigateSearchContent" + e);
		}
		return new Browse();
	}

}

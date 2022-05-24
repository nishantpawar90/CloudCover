package pages;

import org.testng.Reporter;

import reusables.PageAction;

public class HomePage extends PageAction {

	public Browse navigateSearchContent() {
		try {
			clickElement("//a[text()='search content'][1]");
		} catch (Exception e) {
			Reporter.log("Exception :: navigateSearchContent" + e);
		}
		return new Browse();
	}

}

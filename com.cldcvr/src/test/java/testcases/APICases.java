package testcases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import reusables.PageAction;

public class APICases extends PageAction {

	@Test
	public void mycase() {

		RestAssured.baseURI = "https://api.stackexchange.com/2.3/badges";
		RestAssured.given().queryParam("order", "desc").and().queryParam("sort", "rank").and()
				.queryParam("site", "stackoverflow").when().get().then().statusCode(200);
	}
}

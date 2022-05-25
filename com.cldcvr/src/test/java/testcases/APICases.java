package testcases;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import reusables.APIAction;

public class APICases extends APIAction {

//	Case validates the response status code and Schema along with the quota_max and Quota_remaining fields for Badge by ID API.
	@Test
	public void validateBadgeByIDResponse() {
		RestAssured.baseURI = APIURI + "23572";
		RequestSpecification request = given().given().queryParam("order", "desc").and().queryParam("sort", "rank")
				.and().queryParam("site", "stackoverflow");
		Response response = request.when().get();
		validateStatusCode(response, 200);
		File badgeIDSchema = new File(badgeIDSchemaPath);
		validateSchema(response, badgeIDSchema);
		assertStringData(extractDataFromJSon(response, "quota_max"), "300");
		assertBooleanData(Integer.parseInt(extractDataFromJSon(response, "quota_remaining")) < 10000, true);
	}

//	Case validates the response status code and Schema for Badge Recepient by ID API
	@Test
	public void validateBadgeRecepientByIDResponse() {
		RestAssured.baseURI = APIURI + "23572/recipients";
		RequestSpecification request = given().given().queryParam("site", "stackoverflow");
		Response response = request.when().get();
		ValidatableResponse responseBody = response.then().log().body();
		validateStatusCode(response, 200);
		File Schema = new File(badgeRecepientByIDSchemaPath);
		validateSchema(response, Schema);
		assertStringData(extractDataFromJSon(response, "quota_max"), "300");
		assertBooleanData(Integer.parseInt(extractDataFromJSon(response, "quota_remaining")) < 10000, true);
	}

	// Case validates the response status code and Schema for Badge by tag API
	@Test
	public void validateBadgeByTagResponse() {
		RestAssured.baseURI = APIURI + "tags";
		RequestSpecification request = given().given().queryParam("order", "desc").and().queryParam("sort", "rank")
				.and().queryParam("site", "stackoverflow");
		Response response = request.when().get();
		ValidatableResponse responseBody = response.then().log().body();
		validateStatusCode(response, 200);
		File Schema = new File(badgeRecepientByTagSchemaPath);
		validateSchema(response, Schema);
	}

	// Case Validates the invalid status code in case of invalid query parameter
	@Test
	public void validateInvalidResponse() {
		RestAssured.baseURI = APIURI + "tags";
		RequestSpecification request = given().given().queryParam("order", "asc").and().queryParam("sort", "name").and()
				.queryParam("site", "facebook");
		Response response = request.when().get();
		ValidatableResponse responseBody = response.then().log().body();
		validateStatusCode(response, 400);
	}
}

package reusables;

import java.io.File;

import org.testng.Assert;
import org.testng.Reporter;

import base.BaseClass;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

//Class contains all the reusable methods for API testcases.
public class APIAction extends BaseClass {

	public String badgeIDSchemaPath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\jsonSchema\\badgeIDSchema.json";
	public String badgeRecepientByIDSchemaPath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\jsonSchema\\badgeRecepientByIDSchema.json";

	public String badgeRecepientByTagSchemaPath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\jsonSchema\\badgeRecepientByTagSchema.json";

	public void validateStatusCode(Response actualResponse, Integer expectedResponse) {
		try {
			Assert.assertEquals(actualResponse.getStatusCode(), expectedResponse);
			Reporter.log("Response status code validated as " + expectedResponse);
		} catch (Exception e) {
			Reporter.log("Exception :: validateStatusCode" + e);
		}
	}

	public void validateSchema(Response actualResponse, File schemaPath) {
		try {
			actualResponse.then().body(JsonSchemaValidator.matchesJsonSchema(schemaPath));
			Reporter.log("Response schema validated.");
		} catch (Exception e) {
			Reporter.log("Exception :: validateSchema" + e);
		}
	}

	public String extractDataFromJSon(Response actualResponse, String key) {
		String data = null;
		try {
			data = actualResponse.jsonPath().getString(key);
			Reporter.log("Extracted data from JSON. Key " + key + ". Value:: " + data);
		} catch (Exception e) {
			Reporter.log("Exception :: validateSchema" + e);
		}
		return data;
	}

	public void assertStringData(String actualResponse, String expectedResponse) {
		try {
			Assert.assertEquals(actualResponse, expectedResponse);
			Reporter.log("Response validated using assertStringData." + actualResponse);
		} catch (Exception e) {
			Reporter.log("Exception :: assertStringData" + e);
		}
	}

	public void assertBooleanData(boolean actualResponse, boolean expectedResponse) {
		try {
			Assert.assertEquals(actualResponse, expectedResponse);
			Reporter.log("Response validated using assertBooleanData." + actualResponse);
		} catch (Exception e) {
			Reporter.log("Exception :: assertBooleanData" + e);
		}
	}
}

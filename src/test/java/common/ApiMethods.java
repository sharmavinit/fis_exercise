package common;

import java.util.List;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import stepDefinitions.Hooks;

public class ApiMethods {

	private Response response;
	Validations validate = new Validations();

	public void getBaseUri(String apiEndpoint) {
		RestAssured.baseURI = apiEndpoint;
	}

	public void executeGetRequest(String statucode) {
		response = RestAssured.given().when().get();
		Hooks.scenario.log("Response Body:\n"+response.getBody().asString());
		Hooks.softAssert.assertTrue(validate.verifyActualEqualsExpected(String.valueOf(response.getStatusCode()),statucode), "Validates response code");
	}

	public void validatesBPIs(String fieldName, List<String> bpiList) {
		Map<String, Map<String, Object>> fieldsMap = response.then().extract().body().jsonPath().getMap(fieldName);

		for (String bpi : bpiList) {
			if(fieldsMap.containsKey(bpi)) {
				Hooks.scenario.log("Response contains field-"+bpi);
				Hooks.softAssert.assertTrue(fieldsMap.containsKey(bpi), "Response contains " + bpi);
			}
			else {
				Hooks.scenario.log("Response does not contains field-"+bpi);
			}
		}
	}

	public void validateSubField(String fieldName, String expectedDescription) {
		Hooks.softAssert.assertTrue(
				validate.verifyActualEqualsExpected(response.jsonPath().getString(fieldName), expectedDescription),
				"Validate value for " + fieldName);
	}
}

package stepDefinitions;

import java.util.List;

import common.ApiMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApiValidationStepDef {
	ApiMethods apiMethods= new ApiMethods();
	
	@Given("User has API endpoint as (.*?)$")
	public void userCreateBaseUri(String apiEndpoint) {
		apiMethods.getBaseUri(apiEndpoint);
	}

	@When("User executes GET request for the given API and verifies status code as (.*?)$")
	public void userExecutesGetRequest(String expectedStatusCode) {
		apiMethods.executeGetRequest(expectedStatusCode);
	}

	@Then("^User validates that (.*?) fields in response should contain the below values:")
	public void userValidatesResponseBpis(String fieldName,List<String> valuesToValidate) {
	   apiMethods.validatesBPIs(fieldName,valuesToValidate);
	}

	@Then("^User validates value of (.*?) field in response as (.*?)")
	public void userValidatesResponseDescription(String BpiName, String valuesToValidate) {
	    apiMethods.validateSubField(BpiName,valuesToValidate);
	}

}

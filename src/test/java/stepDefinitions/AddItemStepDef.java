package stepDefinitions;

import common.UiActions;
import common.Validations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import pages.HomePage;

public class AddItemStepDef {
	UiActions action= new UiActions();
	Validations validation= new Validations();
	HomePage homePage= new HomePage();

	@Given("User launch application url (.*?)$")
	public void user_launch_application_url(String url) {
	    action.launchUrl(url);
	}
	
	@Then("User verifies Page Title as (.*?)$")
	public void user_verifies_page_title_as_electronics_cars_fashion_collectibles_more_e_bay(String expectedTitle) {
		 String pageTitle= action.getPageTitle();
		   System.out.println(pageTitle);
		   Assert.assertTrue("Verify page title",validation.verifyActualEqualsExpected(pageTitle, expectedTitle));
	}
	
	@And("User check for item number in cart")
	public void user_check_for_item_number_in_cart() {
	    homePage.getInitialCartItemCount();
	}
	
	@When("User search for (.*?) in item search box$")
	public void user_search_for_book_in_item_search_box(String itemToSearch) {
	    homePage.searchItem(itemToSearch);
	}

	@When("User clicks searched item number (.*?) from list$")
	public void user_clicks_first_searched_item_from_list(String itemNumber) {
	    homePage.clickItem(itemNumber);
	}

	@Then("User switch to item window")
	public void user_switch_to_item_window() {
	    action.switchToWindow();
	}

	@Then("User click on (.*?) button$")
	public void user_click_on_button(String buttonToClick) {
	    homePage.clickButton(buttonToClick);
	}
	
	@Then("User verifies item count in cart is increased by (.*?)$")
	public void user_verifies_item_count_in_cart_is_updated(String numberOfitemsAdded) {
		homePage.verifyUpdatedCartValue(numberOfitemsAdded);
	    
	}
}

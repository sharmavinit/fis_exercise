package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.UiActions;
import common.Validations;
import junit.framework.Assert;

public class HomePage {

	String itemSearchBox = "xpath;//div[@id='gh-ac-box2']/input";
	String eachItemLink = "xpath;(//ul[contains(@class,'srp-results srp-list')]/li)[%s]//a[@class='s-item__link']";
	String buttonToClick = "xpath;//span[text()='%s']/../..";
	String cartItemNumber = "xpath;//li[@id='gh-minicart-hover']//i";
	int initailCartItemCount = 0;
	UiActions action = new UiActions();
	Validations validate= new Validations();

	public void searchItem(String itemToSearch) {
		action.enterText(itemSearchBox, itemToSearch, true);
		action.enterText(itemSearchBox, Keys.ENTER);
		action.takeScreenShot("Searched items");
	}

	public void clickItem(String itemNumber) {
		action.click(String.format(eachItemLink, itemNumber));
	}

	public void getInitialCartItemCount() {
		if (action.findElements(cartItemNumber).size() > 0)
			initailCartItemCount = Integer.valueOf(action.getText(cartItemNumber));
	}

	public void clickButton(String buttonName) {
		action.click(String.format(buttonToClick, buttonName));
	}

	public int getCartItemCount() {
		return Integer.valueOf(action.getText(cartItemNumber));
	}
	
	public void verifyUpdatedCartValue(String itemAdded) {
		String actualCartValue= action.getText(cartItemNumber);
		String expectedCartValue= String.valueOf(initailCartItemCount+Integer.valueOf(itemAdded));
		action.takeScreenShot("After add to cart action");
		Assert.assertTrue("Validation of cart item count after add to cart action",validate.verifyActualEqualsExpected(actualCartValue,expectedCartValue));
	}
}

package common;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import stepDefinitions.Hooks;

public class UiActions {

	public WebDriver driver = Hooks.getDriver();

	public void launchUrl(String url) {
		try {
			driver.get(url);
			takeScreenShot("Home Page");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public By getElementProperties(String elementProperty) {
		String locatorName = elementProperty.split(";")[0];
		String locatorValue = elementProperty.split(";")[1];

		switch (locatorName) {

		case "id":
			return By.id(locatorValue);

		case "name":
			return By.name(locatorValue);

		case "classname":
			return By.className(locatorValue);

		case "xpath":
			return By.xpath(locatorValue);
		case "linkText":
			return By.linkText(locatorValue);
		default:
			System.out.println("Search for give locator is not available or invalid locator");
			return null;
		}

	}

	public WebElement findElement(String elementProperty) {
		return driver.findElement(getElementProperties(elementProperty));
	}

	public List<WebElement> findElements(String elementProperty) {
		return driver.findElements(getElementProperties(elementProperty));
	}

	public void click(String elementProperty) {
		findElement(elementProperty).click();
	}

	public void enterText(String elementProperty, String textToEnter, boolean clearText) {
		if (clearText) {
			findElement(elementProperty).clear();
		}
		if (!textToEnter.equalsIgnoreCase(""))
			findElement(elementProperty).sendKeys(textToEnter);
		else
			throw new IllegalArgumentException("text cannot be empty");
	}

	public void enterText(String itemToSearch, Keys key) {
		findElement(itemToSearch).sendKeys(key);

	}

	public String getText(String elementProperty) {
		return findElement(elementProperty).getText();
	}

	public void switchToWindow() {
		String currentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(currentWindow)) {
				driver.switchTo().window(window);
				takeScreenShot("child window");
			}
		}
	}

	public void takeScreenShot(String name) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Define the destination for the screenshot
		File destinationFile = new File("target/screenshots/" + name + ".png");

		try {
			// Copy the screenshot to the destination
			FileUtils.copyFile(screenshot, destinationFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Attach the screenshot to the report
		byte[] screenshotBytes = null;
		try {
			screenshotBytes = FileUtils.readFileToByteArray(destinationFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hooks.scenario.attach(screenshotBytes, "image/png", "Screenshot");
	}
}

package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import common.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	public static WebDriver driver;
	public static Scenario scenario;
	public static SoftAssert softAssert;

	@SuppressWarnings({ "deprecation", "static-access" })
	@Before
	public void beforeScenario(Scenario scenario) {
		this.scenario = scenario;
		if (!scenario.getName().contains("api")) {
			String browserName = "chrome";
			driver = DriverManager.initializeDriver(browserName);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		}
		softAssert = new SoftAssert();

	}

	@After
	public void afterScenario(Scenario scenario) {
			DriverManager.quit();
		softAssert.assertAll();
	}

	public static WebDriver getDriver() {
		return driver;
	}
}

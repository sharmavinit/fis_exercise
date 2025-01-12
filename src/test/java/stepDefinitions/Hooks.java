package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import common.DriverManager;
import common.Validations;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	public static WebDriver driver;
	public static Scenario scenario;
	@SuppressWarnings({ "deprecation", "static-access" })
	@Before
        public void beforeScenario(Scenario scenario) {
		String browserName="chrome";
    		driver= DriverManager.initializeDriver(browserName);
    		driver.manage().deleteAllCookies();
    		driver.manage().window().maximize();
    		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
    		
    		this.scenario= scenario;
    		System.out.println(scenario);
    	}

    @After
    public void afterScenario(Scenario scenario) {
       DriverManager.quit();
    }
    
    public static WebDriver getDriver() {
        return driver;
    }
}

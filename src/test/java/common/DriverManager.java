package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

	public static DriverManager driverManager = new DriverManager();
	private static WebDriver driver = null;

	private DriverManager() {
	}

	public static WebDriver initializeDriver(String browserName) {
		switch (browserName) {

		case "edge":
			driver = new EdgeDriver();
			break;

		case "chrome":
			System.setProperty("webDriver.chrome.driver", "\\common\\browerDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		default:
			throw new IllegalArgumentException("Given browser is not supported");

		}

		return driver;
	}

	public static void quit() {
		if (driver != null)
			driver.quit();
		driver = null;
	}

	public static void close() {
		if (driver != null)
			driver.close();
		driver = null;
	}
}

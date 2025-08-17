package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// DriverFactory class is responsible for managing the WebDriver instance

public class DriverFactory {
	private static WebDriver driver;

	// Returns a WebDriver instance (creates it if it doesn't exist)
	public static WebDriver getDriver() {
		if (driver == null) {
			driver = new ChromeDriver(); // Using ChromeDriver
			driver.manage().window().maximize();
		}
		return driver;
	}

	// Quits the driver after test completion
	public static void quitDriver() {
		if (driver != null) {
			driver.quit(); // Close the browser
		}
	}
}

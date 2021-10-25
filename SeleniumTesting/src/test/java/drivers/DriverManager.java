package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
	
	// Base Url
	private static final String BASE_URL = "http://localhost/Hospital-Appointment-Booking-System/";

	// path to the location of the drivers
	private static final String DRIVER_PATH = "G:\\webdrivers\\chromedriver_95.exe";
	
	// name of the driver being used
	private static final String DRIVER_NAME = "webdriver.chrome.driver";

	// web driver for testing
	private WebDriver webDriver;
	
	// constructor for driver
	public DriverManager() {
		webDriver = new ChromeDriver();
	}
		
	// getters
	public WebDriver getWebDriver() {
		return this.webDriver;
	}
	
	public static String getBaseUrl() {
		return BASE_URL;
	}

	public static String getDriverPath() {
		return DRIVER_PATH;
	}

	public static String getDriverName() {
		return DRIVER_NAME;
	}

}

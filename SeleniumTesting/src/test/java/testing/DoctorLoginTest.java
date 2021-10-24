package testing;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import drivers.DriverManager;

public class DoctorLoginTest {
	
	String baseUrl = "http://localhost/Hospital-Appointment-Booking-System/";
	WebDriver driver;
	
	
	@BeforeMethod
	public void initTest() {
		System.setProperty(DriverManager.getDriverName(), DriverManager.getDriverPath());
		driver = new DriverManager().getWebDriver();
	}

	@AfterMethod
	public void destroyTest() throws Exception{
		
		driver.close();
	}
	
	
	@Test(description="If all fields are empty")
	public void emptyFieldLoginTest() {
		
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("doctor_login_btn")).click();
	
		driver.findElement(By.xpath("//*[@id=\"doc_login\"]")).click();
		
		String actualString = driver.findElement(By.xpath("//*[@id=\"popup\"]")).getText().toString();
		String expectedString = "Please Fill all the fields....";
		
		Assert.assertEquals(actualString, expectedString);

	}
	
	
	
	

}

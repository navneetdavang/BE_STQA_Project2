package testing;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import drivers.DriverManager;

public class BookAppointmentTest {

	String baseUrl = DriverManager.getBaseUrl();
	WebDriver driver;
	
	@BeforeMethod
	public void init() {
		System.setProperty(DriverManager.getDriverName(), DriverManager.getDriverPath());
		driver = new DriverManager().getWebDriver();
	}
	
	@AfterMethod
	public void destroyTest() {
		driver.close();
	}
	
	@Test(description = "Is booking successful")
	
	public void checkBookingSuccess() {
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("patient_login_btn")).click();
		
		// email field
		driver.findElement(By.xpath("//*[@id=\"email_pat\"]")).sendKeys("dhc@gmail.com");
		//password field
		driver.findElement(By.xpath("//*[@id=\"password_pat\"]")).sendKeys("dhc@1234");			
		
		driver.findElement(By.xpath("//*[@id=\"pat_login_btn\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"book\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys("27-10-2021");
		driver.findElement(By.xpath("//*[@id=\"time\"]")).sendKeys("12:00");
		
		driver.findElement(By.xpath("//*[@id=\"book-btn\"]")).click();
		
		String actualAlert = driver.findElement(By.xpath("//*[@id=\"popup\"]")).getText().toString();
		String expectedAlert = "Appointment booked successfully!";
		
		Assert.assertEquals(actualAlert,expectedAlert);
		
	}

		@Test(description = "Show all Appointments")
		
		public void checkAllAppointments() {
			driver.manage().window().maximize();
			driver.get(baseUrl);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.findElement(By.id("patient_login_btn")).click();
			
			// email field
			driver.findElement(By.xpath("//*[@id=\"email_pat\"]")).sendKeys("dhc@gmail.com");
			//password field
			driver.findElement(By.xpath("//*[@id=\"password_pat\"]")).sendKeys("dhc@1234");			
			
			driver.findElement(By.xpath("//*[@id=\"pat_login_btn\"]")).click();
			
			driver.findElement(By.xpath("//*[@id=\"nav_allApp\"]")).click();
			
			String currentPage = driver.getCurrentUrl();
			String actualPage = "http://localhost/Hospital-Appointment-Booking-System/practice.php";
			
			Assert.assertEquals(currentPage, actualPage);
			
		}
}

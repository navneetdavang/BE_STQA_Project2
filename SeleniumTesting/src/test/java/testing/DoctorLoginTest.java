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
	
	String baseUrl = DriverManager.getBaseUrl();
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
	public void emptyFieldLoginTest() throws InterruptedException {
		
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("doctor_login_btn")).click();
	
		driver.findElement(By.xpath("//*[@id=\"doc_login\"]")).click();
		
		String actualString = driver.findElement(By.xpath("//*[@id=\"popup\"]")).getText().toString();
		String expectedString = "Please Fill all the fields....";
		Thread.sleep(2000);
		Assert.assertEquals(actualString, expectedString);

	}
	
	
	@Test(description="If user doesn't exists")
	public void checkUserLoginStatusTest() throws InterruptedException {
		
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("doctor_login_btn")).click();
		
		// email field
		driver.findElement(By.xpath("//*[@id=\"doc_email_id\"]")).sendKeys("nalsdjlv@gmail.com");
		Thread.sleep(2000);
		//password field
		driver.findElement(By.xpath("//*[@id=\"doc_password\"]")).sendKeys("sknklkd");
		Thread.sleep(2000);
		
	
		driver.findElement(By.xpath("//*[@id=\"doc_login\"]")).click();
		
		String actualString = driver.findElement(By.xpath("//*[@id=\"popup\"]")).getText().toString();
		String expectedString = "Username/Password doesn't exists";
		Thread.sleep(2000);
		
		Assert.assertEquals(actualString, expectedString);
	}
	
	@Test(description="If user exists")
	public void checkUserLoginStatusTest2() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("doctor_login_btn")).click();
		
		// email field
		driver.findElement(By.xpath("//*[@id=\"doc_email_id\"]")).sendKeys("nav@gmail.com");
		Thread.sleep(2000);
		//password field
		driver.findElement(By.xpath("//*[@id=\"doc_password\"]")).sendKeys("nav@1234");		// changed the password
		Thread.sleep(2000);
	
		driver.findElement(By.xpath("//*[@id=\"doc_login\"]")).click();
		
		// changed the xpath 
		String actualUrl = driver.findElement(By.xpath("//*[@id=\"doc_email\"]")).getText().toString();
		String expectedUrl = "nav@gmail.com";
		Thread.sleep(2000);
		
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Test(description = "Is logout Successful")
	public void checkLogoutSuccess() 
	{
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("doctor_login_btn")).click();
		
		// email field
		driver.findElement(By.xpath("//*[@id=\"doc_email_id\"]")).sendKeys("nav@gmail.com");
		//password field
		driver.findElement(By.xpath("//*[@id=\"doc_password\"]")).sendKeys("nav@1234");
		
		driver.findElement(By.xpath("//*[@id=\"doc_login\"]")).click();
		
		
		driver.findElement(By.xpath("//*[@id=\"logout_doc\"]")).click(); 
		
		String currPage = driver.getCurrentUrl(); 
		String expectedPage = baseUrl+"Index.php"; 
		
		Assert.assertEquals(currPage, expectedPage);
		
	}

}

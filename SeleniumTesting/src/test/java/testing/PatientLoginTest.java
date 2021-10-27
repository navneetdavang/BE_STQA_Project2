package testing;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import drivers.DriverManager;

public class PatientLoginTest {
	
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
	
	@Test(description="If all fields are empty")
	public void emptyFieldLoginTest() {
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("patient_login_btn")).click();
		
		driver.findElement(By.xpath("//*[@id=\"pat_login_btn\"]")).click();
		
		String actualString = driver.findElement(By.xpath("//*[@id=\"popup\"]")).getText().toString();
		String expectedString = "Please Fill all the fields....";
		
		Assert.assertEquals(actualString, expectedString);
	}
	
	@Test(description="If user does not exist")
	public void checkUserLoginStatusTest() {
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("patient_login_btn")).click();
		
		// email field
		driver.findElement(By.xpath("//*[@id=\"email_pat\"]")).sendKeys("nfwjni@gmail.com");
		//password field
		driver.findElement(By.xpath("//*[@id=\"password_pat\"]")).sendKeys("kjejd");
		
		driver.findElement(By.xpath("//*[@id=\"pat_login_btn\"]")).click();
		
		String actualString = driver.findElement(By.xpath("//*[@id=\"popup\"]")).getText().toString();
		String expectedString = "Username/Password doesn't exists";
		
		Assert.assertEquals(actualString, expectedString);
	}
	
	@Test(description="If user does not exist")
	public void checkUserLoginStatusTest2() {
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("patient_login_btn")).click();
		
		// email field
		driver.findElement(By.xpath("//*[@id=\"email_pat\"]")).sendKeys("dhc@gmail.com");
		//password field
		driver.findElement(By.xpath("//*[@id=\"password_pat\"]")).sendKeys("dhc@1234");			// changed the password
		
		driver.findElement(By.xpath("//*[@id=\"pat_login_btn\"]")).click();
		
		// changed the xpath 
		String actualUrl = driver.findElement(By.xpath("//*[@id=\"pat_email\"]")).getText().toString();
		String expectedUrl = "dhc@gmail.com";
		
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Test(description = "Is logout Successful")
	public void checkLogoutSuccess() 
	{
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.id("patient_login_btn")).click();
		
		// email field
		driver.findElement(By.xpath("//*[@id=\"email_pat\"]")).sendKeys("dhc@gmail.com");
		//password field
		driver.findElement(By.xpath("//*[@id=\"password_pat\"]")).sendKeys("dhc@1234");			
		
		driver.findElement(By.xpath("//*[@id=\"pat_login_btn\"]")).click();
		
		
		driver.findElement(By.xpath("//*[@id=\"pat_logout\"]")).click(); 
		
		String currPage = driver.getCurrentUrl(); 
		String expectedPage = baseUrl+"Index.php"; 
		
		Assert.assertEquals(currPage, expectedPage);
		
	}
	
	
}

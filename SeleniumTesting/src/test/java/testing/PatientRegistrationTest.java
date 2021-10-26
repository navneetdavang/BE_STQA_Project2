package testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import drivers.DriverManager;

public class PatientRegistrationTest {
	
	String baseUrl = DriverManager.getBaseUrl(); 	// base url
	WebDriver driver;
	
	
	By firstName = By.xpath("//*[@id=\"pat_fname\"]");
	By lastName = By.xpath("//*[@id=\"pat_lname\"]");
	By emailId = By.xpath("//*[@id=\"pat_email\"]");
	By password = By.xpath("//*[@id=\"pat_password\"]");
	By confirmPassword = By.xpath("//*[@id=\"pat_password_confirm\"]");
	By signUpBtn = By.xpath("//*[@id=\"patient_registration_btn\"]");
	By submitBtn = By.xpath("//*[@id=\"pat_reg_btn\"]");
	By popup = By.xpath("//*[@id=\"popup\"]");
	By emailHelp = By.xpath("//*[@id=\"emailHelpPat\"]");
	By passwordHelp = By.xpath("//*[@id=\"passwordHelpPat\"]");
	By closeBtn = By.xpath("//*[@id=\"pat-reg-form\"]/div[5]/button[1]");
	
	
	@BeforeMethod
	public void init() {
		System.setProperty(DriverManager.getDriverName(), DriverManager.getDriverPath());
		driver = new DriverManager().getWebDriver();
//		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void destroy() {
		driver.close();
	}
	
	
	@Test(priority=1, description="If all fields are empty")
	public void emptyFieldsCheckTest() {
		
		driver.findElement(signUpBtn).click();
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(popup).getText().toString();
		String expectedMsg = "Please Fill all the fields....";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	
	}
	
	@Test(priority=2, description="If email is invalid")
	public void invalidEmailTest() {
		
		driver.findElement(signUpBtn).click();
		
		driver.findElement(emailId).sendKeys("Sham");
		String actualMsg = driver.findElement(emailHelp).getText().toString();
		String expectedMsg = "Invalid Email Id";
		
		Assert.assertEquals(actualMsg, expectedMsg);
		
	}
	
	@Test(priority=3, description="If password is weak")
	public void invalidPasswordTest() {
	
		driver.findElement(signUpBtn).click();
		
		driver.findElement(password).sendKeys("Sham");
		String actualMsg = driver.findElement(passwordHelp).getText().toString();
		String expectedMsg = "Weak Password";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	@Test(priority=4, description="if password and confirm password are different")
	public void passwordEqualTest() {
		driver.findElement(signUpBtn).click();
	
		driver.findElement(password).sendKeys("Sham@1234");
		driver.findElement(confirmPassword).sendKeys("Sham@12345");
		
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(popup).getText().toString();
		String expectedMsg = "Password and Confirm Password Doesn't Match";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	@Test(priority=5, description="if first name contains non alphabetical chars")
	public void firstNameInvalidInputTest() {
		driver.findElement(signUpBtn).click();
	
		driver.findElement(firstName).sendKeys("Sham@1234");
		
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(firstName).getAttribute("validationMessage");
		String expectedMsg = "Please enter on alphabets only.";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	@Test(priority=6, description="if last name contains non alphabetical chars")
	public void lastNameInvalidInputTest() {
		driver.findElement(signUpBtn).click();
	
		driver.findElement(lastName).sendKeys("Patil32134*");
		
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(lastName).getAttribute("validationMessage");
		String expectedMsg = "Please enter on alphabets only.";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	
	@Test(priority=7, description="If user already exists")
	public void userExistsTest() {
		driver.findElement(signUpBtn).click();
	
		driver.findElement(firstName).sendKeys("Dheeren");
		driver.findElement(lastName).sendKeys("Chirmade");
		driver.findElement(emailId).sendKeys("dhc@gmail.com");
		driver.findElement(password).sendKeys("dhc@1234");
		driver.findElement(confirmPassword).sendKeys("dhc@1234");
		
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(popup).getText().toString();
		String expectedMsg = "User Already Exists!";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}

}

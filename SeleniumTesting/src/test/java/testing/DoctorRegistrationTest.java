package testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import drivers.DriverManager;

public class DoctorRegistrationTest {

	String baseUrl = DriverManager.getBaseUrl(); 	// base url
	WebDriver driver;
	
	
	By firstName = By.xpath("//*[@id=\"f_name_doc\"]");
	By lastName = By.xpath("//*[@id=\"l_name_doc\"]");
	By emailId = By.xpath("//*[@id=\"email_id_doc\"]");
	By password = By.xpath("//*[@id=\"password_doc\"]");
	By confirmPassword = By.xpath("//*[@id=\"password_doc_confirm\"]");
	By speciality = By.xpath("//*[@id=\"speciality\"]");
	By signUpBtn = By.xpath("//*[@id=\"doctor_registration_btn\"]");
	By submitBtn = By.xpath("//*[@id=\"doc_submit_btn\"]");
	By popup = By.xpath("//*[@id=\"popup\"]");
	By emailHelp = By.xpath("//*[@id=\"emailHelp\"]");
	By passwordHelp = By.xpath("//*[@id=\"passwordHelp\"]");
	By closeBtn = By.xpath("//*[@id=\"doctor_registration\"]/div/div/div[2]/form/div[6]/button");
	
	
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
		
		driver.findElement(emailId).sendKeys("Ram");
		String actualMsg = driver.findElement(emailHelp).getText().toString();
		String expectedMsg = "Invalid Email Id";
		
		Assert.assertEquals(actualMsg, expectedMsg);
		
	}
	
	@Test(priority=3, description="If password is weak")
	public void invalidPasswordTest() {
	
		driver.findElement(signUpBtn).click();
		
		driver.findElement(password).sendKeys("Ram");
		String actualMsg = driver.findElement(passwordHelp).getText().toString();
		String expectedMsg = "Weak Password";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	@Test(priority=4, description="if password and confirm password are different")
	public void passwordEqualTest() {
		driver.findElement(signUpBtn).click();
	
		driver.findElement(password).sendKeys("Ram@1234");
		driver.findElement(confirmPassword).sendKeys("Ram@12345");
		
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(popup).getText().toString();
		String expectedMsg = "Password and Confirm Password Doesn't Match";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	@Test(priority=5, description="if first name contains non alphabetical chars")
	public void firstNameInvalidInputTest() {
		driver.findElement(signUpBtn).click();
	
		driver.findElement(firstName).sendKeys("Ram@1234");
		
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(firstName).getAttribute("validationMessage");
		String expectedMsg = "Please enter on alphabets only.";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	@Test(priority=6, description="if last name contains non alphabetical chars")
	public void lastNameInvalidInputTest() {
		driver.findElement(signUpBtn).click();
	
		driver.findElement(lastName).sendKeys("Pawar32134*");
		
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(lastName).getAttribute("validationMessage");
		String expectedMsg = "Please enter on alphabets only.";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	@Test(priority=7, description="if speciality contains non alphabetical chars")
	public void specialityInvalidInputTest() {
		driver.findElement(signUpBtn).click();
	
		driver.findElement(speciality).sendKeys("Pawar32134*");
		
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(speciality).getAttribute("validationMessage");
		String expectedMsg = "Please enter on alphabets only.";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	@Test(priority=8, description="If user already exists")
	public void userExistsTest() {
		driver.findElement(signUpBtn).click();
	
		driver.findElement(firstName).sendKeys("Navneet");
		driver.findElement(lastName).sendKeys("Davang");
		driver.findElement(emailId).sendKeys("nav@gmail.com");
		driver.findElement(password).sendKeys("nav@1234");
		driver.findElement(confirmPassword).sendKeys("nav@1234");
		driver.findElement(speciality).sendKeys("MBBS");
		
		driver.findElement(submitBtn).click();
		
		String actualMsg = driver.findElement(popup).getText().toString();
		String expectedMsg = "User Already Exists!";
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
}

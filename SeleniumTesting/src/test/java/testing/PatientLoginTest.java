package testing;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PatientLoginTest {
	
	@BeforeMethod 
	public void initTest() 
	{
			System.out.println(" Before method called ");
	}

	@AfterMethod 
	public void destroyTest() 
	{
		System.out.println(" After Method called ");
		
	}
	
	@Test 
	public void checkSample() 
	{
		
		System.out.println(" Checked Smaple");
		
	}
	
	@Test
	public void checkPatientLogin() {
		System.out.println(" Checked Patient  ");
	}
}

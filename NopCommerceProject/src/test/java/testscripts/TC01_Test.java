package testscripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericlibrary.BaseTest;
import genericlibrary.UtilityMethods;

public class TC01_Test extends BaseTest {
	
	@Test
	public void addToCartTestCase() throws InterruptedException
	{
		TC01_Test tc=new TC01_Test();
		System.out.println("Test Case 01");
		Thread.sleep(10000);
		test.log(Status.PASS, "Test Case 01");
		test.addScreenCaptureFromPath(tc.takeScreenshot(driver), "Test Case 01");
		
	}
}


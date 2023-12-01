package genericlibrary;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomrepository.BasePage;
import pomrepository.LoginPage;

public class BaseTest extends UtilityMethods {

	/**
	 *
	 * @author Rakesh B
	 *
	 **/

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		sparkReporter = new ExtentSparkReporter("./Reports/" + getCurrentTime() + ".html");
		reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass(@Optional("Chrome") String browser) throws IOException {
		if (browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			Reporter.log("Chrome Browser Launched", true);
		} else if (browser.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			Reporter.log("Edge Browser Launched", true);
		} else {
			Reporter.log("Invalid Browser Name", true);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) throws IOException {
		String name = method.getName();
		test = reports.createTest(name);
		Reporter.log("Execution Started", true);
		test.log(Status.INFO, "Execution Started");
		base_Page = new BasePage(driver);
		DataUtility data_Utility = new DataUtility();
		driver.get(data_Utility.getDataFromProperties("url"));
		base_Page.getLoginLink().click();
		LoginPage login_Page = new LoginPage(driver);
		Reporter.log("Performing Login", true);
		test.log(Status.INFO, "Performing Login");
		login_Page.getEmailTextField().sendKeys(data_Utility.getDataFromProperties("un"));
		login_Page.getPasswordTextField().sendKeys(data_Utility.getDataFromProperties("pwd"));
		login_Page.getLoginButton().click();

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		base_Page.getLogoutLink().click();
		Reporter.log("Performing Logout", true);
		test.log(Status.INFO, "Performing Logout");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
		Reporter.log("Closing Browser", true);
		test.log(Status.INFO, "Closing Browser");
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		reports.flush();
	}
}
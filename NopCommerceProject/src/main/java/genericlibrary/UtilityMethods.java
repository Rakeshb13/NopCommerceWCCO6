 package genericlibrary;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pomrepository.BasePage;

public class UtilityMethods {
	
	public WebDriver driver;
	public  static ExtentSparkReporter sparkReporter;
	public  static ExtentReports reports;
	public  static ExtentTest test;
	public BasePage base_Page;

	public static String getCurrentTime() {
		LocalDateTime l = LocalDateTime.now();
		String name = l.toString().replace(":", "-");
		return name;
	}

	public  void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void passDataToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public void waitUntilVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilInVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitUntilPresenceOfElement(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void switchToWindow(WebDriver driver, String title) {
		Set<String> all_Session_Ids = driver.getWindowHandles();

		for (String session_Id : all_Session_Ids) {
			driver.switchTo().window(session_Id);
			if (fetchWebPageTitle(driver).equals(title)) {
				break;
			}
		}
	}
	
	public String fetchWebPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String fetchWebPageUrl(WebDriver driver)
	{
		return driver.getCurrentUrl();
	}

	public String takeScreenshot(WebDriver driver) {
		String path = "./Screenshot/" + UtilityMethods.getCurrentTime() + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(path);
		try {
			FileHandler.copy(src, trg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "."+path;
	}
}
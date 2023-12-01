package pomrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	@FindBy(id="Email")
	private WebElement emailTextField;

	@FindBy(id="Password")
	private WebElement passwordTextField;

	@FindBy(id="RememberMe")
	private WebElement rememberMeCheckbox;
	
	@FindBy(xpath="//button[text()='Log in']")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver){
		super(driver);
	}

	public WebElement getEmailTextField() {
		return emailTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getRememberMeCheckbox() {
		return rememberMeCheckbox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
}
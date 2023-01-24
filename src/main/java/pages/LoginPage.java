package pages;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import objectRepository.SDObjectRepository;
import utility.ElementActions;

public class LoginPage extends SDObjectRepository {
	Logger log;
	WebDriver driver;
	ElementActions element_Actions;


	By sign_In_Button;
	By login_Btn;
	By email_Field;
	By continue_Btn_UC;
	By code_Field;
	By continue_Btn_Otp;




	public LoginPage(WebDriver driver, Logger log) throws IOException {
		super("LoginPage.properties");
		// TODO Auto-generated constructor stub
		this.log = log;
		this.driver=driver;
		element_Actions=new ElementActions(driver);
		
		sign_In_Button = By.xpath(config.getProperty("sign_In_Button"));
		login_Btn = By.xpath(config.getProperty("login_Btn"));
		email_Field = By.xpath(config.getProperty("email_Field"));
		continue_Btn_UC = By.xpath(config.getProperty("continue_Btn_UC"));
		code_Field = By.xpath(config.getProperty("code_Field"));
		continue_Btn_Otp = By.xpath(config.getProperty("continue_Btn_Otp"));

		


	}
	public void waitForLoginPageToLoad() throws InterruptedException {
		Thread.sleep(5000);
		element_Actions.waitUntilVisibilityLocated(sign_In_Button);
	}

	public boolean checkLoginPage() {

		log.info("Verifying if login page is displayed");
		return element_Actions.checkElementPresence(sign_In_Button);
	}

	public void clickOnLogin() {

		log.info("Clicking Login Button");
		
		element_Actions.waitUntilVisibilityLocated(sign_In_Button);
		element_Actions.findElement(sign_In_Button);
		element_Actions.mouseHover();
		element_Actions.waitUntilVisibilityLocated(login_Btn);
		element_Actions.findElement(login_Btn);
		element_Actions.Click();
		log.info("Login button clicked successfully");

	}

	public boolean checkEmailField() throws IOException {

		log.info("Verifying the Email Field is visible on login page");
		element_Actions.switchToFrame("iframeLogin");
		return element_Actions.checkElementPresence(email_Field);
	}

	public void enterEmail(String username) throws InterruptedException, IOException {
		log.info("Entering email in username field");
		Thread.sleep(2000);
		//element_Actions.mouseHover();
		element_Actions.waitUntilVisibilityLocated(email_Field);
		element_Actions.findElement(email_Field);
		element_Actions.SendKeys(username);
		element_Actions.switchToDefault();
		log.info("Mobile Numebr Entered");

	}

	public boolean checkContinueBtnUC() throws IOException {

		log.info("Verifying the Continu Button is visible on login page");
		element_Actions.switchToFrame("iframeLogin");
		return element_Actions.checkElementPresence(continue_Btn_UC);
	}

	public void clickOnContinueBtnUC() throws IOException {

		log.info("Clicking Continue Button");
		
		element_Actions.waitUntilVisibilityLocated(continue_Btn_UC);
		element_Actions.findElement(continue_Btn_UC);
		element_Actions.Click();
		element_Actions.switchToDefault();
		log.info("Continue button clicked successfully");

	}
	
	
	public void enterOTP(String username) throws InterruptedException, IOException {
		log.info("Entering OTP in code field");
		Thread.sleep(2000);
		element_Actions.switchToFrame("iframeLogin");
		element_Actions.waitUntilVisibilityLocated(code_Field);
		element_Actions.findElement(code_Field);
		element_Actions.Click();
		element_Actions.SendKeys(username);
		log.info("OTP is entered successfully ");

	}

	
	public void clickOnContinueBtnOTP() throws InterruptedException, IOException {
		Thread.sleep(3000);
		log.info("Clicking Continue Button");
		element_Actions.waitUntilVisibilityLocated(continue_Btn_Otp);
		element_Actions.findElement(continue_Btn_Otp);
		element_Actions.Click();
		element_Actions.switchToDefault();
		log.info("Continue button clicked successfully");

	}
	
	
	
	
	
		
}

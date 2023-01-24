package pages;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import objectRepository.SDObjectRepository;
import utility.ElementActions;

public class HomePage extends SDObjectRepository {
	Logger log;
	WebDriver driver;
	ElementActions element_Actions;
	
	
	By men_Footwear_Option;
	By first_Product;
	By user_Icon;
	By cart_Icon;
	By add_To_Cart_Btn;
	By sports_Shoes_Option;
	By mail_Field;
	By search_Arrow;
	By mail_OTP;
	By success_Msg;
	By cart_Heading;
	By refresh_Icon;
	By quantity_Dropdown;
	By item_Price;
	By subtotal;
	
	public HomePage(WebDriver driver, Logger log) throws IOException {
		super("HomePage.properties");
		// TODO Auto-generated constructor stub
		this.log = log;
		this.driver=driver;
		element_Actions=new ElementActions(driver);
		
		men_Footwear_Option = By.xpath(config.getProperty("men_Footwear_Option"));
		first_Product= By.xpath(config.getProperty("first_Product"));
		user_Icon= By.xpath(config.getProperty("user_Icon"));
		cart_Icon= By.xpath(config.getProperty("cart_Icon"));
		add_To_Cart_Btn= By.xpath(config.getProperty("add_To_Cart_Btn"));
		sports_Shoes_Option= By.xpath(config.getProperty("sports_Shoes_Option"));
		mail_Field= By.xpath(config.getProperty("mail_Field"));
		search_Arrow= By.xpath(config.getProperty("search_Arrow"));
		mail_OTP= By.xpath(config.getProperty("mail_OTP"));
		success_Msg= By.xpath(config.getProperty("success_Msg"));
		cart_Heading= By.xpath(config.getProperty("cart_Heading"));
		refresh_Icon= By.xpath(config.getProperty("refresh_Icon"));
		quantity_Dropdown= By.xpath(config.getProperty("quantity_Dropdown"));
		item_Price= By.xpath(config.getProperty("item_Price"));
		subtotal= By.xpath(config.getProperty("subtotal"));

		
		
		
	}
	
	public void waitForHomePageToLoad() {
		element_Actions.waitUntilVisibilityLocated(user_Icon);
	}
	
	public boolean checkHomePage() {

		log.info("Verifying if home page is displayed");
		return element_Actions.checkElementPresence(user_Icon);
	}
	
	public void selectMenFWOption() throws InterruptedException {
        Thread.sleep(3000);
		element_Actions.waitUntilVisibilityLocated(men_Footwear_Option);
		element_Actions.findElement(men_Footwear_Option);
		element_Actions.mouseHover();
		
		element_Actions.waitUntilVisibilityLocated(sports_Shoes_Option);
		element_Actions.findElement(sports_Shoes_Option);
		element_Actions.Click();
		

	}
	
	public void clickOnFirstProduct() {

		element_Actions.waitUntilVisibilityLocated(first_Product);
		element_Actions.findElement(first_Product);
		element_Actions.Click();

	}
	
	public void swichWindow(int i) {
		element_Actions.switchToDesiredWindow(i);
	}
	
	public boolean checkCartIcon() throws InterruptedException {
       Thread.sleep(3000);
		return element_Actions.checkElementPresence(cart_Icon);
	}
	
	
	public void clickAddToCartBtn() throws InterruptedException {
        Thread.sleep(3000);
		element_Actions.waitUntilVisibilityLocated(add_To_Cart_Btn);
		element_Actions.findElement(add_To_Cart_Btn);
		element_Actions.scrollElementIntoView();
		element_Actions.click_JS();
		
		
	}
	
	public void selectSize(String size) throws InterruptedException {
      
        element_Actions.switchToDesiredWindow(1);
        Thread.sleep(5000);
        By productSize =  By.xpath("(//div[contains(@class,'attr-squared attr-pro')])[1]//div[contains(text(),'"+size+"')]");
		element_Actions.waitUntilVisibilityLocated(productSize);
		element_Actions.findElement(productSize);
		element_Actions.scrollElementIntoView();
		element_Actions.click_JS();
		
		
	}
	
	
	public String getOTPFromMail(String mailName,String mailUrl) throws InterruptedException {
		element_Actions.openNewWindow();
		element_Actions.switchToDesiredWindow(1);
		driver.navigate().to(mailUrl);
		element_Actions.waitUntilVisibilityLocated(mail_Field);
		element_Actions.findElement(mail_Field);
		log.info("User entering the name for creating the mail");
		element_Actions.SendKeys(mailName);
		
		element_Actions.findElement(search_Arrow);
		element_Actions.Click();
	    driver.navigate().refresh();
//      element_Actions.findElement(refresh_Icon);
//    	element_Actions.Click();
		Thread.sleep(7000);
		log.info("User Enter the name successfully");
		driver.switchTo().frame("ifmail");
		log.info("OTP Field display on mail");
		
		element_Actions.findElement(mail_OTP);
		element_Actions.scrollElementIntoView();
		String OTP= element_Actions.getElementText();
		log.info("OTP is captured successfully on Mail : "+ OTP);
		driver.switchTo().defaultContent();
		element_Actions.closeWindow();
		element_Actions.switchToDesiredWindow(0);
		return OTP;
		}
	
	
	public String getUserText() throws InterruptedException {
		Thread.sleep(3000);
		element_Actions.waitUntilVisibilityLocated(user_Icon);
		element_Actions.findElement(user_Icon);
		return element_Actions.getElementText();
	}
	
	public String getSuccessMsg() throws InterruptedException {
	       Thread.sleep(3000);
	       element_Actions.waitUntilVisibilityLocated(success_Msg);
			element_Actions.findElement(success_Msg);
			return element_Actions.getElementText();
		}
		
	public void clickOnCartIcon() throws InterruptedException {
        Thread.sleep(3000);
		element_Actions.waitUntilVisibilityLocated(cart_Icon);
		element_Actions.findElement(cart_Icon);
		element_Actions.scrollElementIntoView();
		element_Actions.Click();
		
		
	}
	
	public boolean checkCartHeading() {

		log.info("Verifying if cart heading is displayed");
		return element_Actions.checkElementPresence(cart_Heading);
	}
	
	public String getCartHeading() {
		element_Actions.waitUntilVisibilityLocated(cart_Heading);
		element_Actions.findElement(cart_Heading);
		return element_Actions.getElementText();
	}
	
	public boolean checkQuantityDropdown() {

		return element_Actions.checkElementPresence(quantity_Dropdown);
	}
	
	public void updateQuantity(String quantity) throws InterruptedException {
        Thread.sleep(3000);
       By qua =  By.xpath("//div[contains(@class,'cart-dropdown')]//ul//li[text()='"+quantity+"']");
		element_Actions.waitUntilVisibilityLocated(quantity_Dropdown);
		element_Actions.findElement(quantity_Dropdown);
		element_Actions.Click();
		
		element_Actions.waitUntilVisibilityLocated(qua);
		element_Actions.findElement(qua);
		element_Actions.Click();
		
	}
	
	public String getSelectedQuantity() throws InterruptedException {
		Thread.sleep(3000);
		element_Actions.waitUntilVisibilityLocated(quantity_Dropdown);
		element_Actions.findElement(quantity_Dropdown);
		return element_Actions.getElementText();
	}
	
	
	public int getItemPrice() throws InterruptedException {
		Thread.sleep(3000);
		element_Actions.waitUntilVisibilityLocated(item_Price);
		element_Actions.findElement(item_Price);
		String price = element_Actions.getElementText();
		//String [] pr = price.split(" ");
		String p1 =price.replaceAll("[^0-9]", "");
		int price1=  Integer.parseInt(p1);
		return price1;
	}
	
	public int getSubtotal() throws InterruptedException {
		Thread.sleep(2000);
		element_Actions.waitUntilVisibilityLocated(subtotal);
		element_Actions.findElement(subtotal);
		String price = element_Actions.getElementText();
		String p1 =price.replaceAll("[^0-9]", "");
		int subtotal=  Integer.parseInt(p1);
		return subtotal;
	}
	
	
	
	public boolean checkSubtotalPrice(int price1,String quntity) throws InterruptedException {
		Thread.sleep(3000);
		boolean flag=false;
		element_Actions.waitUntilVisibilityLocated(subtotal);
		element_Actions.findElement(subtotal);
		String price = element_Actions.getElementText();
		String p1 =price.replaceAll("[^0-9]", "");
		int subtotal=  Integer.parseInt(p1);
		int quaInt = Integer.parseInt(quntity);
		
		int checkprice= price1*quaInt;
		
		if(checkprice== subtotal) {
			flag=true;
		}
		
		return flag;
	}
	
	
}

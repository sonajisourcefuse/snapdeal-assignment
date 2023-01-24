package testCases;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.Status;

import pages.HomePage;
import pages.LoginPage;
import snapdeal.app.TestBase;
import utility.XmlReader;


public class SD_001_Verify_Add_To_Cart extends TestBase {
	LoginPage Lp;
	HomePage Hp;
	
	public Logger log;
	
	public void Loader() throws IOException {
		
		log = LogManager.getLogger(SD_001_Verify_Add_To_Cart.class.getName());
		Lp = new LoginPage(driver, log);
		Hp = new HomePage(driver,log);
		
	}
	
	@Test (dataProvider = "Snapdeal_SD001_TestData")
	public void SD001_VerifyAddToCart(String userEmail,String email_URL,String username,String size,String quantity) throws InterruptedException, IOException 
	{
		test = extent.createTest("Home Page SD-001 : Verify that user should be add product to cart and subtotal should get update as per selected quantity successfully");
	 	log.info("Started == Verify that user should be add product to cart and subtotal should get update as per selected quantity successfully");
	    String successMsg="added to your cart";
		
		test.log(Status.INFO,"Entering the URL of the application");
		driver.get(URL);
		Lp.waitForLoginPageToLoad();
		test.log(Status.PASS,"URL of the application is entered successfully");
		test.log(Status.INFO, "Verifying that Login Page is displayed to the user");
		Assert.assertEquals(Lp.checkLoginPage(), true, "Login Page is not displayed to the user");
		test.log(Status.PASS, "Login Page is displayed to the user successfully");
		log.info("Login Page is displayed successfully");
		
		
		test.log(Status.INFO, "Clicking on login button from login page");
		Lp.clickOnLogin();
		test.log(Status.PASS, "Login button is clicked successfully");
		
		test.log(Status.INFO, "Verifying the presence of Email field on login page");
		Assert.assertEquals(Lp.checkEmailField(), true, "Email field is not displayed on login page");
		test.log(Status.PASS, "Email field is successfully displayed on login page");
		
		
		test.log(Status.INFO, "Entering the Email in Email field");
		Lp.enterEmail(userEmail);  //  kakde@yopmail 
		test.log(Status.PASS, "Email is entered successfully");
		
		test.log(Status.INFO, "Verifying the presence of Continue button on login page");
		Assert.assertEquals(Lp.checkContinueBtnUC(), true, "Continue button  is not displayed on login page");
		test.log(Status.PASS, "Continue button is successfully displayed on login page");
		
		test.log(Status.INFO, "Clicking on Continue button");
		Lp.clickOnContinueBtnUC();
		test.log(Status.PASS, "Continue button is clicked successfully");
		
		test.log(Status.INFO, "Getting the OTP from mail");
		String otp = Hp.getOTPFromMail(userEmail, email_URL);
		test.log(Status.PASS, "OTP- "+otp+"is fetched from mail successfully");
		
		test.log(Status.INFO, "Entering the OTP in code field");
		Lp.enterOTP(otp);  
		Lp.clickOnContinueBtnOTP();
		test.log(Status.PASS, "OTP is entered successfully");
		
		test.log(Status.INFO, "Verifying that user is logged in successfully");
		Assert.assertEquals(Hp.getUserText(),username , "User is not logged in successfully");
		test.log(Status.PASS, "User is logged in successfully with username - "+Hp.getUserText());
		
		test.log(Status.INFO, "Selecting sports shoes option from mens footewear");
		log.info("Selecting sports shoes option from mens footewear");
		Hp.selectMenFWOption();
		test.log(Status.PASS, "Sports shoes option is selected successfully");
		log.info("Sports shoes option is selected successfully");
		
		test.log(Status.INFO, "Clicking on first product from sports shoes section");
		log.info("Clicking on first product from sports shoes section");
		Hp.clickOnFirstProduct();
		test.log(Status.PASS, "First product is opend successfully");
		log.info("First product is opend successfully");
		
		test.log(Status.INFO, "Selecting product size - "+size);
		log.info("Selecting product size - "+size);
		Hp.selectSize(size);
		test.log(Status.PASS, "Product is size "+size+" is selected successfully");
		log.info("Product is size "+size+" is selected successfully");
		
		test.log(Status.INFO, "Clicking on Add To Cart button to add product into the cart");
		log.info("Clicking on Add To Cart button to add product into the cart");
		Hp.clickAddToCartBtn();
		Assert.assertEquals(Hp.getSuccessMsg().contains(successMsg), true,"Success message is not displayed successfully");
		test.log(Status.PASS, "Add To Cart button is clicked successfully and message - "+Hp.getSuccessMsg()+" is displayed on screen");
		log.info("Add To Cart button is clicked successfully and message - "+Hp.getSuccessMsg()+" is displayed on screen");
		
		test.log(Status.INFO, "Clicking on cart icon to open it");
		log.info("Clicking on cart icon to open it");
		Hp.clickOnCartIcon();
		Assert.assertEquals(Hp.checkCartHeading(), true,"Cart heaindg is not displayed successfully");
		test.log(Status.PASS, "Cart is opened successfully with heading - "+Hp.getCartHeading());
		log.info("Cart is opened successfully with heading - "+Hp.getCartHeading());
		
		test.log(Status.INFO, "Getting the price of product before updating quantity");
		log.info("Getting the price of product before updating quantity");
		int itemaPrice1 = Hp.getItemPrice();
		test.log(Status.PASS, "Before updating the quantity price of product is "+itemaPrice1);
		log.info("Before updating the quantity price of product is "+itemaPrice1);
		
		test.log(Status.INFO, "Getting the subtotal of product before updating quantity");
		log.info("Getting the subtotal of product before updating quantity");
		int subtotal1 = Hp.getSubtotal();
		test.log(Status.PASS, "Before updating the quantity subtotal of product is - "+subtotal1);
		log.info("Before updating the quantity subtotal of product is - "+subtotal1);
		
		test.log(Status.INFO, "Verifying the presence of Quantity drop-down on cart modal");
		log.info("Verifying the presence of Quantity drop-down on cart modal");
		Assert.assertEquals(Hp.checkQuantityDropdown(), true, "Quantity drop-down is not displayed");
		test.log(Status.PASS, "Quantity drop-down is successfully displayed on cart modal");
		log.info("Quantity drop-down is successfully displayed on cart modal");
		
		
		test.log(Status.INFO, "Updating quantity by selecting value - "+quantity+" from Quantity drop-down ");
		log.info("Updating quantity by selecting value - "+quantity+" from Quantity drop-down ");
		Hp.updateQuantity(quantity);
		Assert.assertEquals(Hp.getSelectedQuantity(), quantity,"Quantity is not selected successfully");
		test.log(Status.PASS, "Quantity - "+quantity+" is successfully updated from Quantity drop-down");
		log.info("Quantity - "+quantity+" is successfully updated from Quantity drop-down");
		
		test.log(Status.INFO, "Getting the subtotal of product after updating qunatity");
		log.info("Getting the subtotal of product after updating qunatity");
		int subtotal2 = Hp.getSubtotal();
		test.log(Status.PASS, "After updating the quantity subtotal of product is - "+subtotal1);
		log.info("After updating the quantity subtotal of product is - "+subtotal1);
		
		test.log(Status.INFO, "Verifying that subtotal gets update as per selected quantity - "+quantity+" from Quantity drop-down ");
		log.info("Verifying that subtotal gets update as per selected quantity - "+quantity+" from Quantity drop-down ");
		Assert.assertEquals(Hp.checkSubtotalPrice(itemaPrice1,quantity), true,"Subtotal is not doubled as per quantity");
		test.log(Status.PASS, "Subtotal is successfully updated as per selected quantity after updating quantity with - "+subtotal2);
		log.info("Subtotal is successfully updated as per selected quantity after updating quantity with - "+subtotal2);
	}
	
	@DataProvider(name = "Snapdeal_SD001_TestData")
  	public Object[][] sd() throws IOException, ParserConfigurationException, SAXException {
  		XmlReader reader = new XmlReader();
  		return reader.testData("TestData.xml", "SD001");
  	}

}

package utility;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	WebDriver driver;
	JavascriptExecutor executor;
	WebDriverWait wait;
	WebElement element;
	List<WebElement> elements;


	@SuppressWarnings("deprecation")
	public ElementActions(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver,20);

	}

	public WebElement findElement(By by) {

		element = driver.findElement(by);
		return element;

	}

	public List<WebElement> findElements(By by) {

		elements = driver.findElements(by);
		return elements;
	}

	public void waitUntilVisibilityLocated(By by) {
		wait.until(ExpectedConditions.elementToBeClickable(wait.until(ExpectedConditions.visibilityOfElementLocated(by))));
	}




	public void waitUntilInVisibilityLocated(By by) {
		//driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}




	public boolean checkElementPresence(By by) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		if (driver.findElements(by).size() > 0) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			return true;
		}

		else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			return false;
		}
	}

	public boolean checkElementVisibility(By by) {
		boolean visibility = true;

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}

		catch (Exception e) {
			visibility = false;
		}

		return visibility;
	}

	public void SendKeys(String text) {
		element.sendKeys(text);

	}

	public void pressEnter() {
		element.sendKeys(Keys.ENTER);

	}


	public void Click() {
		element.click();

	}

	public void click_JS() {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void doubleClick(By by) {

		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(by);
		actions.doubleClick(elementLocator).perform();
	}

	public String getElementText() {

		return element.getText();

	}

	public void selectValueFromDropdown(String dropdown_Value) {

		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(dropdown_Value);
	}


	public void clearField() {

		element.clear();
	}

	public void mouseHover() {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

	}
	
	public void mouseClick() {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();

	}

	public void moveByOffset() {
		try {
			Actions move = new Actions(driver);
			move.moveByOffset(-510,0).build().perform();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public String getAttributeValue() {

		return element.getAttribute("value");
	}

	public String getTextOfSelectedOptionInDropdown(By by) {
		Select select = new Select(driver.findElement(by));
		WebElement option = select.getFirstSelectedOption();
		String selected_Item = option.getText();
		return selected_Item;
	}

	public int checkElementCount(By by) {
		return driver.findElements(by).size();
	}

	public void acceptWindowAlert() throws IOException {

		Alert alert=driver.switchTo().alert();
		alert.accept();
	}

	public String getAlertText() {

		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void switchToFrame(String name) throws IOException {
		driver.switchTo().frame(name);
	}
	
	

	public void switchToDefault() throws IOException {
		driver.switchTo().defaultContent();
	}

	public boolean isAlertPresent() {
		try 
		{
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e)
		{
			return false;
		}
	}

	public void scrollElementIntoView() {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}


	public void scrollElementHorizontally() {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", element);
	}



	public void scrollHorizontally(String position) {


		Actions move = new Actions(driver);

		if(position == "left") {

			move.moveToElement(element).clickAndHold();
			move.moveByOffset(510,0);
			move.release();
			move.perform();
		}
		else
		{

			move.moveToElement(element).clickAndHold();
			move.moveByOffset(-510,0);
			move.release();
			move.perform();
		}



	}

	public void SendKeys(Keys arrowDown) {
		// TODO Auto-generated method stub
		element.sendKeys(arrowDown);
	}

	public void dragAndDrop(By from,By to) {

		Actions action = new Actions(driver);
		WebElement fromElement = driver.findElement(from);
		WebElement toElement = driver.findElement(to);
		action.clickAndHold(fromElement).moveToElement(toElement).release().perform();

	}

	public void dragAndDrop_JS(By from,By to) {

		WebElement LocatorFrom = driver.findElement(from);
		WebElement LocatorTo = driver.findElement(to);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
				+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
				+ "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
				+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
				+ "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
				+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
				+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
				+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
				+ "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
				+ "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
				+ "var dropEvent = createEvent('drop');\n"
				+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
				+ "var dragEndEvent = createEvent('dragend');\n"
				+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
				+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
				+ "simulateHTML5DragAndDrop(source,destination);", LocatorFrom, LocatorTo);

	}
	/*for ReportingBook Cases*/
	
	public boolean isEnabled() {

		return element.isEnabled();
		
	}
	
	public void switchToDesiredWindow(int i) {
		ArrayList<String> windowList = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowList.get(i));
		}
	
	public void openNewWindow() {
		((JavascriptExecutor)driver).executeScript("window.open()");
	}
	
	public void closeWindow() {
		driver.close();
		}
	
	
	



}
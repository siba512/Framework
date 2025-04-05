package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WeBDriverUtility {
	/**
	 * This method is used to maximize the window
	 * @param driver
	 */
	public void toMaximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	/**
	 * This method is used to minimize the window
	 * @param driver
	 */
	public void toMinimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}
	/**
	 * this method will wait until the element loaded in WebPage(Implicit Wait)
	 * @param driver
	 */
	public void toWaitElemnt(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	/**
	 * This method will wait until element to be Clickable(Explicit wait)
	 * @param driver
	 * @param element
	 */
	public void toWaitUntilElementToBeClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This method will wait until the element is visible
	 * @param driver
	 * @param element
	 */
	public void toWaitUntilVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method is used to handle DropDown using index
	 * @param element
	 * @param index
	 */
	public void toHandleDropDown(WebElement element,int index) {
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * This method is used to handle dropdown using value
	 * @param element
	 * @param value
	 */
	public void toHandleDropDown(WebElement element,String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	/**
	 * This method is used to handle DropDown using text
	 * @param text
	 * @param element
	 */
	public void toHandleDropDown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	/**
	 * This method is used to perform mousehover
	 * @param driver
	 * @param element
	 */
	public void toMouseHover(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}
	/**
	 * this method is used to perform RightClick
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
		
	}
	/**
	 * This method is used to perform DoubleClick
	 * @param driver
	 * @param element
	 */
	public void toDoubleClick(WebDriver driver, WebElement element) {
		Actions action=new Actions(driver);
		action.doubleClick(element).perform();;
	}
	/**
	 * This method is used to perform  drag and drop provided driver and webelement
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDragAndDrop(WebDriver driver,WebElement src,WebElement target) {
		Actions action=new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}
	/**
	 * This method is used to handle frame using index
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to handle frame using name_id
	 * @param driver
	 * @param name_id
	 */
	public void toHandleFrame(WebDriver driver,String name_id) {
		driver.switchTo().frame(name_id);
	}
	/**
	 * This method is used to handle frame using element
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	/**
	 * This method is used to exit from frame
	 * @param driver
	 */
	public void toExitFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	/**
	 * This method is used to handle alert PopUp by accepting it
	 * @param driver
	 */
	public void toSwitchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 * This method is used to handle alert PopUp by dismissing it
	 * @param driver
	 */
	public void toSwitchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method is used to capture text in alert PopUp and accept it
	 * @param driver
	 * @return
	 */
	public String tpSwitchToAlertAndCaptureMessage(WebDriver driver){
		Alert popup=driver.switchTo().alert();
		String message=popup.getText();
		popup.accept();
		return message;
	}
	/**
	 * This method is used to take screenshot provided driver and screenshot name
	 * @param driver
	 * @param screenshotname
	 * @throws IOException
	 */
	
	public String toTakeScreenShot(WebDriver driver,String screenshotname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp=ts.getScreenshotAs(OutputType.FILE);
		File src=new File ("./errorShots/" + screenshotname + ".png");
		FileHandler.copy(temp, src);
		String path=src.getAbsolutePath();
		return path;
		
	}
	/**
	 * This method is used to handle or Switch windo * @param driver
	 * @param partialtitle
	 */
	public void toSwitchWindow(WebDriver driver,String partialtitle) {
		Set<String> allids=driver.getWindowHandles();
		for(String id:allids) {
			String title=driver.switchTo().window(id).getTitle();
			if(title.contains(partialtitle)) {
				break;
			}
		}
	}
	
	
	
	

}

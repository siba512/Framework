package vtigerCRM;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Testscript5{

	public static void main(String[] args) throws InterruptedException {
		
		//Step 1: Launch the browser
		WebDriver driver  = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//Step 2:Navigate to the application
		driver.get("http://localhost:8888/");
		
		//Step 3:Login with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 4:Click on Contacts
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 5:Click on Create contact
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
	    //Step 6:Create contact with mandatory fields
	    
		driver.findElement(By.name("lastname")).sendKeys("Bahadur");
		
		//Select organization from organization lookup
		String parent = driver.getWindowHandle();
		//Thread.sleep(3000);
		driver.findElement(By.xpath("//img[contains(@onclick,'return window.open')]")).click();
		//Thread.sleep(3000);
		Set<String> All = driver.getWindowHandles();
		All.remove(parent);
		
		//Transfer control to Child
		for(String child:All)
		{
			driver.switchTo().window(child);
			driver.findElement(By.id("1")).click();
		}
		
		//Switch to Parent window
		driver.switchTo().window(parent);
		
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(logout).perform();
		
		Thread.sleep(3000);
		driver.findElement(By.linkText("Sign Out")).click();
		
		Thread.sleep(3000);
		driver.quit();
	}

}

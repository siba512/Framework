package vtigerCRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class ToCreateContact {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//step1:- Launch the Browser
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Login to application with valid Credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		System.out.println("Login Completed");
		
		//step-3 Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//step-4 Click on Create Contact Look up Image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//step 5-Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Tripathy");
		
		//step -6 Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(lastname.contains("Tripathy")) {
			System.out.println(lastname + ".....Passed");
		}else {
			System.out.println(lastname + "......Failed");
		}
		
		//step 7-logout from application
		WebElement logoutele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout Completed");
		
		// cLose browser
		driver.quit();

	}

}

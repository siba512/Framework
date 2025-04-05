package vtigerCRM;

import java.time.Duration;
import java.util.Random;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganization {

	public static void main(String[] args) throws InterruptedException {
		
		// TODO Auto-generated method stub
		//Launch Browser
		WebDriver driver= new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//Login with valid credentials
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(4000);
		System.out.println("Login Completed");
		
		//Navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();
		Thread.sleep(4000);
		
		//Click on Create Organization Look up Image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		//generate random number
		Random r=new Random();
		int random=r.nextInt(1000);
		
		//Create Organization with mandatory fields
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("Jspider"+random);
		Thread.sleep(4000);
		
		//Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//Alert a=driver.switchTo().alert();
		//System.out.println(a.getText());
		//Thread.sleep(3000);
		//a.accept();
		String organization=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organization.contains("Jspider"+random)) {
			System.out.println(organization + ".....Passed");
		}else {
			System.out.println(organization + ".....Failed");
		}
		
		//logout from application
		WebElement logoutele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout Completed");
		
		// cLose browser
		driver.quit();
		
		
		

	}

}

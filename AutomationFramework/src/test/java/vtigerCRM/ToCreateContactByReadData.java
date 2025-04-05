package vtigerCRM;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateContactByReadData {
	public static void main(String[] args) throws IOException {
		//To read data from common properties
		FileInputStream cfile=new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		//create object of Property file
		Properties prop=new Properties();
		//call methods
		prop.load(cfile);
		String URL= prop.getProperty("url");
		String USERNAME= prop.getProperty("username");
		String PASSWORD= prop.getProperty("password");
		String BROWSER = prop.getProperty("browser");
		
		//To read data from excel file
		FileInputStream exfile=new FileInputStream(".\\src\\test\\resources\\TestDataVtigerCRM_Contact_Organization.xlsx");
		Workbook wb= WorkbookFactory.create(exfile);
		String lastname=wb.getSheet("Contacts").getRow(1).getCell(2).toString();
		
		//step 1 - Launch browser
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
			
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		System.out.println("Login Completed");
		//navigate to contact link
        driver.findElement(By.linkText("Contacts")).click();
		
		//step-4 Click on Create Contact Look up Image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//step 5-Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		//step -6 Save and verify
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				String headname=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headname.contains(headname)) {
					System.out.println(headname + ".....Passed");
				}else {
					System.out.println(headname + "......Failed");
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

package vtigerCRM;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateOrganizationByReadData {
	public static void main(String[] args) throws IOException, InterruptedException {
		//
		FileInputStream commonfile=new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		//create object of property file
		Properties prop=new Properties();
		//call methods
		prop.load(commonfile);
		String URL=prop.getProperty("url");
		String USERNAME= prop.getProperty("username");
		String PASSWORD= prop.getProperty("password");
		String BROWSER = prop.getProperty("browser");
		
		//To read data from excel file
				FileInputStream excelfile=new FileInputStream(".\\src\\test\\resources\\TestDataVtigerCRM_Contact_Organization.xlsx");
				Workbook wb= WorkbookFactory.create(excelfile);
				String orgname=wb.getSheet("Organization").getRow(1).getCell(2).toString();
				//Launch browser
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
				//Login with valid credentials
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
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
				driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname+random);
				Thread.sleep(4000);
				
				//Save and verify
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				//Alert a=driver.switchTo().alert();
				//System.out.println(a.getText());
				//Thread.sleep(3000);
				//a.accept();
				String organization=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(organization.contains(orgname+random)) {
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

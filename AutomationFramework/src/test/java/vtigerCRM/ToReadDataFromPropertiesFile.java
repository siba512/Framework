package vtigerCRM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ToReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		//Create obj of proprety file
		FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		
		//create object of property file
		Properties prop = new Properties();
		//call methods
		prop.load(fis);
		String URL= prop.getProperty("url");
		String USERNAME= prop.getProperty("username");
		String PASSWORD= prop.getProperty("password");
		String BROWSER = prop.getProperty("browser");	
		
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		System.out.println(BROWSER);
		
	
	}

}

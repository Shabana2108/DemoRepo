package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertions {
   WebDriver driver;
   SoftAssert soft;
	
	@BeforeClass
	public void before() {
		driver= new ChromeDriver();
		driver.get("http://ustr-erl2-3644.na.uis.unisys.com:9401/Census/BirthDetail#!");
		}
	
	@Test
	public void encode() {
		soft= new SoftAssert();
		soft.assertEquals(driver.getTitle(), "Request Birth Certificate Online | PSA (NSO) Certificate", "Title is not matched");
		driver.findElement(By.id("txtChildLastName")).sendKeys("Shabana");
		driver.findElement(By.id("txtChildFirstName")).sendKeys("Shabana");
	    WebElement province =driver.findElement(By.id("birthProvince"));
	    System.out.println(province.isSelected());
	    soft.assertFalse(province.isSelected());
	    driver.findElement(By.id("txtBCCDFatherLastName")).sendKeys("Shabana");
	    driver.findElement(By.id("txtChildMiddleName")).sendKeys("Shabana");
	    soft.assertAll();
		
	    
	}
	@AfterClass
	public void teardown() {
		driver.close();
		
	}

}
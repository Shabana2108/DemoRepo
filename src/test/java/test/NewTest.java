package test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NewTest {
  @Test
  public void f() {
	 // ChromeOptions options = new ChromeOptions();
	  
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("hi in english");
		
				
  }
  @Test
  public void f1() throws IOException {
		 // ChromeOptions options = new ChromeOptions();
		  
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.google.com/");
			driver.findElement(By.name("q")).sendKeys("hello");
		   driver.manage().window().maximize();
		   File ss= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		   FileUtils.copyFile(ss,new File(""));
		   
		   JavascriptExecutor js = (JavascriptExecutor)driver;
		   
							
	  }
  
  @Test
  public void m1() {
	  String name ="Shabana";
	  
	  for(int i=0;i<name.length();i++) {
		  char ch = name.charAt(i);
		  
		  if((ch=='a')||(ch=='e')||(ch=='i')||(ch=='o')||(ch=='u')||(ch=='A')||(ch=='E')||(ch=='I')||(ch=='O')||(ch=='U')) {
			  
			  System.out.println("Your name has owvels");		    
			  
		  }
		  else {
			  System.out.println("Your name doesnt has owvels");
		  }
		  
	  }
  }
	  
	 @Test
	 public void m2() {
		 
		 WebDriver driver = new ChromeDriver();
		 driver.get("https://www.saucedemo.com/");
		 String h1 = driver.getWindowHandle();
		 driver.get("https://www.google.com/");
		 String h2 = driver.getWindowHandle();
		 System.out.println(h1);	
		 System.out.println(h2);	
//		 Set<String> windows=driver.getWindowHandles();
//		 
//		 for(String we:windows) {
//			if(we.equals(h2)) {
//				driver.close();
//			}
//		 }
		 
	 }
	 
	 @Test
	 public void m3() {
		 String name="Shabana";
		 StringBuilder ss = new StringBuilder();
		 ss.append(name);
		 System.out.println(ss.reverse());
		 
//		 char[] chaar= name.toCharArray();
//		 for(char ch:chaar) {
//			 System.out.println(ch);
//		 }
	 }
	 
	 
	 
	 
	  
  }
  
  


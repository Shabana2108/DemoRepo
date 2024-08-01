package test;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class demo1 {
	
	@Test
	public void m1() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss, new File("C:\\Users\\SShabana\\OneDrive - Unisys\\Desktop\\demo.png"));
		
	}

}

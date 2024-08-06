package pageObjects;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BREQS {
	
	WebDriver driver = new ChromeDriver();
	Select sel;
	WebDriverWait wait;
	Properties prop;
	FileInputStream fis;
	

///////////////////  Locators  //////////////////////////
	By Login_Btn = By.id("login");
	By Username = By.name("username");
	By Password = By.name("password");
	By CreateRequest = By.id("CertificateRequest");
	By CI_Birth = By.id("initailImageBirth");
	By NoOfCOpies = By.xpath("//label[text()='Number of Copies']//following::select[1]");
	By LastName = By.id("txtChildLastName");
	By FirstName = By.id("txtChildFirstName");
	By MiddleName = By.id("txtChildMiddleName");
	By DOB = By.id("BirthDate");
	By Sex = By.xpath("//span[text()='Male ']");
	By Country = By.id("birthCountry");
	By Province = By.id("birthProvince");
	By City = By.id("birthCity");
	By FatherSLastName = By.id("txtBCCDFatherLastName");
	By FatherSFirstName = By.id("txtBCCDFatherFirstName");
	By FatherSMiddleName = By.id("txtBCCDFatherMiddleName");
	By MotherSLastName = By.id("txtBCCDMotherLastName");
	By MotherSFirstName = By.id("txtBCCDMotherFirstName");
	By MotherSMiddleName = By.id("txtBCCDMotherMiddleName");
	By PurposeOfRequest = By.id("ddlSupplier");
	By Next_Btn = By.id("nextBtn");
	By Confirmation_Yes = By.xpath("//span[text()=' Yes']");
	By Confirmation_No = By.xpath("//span[text()=' No']");
	By RequesterLastName = By.id("txtRequesterLastName");
	By RequesterFirstName = By.id("txtRequesterFirstName");
	By RequesterMiddleName = By.id("txtRequesterMiddleName");
	By DeliveryProvince = By.id("DeliveryProvince");
	By DeliveryCity = By.id("deliveryCity");
	By Address1 = By.id("addressl");
	By Address2 = By.xpath("//input[@id='addressl']//following:: input[1]");
	By Zipcode = By.xpath("//input[@id='addressl']//following:: input[2]");
	By Requester_Next_Btn = By.id("nextBtn");
	By Summary_Next_Btn = By.id("nextBtn");
	By Acknowledge_Done_Btn = By.id("ConfirmtBtn");
	
	
	
	public void launchLoginPage() throws IOException {
		fis= new FileInputStream("C:\\Users\\SShabana\\eclipse-workspace\\com.serbilis\\src\\test\\resources\\resources.config");
		prop = new Properties();
		prop.load(fis);
		driver.get(prop.getProperty("QAB2EcensusUrl"));
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.findElement(Username).sendKeys((prop.getProperty("B2BREQSUname")));
		driver.findElement(Password).sendKeys((prop.getProperty("B2BREQSPword")));
		driver.findElement(Login_Btn).click();
	}
	
	public String getOTPfromDB() throws SQLException {
		String DB_URL ="jdbc:sqlserver://USTR-ERL2-0643\\\\PSAQA:1434;databaseName=Civil Registry System;encrypt=true;trustServerCertificate=true";
		String DB_UserName = "PSATestLiveTeam";
		String DB_Password = "PSATest#LiveTeam";
		Connection con = DriverManager.getConnection(DB_URL,DB_UserName,DB_Password);
		System.out.println("Connected to Databse");
		String query = "select * from web.OTP where AccessNo='Shabana'";
		Statement stmt = con.createStatement();
		ResultSet resultset = stmt.executeQuery(query);
		while (resultset.next()) {
		String OTP = resultset.getString("OTP");
		System.out.println(OTP);
		return OTP;
		}
		return "";
		
	}
	
	public void enterOTP(String OTP) {
		String[] otp = OTP.split("");
		driver.findElement(By.id("pin1")).sendKeys(otp[0]);
		driver.findElement(By.id("pin2")).sendKeys(otp[1]);
		driver.findElement(By.id("pin3")).sendKeys(otp[2]);
		driver.findElement(By.id("pin4")).sendKeys(otp[3]);
		driver.findElement(By.id("pin5")).sendKeys(otp[4]);
		driver.findElement(By.id("pin6")).sendKeys(otp[5]);
	}
	
	
	
	public void clickCreateRequest() throws InterruptedException {
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(CreateRequest)).click();
	}
	
	public void selectCertificate() throws InterruptedException {
		driver.findElement(CI_Birth).click();
		Thread.sleep(1000);
	}
	public void enterRequestDetails() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(NoOfCOpies).sendKeys("1");
	}
	
	
	

}

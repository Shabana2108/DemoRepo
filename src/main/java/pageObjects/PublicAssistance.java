package pageObjects;

import java.awt.Robot;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class PublicAssistance {
	HelpDesk HD= new HelpDesk();
	WebDriver driver = new ChromeDriver();
	Properties prop;
	Select sel;
    JavascriptExecutor js;
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    Robot robot;
    
    
    
 //// Locators //////   
    By PublicAssistance = By.id("301");
    By UserRole= By.id("userdisplayrolecode");
    By LogNewQuery= By.partialLinkText("Log New Query");
    By ModeOfQry = By.xpath("//h4[text()='Log New Query']/following::select[1]");
    By Categeory = By.xpath("//h4[text()='Log New Query']/following::select[2]");
    By SubCategeory = By.xpath("//h4[text()='Log New Query']/following::select[3]");
    By SeverityLevel = By.xpath("//h4[text()='Log New Query']/following::select[4]");
    By FirstName = By.xpath("//label[text()='First Name']/following::input[1]");
    By MiddleName = By.xpath("//label[text()='First Name']/following::input[2]");
    By LastName = By.xpath("//label[text()='First Name']/following::input[3]");
    By EmailAddress = By.xpath("//label[text()='First Name']/following::input[4]");
    By PhoneNo = By.xpath("(//input[@id='phonenumber'])[2]");
    By Subject = By.id("sub");
    By Description = By.id("USRdescription");
    By GenQryRefNo_Btn = By.xpath("//button[text()='Generate Query Reference Number']");
    By Success_text = By.xpath("//h4[@id='USRSuccessheader']/following::div[3]");
    By Success_textline2 = By.xpath("//h4[@id='USRSuccessheader']/following::div[4]");
    By QryRefNo= By.id("PAQRefNumber");	
    By SuccessPopup_OK_Btn = By.xpath("(//button[text()='Ok'])[1]");
    By Resolution_Response= By.xpath("//label[text()='Resolution/Response']/following::textarea");
    By Status = By.xpath("//label[text()='Resolution/Response']/following::select");
    By SearchQuery= By.partialLinkText("Search Query");
    By QueryRefNo_field = By.id("QueryReferenceNumber");
    By Search = By.xpath("//button[@data-bind='click: SearchByReferenceNumber']");
    By QryRefNoLink= By.xpath("//table[@id='SearchResultsTable']//tbody/tr[1]/td[1]//span");
    By Save_Btn = By.xpath("//button[text()='Save']");
    By SuccessmsgID = By.id("popUpForAlertText");
    By SuccessPopup_OK = By.id("AlertButton");
    By Reopen_Btn= By.xpath("//button[text()='Reopen']");
    By ConfirmationmsgID = By.id("popUpDetailsCallBack");
    By ConfirmationYES = By.id("btnRightCallBack");
    By ConfirmationNO = By.id("btnLeftCallBack");
    By ViewFAQs = By.partialLinkText("View FAQs");
    By CloseFAQs_Btn = By.xpath("(//button[text()='Close'])[1]");
    By ViewConcernsAndComplaintsReport = By.partialLinkText("View Concerns and Complaints Report");
    By FromDate = By.id("FromDateId");
    By ToDate = By.id("ToDateId");
    By GenerateReport_Btn= By.xpath("//button[text()='Generate Report']");
    
    
    
    
    
    
    
    
    
    
	
	public void launchPA() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\SShabana\\eclipse-workspace\\com.serbilis\\src\\test\\resources\\resources.config");
		prop= new Properties();
		prop.load(fis);
		driver.get(prop.getProperty("HelpDeskUrl"));
		driver.manage().window().maximize();
		driver.navigate().refresh();
	}
	
	public void scrollDown() {
		js= (JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,1000)");
	}
	
	public void verifyAndUpdate_RoleAndGroupID() throws SQLException{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    String currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	    System.out.println("My current role is " + currentRole); 
	    while (!currentRole.equalsIgnoreCase("Public Assistance Personnel")) {
	        HD.connecting_DB_updatingroleID(8, 1157);
	        HD.connecting_DB_updateGroupID(1,1157);
	        driver.navigate().refresh();
	        currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentRole); 
	            }
	}
	
	public void clickPA() throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(PublicAssistance)).click();
	}
		
	public void clickLogNewQuery() {
		driver.findElement(LogNewQuery).click();
	}
		
	public void selectModeOFQry(int mode) {
		WebElement Mode=driver.findElement(ModeOfQry);
		sel= new Select(Mode);
		sel.selectByIndex(mode);
	}
	
	public void selectCategeory(int categeory) {
		WebElement Catgry=driver.findElement(Categeory);
		sel= new Select(Catgry);
		sel.selectByIndex(categeory);
	}
	
	public void selectSubCategeory(int sub_categry) {
		WebElement subCatgry=driver.findElement(SubCategeory);
		sel= new Select(subCatgry);
		sel.selectByIndex(sub_categry);
	}
	
	public void selectSeverity(int severity) {
		WebElement Severity=driver.findElement(SeverityLevel);
		sel= new Select(Severity);
		sel.selectByIndex(severity);
	}
	
	public void enterSenderDetails() throws InterruptedException {
		driver.findElement(FirstName).sendKeys("Shabana");
		driver.findElement(MiddleName).sendKeys("S");
		driver.findElement(LastName).sendKeys("Shaikh");
		driver.findElement(EmailAddress).sendKeys("Shabana.S@Unisys.com");
		driver.findElement(PhoneNo).sendKeys("09123456789");
		driver.findElement(Subject).sendKeys("Generating Query");
		driver.findElement(Description).sendKeys("Generating Query through Automation by Shabana");
	}
	
	public void clickGenQryRefNoBtn() throws InterruptedException {
		Thread.sleep(1000);
		scrollDown();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(GenQryRefNo_Btn)).click();
	}
	
	public String verifySuccessPopup() throws InterruptedException {
		Thread.sleep(2000);
		String ActualText1= driver.findElement(Success_text).getText();
		String Text2= driver.findElement(Success_textline2).getText();
		String ActualText2=Text2.substring(0, Text2.indexOf(":"));
		String ExpectedText1 ="Below is your query reference number,please use the same for your future references.";
		String ExpectedText2 ="Query Reference Number ";
		System.out.println("Text is "+ActualText1);
		Thread.sleep(1000);
		Assert.assertEquals(ActualText1, ExpectedText1);
		Assert.assertEquals(ActualText2, ExpectedText2);
		System.out.println(ActualText1 + ActualText2);
		String QryRefNumber= driver.findElement(QryRefNo).getText();
		System.out.println(QryRefNumber);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(SuccessPopup_OK_Btn)).click();
		return QryRefNumber;		
	}
	
	public void clickSearchQry() {
		wait.until(ExpectedConditions.elementToBeClickable(SearchQuery)).click();
	}
	
	public void searchWithQryRefNo(String QryRefNo) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(QueryRefNo_field).sendKeys(QryRefNo);
		Thread.sleep(1000);
		driver.findElement(Search).click();
	}
	
	public void clickQryRefNoLink() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(QryRefNoLink).click();
	}
	
	public void enterResolution() {
		scrollDown();
		driver.findElement(Resolution_Response).sendKeys("Entering Resolution through Automation");
	}
	
	public void selectStatus(String status) {
		WebElement elemnt =driver.findElement(Status);
		sel= new Select(elemnt);
		sel.selectByVisibleText(status);
	}
	public void clickSave() throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(Save_Btn).click();
		Thread.sleep(1000);
		String ActualMsg = driver.findElement(SuccessmsgID).getText();
		Thread.sleep(1000);
		String ExpectedMsg = "Record saved.";
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		System.out.println(ActualMsg);
		Thread.sleep(1000);
		driver.findElement(SuccessPopup_OK).click();
	}
	
		
	public void clickReopen() throws InterruptedException {
		scrollDown();
		Thread.sleep(1000);
		driver.findElement(Reopen_Btn).click();
	}
	
	public void acceptReopenCnfrmn() throws InterruptedException {
		Thread.sleep(1000);
		String ActualCnfrmnMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationmsgID)).getText();
	    String ExpectedCnfrmnMsg = "Are you sure you want to reopen this query?";
		System.out.println(ActualCnfrmnMsg);
		Assert.assertTrue(ActualCnfrmnMsg.contains(ExpectedCnfrmnMsg));
		driver.findElement(ConfirmationNO).click();
		Thread.sleep(1000);
		driver.findElement(Reopen_Btn).click();
		Thread.sleep(1000);
		driver.findElement(ConfirmationYES).click();
	}
	
	public void save_QueryReopen() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(Save_Btn).click();
		Thread.sleep(1000);
		String ActualMsg = driver.findElement(SuccessmsgID).getText();
		String ExpectedMsg = "Query reopened.";
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		System.out.println(ActualMsg);
		Thread.sleep(1000);
		driver.findElement(SuccessPopup_OK).click();
	}
	
	public void clickViewFAQs() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(ViewFAQs).click();
	}
	
	public void closeFAQs() throws InterruptedException {
		Thread.sleep(1000);
		scrollDown();
		Thread.sleep(1000);
		driver.findElement(CloseFAQs_Btn).click();
	}
	
	public void clickViewConcernsAndComplaintsReport() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(ViewConcernsAndComplaintsReport).click();
	}
	
	public void enterFromAndToDates(String fromDate, String toDate) {
		driver.findElement(FromDate).sendKeys(fromDate);
		driver.findElement(ToDate).sendKeys(toDate);
	}
	
	public void clickGenerateReport() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(GenerateReport_Btn).click();
	}

}

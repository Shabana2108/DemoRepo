package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HelpDesk {
	static Properties prop;
    WebDriver driver = new ChromeDriver();
    Select sel;
    JavascriptExecutor js;
    WebDriverWait wait;
    Robot robot;
    	   
    
 ////////////// LOCATORS OF HELPDESK ///////////////
    By HelpDesk= By.id("300");
    By UserRole= By.id("userdisplayrolecode");
    
 ///// Submit USR /////  TestCase IDs =91877
    By HomePage = By.linkText("Help Desk Home");
    By SubmitUSR = By.linkText("Submit USR");
    By Categeory = By.xpath("//label[text()='Category']/following::select[1]");
    By HardwareType = By.id("USRCategoryId");
    By USRSubject = By.xpath("(//input[@id='USRSubject'])[2]");
    By USRDescription = By.id("USRdescription");
    By USRTagNo = By.id("USRTagNo");
    By USRSerialNo = By.id("USRSerialNo");
    By Attachment = By.id("UploadImageBtn");
    By USRSubmit = By.xpath("//button[@data-bind='click: SubmitUSRClick']");
    By USRCancel = By.xpath("//button[@data-bind='click: USRCancelClick']");
    By SuccessmsgID = By.id("popUpForAlertText");
    By ConfirmationmsgID = By.id("popUpDetailsCallBack");
    By ConfirmationYES = By.id("btnRightCallBack");
    By ConfirmationNO = By.id("btnLeftCallBack");
    By SuccessPopup_OK = By.id("AlertButton");
    
  
 ////// Tag USR as Resolved ///////// TestCase ID = 91904
    By Search_ViewUSR = By.linkText("Search/View USRs");
    By SearchUSR  = By.id("SearchNoUserIdBtn");
    By USRID_feild = By.id("USRIDTxtFld");
    By SearchwithUSRID = By.id("SearchWithUserIdBtn");
    By USRIDlink = By.xpath("//table[@id='USRSearchResultsTable']/tbody/tr/td[1]//a");
    By MarkResolved_Button = By.id("MarkResolvedBtnId");
    By USRRemarks_Textbox = By.id("USRRemarks");
    By USRResolve_Save_Button = By.xpath("//button[@data-bind='click: OnUSRRemarksSaveClick']");
    By USRResolve_Cancel_Button = By.xpath("//button[@data-bind='click: OnUSRRemarksCancelClick']");
    By USRResolve_SuccessPopup_Ok=By.id("AlertButton");
   
  ///// HelpDesk Officer returning pending USR to HelpDesk ////////   	TestCase ID = 91910
    By ReturntoHelpDesk_Button = By.id("ReturnToHDBtnId");
    By USRSearchStatus = By.xpath("(//input[@id='SearchUSRStatus'])[2]");
    
  //// Referring to support group////
    By ReferToSpprtGrp_Button= By.id("ReferToSpprtGrpBtnId");
   
  //// Add and Update Knowledge Base /////////  TestCase ID = 178426
    By KnowledgeBase = By.linkText("Knowledge Base");
    By Upload_Button = By.xpath("//input[@id='fileLoader']/following::button[1]");
   		   
/////////SGC Assigns the USR to SGP////
    By AssignSGP_Button = By.id("AsgnToSpprtGrpPersonnelBtnId"); 
    By AssignSGPDropdown = By.xpath("//button[@id='AsgnToSpprtGrpPersonnelBtnId']/following::select[2]");
    By Assign_Button = By.xpath("//button[@data-bind='click: OnSGPersonnelAssignClick']");
    
 ////// SGP handles USR ///////////
    By Reply_link = By.id("USRReplyClick");
    By ReplyMessage_Textbox = By.id("USRReplyMessage");
    By Reply_AttachFile =By.xpath("//textarea[@id='USRReplyMessage']/following::input[2]");
    By SubmitRepy_Button =By.xpath("//button[@data-bind='click:submitReply']");
    
  ///////Submit USR OnBehalf of User /////////////
    By SubmitUSRonBehalf = By.xpath("//a[text()='Knowledge Base']/following::a[1]");
    By Group_Dropdown =By.xpath("//label[text()='Group']//following::select[1]");
    By UserID_Dropdown = By.id("USErIDForNotification");
    By SubmitOnBehlf_USRSubject = By.id("USRSubject");
    
/////////CLosing USR by HDO//////
    By CloseButton= By.id("CloseUSRBtnId");
    
////////Reopening USR by HDO///////////
    By Reopen_Button= By.id("USRReopenBtnId");
    By Reopen_Save_Button = By.xpath("(//button[text()='Save'])[3]");
    
///OS-Submit feedback /////////////////////////
    By ViewORSubmit_Feedback = By.linkText("View/Submit Feedback");
    By WeekEndingSelect= By.xpath("//button[@id='ViewOrSubmitFeedbackBtnId']//preceding::select[1]");
    By ViewORSUbmit_Button= By.id("ViewOrSubmitFeedbackBtnId");
    By EnterFeedback_TextBox= By.id("OSFeedbackMessage");
    By OSFeedback_Save_Button = By.xpath("//button[@data-bind='click: onFeedbackSaveClick']");
    
///HDO-replies to OS feedback ////////
    By HDOReplyFeedback_Save = By.xpath("//button[@data-bind='click: onCommentSaveClick']");
    
    
    
  ///////////////////////////////////////////////////////////////////////////  
 /////////////////////// Methods for Submit USR ///////////////////////////// 
 ///////////////////////////////////////////////////////////////////////////   
  
	public void launchHD() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		FileInputStream fis = new FileInputStream("C:\\Users\\SShabana\\eclipse-workspace\\com.serbilis\\src\\test\\resources\\resources.config");
		prop= new Properties();
		prop.load(fis);
		driver.get(prop.getProperty("HelpDeskUrl"));
		driver.manage().window().maximize();
		driver.navigate().refresh();
//		driver.findElement(By.name("username")).sendKeys("SShabana");
//		driver.findElement(By.name("password")).sendKeys("S!Sh%1abana*");
//		driver.findElement(By.xpath("//button[text()='LOG IN']")).click();
	    }
	
	
	public void scrollDown() {
		js= (JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,500)");
	     }
	
	
	public void changeRoleToHelpDesk() throws SQLException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    driver.navigate().refresh();
	    String currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	    System.out.println("My current role is " + currentRole); 
		while (!currentRole.equalsIgnoreCase("Help Desk Officer")) {
	        connecting_DB_updatingroleID(2, 1157);
	        connecting_DB_updateGroupID(1,1157);
	        driver.navigate().refresh();
	        currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentRole); 
	            }
	}
	
	public void SubmitUSR_ClickingOnHelpDesk()   {
		wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(HelpDesk));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		//Thread.sleep(2000);
		//driver.findElement(HelpDesk).click();
		System.out.println("Clicked on Help Desk Menu");
		    }
	
	
	public void SubmitUSR_ClickingOnSubmitUSR() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(SubmitUSR));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		//driver.findElement(SubmitUSR).click();
        	}
	
	
	public void SubmitUSR_SelectCategeory(int categeoryindex) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement categeory =wait.until(ExpectedConditions.visibilityOfElementLocated(Categeory));
		sel = new Select(categeory);
		sel.selectByIndex(categeoryindex);
        	}
	
	
	public void SubmitUSR_SelectHardwareType(int hardwareIndex) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement hardwaretype =wait.until(ExpectedConditions.visibilityOfElementLocated(HardwareType));
		sel = new Select(hardwaretype);
		sel.selectByIndex(hardwareIndex);
			}
	
	
	public void SubmitUSR_EnterInputFields() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRSubject)).sendKeys("Test Automation by shabana");
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRDescription)).sendKeys("Test Automation by shabana");
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRTagNo)).sendKeys("7986543");
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRSerialNo)).sendKeys("674654");
		   }
	
	
	public void SubmitUSR_Attachment() throws AWTException, InterruptedException {
		driver.findElement(Attachment).click();
		robot = new Robot();
		StringSelection ss = new StringSelection("C:\\Users\\SShabana\\eclipse-workspace\\com.serbilis\\src\\test\\resources\\depositslip200.tiff");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		System.out.println("Attached uploaded successfully");
        	}
	
	
	public String SubmitUSR_ClickSubmit(){
		scrollDown();
		driver.findElement(USRSubmit).click();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		String ActualSuccessMsg =wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		String ExpectedSuccessMsg = "USR is submitted successfully.";
		System.out.println(ActualSuccessMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
		Assert.assertTrue(ActualSuccessMsg.contains(ExpectedSuccessMsg));
		System.out.println("USR Created Successfully");
		String USRNo=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-bind='text:usrId']"))).getText();
		System.out.println("USR is generated and USR No is "+USRNo);
		return USRNo;
        	}
	
	
	public void SubmitUSR_ClickCancel() throws InterruptedException{
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		scrollDown();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRCancel)).click();
		String ActualCancelMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationmsgID)).getText();
	    String ExpectedCancelMsg = "Are you sure you want to cancel?";
		System.out.println(ActualCancelMsg);
		Assert.assertTrue(ActualCancelMsg.contains(ExpectedCancelMsg));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationNO)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRCancel)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationYES)).click();
		System.out.println("USR Cancelled Successfully");
			}
	
	
//////////////////////////////////////////////////////////////////////	
//////////////////// Methods for Tag USR as Resolved /////////////////	
/////////////////////////////////////////////////////////////////////
	
	public void SearchUSR() {
		WebElement element=driver.findElement(Search_ViewUSR);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		     }
	   
	
	public void connecting_DB_updatingroleID(int role, int userid) throws SQLException {
		String JDBC_URL="jdbc:sqlserver://USTR-ERL2-0643\\PSAQA:1434;databaseName=CRS_HelpDesk;encrypt=true;trustServerCertificate=true";
		String JDBC_Username="PSATestLiveTeam";
		String JDBC_Password="PSATest#LiveTeam";
		Connection con = DriverManager.getConnection(JDBC_URL, JDBC_Username, JDBC_Password);
		System.out.println("Connected to the Database");
	   	String query = "update appgroups set RoleID=? where userid=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, role);
		stmt.setInt(2, userid);
		int rowsUpdated=stmt.executeUpdate();
		System.out.println("Number of rows updated " +rowsUpdated);
		System.out.println("Updated Role ID successfully" );
		   }
	
	   
	public void connecting_DB_updateGroupID(int groupID, int userid) throws SQLException {
	   String JDBC_URL="jdbc:sqlserver://USTR-ERL2-0643\\PSAQA:1434;databaseName=CRS_HelpDesk;encrypt=true;trustServerCertificate=true";
	   String JDBC_Username="PSATestLiveTeam";
	   String JDBC_Password="PSATest#LiveTeam";
	   Connection con = DriverManager.getConnection(JDBC_URL, JDBC_Username, JDBC_Password);
	   System.out.println("Connected to the Database");
   	   String query = "update appgroups set GroupID=? where userid=?";
	   PreparedStatement stmt = con.prepareStatement(query);
	   stmt.setInt(1, groupID);
	   stmt.setInt(2, userid);
	   int rowsUpdated=stmt.executeUpdate();
	   System.out.println("Number of rows updated "+rowsUpdated);
	   System.out.println("Updated Group ID successfully" );
		  }
	   
	   
	public String submitUSR() throws IOException, SQLException {
		launchHD();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    driver.navigate().refresh();
	    String currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	    System.out.println("My current role is " + currentRole); 
		    
		    while (!currentRole.equalsIgnoreCase("Help Desk Officer")) {
		        connecting_DB_updatingroleID(2, 1157);
		        connecting_DB_updateGroupID(1,1157);
		        driver.navigate().refresh();
		        currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
		        System.out.println("My current role is " + currentRole); 
		            }
		    
	    SubmitUSR_ClickingOnHelpDesk();
        SubmitUSR_ClickingOnSubmitUSR();
	    SubmitUSR_SelectCategeory(1);
	    SubmitUSR_SelectHardwareType(1);
	    SubmitUSR_EnterInputFields();
	    String USRNO = SubmitUSR_ClickSubmit();
	    return USRNO;
	     	}
	   
	
	   //Referring to Support group makes USR status as pending
	public void refferingUSRToSupportGroup(String USRNO) throws SQLException, InterruptedException {
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	   driver.navigate().refresh();
	   wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   String currentrole= wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	   System.out.println("My current role is "+ currentrole); 
	  
	   while (!currentrole.equalsIgnoreCase("Help Desk Officer")) {
	        connecting_DB_updatingroleID(2, 1157);
	        connecting_DB_updateGroupID(1,1157);
	        driver.navigate().refresh();
	        currentrole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentrole); 
	            }
	
	   SubmitUSR_ClickingOnHelpDesk();
	   SearchUSR();
	   System.out.println("USR No is "+USRNO);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(USRID_feild)).sendKeys(USRNO);
	   Thread.sleep(1000);
	   driver.findElement(SearchwithUSRID).click();
	   Thread.sleep(1000);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(USRIDlink)).click();
	   scrollDown();
	   Thread.sleep(1000);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(ReferToSpprtGrp_Button)).click();
 	   scrollDown();
 	   Thread.sleep(1000);
 	   WebElement ReferSelect= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='ReferToSpprtGrpBtnId']/following::select[1]")));
	   sel= new Select(ReferSelect);
	   sel.selectByVisibleText("CRS HELP DESK");
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-bind='click: OnSupprtGrpAssignClick']"))).click();
	   wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
	   System.out.println("USR is reffered Successfully"); 
			 }
	
		   
	public void SGCResolvesUSR(String USRNO) throws SQLException, InterruptedException {
	   driver.navigate().refresh();
	   wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   String currentrole= wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	   System.out.println("My current role is "+ currentrole);
	   System.out.println("User ID is "+ USRNO);
	   
	   while (!currentrole.equalsIgnoreCase("Support Group Coordinator")) {
	        connecting_DB_updatingroleID(3, 1157);
	        connecting_DB_updateGroupID(1,1157);
	        driver.navigate().refresh();
	        currentrole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentrole); 
	            }
	   
	   SubmitUSR_ClickingOnHelpDesk();
	   SearchUSR();
	   driver.findElement(USRID_feild).sendKeys(USRNO);
	   Thread.sleep(1000);
	   driver.findElement(SearchwithUSRID).click();
	   Thread.sleep(1000);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(USRIDlink)).click();
	   scrollDown();
	   Thread.sleep(1000);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(MarkResolved_Button)).click();
	   wait.until(ExpectedConditions.visibilityOfElementLocated(USRRemarks_Textbox)).sendKeys("Testing SGC Resolving the USR through Automation By Shabana");
	   scrollDown();
	   Thread.sleep(1000);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(USRResolve_Save_Button)).click();
	   String ActualResolvecnfmnMsg= wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationmsgID)).getText();
	   String ExpectedResolvecnfmnMsg = "Are you sure you want to resolve USR?";
	   Assert.assertEquals(ActualResolvecnfmnMsg, ExpectedResolvecnfmnMsg);
	   System.out.println(ActualResolvecnfmnMsg);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationYES)).click();
	   String ActualResolvedMsg= wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
	   String ExpectedResolvedMsg ="USR resolved";
	   System.out.println(ActualResolvedMsg);
	   Assert.assertEquals(ActualResolvedMsg, ExpectedResolvedMsg);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
	   	   }
	   
	   
//////////////////////////////////////////////////////////////////////
//////////HelpDesk Officer returning pending USR to HelpDesk//////////
/////////////////////////////////////////////////////////////////////    
			   
	public void HDORetuningUSRToHD(String USRNO) throws SQLException, InterruptedException {
	   driver.navigate().refresh();
	   wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   String currentrole= wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	   System.out.println("My current role is "+ currentrole);
	   System.out.println("User ID is "+ USRNO);
	   
	   while (!currentrole.equalsIgnoreCase("Help Desk Officer")) {
	        connecting_DB_updatingroleID(2, 1157);
	        connecting_DB_updateGroupID(1,1157);
	        driver.navigate().refresh();
	        currentrole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentrole); 
	            }
	   
  	   SubmitUSR_ClickingOnHelpDesk();
	   SearchUSR();
	   wait.until(ExpectedConditions.visibilityOfElementLocated(USRID_feild)).sendKeys(USRNO);
	   Thread.sleep(1000);
	   driver.findElement(SearchwithUSRID).click();
	   Thread.sleep(1000);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(USRIDlink)).click();	   
	   scrollDown();
	   Thread.sleep(1000);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(ReturntoHelpDesk_Button)).click();
	   String ActualRetruncnfmnMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationmsgID)).getText();
	   String ExpectedReturncnfmnMsg = "Are you sure you want to return the USR to Help Desk?";
	   Assert.assertEquals(ActualRetruncnfmnMsg, ExpectedReturncnfmnMsg);
	   System.out.println(ActualRetruncnfmnMsg);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationYES)).click();
	   String ExpectedReturnedMsg ="USR Returned to Help Desk";
	   String ActualReturnedMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
	   Assert.assertEquals(ActualReturnedMsg, ExpectedReturnedMsg);
	   System.out.println(ActualReturnedMsg);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
	   String StatusofUSR=wait.until(ExpectedConditions.visibilityOfElementLocated(USRSearchStatus)).getAttribute("value");
	   System.out.println("The status of the USR is "+ StatusofUSR);
	   	   }
	   
	   
//////////////////////////////////////////////////////////////////////
////////////////////Add and Update Knowledge Base ////////////////////	
///////////////////////////////////////////////////////////////////// 
	   
	public void UploadKnowledgeBase() throws IOException, SQLException, AWTException, InterruptedException {
	   launchHD();
	   wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   driver.navigate().refresh();
	   String currentrole=wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	   System.out.println("My current role is "+ currentrole);
	   
	   while (!currentrole.equalsIgnoreCase("System Administrator")) {
	        connecting_DB_updatingroleID(1, 1157);
	        connecting_DB_updateGroupID(1,1157);
	        driver.navigate().refresh();
	        currentrole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentrole); 
	            }
	   
       SubmitUSR_ClickingOnHelpDesk();
       Thread.sleep(1000);
       driver.findElement(KnowledgeBase).click();
       Thread.sleep(2000);
       driver.findElement(Upload_Button).click();
	   robot = new Robot();
	   StringSelection ss = new StringSelection("C:\\Users\\SShabana\\eclipse-workspace\\com.serbilis\\src\\test\\resources\\HD Knowledge Base.pdf");
	   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	   Thread.sleep(2000);
	   robot.keyPress(KeyEvent.VK_CONTROL);
	   robot.keyPress(KeyEvent.VK_V);
	   robot.keyRelease(KeyEvent.VK_CONTROL);
	   robot.keyRelease(KeyEvent.VK_V);
	   Thread.sleep(2000);
	   robot.keyPress(KeyEvent.VK_ENTER);
	   robot.keyRelease(KeyEvent.VK_ENTER);
	   String ActualUploadedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
	   String ExpectedUploadedMsg ="File uploaded successfully.";
	   Assert.assertEquals(ActualUploadedMsg, ExpectedUploadedMsg);
	   System.out.println(ActualUploadedMsg);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
	   	   }
	   
	   
//////////////////////////////////////////////////////////////////////
////////////////////SGP_Handle USR ////////////////////	 Testcase ID = 178558
///////////////////////////////////////////////////////////////////// 
   
	public void SGCAssigningUSRToSGP(String USRNO) throws SQLException, InterruptedException {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String currentrole=wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
		System.out.println("My current role is "+ currentrole);
		System.out.println("User ID is "+ USRNO);
		
		while (!currentrole.equalsIgnoreCase("Support Group Coordinator")) {
	        connecting_DB_updatingroleID(3, 1157);
	        connecting_DB_updateGroupID(1,1157);
	        driver.navigate().refresh();
	        currentrole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentrole); 
	            }
		
		SubmitUSR_ClickingOnHelpDesk();
		SearchUSR();
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRID_feild)).sendKeys(USRNO);
		Thread.sleep(1000);
		driver.findElement(SearchwithUSRID).click();
		Thread.sleep(1000);
		driver.findElement(USRIDlink).click();
		scrollDown();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(AssignSGP_Button)).click();
		scrollDown();
		Thread.sleep(1000);
		WebElement selectSGP=wait.until(ExpectedConditions.visibilityOfElementLocated(AssignSGPDropdown));
		sel= new Select(selectSGP);
		sel.selectByValue("1157");
		wait.until(ExpectedConditions.visibilityOfElementLocated(Assign_Button)).click();
		String ActualAssignedMsg =wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		String ExpectedAssignedMsg ="USR assigned to Support Group Personnel";
		Assert.assertEquals(ActualAssignedMsg, ExpectedAssignedMsg);
		System.out.println(ActualAssignedMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
		System.out.println("SGC assigned USR to SGP successfully");
		   }
	   
	
	public void SGPHandlesUSR(String USRNO) throws IOException, SQLException, AWTException, InterruptedException {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String currentrole=wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
		System.out.println("My current role is "+ currentrole);
		System.out.println("User ID is "+ USRNO);
		
		while (!currentrole.equalsIgnoreCase("Support Group Personnel")) {
	        connecting_DB_updatingroleID(4, 1157);
	        connecting_DB_updateGroupID(1,1157);
	        driver.navigate().refresh();
	        currentrole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentrole); 
	            }
		
		System.out.println("Entered in SGPHandling testcase");
		SubmitUSR_ClickingOnHelpDesk();
		SearchUSR();
		driver.findElement(USRID_feild).sendKeys(USRNO);
		Thread.sleep(1000);
		driver.findElement(SearchwithUSRID).click();
		Thread.sleep(1000);
		driver.findElement(USRIDlink).click();
		scrollDown();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Reply_link)).click();
		scrollDown();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ReplyMessage_Textbox)).sendKeys("Replying through automation script by Shabana as SGP");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SubmitRepy_Button)).click();
		String ActualRepliedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		String ExpectedRepliedMsg ="Response saved successfully.";
		Assert.assertEquals(ActualRepliedMsg, ExpectedRepliedMsg);
		System.out.println(ActualRepliedMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(MarkResolved_Button)).click();
		scrollDown();
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRRemarks_Textbox)).sendKeys("Testing SGP Resolving the USR through Automation By Shabana");
		scrollDown();
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRResolve_Save_Button)).click();
		String ActualResolvecnfmnMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationmsgID)).getText();
		String ExpectedResolvecnfmnMsg = "Are you sure you want to resolve USR?";
		Assert.assertEquals(ActualResolvecnfmnMsg, ExpectedResolvecnfmnMsg);
		System.out.println(ActualResolvecnfmnMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationYES)).click();
		String ExpectedResolvedMsg ="USR resolved";
		String ActualResolvedMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		Assert.assertEquals(ActualResolvedMsg, ExpectedResolvedMsg);
		System.out.println(ActualResolvedMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
		   }
	   
     
	   
//////////////////////////////////////////////////////////////////////
/////////////Submit USR on-behalf of user ////////////////////	 Testcase ID = 178764
///////////////////////////////////////////////////////////////////// 
	   
	public void clickingOnSubmitUSROnBehalfOfUser() {
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(SubmitUSRonBehalf)).click();
		   }
	
	
	public void selectGroup() {
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement Group=wait.until(ExpectedConditions.visibilityOfElementLocated(Group_Dropdown));
		sel= new Select(Group);
		sel.selectByValue("2");
		System.out.println("Group is selected");
		   }
	
	
	public void selectUserID() {
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement UserID=wait.until(ExpectedConditions.visibilityOfElementLocated(UserID_Dropdown));
		sel= new Select(UserID);
		sel.selectByValue("1180");
		System.out.println("User ID selected");
	       }	   
	   
	   
	public String submitUSROnBehalfOfUser() throws IOException, SQLException, AWTException, InterruptedException {
		launchHD();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().refresh();
		String currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
		System.out.println("My current role is " + currentRole); 
			
		while (!currentRole.equalsIgnoreCase("Help Desk Officer")) {
		        connecting_DB_updatingroleID(2, 1157);
		        connecting_DB_updateGroupID(1,1157);
		        driver.navigate().refresh();
		        currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
		        System.out.println("My current role is " + currentRole); 
		        connecting_DB_updateGroupID(2,1180);
		        System.out.println("Updated Stefy's groupID to Application Development"); 
		        }
		    
		SubmitUSR_ClickingOnHelpDesk();
		clickingOnSubmitUSROnBehalfOfUser();
		selectGroup();
		selectUserID();
		SubmitUSR_SelectCategeory(2);
		SubmitUSR_SelectHardwareType(2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SubmitOnBehlf_USRSubject)).sendKeys("Submitting USR by Shabana");
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRDescription)).sendKeys("Submitting USR by Shabana");
		SubmitUSR_Attachment();
		String USRNO = SubmitUSR_ClickSubmit();
		System.out.println("USR No for the User is "+USRNO);   	    
		return USRNO;
		    }
	

//////////////////////////////////////////////////////////////////////
/////////////Verify Attach in the reply ////////////////////	 Testcase ID = 253792
///////////////////////////////////////////////////////////////////// 
	   
	public void launch_SelectOutletPersonnel() throws SQLException, IOException{
		launchHD();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().refresh();
		String currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
		System.out.println("My current role is " + currentRole); 
		
		while (!currentRole.equalsIgnoreCase("Outlet Personnel")) {
			connecting_DB_updatingroleID(6, 1157);
		    driver.navigate().refresh();
		    currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
		    System.out.println("My current role is " + currentRole); 
		    // connecting_DB_updateGroupID(2,1180);
		    System.out.println("Updated role ID to Outlet Personnel"); 
		        }
		   }
	
	
	public void searchWithUSR(String USRNO) throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRID_feild)).sendKeys(USRNO);
		Thread.sleep(1000);
		driver.findElement(SearchwithUSRID).click();
			}
	
	   
	public void clickOnSearchedUSR() throws InterruptedException {
		
		Thread.sleep(1000);
		driver.findElement(USRIDlink).click();
		scrollDown();
		   	}
	
		
	public void replyUSRWithAttachment() throws AWTException, InterruptedException {
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		Thread.sleep(1000);
		driver.findElement(Reply_link).click();
		scrollDown();
		Thread.sleep(1000);
		WebElement ReplyMessageTextBox=wait.until(ExpectedConditions.visibilityOfElementLocated(ReplyMessage_Textbox));
		ReplyMessageTextBox.sendKeys("Testing reply USR with attachment in automation by shabana");
		ReplyMessageTextBox.sendKeys(Keys.TAB);
//      wb = new WebDriverWait(driver,Duration.ofSeconds(10));
//	    WebElement AttachmentButton= wb.until(ExpectedConditions.elementToBeClickable(Reply_AttachFile));
		scrollDown();
		Thread.sleep(2000);
		WebElement element=driver.findElement(Reply_AttachFile);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);	
		Thread.sleep(1000);
		robot = new Robot();
		StringSelection ss = new StringSelection("C:\\Users\\SShabana\\eclipse-workspace\\com.serbilis\\src\\test\\resources\\depositslip200.tiff");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SubmitRepy_Button)).click();
		   }
	
	   
	public void verifyReplySuccesspopup()  {
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		String ActualRepliedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		String ExpectedRepliedMsg ="Response saved successfully.";
		Assert.assertEquals(ActualRepliedMsg, ExpectedRepliedMsg);
		System.out.println(ActualRepliedMsg);
		   }

	
//////////////////////////////////////////////////////////////////////
//////Verify the button(Reopen)when a HDO selects a USR with status 'Closed' ////////////////////	 Testcase ID = 253783
///////////////////////////////////////////////////////////////////// 
	   
	public void markResolvedByHDO() throws InterruptedException {
		scrollDown();
		Thread.sleep(1000);
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(MarkResolved_Button)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRRemarks_Textbox)).sendKeys("Testing HDO Resolving the USR through Automation By Shabana");
		scrollDown();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRResolve_Save_Button)).click();
		String ExpectedResolvecnfmnMsg = "Are you sure you want to resolve USR?";
		String ActualResolvecnfmnMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationmsgID)).getText();
		Assert.assertEquals(ActualResolvecnfmnMsg, ExpectedResolvecnfmnMsg);
		System.out.println(ActualResolvecnfmnMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationYES)).click();
		String ActualResolvedMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		String ExpectedResolvedMsg ="USR resolved";
		Assert.assertEquals(ActualResolvedMsg, ExpectedResolvedMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
	        }
	   
	   
	public void closeUSRByHDO() throws InterruptedException  {
		scrollDown();
		Thread.sleep(1000);
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CloseButton)).click();
		String ActualResolvecnfmnMsg =wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationmsgID)).getText();
		String ExpectedResolvecnfmnMsg = "Are you sure you want to close the USR?";
		Assert.assertEquals(ActualResolvecnfmnMsg, ExpectedResolvecnfmnMsg);
		System.out.println(ActualResolvecnfmnMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationYES)).click();
		String ExpectedClosedMsg= "USR Closed.";
		String ActualClosedMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		Assert.assertEquals(ActualClosedMsg, ExpectedClosedMsg);
		System.out.println(ActualClosedMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
			}
	
	
	public void reopenUSRByHDO() throws InterruptedException  {
		wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		Thread.sleep(1000);
		driver.findElement(Reopen_Button).click();
		scrollDown();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(USRRemarks_Textbox)).sendKeys("Testing HDO Reopening the USR through Automation By Shabana");
		scrollDown();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Reopen_Save_Button)).click();
		String ExpectedReopencnfmnMsg = "Are you sure you want to reopen the USR?";
		String ActualReopencnfmnMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationmsgID)).getText();
		Assert.assertEquals(ActualReopencnfmnMsg, ExpectedReopencnfmnMsg);
		System.out.println(ActualReopencnfmnMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmationYES)).click();
		String ExpectedReopenedMsg ="USR reopened.";
		String ActualReopenedMsg= wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		Assert.assertEquals(ActualReopenedMsg, ExpectedReopenedMsg);
		System.out.println(ActualReopenedMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
	        }
	   
	   
//////////////////////////////////////////////////////////////////////
//Verify if OS can enter feedback response multiple times in the ongoing week///////	 Testcase ID = 187452
///////////////////////////////////////////////////////////////////// 	   
	  
	public String OS_SubmitFeedback() throws SQLException, IOException, InterruptedException {
		launchHD();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    driver.navigate().refresh();
	    String currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	    System.out.println("My current role is " + currentRole); 
	    
	    while (!currentRole.equalsIgnoreCase("Outlet Supervisor")) {
	        connecting_DB_updatingroleID(5, 1157);
	        connecting_DB_updateGroupID(2,1157);
	        driver.navigate().refresh();
	        currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentRole); 
	           }
	    
	    SubmitUSR_ClickingOnHelpDesk();
	    Thread.sleep(1000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(ViewORSubmit_Feedback)).click();
	    WebElement weekEndingSelect=wait.until(ExpectedConditions.visibilityOfElementLocated(WeekEndingSelect));
	    sel= new Select(weekEndingSelect);
	    sel.selectByIndex(1);
	    Thread.sleep(1000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(ViewORSUbmit_Button)).click();
	    String Feedback="Entering feedback to the HDO from Outlet Supervisor through Automation By shabana";
	    wait.until(ExpectedConditions.visibilityOfElementLocated(EnterFeedback_TextBox)).sendKeys(Feedback);
	    Thread.sleep(1000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(OSFeedback_Save_Button)).click();
	    String ExpectedFeedbackSuccessMsg = "Feedback saved successfully";
	    String ActualFeedbackSuccessMsg= wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
	    Assert.assertEquals(ActualFeedbackSuccessMsg,ExpectedFeedbackSuccessMsg);
	    System.out.println(ActualFeedbackSuccessMsg);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
	    return Feedback;
	       }

	  
	public void updatingRoleTOHD() throws IOException, SQLException {
		launchHD();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    driver.navigate().refresh();
	    String currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	    System.out.println("My current role is " + currentRole); 
	    
	    while (!currentRole.equalsIgnoreCase("Help Desk Officer")) {
	        connecting_DB_updatingroleID(2, 1157);
	        connecting_DB_updateGroupID(1,1157);
	        driver.navigate().refresh();
	        currentRole = wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole)).getText();
	        System.out.println("My current role is " + currentRole); 
	           }
	    }
	public void HDOReplies_OSFeedback(String Feedback) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(ViewORSubmit_Feedback)).click();
	    WebElement weekEndingSelect=wait.until(ExpectedConditions.visibilityOfElementLocated(WeekEndingSelect));
	    sel= new Select(weekEndingSelect);
	    sel.selectByIndex(1);
	    Thread.sleep(1000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(ViewORSUbmit_Button)).click();
	    String Feedback_Text =driver.findElement(By.xpath("//table[@id='OSFeedbackTable']/tbody/tr[1]/td[3]")).getText();
	    String[] Actual_Feedback =Feedback_Text.split(":");
	    Assert.assertEquals(Actual_Feedback[2].trim(),Feedback);
	    System.out.println("OS feedback found and HDO replying on feedback");
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//table[@id='OSFeedbackTable']/tbody/tr[1]/td[4]/button")).click();
	    scrollDown();
	    driver.findElement(By.xpath("//div[@id='HDOAddCommentSection']/button")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//div[@id='HDOCommentSection']//textarea")).sendKeys("HDO replying on OS feedback through Automation by Shabana");
	    Thread.sleep(1000);
	    scrollDown();
	    driver.findElement(HDOReplyFeedback_Save).click();
	    String ActualSuccessMsg =wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		String ExpectedSuccessMsg = "Comment saved successfully";
		System.out.println(ActualSuccessMsg);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessPopup_OK)).click();
		Assert.assertTrue(ActualSuccessMsg.contains(ExpectedSuccessMsg));
		   }
}

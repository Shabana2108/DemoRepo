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
    By CancelmsgID = By.id("popUpDetailsCallBack");
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
    By Reply_AttachFile =By.xpath("//input[@id='usrReplyAttchfiles']");
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
	
	public void SubmitUSR_ClickingOnHelpDesk() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(3000);
		driver.findElement(HelpDesk).click();
		//driver.findElement(HomePage).click();
		System.out.println("Clicked on Help Desk Menu");
	}
	public void SubmitUSR_ClickingOnSubmitUSR() throws InterruptedException{
		driver.findElement(SubmitUSR).click();
	}
	public void SubmitUSR_SelectCategeory(int categeoryindex) throws InterruptedException{
		WebElement categeory =driver.findElement(Categeory);
		sel = new Select(categeory);
		sel.selectByIndex(categeoryindex);
	}
	public void SubmitUSR_SelectHardwareType(int hardwareIndex) throws InterruptedException{
		WebElement hardwaretype=driver.findElement(HardwareType);
		sel = new Select(hardwaretype);
		sel.selectByIndex(hardwareIndex);
		Thread.sleep(1000);
	}
	public void SubmitUSR_EnterInputFields() throws InterruptedException{
		driver.findElement(USRSubject).sendKeys("Test Automation by shabana");
		driver.findElement(USRDescription).sendKeys("Test Automation by shabana");
		driver.findElement(USRTagNo).sendKeys("7986543");
		driver.findElement(USRSerialNo).sendKeys("674654");
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
	
	public String SubmitUSR_ClickSubmit() throws InterruptedException{
		scrollDown();
		driver.findElement(USRSubmit).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement ActualMsg =wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID));
		String ExpectedSuccessMsg = "USR is submitted successfully.";
		String ActualSuccessMsg=ActualMsg.getText();
		System.out.println(ActualSuccessMsg);
		driver.findElement(SuccessPopup_OK).click();
		Assert.assertTrue(ActualSuccessMsg.contains(ExpectedSuccessMsg));
		System.out.println("USR Created Successfully");
		Thread.sleep(2000);
		String USRNo=driver.findElement(By.xpath("//span[@data-bind='text:usrId']")).getText();
		System.out.println("USR is generated and USR No is "+USRNo);
		return USRNo;
	}
	
	public void SubmitUSR_ClickCancel() throws InterruptedException{
		String ExpectedCancelMsg = "Are you sure you want to cancel?";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		scrollDown();
	    Thread.sleep(1000);
	    driver.findElement(USRCancel).click();
		Thread.sleep(2000);
		String ActualCancelMsg = driver.findElement(CancelmsgID).getText();
		System.out.println(ActualCancelMsg);
		Assert.assertTrue(ActualCancelMsg.contains(ExpectedCancelMsg));
		driver.findElement(ConfirmationNO).click();
		Thread.sleep(1000);
		driver.findElement(USRCancel).click();
		Thread.sleep(1000);
		driver.findElement(ConfirmationYES).click();
		System.out.println("USR Cancelled Successfully");
		
	}
	
//////////////////////////////////////////////////////////////////////	
//////////////////// Methods for Tag USR as Resolved /////////////////	
/////////////////////////////////////////////////////////////////////
	
	
	   public void SearchUSR() {
		   driver.findElement(Search_ViewUSR).click();
	   }
	
	   public void connecting_DB_updatingroleID(int role, int userid) throws SQLException {
		   String JDBC_URL="jdbc:sqlserver://USTR-ERL2-0643\\PSAQA:1434;databaseName=CRS_HelpDesk;encrypt=true;trustServerCertificate=true";
		   String JDBC_Username="PSATestLiveTeam";
		   String JDBC_Password="PSATest#LiveTeam";
		   Connection con = DriverManager.getConnection(JDBC_URL, JDBC_Username, JDBC_Password);
		   System.out.println("Connected to DB");
	   	   String query = "update appgroups set RoleID=? where userid=?";
		   PreparedStatement stmt = con.prepareStatement(query);
		   stmt.setInt(1, role);
		   stmt.setInt(2, userid);
		   int rowsUpdated=stmt.executeUpdate();
		   System.out.println(rowsUpdated);
		   System.out.println("updated" );
		   
	   }
	   
	   public void connecting_DB_updateGroupID(int groupID, int userid) throws SQLException {
		   String JDBC_URL="jdbc:sqlserver://USTR-ERL2-0643\\PSAQA:1434;databaseName=CRS_HelpDesk;encrypt=true;trustServerCertificate=true";
		   String JDBC_Username="PSATestLiveTeam";
		   String JDBC_Password="PSATest#LiveTeam";
		   Connection con = DriverManager.getConnection(JDBC_URL, JDBC_Username, JDBC_Password);
		   System.out.println("Connected to DB");
	   	   String query = "update appgroups set GroupID=? where userid=?";
		   PreparedStatement stmt = con.prepareStatement(query);
		   stmt.setInt(1, groupID);
		   stmt.setInt(2, userid);
		   int rowsUpdated=stmt.executeUpdate();
		   System.out.println(rowsUpdated);
		}
	   
	   
	   public String submitUSR() throws InterruptedException, IOException, SQLException {
		    launchHD();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    driver.navigate().refresh();
		    WebElement roleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userdisplayrolecode")));
		    String currentRole = roleElement.getText();
		    System.out.println("My current role is " + currentRole); 
		    
		    while (!currentRole.equalsIgnoreCase("Help Desk Officer")) {
		        connecting_DB_updatingroleID(2, 1157);
		        
		        // Refresh and re-check the role
		        driver.navigate().refresh();
		        roleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userdisplayrolecode")));
		        currentRole = roleElement.getText();
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
		   driver.navigate().refresh();
		   String currentrole= driver.findElement(By.id("userdisplayrolecode")).getText();
		   System.out.println("My current role is "+ currentrole); 
		   Thread.sleep(1000);
		   if(!currentrole.equalsIgnoreCase("Help Desk Officer")) {
	    	   connecting_DB_updatingroleID(2,1157);
	    	   refferingUSRToSupportGroup(USRNO);
			   
			   }
		   else {
			   SubmitUSR_ClickingOnHelpDesk();
			   SearchUSR();
			   System.out.println("USR No is "+USRNO);
			   driver.findElement(USRID_feild).sendKeys(USRNO);
			   Thread.sleep(3000);
			   driver.findElement(SearchwithUSRID).click();
			   Thread.sleep(1000);
			   driver.findElement(USRIDlink).click();			   
			   scrollDown();
			   Thread.sleep(1000);
			   driver.findElement(By.id("ReferToSpprtGrpBtnId")).click();
			   scrollDown();
			   Thread.sleep(1000);
			   WebElement ReferSelect=driver.findElement(By.xpath("//button[@id='ReferToSpprtGrpBtnId']/following::select[1]"));
			   sel= new Select(ReferSelect);
			   sel.selectByVisibleText("CRS HELP DESK");
			   driver.findElement(By.xpath("//button[@data-bind='click: OnSupprtGrpAssignClick']")).click();
			   Thread.sleep(1000);
			   driver.findElement(SuccessPopup_OK).click();
			   System.out.println("USR is reffered Successfully"); 
			   			   
		   }
		   
	   }
	   
	   /*not required
	   public String connecting_DB_getUSRID() throws SQLException, InterruptedException, IOException {
		   String USRNO=submitUSR();
		   String JDBC_URL="jdbc:sqlserver://USTR-ERL2-0643\\PSAQA:1434;databaseName=CRS_HelpDesk;encrypt=true;trustServerCertificate=true";
		   String JDBC_Username="PSATestLiveTeam";
		   String JDBC_Password="PSATest#LiveTeam";
		   Connection con = DriverManager.getConnection(JDBC_URL, JDBC_Username, JDBC_Password);
		   System.out.println("Connected to DB");
		   Statement stmt=con.createStatement();
		   String query = "select * from dbo.USR where USRStatus='P'and ReferredToGroupID=1 order by USRID desc;";
		   ResultSet rs= stmt.executeQuery(query);
		   while(rs.next()) {
			  String USR= rs.getString("USRID");
			  System.out.println(USR);
			  return USR;
		   }
		   return "";
	   }*/
	   
	   
	   public void SGCResolvesUSR(String USRNO) throws SQLException, InterruptedException {
		   driver.navigate().refresh();
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   String currentrole= driver.findElement(By.id("userdisplayrolecode")).getText();
		   System.out.println("My current role is "+ currentrole);
		   System.out.println("User ID is "+ USRNO);
		   Thread.sleep(2000);
		   if(!currentrole.equalsIgnoreCase("Support Group Coordinator")) {
			   connecting_DB_updatingroleID(3,1157);
			   SGCResolvesUSR(USRNO);
			   }
		   else {
			   SubmitUSR_ClickingOnHelpDesk();
			   SearchUSR();
			   driver.findElement(USRID_feild).sendKeys(USRNO);
			   Thread.sleep(1000);
			   driver.findElement(SearchwithUSRID).click();
			   Thread.sleep(1000);
			   driver.findElement(USRIDlink).click();			   
			   scrollDown();
			   Thread.sleep(1000);
			   driver.findElement(MarkResolved_Button).click();	
			   Thread.sleep(1000);
			   driver.findElement(USRRemarks_Textbox).sendKeys("Testing SGC Resolving the USR through Automation By Shabana");
			   scrollDown();
			   Thread.sleep(1000);
			   driver.findElement(USRResolve_Save_Button).click();	
			   Thread.sleep(1000);
			   String ExpectedResolvecnfmnMsg = "Are you sure you want to resolve USR?";
			   String ActualResolvecnfmnMsg= driver.findElement(By.id("popUpDetailsCallBack")).getText();
			   Assert.assertEquals(ActualResolvecnfmnMsg, ExpectedResolvecnfmnMsg);
			   System.out.println("Confirmation message is as expected");
			   driver.findElement(ConfirmationYES).click();
			   Thread.sleep(1000);
			   String ExpectedResolvedMsg ="USR resolved";
			   String ActualResolvedMsg=driver.findElement(By.id("popUpForAlertText")).getText();
			   Assert.assertEquals(ActualResolvedMsg, ExpectedResolvedMsg);
			   Thread.sleep(1000);
			   driver.findElement(SuccessPopup_OK).click();
			   System.out.println("Testcase passed");
			   }
	   }
	   
	   
//////////////////////////////////////////////////////////////////////
//////////HelpDesk Officer returning pending USR to HelpDesk//////////
/////////////////////////////////////////////////////////////////////    
			   
	   public void HDORetuningUSRToHD(String USRNO) throws SQLException, InterruptedException {
		   driver.navigate().refresh();
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   String currentrole= driver.findElement(By.id("userdisplayrolecode")).getText();
		   System.out.println("My current role is "+ currentrole);
		   System.out.println("User ID is "+ USRNO);
		   Thread.sleep(2000);
		   if(!currentrole.equalsIgnoreCase("Help Desk Officer")) {
			   connecting_DB_updatingroleID(2,1157);
			   HDORetuningUSRToHD(USRNO);
			   }
		   else {
			   SubmitUSR_ClickingOnHelpDesk();
			   SearchUSR();
			   driver.findElement(USRID_feild).sendKeys(USRNO);
			   Thread.sleep(1000);
			   driver.findElement(SearchwithUSRID).click();
			   Thread.sleep(1000);
			   driver.findElement(USRIDlink).click();			   
			   scrollDown();
			   Thread.sleep(1000);
			   driver.findElement(ReturntoHelpDesk_Button).click();	
			   Thread.sleep(1000);
			   String ExpectedReturncnfmnMsg = "Are you sure you want to return the USR to Help Desk?";
			   String ActualRetruncnfmnMsg= driver.findElement(By.id("popUpDetailsCallBack")).getText();
			   Assert.assertEquals(ActualRetruncnfmnMsg, ExpectedReturncnfmnMsg);
			   System.out.println("Confirmation message is as expected");
			   driver.findElement(ConfirmationYES).click();
			   Thread.sleep(1000);
			   String ExpectedReturnedMsg ="USR Returned to Help Desk";
			   String ActualReturnedMsg=driver.findElement(By.id("popUpForAlertText")).getText();
			   Assert.assertEquals(ActualReturnedMsg, ExpectedReturnedMsg);
			   Thread.sleep(1000);
			   driver.findElement(SuccessPopup_OK).click();
			   Thread.sleep(1000);
			   String StatusofUSR=driver.findElement(USRSearchStatus).getAttribute("value");
			   System.out.println("The status of the USR is "+ StatusofUSR);
			   System.out.println("Testcase passed");
			   }
	   }
	   
//////////////////////////////////////////////////////////////////////
////////////////////Add and Update Knowledge Base ////////////////////	
///////////////////////////////////////////////////////////////////// 
	   
	   public void UploadKnowledgeBase() throws IOException, InterruptedException, SQLException, AWTException {
		   launchHD();
		   Thread.sleep(1000);
		   driver.navigate().refresh();
		   Thread.sleep(1000);
		   String currentrole= driver.findElement(By.id("userdisplayrolecode")).getText();
		   System.out.println("My current role is "+ currentrole);
		   Thread.sleep(2000);
		   if(!currentrole.equalsIgnoreCase("System Administrator")) {
			   connecting_DB_updatingroleID(1,1157);
			   UploadKnowledgeBase();
			   }
		   else {
			   SubmitUSR_ClickingOnHelpDesk();
			   driver.findElement(KnowledgeBase).click();
			   Thread.sleep(1000);
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
			   Thread.sleep(2000);
			   String ExpectedUploadedMsg ="File uploaded successfully.";
			   String ActualUploadedMsg =driver.findElement(SuccessmsgID).getText();
			   Assert.assertEquals(ActualUploadedMsg, ExpectedUploadedMsg);
			   Thread.sleep(1000);
			   System.out.println("File Uploaded successfully");
			   driver.findElement(SuccessPopup_OK).click();
			   Thread.sleep(1000);
			   System.out.println("Testcase passed");
			     	   
		   }
	   }
	   
//////////////////////////////////////////////////////////////////////
////////////////////SGP_Handle USR ////////////////////	 Testcase ID = 178558
///////////////////////////////////////////////////////////////////// 
   
	   
	   public void SGCAssigningUSRToSGP(String USRNO) throws SQLException, InterruptedException {
		   driver.navigate().refresh();
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   String currentrole= driver.findElement(By.id("userdisplayrolecode")).getText();
		   System.out.println("My current role is "+ currentrole);
		   System.out.println("User ID is "+ USRNO);
		   Thread.sleep(2000);
		   if(!currentrole.equalsIgnoreCase("Support Group Coordinator")) {
			   connecting_DB_updatingroleID(3,1157);
			   SGCAssigningUSRToSGP(USRNO);
			   }
		   else {
			   SubmitUSR_ClickingOnHelpDesk();
			   SearchUSR();
			   driver.findElement(USRID_feild).sendKeys(USRNO);
			   Thread.sleep(1000);
			   driver.findElement(SearchwithUSRID).click();
			   Thread.sleep(1000);
			   driver.findElement(USRIDlink).click();			   
			   scrollDown();
			   Thread.sleep(1000);
			   driver.findElement(AssignSGP_Button).click();
			   scrollDown();
			   WebElement selectSGP=driver.findElement(AssignSGPDropdown);
			   sel= new Select(selectSGP);
			   sel.selectByValue("1157");
			   Thread.sleep(1000);
			   driver.findElement(Assign_Button).click();
			   WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			   WebElement Actualmsg=wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID));
			   String ExpectedAssignedMsg ="USR assigned to Support Group Personnel";
			   String ActualAssignedMsg =Actualmsg.getText();
			   Assert.assertEquals(ActualAssignedMsg, ExpectedAssignedMsg);
			   System.out.println("Assigned to SGP Successfully");
			   driver.findElement(SuccessPopup_OK).click();
			   System.out.println("Existed from assigning USR to SGP testcase");
		   }
	   }
	   
	   
	   public void SGPHandlesUSR(String USRNO) throws IOException, InterruptedException, SQLException, AWTException {
		   driver.navigate().refresh();
		   Thread.sleep(1000);
		   String currentrole= driver.findElement(By.id("userdisplayrolecode")).getText();
		   System.out.println("My current role is "+ currentrole);
		   Thread.sleep(2000);
		   if(!currentrole.equalsIgnoreCase("Support Group Personnel")) {
			   connecting_DB_updatingroleID(4,1157);
			   SGPHandlesUSR(USRNO);
			   }
		   else {
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
			   driver.findElement(Reply_link).click();
			   scrollDown();
			   Thread.sleep(1000);
			   driver.findElement(ReplyMessage_Textbox).sendKeys("Replying through automation script by Shabana as SGP");
			   Thread.sleep(1000);
			   driver.findElement(SubmitRepy_Button).click();
			   WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
			   WebElement Actualmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID));
			   String ExpectedRepliedMsg ="Response saved successfully.";
			   String ActualRepliedMsg =Actualmsg.getText();
			   Assert.assertEquals(ActualRepliedMsg, ExpectedRepliedMsg);
			   Thread.sleep(1000);
			   System.out.println("SGP replied Successfully");
			   driver.findElement(SuccessPopup_OK).click();
			   Thread.sleep(1000);
			   driver.findElement(MarkResolved_Button).click();	
			   Thread.sleep(1000);
			   scrollDown();
			   driver.findElement(USRRemarks_Textbox).sendKeys("Testing SGP Resolving the USR through Automation By Shabana");
			   scrollDown();
			   Thread.sleep(1000);			  
			   driver.findElement(USRResolve_Save_Button).click();	
			   Thread.sleep(1000);
			   String ExpectedResolvecnfmnMsg = "Are you sure you want to resolve USR?";
			   String ActualResolvecnfmnMsg= driver.findElement(By.id("popUpDetailsCallBack")).getText();
			   Assert.assertEquals(ActualResolvecnfmnMsg, ExpectedResolvecnfmnMsg);
			   System.out.println("Confirmation message is as expected");
			   driver.findElement(ConfirmationYES).click();
			   Thread.sleep(1000);
			   String ExpectedResolvedMsg ="USR resolved";
			   String ActualResolvedMsg=driver.findElement(By.id("popUpForAlertText")).getText();
			   Assert.assertEquals(ActualResolvedMsg, ExpectedResolvedMsg);
			   Thread.sleep(1000);
			   driver.findElement(SuccessPopup_OK).click();
			   System.out.println("Testcase passed");
			   
		   }
	   
     }
	   
//////////////////////////////////////////////////////////////////////
/////////////Submit USR on-behalf of user ////////////////////	 Testcase ID = 178764
///////////////////////////////////////////////////////////////////// 
	   
	   public void clickingOnSubmitUSROnBehalfOfUser() {
		   WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		   WebElement SubmitUSROnBehalf=wait.until(ExpectedConditions.visibilityOfElementLocated(SubmitUSRonBehalf));
		   SubmitUSROnBehalf.click();
	   }
	   
	   public void selectGroup() {
		  WebElement Group= driver.findElement(Group_Dropdown);
		  sel= new Select(Group);
		  sel.selectByValue("2");
		  System.out.println("Group is selected");
		   
	   }
       public void selectUserID() {
    	   WebElement UserID= driver.findElement(UserID_Dropdown);
 		  sel= new Select(UserID);
 		  sel.selectByValue("1180");
 		  System.out.println("User ID selected");
	   }
	   
	   
	   
	   public String submitUSROnBehalfOfUser() throws InterruptedException, IOException, SQLException, AWTException {
		    launchHD();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    driver.navigate().refresh();
		    WebElement roleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userdisplayrolecode")));
		    String currentRole = roleElement.getText();
		    System.out.println("My current role is " + currentRole); 
		    
		    while (!currentRole.equalsIgnoreCase("Help Desk Officer")) {
		        connecting_DB_updatingroleID(2, 1157);
		        
		        // Refresh and re-check the role
		        driver.navigate().refresh();
		        roleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userdisplayrolecode")));
		        currentRole = roleElement.getText();
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
		    driver.findElement(SubmitOnBehlf_USRSubject).sendKeys("Test Automation by shabana");
			driver.findElement(USRDescription).sendKeys("Test Automation by shabana");
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
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    driver.navigate().refresh();
		    WebElement roleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userdisplayrolecode")));
		    String currentRole = roleElement.getText();
		    System.out.println("My current role is " + currentRole); 
		    
		    while (!currentRole.equalsIgnoreCase("Outlet Personnel")) {
		        connecting_DB_updatingroleID(6, 1157);
		        
		        // Refresh and re-check the role
		        driver.navigate().refresh();
		        roleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userdisplayrolecode")));
		        currentRole = roleElement.getText();
		        System.out.println("My current role is " + currentRole); 
		       // connecting_DB_updateGroupID(2,1180);
		        System.out.println("Updated role ID to Outlet Personnel"); 
		        }
		   
	   }
	   public void searchWithUSR(String USRNO) throws InterruptedException {
		   driver.findElement(USRID_feild).sendKeys(USRNO);
		   Thread.sleep(3000);
		   driver.findElement(SearchwithUSRID).click();
		   Thread.sleep(1000);
	   }
	   
	   public void clickOnSearchedUSR() throws InterruptedException {
		   driver.findElement(USRIDlink).click();			   
		   scrollDown();
		   Thread.sleep(1000);
	   }
	   public void replyUSRWithAttachment() throws AWTException, InterruptedException {
		   driver.findElement(Reply_link).click();
		   scrollDown();
		   wb= new WebDriverWait(driver,Duration.ofSeconds(10));
		   WebElement ReplyMessageTextBox=wb.until(ExpectedConditions.visibilityOfElementLocated(ReplyMessage_Textbox));
		   ReplyMessageTextBox.sendKeys("Testing reply USR with attachment in automation by shabana");
		   ReplyMessageTextBox.sendKeys(Keys.TAB);
//		   wb = new WebDriverWait(driver,Duration.ofSeconds(10));
//		   WebElement AttachmentButton= wb.until(ExpectedConditions.elementToBeClickable(Reply_AttachFile));
		   scrollDown();
		   Thread.sleep(2000);
		   driver.findElement(Reply_AttachFile).click();		   
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
		   driver.findElement(SubmitRepy_Button).click();
	   }
	   
	   public void verifyReplySuccesspopup() throws InterruptedException {
		   WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		   WebElement Actualmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID));
		   String ExpectedRepliedMsg ="Response saved successfully.";
		   String ActualRepliedMsg =Actualmsg.getText();
		   Assert.assertEquals(ActualRepliedMsg, ExpectedRepliedMsg);
		   Thread.sleep(1000);
		   System.out.println("Testcase passed");
		   
	   }

//////////////////////////////////////////////////////////////////////
//////Verify the button(Reopen)when a HDO selects a USR with status 'Closed' ////////////////////	 Testcase ID = 253783
///////////////////////////////////////////////////////////////////// 
	   
	   public void markResolvedByHDO() throws InterruptedException {
		   scrollDown();
		   Thread.sleep(1000);
		   driver.findElement(MarkResolved_Button).click();	
		   Thread.sleep(1000);
		   driver.findElement(USRRemarks_Textbox).sendKeys("Testing HDO Resolving the USR through Automation By Shabana");
		   scrollDown();
		   Thread.sleep(1000);
		   driver.findElement(USRResolve_Save_Button).click();	
		   Thread.sleep(1000);
		   String ExpectedResolvecnfmnMsg = "Are you sure you want to resolve USR?";
		   String ActualResolvecnfmnMsg= driver.findElement(By.id("popUpDetailsCallBack")).getText();
		   Assert.assertEquals(ActualResolvecnfmnMsg, ExpectedResolvecnfmnMsg);
		   System.out.println("Confirmation message is as expected");
		   driver.findElement(ConfirmationYES).click();
		   Thread.sleep(1000);
		   String ExpectedResolvedMsg ="USR resolved";
		   String ActualResolvedMsg=driver.findElement(By.id("popUpForAlertText")).getText();
		   Assert.assertEquals(ActualResolvedMsg, ExpectedResolvedMsg);
		   Thread.sleep(1000);
		   driver.findElement(SuccessPopup_OK).click();
		   
	   }
	   public void closeUSRByHDO() throws InterruptedException {
		   scrollDown();
		   driver.findElement(CloseButton).click();	
		   wb= new WebDriverWait(driver,Duration.ofSeconds(10));
		   String ActualResolvecnfmnMsg =wb.until(ExpectedConditions.visibilityOfElementLocated(CancelmsgID)).getText();
		   String ExpectedResolvecnfmnMsg = "Are you sure you want to close the USR?";
		   Assert.assertEquals(ActualResolvecnfmnMsg, ExpectedResolvecnfmnMsg);
		   System.out.println("Confirmation message is as expected");
		   driver.findElement(ConfirmationYES).click();
		   String ExpectedClosedMsg= "USR Closed.";
		   String ActualClosedMsg=wb.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID)).getText();
		   Assert.assertEquals(ActualClosedMsg, ExpectedClosedMsg);
		   System.out.println("USR is closed");
		   Thread.sleep(1000);
		   driver.findElement(SuccessPopup_OK).click();
		 		   
	   }
	   
	   public void reopenUSRByHDO() throws InterruptedException {
		   wb= new WebDriverWait(driver,Duration.ofSeconds(20));
		   WebElement ReopenButton=wb.until(ExpectedConditions.visibilityOfElementLocated(Reopen_Button));
		   ReopenButton.click();
		   scrollDown();
		   Thread.sleep(1000);
		   driver.findElement(USRRemarks_Textbox).sendKeys("Testing HDO Reopening the USR through Automation By Shabana");
		   scrollDown();
		   WebElement ReopenSaveButton=wb.until(ExpectedConditions.visibilityOfElementLocated(Reopen_Save_Button));
		   ReopenSaveButton.click();
		   String ExpectedReopencnfmnMsg = "Are you sure you want to reopen the USR?";
		   WebElement ActualReopencnfmn=wb.until(ExpectedConditions.visibilityOfElementLocated(By.id("popUpDetailsCallBack")));
		   String ActualReopencnfmnMsg= ActualReopencnfmn.getText();
		   System.out.println(ActualReopencnfmnMsg);
		   Assert.assertEquals(ActualReopencnfmnMsg, ExpectedReopencnfmnMsg);
		   System.out.println("Confirmation message is as expected");
		   driver.findElement(ConfirmationYES).click();
		   Thread.sleep(1000);
		   String ExpectedReopenedMsg ="USR reopened.";
		   WebElement ActualReopenedcnfmn=wb.until(ExpectedConditions.visibilityOfElementLocated(By.id("popUpForAlertText")));
		   String ActualReopenedMsg= ActualReopenedcnfmn.getText();
		   System.out.println(ActualReopencnfmnMsg);
		   Assert.assertEquals(ActualReopenedMsg, ExpectedReopenedMsg);
		   Thread.sleep(1000);
		   driver.findElement(SuccessPopup_OK).click();
		   	   
	   }
//////////////////////////////////////////////////////////////////////
//Verify if OS can enter feedback response multiple times in the ongoing week///////	 Testcase ID = 187452
///////////////////////////////////////////////////////////////////// 	   
	   public void OS_SubmitFeedback() throws InterruptedException, SQLException, IOException {
	   launchHD();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    driver.navigate().refresh();
	    WebElement roleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userdisplayrolecode")));
	    String currentRole = roleElement.getText();
	    System.out.println("My current role is " + currentRole); 
	    
	    while (!currentRole.equalsIgnoreCase("Outlet Supervisor")) {
	        connecting_DB_updatingroleID(5, 1157);
	        
	        // Refresh and re-check the role
	        driver.navigate().refresh();
	        roleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userdisplayrolecode")));
	        currentRole = roleElement.getText();
	        System.out.println("My current role is " + currentRole); 
	        connecting_DB_updateGroupID(2,1157);
	        System.out.println("Updated Stefy's groupID to Application Development"); 
	        }
	    
	    SubmitUSR_ClickingOnHelpDesk();
	    wb= new WebDriverWait(driver,Duration.ofSeconds(10));
	    WebElement ViewOrSubmit_Feedback=wb.until(ExpectedConditions.visibilityOfElementLocated(ViewORSubmit_Feedback));
	    ViewOrSubmit_Feedback.click();
	    WebElement weekEndingSelect=wb.until(ExpectedConditions.visibilityOfElementLocated(WeekEndingSelect));
	    sel= new Select(weekEndingSelect);
	    sel.selectByIndex(1);
	    WebElement viewOrSubmit_Button=wb.until(ExpectedConditions.visibilityOfElementLocated(ViewORSUbmit_Button));
	    viewOrSubmit_Button.click();
	    WebElement enterFeedback=wb.until(ExpectedConditions.visibilityOfElementLocated(EnterFeedback_TextBox));
	    enterFeedback.sendKeys("Entering feedback to the HDO from Outlet Supervisor through Automation By shabana");
	    WebElement FeedbackSave=wb.until(ExpectedConditions.visibilityOfElementLocated(OSFeedback_Save_Button));
	    FeedbackSave.click();
	    String ExpectedFeedbackSuccessMsg = "Feedback saved successfully";
	    WebElement ActualFeedBackSuccessMsg= wb.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID));
	    String ActualFeedbackSuccessMsg = ActualFeedBackSuccessMsg.getText();
	    Assert.assertEquals(ActualFeedbackSuccessMsg,ExpectedFeedbackSuccessMsg);
	    System.out.println("Feedback sent successfully");
	    driver.findElement(SuccessPopup_OK);
	    
	   }
	   public void gittestingMethod() throws InterruptedException {
		   WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		   WebElement Actualmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessmsgID));
		   String ExpectedRepliedMsg ="Response saved successfully.";
		   String ActualRepliedMsg =Actualmsg.getText();
		   Assert.assertEquals(ActualRepliedMsg, ExpectedRepliedMsg);
		   Thread.sleep(1000);
		   System.out.println("Testcase passed");
		   
	   }
	   
	   
}

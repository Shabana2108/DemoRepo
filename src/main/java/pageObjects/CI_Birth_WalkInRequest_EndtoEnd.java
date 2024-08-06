package pageObjects;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CI_Birth_WalkInRequest_EndtoEnd {
     static Properties prop;
     WebDriver driver = new ChromeDriver();
     Select sel;
     JavascriptExecutor js;
     WebDriverWait wb;
  //   String outletcode=driver.findElement(By.xpath("//div[@id='userdisplayoutletcode']")).getText();
 //    WebElement checkbox =driver.findElement(By.xpath("//input[@id='ToggleSlider']"));
   
     
	public void launch() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\SShabana\\eclipse-workspace\\com.serbilis\\src\\test\\resources\\resources.config");
		prop= new Properties();
		prop.load(fis);
		driver.get(prop.getProperty("QAB2Url"));
		driver.manage().window().maximize();

	}
	
	   public void navigatingToHomePage() {
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   driver.findElement(By.id("dashboard")).click();
	   }
	
		public String EncodeCIBirth(int Type) throws InterruptedException, IOException {//default parameter
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			launch();
			WebElement checkbox =driver.findElement(By.xpath("//input[@id='ToggleSlider']"));
		   if(checkbox.isSelected()) {
			System.out.println("Counter is open");
			
		}
		else {
			System.out.println("Counter is closed");
			counterOpen();
			
		}
		   String outletcode=driver.findElement(By.xpath("//div[@id='userdisplayoutletcode']")).getText();
		   System.out.println(outletcode);
		   
			if(!outletcode.equals("003")) {
				changeOutlet("CENTRAL OUTLET");
				EncodeCIBirth(1);
		    	}
			else 
			{
				navigatingToHomePage();
//		driver.findElement(By.xpath("//input[@id='ToggleSlider']/following::span[1]")).click();
//		driver.findElement(By.xpath("//button[@id='AlertButton']")).click();
		driver.findElement(By.id("101")).click();
		driver.findElement(By.linkText("Walk-in Request Entry")).click();
		WebElement TypeOfRequest=driver.findElement(By.id("WalkInTransactiontype"));
		sel= new Select(TypeOfRequest);
		sel.selectByIndex(Type);
		driver.findElement(By.id("noOfCopies")).sendKeys("1");
		driver.findElement(By.id("txtBirthPriLastName")).sendKeys(prop.getProperty("DLastName"));
		driver.findElement(By.id("txtBirthPriFirstName")).sendKeys(prop.getProperty("DFirstName"));
		driver.findElement(By.id("txtBirthPriMiddleName")).sendKeys(prop.getProperty("DMiddleName"));
		WebElement Sex=driver.findElement(By.id("txtBirthSex"));
		sel=new Select(Sex);
		sel.selectByIndex(1);
		WebElement Month=driver.findElement(By.id("birthMonth"));
		sel=new Select(Month);
		sel.selectByIndex(1);
		driver.findElement(By.id("birthDate")).sendKeys("01");
		driver.findElement(By.id("birthYear")).sendKeys(prop.getProperty("Year"));
		WebElement Country=driver.findElement(By.id("birthCountry"));
		sel=new Select(Country);
		sel.selectByVisibleText("PHILIPPINES");
		WebElement Province=driver.findElement(By.id("birthProvince"));
		sel=new Select(Province);
		sel.selectByVisibleText("ABRA");
		driver.findElement(By.id("birthCity")).click();
		WebElement City=driver.findElement(By.id("birthCity"));
		sel=new Select(City);
		sel.selectByIndex(1);
		driver.findElement(By.id("txtBirthSecLastName")).sendKeys(prop.getProperty("FLastName"));
		driver.findElement(By.id("txtBirthSecFirstName")).sendKeys(prop.getProperty("FFirstName"));
		driver.findElement(By.id("txtBirthSecMiddleName")).sendKeys(prop.getProperty("FMiddleName"));
		driver.findElement(By.id("txtBirthAuxLastName")).sendKeys(prop.getProperty("MLastName"));
		driver.findElement(By.id("txtBirthAuxFirstName")).sendKeys(prop.getProperty("MFirstName"));
		driver.findElement(By.id("txtBirthAuxMiddleName")).sendKeys(prop.getProperty("MMiddleName"));
		WebElement Purpose=driver.findElement(By.id("cmbRequestPurpose"));
		sel=new Select(Purpose);
		sel.selectByIndex(1);
		driver.findElement(By.xpath("(//button[text()='Save'])[1]")).click();
		driver.findElement(By.id("txtReqLastName")).sendKeys(prop.getProperty("RLastName"));
		driver.findElement(By.id("txtReqFirstName")).sendKeys(prop.getProperty("RFirstName"));
		driver.findElement(By.id("txtReqMiddleName")).sendKeys(prop.getProperty("RMiddleName"));
		driver.findElement(By.id("txtAddress")).sendKeys(prop.getProperty("Add1"));
		driver.findElement(By.id("streetAddress")).sendKeys(prop.getProperty("Add2"));
		WebElement DeliveryProvince=driver.findElement(By.id("cmbProvince"));
		sel=new Select(DeliveryProvince);
		sel.selectByVisibleText("ABRA");
		Thread.sleep(1000);
		driver.findElement(By.id("cmbCity")).click();
		WebElement DeliveryCity=driver.findElement(By.id("cmbCity"));
		sel=new Select(DeliveryCity);
		sel.selectByVisibleText("BANGUED");
		driver.findElement(By.id("submitbtn")).click();
		driver.findElement(By.id("finalSubmitbtn")).click();
		Thread.sleep(1000);
		WebElement RefNo=driver.findElement(By.xpath("//table[@id='WalkInTranDetails']/tbody/tr[1]/td[2]"));
		String RefNO=RefNo.getText();
		System.out.println(RefNO);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[text()='×'])[2]")).click();
		Thread.sleep(2000);
		return RefNO;
			}
			Thread.sleep(2000);
	    return "";
	  
	}

		//Cash balancing while payment
	    public void cashBalancing() {
	    	driver.findElement(By.id("102")).click();
			driver.findElement(By.linkText("Receive Payments")).click();
	    	driver.findElement(By.id("AlertButton")).click();
	    	driver.findElement(By.xpath("//h5[text()='Cash Balancing']")).click();
	    	String RemittanceAmount =driver.findElement(By.id("txtCashIn")).getText();
	    	System.out.println(RemittanceAmount);
	    }
	    
	    
	    
	    
	    //Updating PaymentStatusCode to Paid in DB 
		public void updatingDB(String ReferenceNo) throws SQLException, ClassNotFoundException, InterruptedException, IOException  {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
			Thread.sleep(3000);
			System.out.println("Reference number in updating DB method "+ ReferenceNo);
			String TrnsctnNoWithoutHyphen=ReferenceNo.replaceAll("-","");
			System.out.println(TrnsctnNoWithoutHyphen);
			String SeriesNO = TrnsctnNoWithoutHyphen.substring(0, TrnsctnNoWithoutHyphen.length() - 3);
			System.out.println(SeriesNO);
			String SequenceNo = TrnsctnNoWithoutHyphen.substring(TrnsctnNoWithoutHyphen.length() - 3);
			System.out.println(SequenceNo);
			/*3868 DB Details
			String JDBC_URL = "jdbc:sqlserver://USTR-MVM-3868;databaseName=Civil Registry System;encrypt=true;trustServerCertificate=true";
			String JDBC_USER = "PSATestLiveTeam";
			String JDBC_PASSWORD = "PSATest#LiveTeam";
			*/
			String JDBC_URL = "jdbc:sqlserver://USTR-ERL2-0643\\PSAQA:1434;databaseName=Civil Registry System;encrypt=true;trustServerCertificate=true";
			String JDBC_USER = "PSATestLiveTeam";
			String JDBC_PASSWORD = "PSATest#LiveTeam";
			Connection con= DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	        
			String query="update encode.Request set PaymentStatusCode='P' where SeriesNo=? and SequenceNo=?";
			 PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, SeriesNO);
			stmt.setString(2, SequenceNo);
			
			//String query="update encode.Request set PaymentStatusCode='P' where SeriesNo="+SeriesNO+ "and SequenceNo="+ SequenceNo;
			int rowsupdated=stmt.executeUpdate();
			System.out.println("Qury executed and rows updated are "+rowsupdated);
	}          
	    
		
		
		
		//Payment
		public void payment() throws InterruptedException, IOException {
			
			String ReferenceNo=EncodeCIBirth(1);
			driver.findElement(By.id("102")).click();
			driver.findElement(By.linkText("Receive Payments")).click();
			WebElement PaymentSearchCriteria=driver.findElement(By.id("ddlSelectFilter"));
			sel=new Select(PaymentSearchCriteria);
			sel.selectByIndex(5);
			driver.findElement(By.id("txtSeatch")).sendKeys(ReferenceNo);
			driver.findElement(By.id("btnSarchPayment")).click();
			driver.findElement(By.xpath("(//th[text()='Payment Status'])[1]/following::a[1]")).click();
			Thread.sleep(2000);
			WebElement validID=driver.findElement(By.id("ddlValidIDPresented"));
			sel=new Select(validID);
			sel.selectByIndex(1);
			//String TotalAmt=driver.findElement(By.id("TotalAmt")).getText();
			driver.findElement(By.id("txtAmountTendered")).sendKeys("155");
			Thread.sleep(1000);
			driver.findElement(By.id("PrintOR")).click();
			
		}
		
		
		
		public void processing(String ReferenceNo) throws IOException, InterruptedException {
			//launch();
			Thread.sleep(1000);
			String outletcode=driver.findElement(By.xpath("//div[@id='userdisplayoutletcode']")).getText();
			System.out.println(outletcode);
			if(!outletcode.equals("002")) {
				changeOutlet("SECONDARY BACK OFFICE");
				processing(ReferenceNo);
		    	}
			
			else {	
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				System.out.println("Processing outlet is opened "+ outletcode);
				navigatingToHomePage();
			    driver.findElement(By.id("103")).click();
			    driver.findElement(By.linkText("Processing Queue")).click();
			    driver.findElement(By.xpath("//input[@type='search']")).sendKeys(ReferenceNo);
			    driver.findElement(By.xpath("//th[text()='Action']/following::a[1]")).click();
			    driver.findElement(By.id("BrenField")).sendKeys(prop.getProperty("BreNNo"));
			    js = (JavascriptExecutor) driver;
			    js.executeScript("window.scrollBy(0,500)");
			    driver.findElement(By.id("SearchClick")).click();
			    driver.findElement(By.xpath("(//th[text()='Action'])[1]/following::a[1]")).click();
			    driver.findElement(By.id("print")).click();
			    wb = new WebDriverWait(driver,Duration.ofSeconds(10));
			    wb.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("IssueCertificateContinueBtn"))));
			    driver.findElement(By.id("IssueCertificateContinueBtn")).click();
			    driver.findElement(By.id("Yesbutton")).click();
			    driver.findElement(By.id("AlertButton")).click();
			    //id= popUpForAlertText  //text=Certificate Document created successfully == for validation check
			    System.out.println("Processed positive certificate for the Reference number "+ ReferenceNo);
		
			}
		}
		
			
		
		public void releasing(String ReferenceNo ) throws InterruptedException {
			System.out.println("Navigated to Releasing");
			driver.findElement(By.xpath("//img[@src='/Common/Content/images/more-icon.svg']")).click();
			driver.findElement(By.id("104")).click();
			driver.findElement(By.linkText("Release Certificate")).click();
			driver.findElement(By.id("TransNum")).sendKeys(ReferenceNo);
			driver.findElement(By.id("validsearch")).click();
			driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
			js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			driver.findElement(By.id("print")).click();
			WebElement releaseValidID =driver.findElement(By.id("ddlclaimentreason"));
			sel= new Select(releaseValidID);
			sel.selectByIndex(1);
			driver.findElement(By.id("proceed")).click();
			Thread.sleep(1000);
			WebElement printer =driver.findElement(By.id("printers"));
			sel= new Select(printer);
			sel.selectByIndex(2);
			driver.findElement(By.id("FinalPrintButton")).click();
			driver.findElement(By.id("continue")).click();
			//from here we can write method for "NO-reprint" also
			Thread.sleep(1000);
			driver.findElement(By.id("secpa_0")).sendKeys(prop.getProperty("SecpaNumber1"));
			driver.findElement(By.id("secpa_1")).sendKeys(prop.getProperty("SecpaNumber2"));
			Thread.sleep(1000);
			driver.findElement(By.id("saveContinue")).click();
			//Transaction number 08947-003-00001-001  has been printed successfully   -- popup locator (//div[@class='modal-content'])[3]-- for validation
			driver.findElement(By.xpath("(//button[text()='×'])[3]")).click();
			driver.findElement(By.xpath("//div[contains(text(),'has been printed')]/following::button[1]")).click();
			//String title=driver.getTitle();   --- for validation
			System.out.println("Released Positive certificate successfully");
		}
		
		
		
		public void counterOpen() throws InterruptedException {
			driver.findElement(By.xpath("//input[@id='ToggleSlider']/following::span[1]")).click();
			driver.findElement(By.id("AlertButton")).click();
			
			}
		
		
		
		public void changeOutlet(String outletcode) throws IOException, InterruptedException {
			navigatingToHomePage();
			driver.findElement(By.xpath("//img[@src='/Common/Content/images/more-icon.svg']")).click();
			driver.findElement(By.id("180")).click();
			driver.findElement(By.linkText("User Detail Information")).click();
			driver.findElement(By.xpath("//div[@id='tblUserDetails_filter']/descendant::input")).sendKeys("ssb");
			//table
			WebElement table =driver.findElement(By.id("tblUserDetails"));			
			List<WebElement> rows=table.findElements(By.tagName("tr"));			
			WebElement lastrow= rows.get(rows.size()-1);
			List<WebElement> columns=lastrow.findElements(By.tagName("td"));
			js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			Thread.sleep(1000);
			columns.get(7).findElement(By.tagName("a")).click();
						
			WebElement outlet=driver.findElement(By.id("txtOutletId"));
			sel= new Select(outlet);
			sel.selectByVisibleText(outletcode);
			driver.findElement(By.id("btnUserConfirm")).click();
			Thread.sleep(2000);
			driver.quit();
			driver = new ChromeDriver();
//			Actions actions = new Actions(driver);
//            actions.sendKeys(Keys.CONTROL + "t").perform();
//			((JavascriptExecutor) driver).executeScript("window.open()");
//			ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//			driver.switchTo().window(tabs.get(1));
			driver.get(prop.getProperty("Url"));
			driver.manage().window().maximize();
						
		}
		
					
			
		
}			    
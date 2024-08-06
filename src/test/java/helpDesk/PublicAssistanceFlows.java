package helpDesk;

import pageObjects.PublicAssistance;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PublicAssistanceFlows {
	
	PublicAssistance PA; 
	@AfterMethod
	public void TestAfter(ITestResult result) {
		if (result.getStatus()==ITestResult.SUCCESS) {
			System.out.println(result.getMethod().getMethodName() + " PASSED");
		}
		else if (result.getStatus()==ITestResult.FAILURE) {
			System.out.println(result.getMethod().getMethodName() + " FAILED");
		}
	}
	
	//Testcase = 96572
	@Test
	public void reOpenClosedQuery() throws IOException, SQLException, InterruptedException {
		PA= new PublicAssistance();
		PA.launchPA();
		PA.verifyAndUpdate_RoleAndGroupID();
		PA.clickPA();
		PA.clickLogNewQuery();
		PA.selectModeOFQry(1);
		PA.selectCategeory(1);
		PA.selectSubCategeory(1);
		PA.selectSeverity(2);
		PA.enterSenderDetails();
		PA.clickGenQryRefNoBtn();
		String QryRefNo=PA.verifySuccessPopup();
		PA.clickPA();
		PA.clickSearchQry();
		PA.searchWithQryRefNo(QryRefNo);
		PA.clickQryRefNoLink();
		PA.enterResolution();
		PA.selectStatus("CLOSED");//there are 2 status either CLOSED or OPEN
		PA.clickSave();
		PA.searchWithQryRefNo(QryRefNo);
		PA.clickQryRefNoLink();
		PA.clickReopen();
		PA.acceptReopenCnfrmn();
		PA.enterResolution();
		PA.save_QueryReopen();
	}
	
	//Testcase = 96576
	@Test
	public void viewFAQs() throws IOException, SQLException, InterruptedException {
		PA= new PublicAssistance();
		PA.launchPA();
		PA.verifyAndUpdate_RoleAndGroupID();
		PA.clickPA();
		PA.clickViewFAQs();
		PA.closeFAQs();
	}
	
	@Test
	public void ViewConcernsAndComplaintsReport() throws IOException, SQLException, InterruptedException {
		PA= new PublicAssistance();
		PA.launchPA();
		PA.verifyAndUpdate_RoleAndGroupID();
		PA.clickPA();
		PA.clickViewConcernsAndComplaintsReport();
		PA.enterFromAndToDates("08012024", "08042024");
		PA.clickGenerateReport();
	}

}

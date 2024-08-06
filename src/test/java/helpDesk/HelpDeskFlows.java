package helpDesk;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageObjects.HelpDesk;

public class HelpDeskFlows {
	
	HelpDesk helpDesk;
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			System.out.println(result.getMethod().getMethodName()+" PASSED");
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			System.out.println(result.getMethod().getMethodName()+" FAILED");
			System.out.println("Error is "+result.getThrowable());
		}
		else if (result.getStatus()==ITestResult.SKIP){
			System.out.println(result.getMethod().getMethodName()+" SKIPPED");
		}
		
	}
	
	//TestCase ID = 91877
	@Test (priority=0)
	public void createUSR() throws IOException, InterruptedException, SQLException {
		helpDesk = new HelpDesk();
		helpDesk.launchHD();
		helpDesk.changeRoleToHelpDesk();
		helpDesk.SubmitUSR_ClickingOnHelpDesk();
		helpDesk.SubmitUSR_ClickingOnSubmitUSR();
		helpDesk.SubmitUSR_SelectCategeory(1);
		helpDesk.SubmitUSR_SelectHardwareType(1);
		helpDesk.SubmitUSR_EnterInputFields();
		helpDesk.SubmitUSR_ClickSubmit();
		helpDesk.SubmitUSR_ClickingOnHelpDesk();
		helpDesk.SubmitUSR_ClickingOnSubmitUSR();
		helpDesk.SubmitUSR_SelectCategeory(1);
		helpDesk.SubmitUSR_ClickCancel();
		
	}
	
	//TestCase ID = 91904
	@Test(priority=1)
	public void tagUSR_Resolved() throws IOException, SQLException, InterruptedException {
		helpDesk = new HelpDesk();
		String USRNO=helpDesk.submitUSR();
		helpDesk.refferingUSRToSupportGroup(USRNO);
        helpDesk.SGCResolvesUSR(USRNO);
        }	
	
	//TestCase ID = 91910
	@Test(priority=2)
	public void helpDeskOfficer_returningUSRToHD() throws IOException, SQLException, InterruptedException {
		helpDesk = new HelpDesk();
		String USRNO=helpDesk.submitUSR();
		helpDesk.refferingUSRToSupportGroup(USRNO);
		helpDesk.HDORetuningUSRToHD(USRNO);
		}
		
	//TestCase ID = 178426
	@Test(priority=3)
	public void addAndUpdateKnowledgeBase() throws IOException,SQLException, AWTException, InterruptedException  {
		helpDesk = new HelpDesk();
		helpDesk.UploadKnowledgeBase();			
	    }
			
	//TestCase ID = 178558
	@Test(priority=4)
	public void SGP_HandleUSR() throws  AWTException, IOException, SQLException, InterruptedException {
		helpDesk = new HelpDesk();
		String USRNO=helpDesk.submitUSR();
		helpDesk.refferingUSRToSupportGroup(USRNO);
		helpDesk.SGCAssigningUSRToSGP(USRNO);
		helpDesk.SGPHandlesUSR(USRNO);
		}
			
	//TestCase ID = 178764
	@Test(priority=5)
	public void submitUSR_onBehalfOfUser() throws AWTException, IOException, SQLException, InterruptedException {
		helpDesk = new HelpDesk();
		helpDesk.submitUSROnBehalfOfUser();
		}
		
	//TestCase ID = 253784//Resolving USR by SGC
	@Test(priority=6)
	public void SGC_ResolvingUSR() throws IOException, SQLException, AWTException, InterruptedException {
		helpDesk = new HelpDesk();
		String USRNO=helpDesk.submitUSR();
		helpDesk.refferingUSRToSupportGroup(USRNO);
		helpDesk.SGCResolvesUSR(USRNO);
		}	
	
	//TestCase ID = 253784//Assigning USR to SGP
	@Test(priority=7)
	public void SGCSelectsPendingUSR() throws IOException, SQLException, AWTException, InterruptedException {
		helpDesk = new HelpDesk();
		String USRNO=helpDesk.submitUSR();
		helpDesk.refferingUSRToSupportGroup(USRNO);
		helpDesk.SGCAssigningUSRToSGP(USRNO);
		}
	
	//TestCase ID = 253792//Verify the Attach button from the add reply ------------This needs to work still
	@Test	(priority=8)
	public void verifyAttachInReply() throws IOException, SQLException, AWTException, InterruptedException {
		helpDesk = new HelpDesk();
		String USRNO=helpDesk.submitUSR();
		helpDesk.launch_SelectOutletPersonnel();
		helpDesk.SubmitUSR_ClickingOnHelpDesk();
		helpDesk.SearchUSR();
		helpDesk.searchWithUSR(USRNO);
		helpDesk.clickOnSearchedUSR();
		helpDesk.replyUSRWithAttachment();
		helpDesk.verifyReplySuccesspopup();
		}
	
	//TestCase ID = 253783//Verify the button(Reopen)when a HDO selects a USR with status 'Closed'
	@Test(priority=9)
	public void verifyReopenForClosedUSR() throws IOException, SQLException, InterruptedException {
		helpDesk = new HelpDesk();
		String USRNO=helpDesk.submitUSR();
		helpDesk.SubmitUSR_ClickingOnHelpDesk();
		helpDesk.SearchUSR();
		helpDesk.searchWithUSR(USRNO);
		helpDesk.clickOnSearchedUSR();
		helpDesk.markResolvedByHDO();
		helpDesk.SubmitUSR_ClickingOnHelpDesk();
		helpDesk.SearchUSR();
		helpDesk.searchWithUSR(USRNO);
		helpDesk.clickOnSearchedUSR();
		helpDesk.closeUSRByHDO();
		helpDesk.SubmitUSR_ClickingOnHelpDesk();
		helpDesk.SearchUSR();
		helpDesk.searchWithUSR(USRNO);
		helpDesk.clickOnSearchedUSR();
		helpDesk.reopenUSRByHDO();
		System.out.println("All testcases executed");
		}
		
		
	//TestCase ID = 187452//Verify if OS can enter feedback response multiple times in the ongoing week
	@Test(priority=10)
	public void OS_ResponseOnFeedback() throws IOException, SQLException, InterruptedException {
		helpDesk = new HelpDesk();
		String Feedback=helpDesk.OS_SubmitFeedback();
		helpDesk.updatingRoleTOHD();
		helpDesk.SubmitUSR_ClickingOnHelpDesk();
		helpDesk.HDOReplies_OSFeedback(Feedback);
		System.out.println("First time feedback reply submitted");
		helpDesk.SubmitUSR_ClickingOnHelpDesk();
		helpDesk.HDOReplies_OSFeedback(Feedback);
		System.out.println("Second time feedback reply submitted");
		
		}
}

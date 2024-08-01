package helpDesk;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageObjects.HelpDesk;

public class SubmitUSR {
	HelpDesk helpDesk;
	
	//TestCase ID = 91877
	@Test 
	public void createUSR() throws IOException, InterruptedException {
		helpDesk = new HelpDesk();
		helpDesk.launchHD();
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
		System.out.println("Testcase Pass");
						
	}

	
	//TestCase ID = 91904
	@Test
	public void tagUSR_Resolved() throws IOException, SQLException, InterruptedException {
		helpDesk = new HelpDesk();
		String USRNO=helpDesk.submitUSR();
		helpDesk.refferingUSRToSupportGroup(USRNO);
        helpDesk.SGCResolvesUSR(USRNO);
        
		
		}
	
	//TestCase ID = 91910
	//@Test
	public void helpDeskOfficer_returningUSRToHD() throws InterruptedException, IOException, SQLException {
		helpDesk = new HelpDesk();
		String USRNO=helpDesk.submitUSR();
		helpDesk.refferingUSRToSupportGroup(USRNO);
		helpDesk.HDORetuningUSRToHD(USRNO);
		
	}
	
	//TestCase ID = 178426
		//@Test
		public void addAndUpdateKnowledgeBase() throws IOException, InterruptedException, SQLException, AWTException  {
			helpDesk = new HelpDesk();
			helpDesk.UploadKnowledgeBase();
			
	}
		
	//TestCase ID = 178558
		//@Test
		public void SGP_HandleUSR() throws  AWTException, InterruptedException, IOException, SQLException {
			helpDesk = new HelpDesk();
			String USRNO=helpDesk.submitUSR();
			helpDesk.refferingUSRToSupportGroup(USRNO);
			helpDesk.SGCAssigningUSRToSGP(USRNO);
			helpDesk.SGPHandlesUSR(USRNO);
					
			}
		
		//TestCase ID = 178764
		//@Test
		public void submitUSR_onBehalfOfUser() throws AWTException, InterruptedException, IOException, SQLException {
			helpDesk = new HelpDesk();
			helpDesk.submitUSROnBehalfOfUser();
							
			}
				
		//TestCase ID = 253784//Resolving USR by SGC
		//@Test
		public void SGC_ResolvingUSR() throws InterruptedException, IOException, SQLException, AWTException {
			helpDesk = new HelpDesk();
			String USRNO=helpDesk.submitUSR();
			helpDesk.refferingUSRToSupportGroup(USRNO);
	        helpDesk.SGCResolvesUSR(USRNO);
									
			}	
		//TestCase ID = 253784//Assigning USR to SGP
		//@Test
		public void SGCSelectsPendingUSR() throws InterruptedException, IOException, SQLException, AWTException {
			helpDesk = new HelpDesk();
			String USRNO=helpDesk.submitUSR();
			helpDesk.refferingUSRToSupportGroup(USRNO);
			helpDesk.SGCAssigningUSRToSGP(USRNO);
													
			}
		//TestCase ID = 253792//Verify the Attach button from the add reply
		//@Test	
		public void verifyAttachInReply() throws IOException, InterruptedException, SQLException, AWTException {
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
		//@Test
		public void verifyReopenForClosedUSR() throws InterruptedException, IOException, SQLException {
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
		}
		
		//TestCase ID = 187452//Verify if OS can enter feedback response multiple times in the ongoing week
		//@Test
		public void OS_ResponseOnFeedback() throws InterruptedException, IOException, SQLException {
			helpDesk = new HelpDesk();
			helpDesk.OS_SubmitFeedback();
			
			
		}
}

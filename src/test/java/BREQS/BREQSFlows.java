package BREQS;

import java.io.IOException;
import java.sql.SQLException;
import org.testng.annotations.Test;

import pageObjects.BREQS;

public class BREQSFlows {
	BREQS breqs;
	
	@Test
	public void EncodeBREQS() throws IOException, SQLException, InterruptedException {
		breqs = new BREQS();
		breqs.launchLoginPage();
		String OTP=breqs.getOTPfromDB();
		breqs.enterOTP(OTP);
		breqs.clickCreateRequest();

		
	}

}

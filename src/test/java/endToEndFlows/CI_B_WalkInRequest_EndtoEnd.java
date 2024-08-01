package endToEndFlows;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CI_Birth_WalkInRequest_EndtoEnd;

public class CI_B_WalkInRequest_EndtoEnd {
	CI_Birth_WalkInRequest_EndtoEnd pageObject;
	
	@Test 
	public void CI_Birth_RequestEntry() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
		pageObject = new CI_Birth_WalkInRequest_EndtoEnd();
//		pageObject.launch();
//		pageObject.navigatingToHomePage();
//		pageObject.cashBalancing();
		String Reference =pageObject.EncodeCIBirth(1);
		Thread.sleep(2000);
    	pageObject.updatingDB(Reference);
//		pageObject.payment();	
		pageObject.processing(Reference);
		pageObject.releasing(Reference);

		
	}
	@Test
	public void connectDB() throws SQLException {
		String JDBC_URL = "jdbc:sqlserver://USTR-ERL2-0643\\PSAQA:1434;databaseName=Civil Registry System;encrypt=true;trustServerCertificate=true";
		String JDBC_USER = "PSATestLiveTeam";
		String JDBC_PASSWORD = "PSATest#LiveTeam";
		
		Connection con= DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        Statement stmt = con.createStatement();
		//String query="update encode.Request set PaymentStatusCode='P' where SeriesNo="+Ref+" and"+ SequenceNo+"=1";
		String query="update encode.Request set PaymentStatusCode='P' where SeriesNo='0895599100001' and SequenceNo=1";
		stmt.executeUpdate(query);
		System.out.println("Qury executed");
		String TransactionNumber = "08955-003-00001-001";
		 String Modified=TransactionNumber.replaceAll("-","");
		 System.out.println(Modified);
		 System.out.println(TransactionNumber);
		 String SeriesNo = Modified.substring(0, Modified.length() - 3);
		 System.out.println(SeriesNo);
	}
	
	@Test
	public void CHecking() throws SQLException, IOException, InterruptedException {
		pageObject = new CI_Birth_WalkInRequest_EndtoEnd();
		pageObject.launch();
		pageObject.counterOpen();
	}
	
	}
	


	


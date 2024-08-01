package endToEndFlows;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderSample {
	
	@Test (dataProvider="dt")
	public void dataproviding(String s, String S, String d) throws IOException {
		System.out.println("print dp 1" +" "+s );
		System.out.println("print dp 2" +" "+S );
		System.out.println("print dp 3" +" "+d );
		
	}
	
	@DataProvider(name="data12")
	public Object[][] data1(){
		return new Object[][]
		{
			{"Shabana","Shabikh",1}
			
		};
	}
	
	
	
	
	
	
	@DataProvider(name= "dt")
	public Object[][] dataset(){
		return new Object[][] {
			{"username from end to end", "Password ETE", "test ETE"}
		};
	}

	
}




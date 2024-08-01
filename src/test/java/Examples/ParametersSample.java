package Examples;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class ParametersSample {
	
	@Test
	@Parameters({"name" , "password"})
	public void m1(String a, int b) {
		System.out.println(a +" "+b);
	}
	
		

}

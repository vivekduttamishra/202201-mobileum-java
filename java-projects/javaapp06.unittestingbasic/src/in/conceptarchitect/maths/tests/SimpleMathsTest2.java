package in.conceptarchitect.maths.tests;

import org.junit.Test;

import in.conceptarchitect.maths.SimpleMaths;
import static org.junit.Assert.*;

public class SimpleMathsTest2 {

	@Test
	public void plusTest() {
		int x=20;
		int y=30;
		int result =SimpleMaths.plus(x,y);
		assertEquals(x+y, result);
	}
	@Test
	public void minusTest() {
		int x=20;
		int y=30;
		int result =SimpleMaths.minus(x,y);
		
		assertEquals(x-y, result);
	}
	
	@Test
	public void multiplyTest() {
		int x=20;
		int y=30;
		int result =SimpleMaths.multiply(x,y);
		
		assertEquals(x*y, result);
	}
	
	@Test
	public void divideShouldWorkForNonZeroDenominator() {
		int x=20;
		int y=30;
		int result =SimpleMaths.divide(x,y);
		assertEquals(x/y, result);
	}
	
	@Test
	public void divideShouldFailFor0AsDenominator() {
		
	}
	
	
	
}

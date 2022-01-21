package in.conceptarchitect.maths.tests;

import org.junit.Assert;
import org.junit.Test;

import in.conceptarchitect.maths.SimpleMaths;

public class SimpleMathsTest {

	@Test
	public void test1() {
		int x=20;
		int y=30;
		int result =SimpleMaths.plus(x,y);
		
		Assert.assertEquals(x+y, result);
	}
	@Test
	public void test2() {
		int x=20;
		int y=30;
		int result =SimpleMaths.minus(x,y);
		System.out.printf("SimpleMath.minus(%d,%d)=>%d\n",x,y,result);
	}
	
	@Test
	public void test3() {
		int x=20;
		int y=30;
		int result =SimpleMaths.multiply(x,y);
		System.out.printf("SimpleMath.multiply(%d,%d)=>%d\n",x,y,result);
	}
	
	@Test
	public void test4() {
		int x=20;
		int y=30;
		int result =SimpleMaths.divide(x,y);
		System.out.printf("SimpleMath.multiply(%d,%d)=>%d\n",x,y,result);
	}
	
}

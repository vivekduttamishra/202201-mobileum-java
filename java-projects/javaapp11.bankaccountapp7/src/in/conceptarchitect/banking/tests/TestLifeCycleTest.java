package in.conceptarchitect.banking.tests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestLifeCycleTest {

	@BeforeClass
	public static void oneTimeSetup() {
		System.out.println("before class");
	}
	
	@Before
	public void init() {
		System.out.println("before");
	}
	@After
	public void clean() {
		System.out.println("after");
	}
	
	@AfterClass
	public static void finalCleanup() {
		System.out.println("after class");
	}
	
	@Test
	public void test1() {
		System.out.println("test1");
	}
	
	@Test
	public void anotherTest() {
		System.out.println("test2");
	}
	
	@Test
	public void aFailingTest() {
		System.out.println("a failing test");
		fail("This test is made to fail");
	}
	
	@Ignore @Test
	public void ignoredTest() {
		
	}
	
	@Test
	public void theFinalTest() {
		System.out.println("The final test");
	}
	
}

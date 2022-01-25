package in.conceptarchitect.ems.tests;

import static in.conceptarchitect.utils.testing.CustomAssert.assertContainsSubstring;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import in.conceptarchitect.ems.Manager;

public class ManagerTest {

	Manager manager;
	int id=1;
	String name="Abhay";
	double salary=50000;
	
	@Before
	public void arrange() {
		manager=new Manager();
		manager.setId(id);
		manager.setName(name);
		manager.setSalary(salary);
	}
	
	@Test
	public void managerHasASalary() {
		assertEquals(salary, manager.getSalary(),0);
	}

	@Test
	public void managerInfoIncludesEmployeeName() {
		assertContainsSubstring(manager.info(), name);
	}
	
	@Test
	public void managerHasAName() {
		
		assertEquals(name, manager.email);
	}
	
}









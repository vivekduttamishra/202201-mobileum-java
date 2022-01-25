package in.conceptarchitect.ems.tests;

import static in.conceptarchitect.utils.testing.CustomAssert.assertContainsSubstring;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.ems.Employee;
import in.conceptarchitect.ems.Manager;

public class ManagerTest {

	Manager manager;
	int id=1;
	String name="Abhay";
	double salary=50000;
	String project="L&D";
	
	@Before
	public void arrange() {
		manager=new Manager(id,name,project, salary);
//		manager.setId(id);
//		manager.setName(name);
//		manager.setSalary(salary);
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
	public void managerHasNewPropertyNamedProject() {
		assertEquals(project, manager.getProject());
	}
	
	@Test
	public void managerCanBeReassignedToANewProject() {
		String newProject="New Project";
		manager.setProject(newProject);
		assertEquals(newProject,manager.getProject());
	}
	
	@Test
	public void managerInfoIncludesTheWordManager() {
		assertContainsSubstring(manager.info(), "Manager");
	}
	
	@Test
	public void managerInfoIncludesProjectName() {
		System.out.println(manager.info());
		assertContainsSubstring(manager.info(), project);
	}
	
	@Test
	public void managerObjectCanBeTreatedAsAnEmployeeObject() {
		Employee emp=manager; 
		
		assertNotNull(emp);
		assertEquals(name, emp.getName());
	}
	
	@Test public void managerIsAnInstanceofEmployee() {
		assertTrue( manager instanceof Employee) ;
	}
	
	@Test public void employeeIsNotAManger() {
		Employee emp=new Employee(id,name,salary);
		assertFalse( emp instanceof Manager);
	}
	
	@Test public void newManagerFeaturesAreNotPartOfSuperClass() {
		Employee emp=new Employee(id,name,salary);
		
		assertContainsSubstring(emp.info(),"Manager");
	}
	
	@Test public void weCanAccessOverridenMethodOfManagerUsingEmployeeReference() {
		
		Employee emp= manager;		
		assertContainsSubstring(emp.info(), project);
		
	}
}









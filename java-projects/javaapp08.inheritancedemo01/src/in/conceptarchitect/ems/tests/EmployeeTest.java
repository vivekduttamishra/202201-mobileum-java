package in.conceptarchitect.ems.tests;

import static org.junit.Assert.assertEquals;
import static in.conceptarchitect.utils.testing.CustomAssert.*;
import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.ems.Employee;

public class EmployeeTest {

	Employee employee;
	int id=1;
	String name="Abhay";
	double salary=50000;
	
	@Before
	public void arrange() {
		employee=new Employee(id,name,salary);
//		employee.setId(id);
//		employee.setName(name);
//		employee.setSalary(salary);
	}
	
	@Test
	public void employeeHasASalary() {
		assertEquals(salary, employee.getSalary(),0);
	}

	@Test
	public void employeeInfoIncludesEmployeeName() {
		assertContainsSubstring(employee.info(), name);
	}
	
}

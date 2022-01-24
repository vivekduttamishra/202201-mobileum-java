package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;

public class BankAccountInterestRateTest {
	
	@Test
	public void twoAccountsShouldHaveSameInterestRate() {
		
		BankAccount a1=new BankAccount(1,"Vivek","p@ss",20000, 12);
		BankAccount a2=new BankAccount(1,"Shivanshi","p@ss",20000, 13);
		
		assertEquals(a2.getInterestRate(),a1.getInterestRate(),0.01);	
		
	}

}

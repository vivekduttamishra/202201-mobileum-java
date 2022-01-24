package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;

public class BankAccountInterestRateTest {
	
	@Test
	public void twoAccountsShouldHaveSameInitialInterestRate() {
		
		BankAccount.setInterestRate(12);
		
		BankAccount a1=new BankAccount(1,"Vivek","p@ss",20000);
		BankAccount a2=new BankAccount(1,"Shivanshi","p@ss",20000);
		
		
		
		assertEquals(12,a1.getInterestRate(),0.01);	
		assertEquals(12,a2.getInterestRate(),0.01);
	}
	
	@Test
	public void changingInterestRateForOneChangesForEveryone() {
		
		BankAccount a1=new BankAccount(1,"Vivek","p@ss",20000);
		BankAccount a2=new BankAccount(1,"Shivanshi","p@ss",20000);
		
		double newRate=15;
		
		a1.setInterestRate(newRate);
		
		assertEquals(newRate,a1.getInterestRate(),0.01);	
		assertEquals(newRate,a2.getInterestRate(),0.01);
	}
	
	@Test
	public void changingInterestRateForBankAccountChangesForEveryone() {
		
		BankAccount a1=new BankAccount(1,"Vivek","p@ss",20000);
		BankAccount a2=new BankAccount(1,"Shivanshi","p@ss",20000);
		
		double newRate=15;
		
		BankAccount.setInterestRate(newRate);
		
		assertEquals(newRate,a1.getInterestRate(),0.01);	
		assertEquals(newRate,a2.getInterestRate(),0.01);
	}
	
	
	@Test
	public void differenceOfThanDesiredAccuracyIsConsideredEqual() {
		double x=2.201;
		double y=2.202;
		
		assertEquals(x,y,0.01);
	}

}



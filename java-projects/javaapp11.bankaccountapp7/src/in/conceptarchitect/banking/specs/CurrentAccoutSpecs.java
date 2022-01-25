package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.ResponseStatus;

public class CurrentAccoutSpecs {
	String name="Name";
	double balance=50000;
	String correctPassword="p@ss";
	double interestRate=12;
	CurrentAccount account;

	@Before
	public void setUp() throws Exception {
		account=new CurrentAccount(1,name,correctPassword,balance);
	}

	
	@Test
	public void currentAccountIsATypeOfBankAccount() {
		assertTrue(account instanceof BankAccount);
	}
	
	
	@Test
	public void creditInterstDoesntCreditInterest() {
		account.creditInterest(interestRate);
		assertEquals(balance, account.getBalance(),0);
	}
	

	
	
	@Test
	public void withdrawCanWithdrawEntireBlance() {
		
		var result=account.withdraw(balance, correctPassword);
		
		assertEquals(ResponseStatus.SUCCESS, result.getCode());
		assertEquals(0, account.getBalance(),0);
	}
	
	
	@Test
	public void withdrawFailsForWrongPassword() {
		
		var result=account.withdraw(1, "not-"+correctPassword);
		
		assertEquals(ResponseStatus.INVALID_CREDENTIALS,result.getCode());
		assertEquals(balance, account.getBalance(),0);
		
	}
	
	
	

}

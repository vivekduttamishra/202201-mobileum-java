package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.ResponseStatus;
import in.conceptarchitect.banking.SavingsAccount;

public class SavingsAccountSpecs {
	String name="Name";
	double balance=50000;
	String correctPassword="p@ss";
	double interestRate=12;
	SavingsAccount account;
	double minBalance=5000;

	@Before
	public void setUp() throws Exception {
		
		account=new SavingsAccount(1, name, correctPassword, balance);
	}

	
	@Test
	public void savingsAccountIsATypeOfBankAccount() {
	
		assertTrue(account instanceof BankAccount);
	}
	
	
	@Test
	public void creditInterstCreditsStandardInterest() {
		
		account.creditInterest(interestRate);
		var expectedBalanceAfterCredit= balance*(1+ interestRate/1200);
		
		assertEquals(expectedBalanceAfterCredit, account.getBalance(),0.01);
	}
	
	
	@Test
	public void savingsAccountHasDefinedMinBalanceAs5000() {
		
		var accountMinBalance= account.getMinBalance();
		
		assertEquals(minBalance,accountMinBalance,0);
		
	}
	
	
	@Test
	public void withdrawCantWithdrawIfBalanceDipsBelowMinBalance() {
		
		var response= account.withdraw(balance-minBalance+1, correctPassword);
		
		assertEquals(ResponseStatus.INSUFFICIENT_FUNDS, response.getCode());
	}
	
	
	@Test
	public void withdrawFailsForWrongPassword() {
		var response=account.withdraw(1, "not-"+correctPassword);
		
		assertEquals(ResponseStatus.INVALID_CREDENTIALS, response.getCode());
	}
	
	
	

}

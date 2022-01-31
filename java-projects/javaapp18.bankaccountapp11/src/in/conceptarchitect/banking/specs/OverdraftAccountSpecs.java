package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.OverdraftAccount;
import in.conceptarchitect.banking.ResponseStatus;
import in.conceptarchitect.banking.exceptions.InvalidCredentialsException;

public class OverdraftAccountSpecs {
	String name="Name";
	double balance=50000;
	String correctPassword="p@ss";
	double interestRate=12;
	OverdraftAccount account;
	double odLimit=balance/10;

	@Before
	public void setUp() throws Exception {
		account=new OverdraftAccount(1,name,correctPassword,balance);
		
	}


	@Test
	public void overdraftAccountIsATypeOfBankAccount() {
		assertTrue(account instanceof BankAccount);
	}
	
	
	@Test
	public void creditInterstCreditsStandardInterest() {
		double expected= balance*(1+interestRate/1200);
		account.creditInterest(interestRate);
		assertEquals(expected, account.getBalance(),0.01);
	}
	
	
	@Test
	public void savingsAccountHasOdLimitEqualTo10PercentOfInitialBalance() {
	
		assertEquals(odLimit, account.getOdLimit(),0.01);
	}
	
	
	@Test
	public void odLimitChangesOnDepositWhenBalanceCrossHistoricMaxBalance() {
		double deposit=15000;
		double expectedNewOdLimit= (balance+deposit)/10;
		
		account.deposit(deposit);
		
		assertEquals(expectedNewOdLimit, account.getOdLimit(),0.01);
		
		
		
	}
	
	@Test
	public void odLimitDoesntChangeOnWithdrawal() {
		account.withdraw(balance, correctPassword);
		
		assertEquals(0, account.getBalance(),0.01); //proves withdraw was successful.
		
		assertEquals(odLimit, account.getOdLimit(),0.01);
	}
	
	
	@Test
	public void odLimitDoesntChangesOnDepositWhenBalanceDoesntCrossHistoricMaxBalance() {
		
		//ARRANGE
		double largeSum=50000;
		account.deposit(largeSum); 
		double historicMaxBalance= account.getBalance();
		double odLimitAtHistoricMaxBalance= account.getOdLimit(); //10000
		
		//let us reduce the balance in the account
		account.withdraw(largeSum, correctPassword);
		
		//verify balance has decreased
		assertEquals(balance, account.getBalance(),0.01);
		
		//ACT
		double smallSum=10000;
		account.deposit(smallSum);
		assertEquals(balance+smallSum, account.getBalance(),0.01);
		
		//What should be odLimit
		assertEquals(odLimitAtHistoricMaxBalance, account.getOdLimit(),0.01);
		
		
	}
	
	
	@Test
	public void odLimitChangesOnCreditInterestWhenBalanceCrossHistoricMaxBalance() {
		account.creditInterest(interestRate);
		double balance=account.getBalance();
		double expectedOdLimit=balance/10;
		
		assertEquals(expectedOdLimit, account.getOdLimit(),0.01);
		
	}
	
	@Ignore
	@Test
	public void odLimitDoesntChangesOnCreditInterestWhenBalanceDoesntCrossHistoricMaxBalance() {
		
	}
	
	
	
	
	@Test
	public void withdrawCanWithdrawUptoBalancePlusOdLimit() {
		
		
		var withdraw= balance+odLimit/2;		//52500
		
		account.withdraw(withdraw, correctPassword);
		
		assertTrue(account.getBalance()<0);
		
	}
	
	
	@Test
	public void withdrawMoreThanBalanceGet1PercentFee() {

		var withdraw= balance+odLimit/2;		//52500
		var od= balance-withdraw; //-2500
		var odCharge= od/100; //1% of of 2500 ---> 25
		
		var finalBalance= od+odCharge; // -2525
		
		account.withdraw(withdraw, correctPassword);
		
		
		
		assertEquals(finalBalance, account.getBalance(),0.01);
	}
	
	
	
	@Test(expected=InvalidCredentialsException.class)
	public void withdrawFailsForWrongPassword() {
		account.withdraw(1, "not-"+correctPassword);
		
	}
	
	
	

}

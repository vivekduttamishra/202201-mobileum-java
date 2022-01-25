package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.banking.Bank;

public class BankSpecs {
	
	final String bankName="ICICI";
	final double rate=12;
	final String correctPassword="p@ss";
	final double initialBalance=50000;
	
	int existingAccount1, existingAccount2;
	int initialTotalAccounts;
	Bank bank;
	
	@Before
	public void arrange() {
		//ARRANGE
		bank=new Bank(bankName,rate);
		existingAccount1=bank.openAccount("Name1", correctPassword, initialBalance);
		existingAccount2=bank.openAccount("Name", correctPassword, initialBalance);
		initialTotalAccounts=bank.getAccountCount();
	}
	
	
	@Test
	public void bankShouldHaveAName() {
				
		//ACT
		Bank bank=new Bank(bankName,10);
		
		//ASSERT
		assertEquals(bankName, bank.getName());
		
	}
	
	
	@Test
	public void bankShouldHaveAInterestRAte() {
		
		//ACT
		Bank bank=new Bank("Some Name",rate);
		
		//ASSERT
		assertEquals(rate, bank.getRate(),0);
				
		
	}
	
	@Test
	public void openAccountShouldTakeNamePasswordAndBalanceAndReturnAccountNumber() {
		
		//ACT
		int accountNumber1 = bank.openAccount("Aman", "mypassword", 1000.0);
		
		// ASSERT
		assertTrue(accountNumber1 > 0);
	}
	
	
	@Test		
	public void openAccountShouldReturnUniqueAccountNumber() {
		
		
		// ACT 
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0 );
		var accountNumber2 = bank.openAccount("arpit", "hispassword", 2000.0 );
		
		// ASSERT
		assertNotEquals(accountNumber1, accountNumber2);
	}
	
	
	@Test
	public void openAccountShouldIncreaseTotalAccountCountInTheBank() {
		
		
		// ACT 
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0 );
		
		
		// ASSERT
		assertEquals(initialTotalAccounts+1, bank.getAccountCount());
	}
	
	
	
	@Test
	public void closeAccountShouldFailFromInvalidAccountNumber() {
		
		//ACT
		var result = bank.closeAccount(initialTotalAccounts+1, "any-password");
		
		assertEquals(-1, result,0);
		
		
	}
	
	@Test
	public void closeAccountShouldFailFromInvalidAccountPassword() {
		//ACT
		var result = bank.closeAccount(existingAccount1, "wrong-password");
		
		assertEquals(-1, result,0);
		
	}
	
	
	
	@Test
	public void closeAccountShouldCloseTheAccountWithRightCredentials() {
		//ACT
		var result = bank.closeAccount(existingAccount1, correctPassword);
		
		//ASSERT
		assertNotEquals(-1, result,0);
	}
	
	
	@Ignore
	@Test
	public void closeAccountShouldReturnBalanceOnSuccessfulClosure() {
		
		//ACT
		var result= bank.closeAccount(existingAccount1, correctPassword);
		//ASSERT
		assertEquals(initialBalance, result,0.01);
		
		
	}
	
	@Test
	public void closeAccountShouldReduceTheAccountCountInTheBank() {
		//ACT
		var result= bank.closeAccount(existingAccount1, correctPassword);
		
		//ASSERT
		assertEquals(initialTotalAccounts-1, bank.getAccountCount());
	}
	
	@Test
	public void closeShouldFailForAlreadyClosedAccount() {
		
		//ARRANGE
		bank.closeAccount(existingAccount1, correctPassword);
		//Now existingAccount1 is closed. It can't be closed again
		
		//ACT
		var result=bank.closeAccount(existingAccount1, correctPassword);		
		
		//ASSERT
		assertEquals(-1, result,0);
		
	}
	
	@Test
	public void accountNumberShouldNotBeReused() {
		//ARRANGE
		String a4Name="Account 4";
		String a5Name="Account 5";
		bank.openAccount("Name", correctPassword, initialBalance); //3
		bank.openAccount(a4Name, correctPassword, initialBalance); //4
		
		bank.closeAccount(3, correctPassword); //we closed account 3
		
		//ACT
		
		var accountNumber= bank.openAccount(a5Name, correctPassword, initialBalance);
		
		
		//ASSERT
		assertEquals(5,accountNumber);
		
		var account4= bank.getAccount(4,correctPassword);
		
		assertEquals(a4Name, account4.getName());
		
		
		
		
		
	}
	
	
	
	@Ignore
	@Test
	public void weShouldNotBeAbleToGetClosedAccount() {
		
	}
	
	@Ignore
	@Test
	public void creditInterstShouldCreditInterstToAllAccounts() {
		
	}
	
	@Ignore
	@Test
	public void getBalanceShouldReturnBalanceForCorrectAccountAndPassword() {
		
	}
	@Ignore
	@Test
	public void getBalanceShouldFailForInvalidAccountNumber() {
		
	}
	@Ignore
	@Test
	public void getBalanceShouldFailForInvalidPassword() {
		
	}
	@Ignore
	@Test
	public void depositShouldFailForInvalidAccountNumber() {
		
	}
	@Ignore
	@Test
	public void depositShouldFailForInvalidAmount() {
		
	}
	@Ignore
	@Test
	public void depositShouldCreditBalanceOnSuccess() {
		
	}
	@Ignore
	@Test
	public void withdrawShouldFailForInvalidAccountNumber() {
		
	}
	@Ignore
	@Test
	public void withdrawShouldFailForInvalidPassword() {
		
	}
	@Ignore
	@Test
	public void withdrawShouldFailForInvalidAmount() {
		
	}
	@Ignore
	@Test
	public void withdrawShouldFailForOverDraft() {
		
	}
	@Ignore
	@Test
	public void withdrawShouldReduceBalanceByAmountOnSuccess() {
		
	}
	
	@Ignore
	@Test
	public void transferShouldFailForInvalidSourceAccountNumber() {
		
	}
	@Ignore
	@Test
	public void transferShouldFailForInvalidPassword() {
		
	}
	@Ignore
	@Test
	public void transferShouldFailForInvalidAmount() {
		
	}
	@Ignore
	@Test
	public void transferShouldFailForOverDraft() {
		
	}
	@Ignore
	@Test
	public void transferShouldReduceBalanceInSourceAccountOnSuccess() {
		
	}
	@Ignore
	@Test
	public void transferShouldFailForInvalidTargetAccountNumber() {
		
	}
	@Ignore
	@Test
	public void transferShouldAddBalanceInTargetOnSuccess() {
		
	}
	
	
	
	
	
	
	
	
	
	

}

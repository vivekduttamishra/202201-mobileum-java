package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.banking.Bank;

public class BankSpecsV1 {
	
	
	@Test
	public void bankShouldHaveAName() {
		
		//ARRANGE
		String name="ICICI";
		
		//ACT
		Bank bank=new Bank(name,10);
		
		//ASSERT
		assertEquals(name, bank.getName());
		
	}
	
	
	@Test
	public void bankShouldHaveAInterestRAte() {
		//ARRANGE
		
		double rate=12;
		
		//ACT
		Bank bank=new Bank("Some Name",rate);
		
		//ASSERT
		assertEquals(rate, bank.getRate(),0);
				
		
	}
	
	@Test
	public void openAccountShouldTakeNamePasswordAndBalanceAndReturnAccountNumber() {
		// ARRANGE
		Bank bank = new Bank("ICICI", 10);
		 
		// ACT
		int accountNumber1 = bank.openAccount("Aman", "mypassword", 1000.0);
		
		// ASSERT
		assertTrue(accountNumber1 > 0);
	}
	
	
	@Test		
	public void openAccountShouldReturnUniqueAccountNumber() {
		// ARRANGE
		Bank bank = new Bank("ICICI", 10);
		
		// ACT 
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0 );
		var accountNumber2 = bank.openAccount("arpit", "hispassword", 2000.0 );
		
		// ASSERT
		assertNotEquals(accountNumber1, accountNumber2);
	}
	
	
	@Test
	public void openAccountShouldIncreaseTotalAccountCountInTheBank() {
		//Arrange
		Bank bank = new Bank("ICICI", 10);
		
		// ACT 
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0 );
		var accountNumber2 = bank.openAccount("arpit", "hispassword", 2000.0 );
		
		// ASSERT
		assertEquals(2, bank.getAccountCount());
	}
	
	
	
	@Test
	public void closeAccountShouldFailFromInvalidAccountNumber() {
		//Arrange
		Bank bank = new Bank("ICICI", 10);
		
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0 );
		var accountNumber2 = bank.openAccount("arpit", "hispassword", 2000.0 );
		
		//ACT
		var result = bank.closeAccount(100, "any-password");
		
		//assertFalse(result);
		assertEquals(-1, result,0);
		
		
	}
	
	@Test
	public void closeAccountShouldFailFromInvalidAccountPassword() {
		//ARRANGE
		Bank bank = new Bank("ICICI", 10);
				
		var accountNumber1 = bank.openAccount("aman", "mypassword", 1000.0 );
		var accountNumber2 = bank.openAccount("arpit", "hispassword", 2000.0 );
		
		//ACT
		var result = bank.closeAccount(accountNumber1, "wrong-password");
		
		assertFalse(result==-1);
		
	}
	
	
	
	@Test
	public void closeAccountShouldCloseTheAccountWithRightCredentials() {
		//ARRANGE
		Bank bank = new Bank("ICICI", 10);
		String correctPassword="p@ss";
		
		
		var accountNumber1 = bank.openAccount("aman", correctPassword, 1000.0 );
		var accountNumber2 = bank.openAccount("arpit", correctPassword, 2000.0 );
		
		//ACT
		var result = bank.closeAccount(accountNumber1, correctPassword);
		
		assertTrue(result!=-1);
	}
	@Ignore
	@Test
	public void closeAccountShouldReturnBalanceOnSuccessfulClosure() {
		
		
		
		
	}
	@Ignore
	@Test
	public void closeAccountShouldReduceTheAccountCountInTheBank() {
		
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

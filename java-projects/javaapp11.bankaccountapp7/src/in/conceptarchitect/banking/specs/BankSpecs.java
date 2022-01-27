package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import in.conceptarchitect.banking.Bank;
import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.OverdraftAccount;
import in.conceptarchitect.banking.SavingsAccount;

public class BankSpecs {
	
	final String bankName="ICICI";
	final double rate=12;
	final String correctPassword="p@ss";
	final double initialBalance=50000;
	
	int savingsAccountNumber, currentAccountNumber,odAccountNumber;
	int initialTotalAccounts;
	Bank bank;
	BankAsserts bankAsserts;
	
	@Before
	public void arrange() {
		//ARRANGE
		bank=new Bank(bankName,rate);
		savingsAccountNumber=bank.openAccount("savings","Name1", correctPassword, initialBalance);
		currentAccountNumber=bank.openAccount("current","Name", correctPassword, initialBalance);
		odAccountNumber=bank.openAccount("overdraft", "Name1", correctPassword,initialBalance);
		bankAsserts=new BankAsserts(bank, correctPassword, initialBalance);
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
	public void openAccountShouldTakeAccountTypeNamePasswordAndBalanceAndReturnAccountNumber() {
		
		//ACT
		int accountNumber1 = bank.openAccount("savings", "Aman", "mypassword", 1000.0);
		
		// ASSERT
		assertTrue(accountNumber1 > 0);
	}
	
	
	@Test		
	public void openAccountShouldReturnUniqueAccountNumber() {
		
		
		// ACT 
		var accountNumber1 = bank.openAccount("savings","aman", "mypassword", 1000.0 );
		var accountNumber2 = bank.openAccount("savings","arpit", "hispassword", 2000.0 );
		
		// ASSERT
		assertNotEquals(accountNumber1, accountNumber2);
	}
	
	
	@Test
	public void openAccountShouldIncreaseTotalAccountCountInTheBank() {
		
		
		// ACT 
		var accountNumber1 = bank.openAccount("savings","aman", "mypassword", 1000.0 );
		
		
		// ASSERT
		assertEquals(initialTotalAccounts+1, bank.getAccountCount());
	}
	
	
	@Test
	public void openAccountShouldSuccessfullyCreateSavingsAccount() {
	
		var accountNumber= bank.openAccount("savings", "Name1", correctPassword, initialBalance);
		
		var account=bank.getAccount(accountNumber, correctPassword);
		
		assertTrue("Account is Not Savings Account",account instanceof SavingsAccount);
		
	}
	
	
	@Test
	public void openAccountShouldSuccessfullyCreateCurrentAccount() {
		var accountNumber= bank.openAccount("current", "Name1", correctPassword, initialBalance);
		
		var account=bank.getAccount(accountNumber, correctPassword);
		
		
		assertTrue("Account is not current Account", account instanceof CurrentAccount);
	}
	
	
	
	@Test
	public void openAccountShouldSuccessfullyCreateOverdraftAccount() {
		var accountNumber= bank.openAccount("overdraft", "Name1", correctPassword, initialBalance);
		
		var account=bank.getAccount(accountNumber, correctPassword);
		
		
		assertTrue( account instanceof OverdraftAccount);
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
		var result = bank.closeAccount(savingsAccountNumber, "wrong-password");
		
		assertEquals(-1, result,0);
		
	}
	
	@Test
	public void closeAccountShouldFailForNegativeBalance() {
		//Arrange ---> make sure you have a negative balance before starting
		bank.withdraw(odAccountNumber, initialBalance+1, correctPassword);
		//Is this withdrawal success	
		//assertEquals(-1, bank.getAccount(odAccountNumber,correctPassword).getBalance(),0);
		var balance= bank.getAccount(odAccountNumber, correctPassword).getBalance();
		assumeTrue("Account doesn't meet precondition of  negative balance", balance<0);
		
		//ACT
		var amount=bank.closeAccount(odAccountNumber, correctPassword);
		
		//Assert 
		assertEquals(-1,amount,0);		
		
	}
	
	
	@Test
	public void closeAccountShouldCloseTheAccountWithRightCredentials() {
		//ACT
		var result = bank.closeAccount(savingsAccountNumber, correctPassword);
		
		//ASSERT
		assertNotEquals(-1, result,0);
	}
	
	
	@Ignore
	@Test
	public void closeAccountShouldReturnBalanceOnSuccessfulClosure() {
		
		//ACT
		var result= bank.closeAccount(savingsAccountNumber, correctPassword);
		//ASSERT
		assertEquals(initialBalance, result,0.01);
		
		
	}
	
	@Test
	public void closeAccountShouldReduceTheAccountCountInTheBank() {
		//ACT
		var result= bank.closeAccount(savingsAccountNumber, correctPassword);
		
		//ASSERT
		assertEquals(initialTotalAccounts-1, bank.getAccountCount());
	}
	
	@Test
	public void closeShouldFailForAlreadyClosedAccount() {
		
		//ARRANGE
		bank.closeAccount(savingsAccountNumber, correctPassword);
		//Now existingAccount1 is closed. It can't be closed again
		
		//ACT
		var result=bank.closeAccount(savingsAccountNumber, correctPassword);		
		
		//ASSERT
		assertEquals(-1, result,0);
		
	}
	
	@Test
	public void accountNumberShouldNotBeReused() {
		//ARRANGE
		
		String a4Name="Account "+(initialTotalAccounts+1);
		String a5Name="Account "+(initialTotalAccounts+2);
		bank.openAccount("savings",a4Name, correctPassword, initialBalance); //4
		bank.openAccount("savings",a5Name, correctPassword, initialBalance); //5
		
		bank.closeAccount(3, correctPassword); //we closed account 3
		
		//ACT
		
		var accountNumber= bank.openAccount("savings",a5Name, correctPassword, initialBalance);
		
		
		//ASSERT
		//This is the third account we created after initial setup
		assertEquals(initialTotalAccounts+3,accountNumber);
		
		var account4= bank.getAccount(4,correctPassword);
		
		assertEquals(a4Name, account4.getName());
		
		
		
		
		
	}
	
	
	
	
	@Test
	public void weShouldNotBeAbleToGetClosedAccount() {
		var amount=bank.closeAccount(currentAccountNumber, correctPassword);
		assumeTrue(amount==initialBalance); //our account is indeed closed
		
		var account=bank.getAccount(currentAccountNumber, correctPassword);
		
		assertNull(account); //account returned must be null
	}
	
	
	@Test
	public void creditInterstShouldCreditInterstToSavingsAccounts() {
		bank.creditInterest();
		double expectedBalance= initialBalance*(1+ rate/1200);
		
		bankAsserts.assertBalance(savingsAccountNumber, expectedBalance);
		
	}
	
	@Test
	public void creditInterstShouldCreditInterstToOverdraftAccounts() {
		bank.creditInterest();
		double expectedBalance= initialBalance*(1+ rate/1200);		
		bankAsserts.assertBalance(odAccountNumber, expectedBalance);
		
	}
	
	@Test
	public void creditInterstShouldNotCreditInterstToCurrentAccounts() {
		bank.creditInterest();				
		bankAsserts.assertBalanceUnchanged(currentAccountNumber);
		
	}
	
	
	@Test
	public void creditInterstShouldNotCreditInterstToClosedAccount() {
		
		bank.closeAccount(currentAccountNumber, correctPassword);
		assumeTrue(bank.getAccount(currentAccountNumber, correctPassword)==null);
		
		
		bank.creditInterest();				
		
		
		
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

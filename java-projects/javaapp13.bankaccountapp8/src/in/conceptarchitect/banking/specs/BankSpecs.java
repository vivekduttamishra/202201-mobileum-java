package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import in.conceptarchitect.banking.Response;
import in.conceptarchitect.banking.ResponseStatus;
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
	public void openAccountShouldFailForInvalidAccountType() {
		var result=bank.openAccount("unknown type","Name",correctPassword,initialBalance);
		
		assertEquals(-1, result);
		assertEquals(initialTotalAccounts, bank.getAccountCount());
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
		
		//no crash is good for now.
		
	}
	
	
	@Test
	public void getBalanceShouldReturnBalanceForCorrectAccountAndPassword() {
		double balance=bank.getBalance(savingsAccountNumber, correctPassword);
		
		assertEquals(initialBalance,balance, 0.01);
	}
	
	@Test
	public void getBalanceShouldFailForNegativeAccountNumber() {
		double balance=bank.getBalance(-1, correctPassword);
		assertEquals(-1, balance,0);
	}
	
	
	@Test
	public void getBalanceShouldFailForInvalidAccountNumber() {
		double balance=bank.getBalance(initialTotalAccounts+1, correctPassword);
		assertEquals(-1, balance,0);
				
	}
	
	@Test
	public void getBalanceShouldFailForInvalidPassword() {
		double balance=bank.getBalance(savingsAccountNumber,"not"+ correctPassword);
		assertEquals(-1, balance,0);
	}
	
	@Test
	public void getBalanceShouldFailForClosedAccount() {
		//Arrange
		bank.closeAccount(savingsAccountNumber, correctPassword);
		//Act
		double balance=bank.getBalance(savingsAccountNumber, correctPassword);
		//Assert
		assertEquals(-1, balance,0);
	}
	
	
	
	@Test
	public void depositShouldFailForInvalidAccountNumber() {
		boolean result= bank.deposit(initialTotalAccounts+1, 20000);
		
		assertFalse(result);
	}
	
	@Test
	public void depositShouldFailForInvalidAmount() {
	
		assertFalse(bank.deposit(savingsAccountNumber, -1));
		
	}
	
	@Test
	public void depositShouldCreditBalanceOnSuccess() {
	
		boolean  result= bank.deposit(savingsAccountNumber, 1);
		assertTrue(result);
		bankAsserts.assertBalance(savingsAccountNumber, initialBalance+1);
	}
	
	@Test
	public void withdrawShouldFailForInvalidAccountNumber() {
		Response response= bank.withdraw(initialTotalAccounts+1, 1, correctPassword);

		assertEquals(ResponseStatus.INVALID_ACCOUNT, response.getCode());
	}
	
	@Test
	public void withdrawShouldFailForInvalidPassword() {
	
		var response= bank.withdraw(savingsAccountNumber, 1, "not-"+correctPassword);

		assertEquals(ResponseStatus.INVALID_CREDENTIALS,response.getCode());
		bankAsserts.assertBalanceUnchanged(savingsAccountNumber);
	}
	
	@Test
	public void withdrawShouldFailForInvalidAmount() {
		var response=bank.withdraw(currentAccountNumber, -1, correctPassword);
		assertEquals(ResponseStatus.INVALID_AMOUNT,response.getCode());
		bankAsserts.assertBalanceUnchanged(savingsAccountNumber);
	}
	
	
	
	@Test
	public void withdrawShouldFailForAmountExceedMinBalanceInSavingsAccount() {
		SavingsAccount account=(SavingsAccount) bank.getAccount(savingsAccountNumber, correctPassword);
		var response= bank.withdraw(savingsAccountNumber, initialBalance-account.getMinBalance()+1, correctPassword);
		assertEquals(ResponseStatus.INSUFFICIENT_FUNDS,response.getCode());
		bankAsserts.assertBalanceUnchanged(savingsAccountNumber);
	}
	
	@Test
	public void withdrawShouldFailForOverDraftForCurrentAccount() {
		var toWithdraw= initialBalance + 1;
		
		var response= bank.withdraw(currentAccountNumber, toWithdraw, correctPassword);
		assertEquals(ResponseStatus.INSUFFICIENT_FUNDS,response.getCode());
		bankAsserts.assertBalanceUnchanged(currentAccountNumber);
		
	}
	
	@Test
	public void withdrawShouldFailForAmountOverBalancePlusOdLimitForOdAccount() {
		var toWithdraw= initialBalance*1.1 + 1;
		
		var response= bank.withdraw(odAccountNumber, toWithdraw, correctPassword);
		assertEquals(ResponseStatus.INSUFFICIENT_FUNDS,response.getCode());
		bankAsserts.assertBalanceUnchanged(odAccountNumber);
	}
	
	
	
	@Test
	public void withdrawShouldReduceBalanceByAmountOnSuccess() {
		
		var response= bank.withdraw(savingsAccountNumber, 1, correctPassword);
		
		assertEquals(ResponseStatus.SUCCESS, response.getCode());
		bankAsserts.assertBalance(savingsAccountNumber, initialBalance-1);
		
	}
	
	
	@Test
	public void transferShouldFailForInvalidSourceAccountNumber() {
		var response= bank.transfer(initialTotalAccounts+1, 1, correctPassword, savingsAccountNumber);
		
		assertEquals(ResponseStatus.INVALID_ACCOUNT,response.getCode());
		assertEquals("Invalid Source Account",response.getMessage());
		bankAsserts.assertBalanceUnchanged(savingsAccountNumber);
		
		
	}
	
	@Test
	public void transferShouldFailForInvalidTargetAccountNumber() {
		var response= bank.transfer(savingsAccountNumber, 1, correctPassword, initialTotalAccounts+1);
		
		assertEquals(ResponseStatus.INVALID_ACCOUNT,response.getCode());
		assertEquals("Invalid Target Account",response.getMessage());
		bankAsserts.assertBalanceUnchanged(savingsAccountNumber);
		
	}
	
	@Test
	public void transferShouldFailForInvalidPassword() {
		
		var response= bank.transfer(currentAccountNumber, 1, "not-"+correctPassword, savingsAccountNumber);
		
		assertEquals(ResponseStatus.INVALID_CREDENTIALS,response.getCode());
		bankAsserts.assertBalanceUnchanged(savingsAccountNumber);
		bankAsserts.assertBalanceUnchanged(currentAccountNumber);
		
	}
	
	@Test
	public void transferShouldFailForInvalidAmount() {
		
		var response= bank.transfer(currentAccountNumber, -1, correctPassword, savingsAccountNumber);
		
		assertEquals(ResponseStatus.INVALID_AMOUNT,response.getCode());
		bankAsserts.assertBalanceUnchanged(savingsAccountNumber);
		bankAsserts.assertBalanceUnchanged(currentAccountNumber);
		
	}
	
	@Test
	public void transferShouldFailForOverDraft() {
		var response= bank.transfer(currentAccountNumber, initialBalance+1, correctPassword, savingsAccountNumber);
		
		assertEquals(ResponseStatus.INSUFFICIENT_FUNDS,response.getCode());
		bankAsserts.assertBalanceUnchanged(savingsAccountNumber);
		bankAsserts.assertBalanceUnchanged(currentAccountNumber);
		
		
	}
	
	@Test
	public void transferShouldReduceBalanceInSourceAccountOnSuccess() {
		
		var response= bank.transfer(currentAccountNumber, 1, correctPassword, savingsAccountNumber);		
		assertEquals(ResponseStatus.SUCCESS,response.getCode());		
		bankAsserts.assertBalance(currentAccountNumber,initialBalance-1);
	}
	
	@Test
	public void transferShouldIncreseBalanceInTargetAccountOnSuccess() {
		
		var response= bank.transfer(currentAccountNumber, 1, correctPassword, savingsAccountNumber);		
		assertEquals(ResponseStatus.SUCCESS,response.getCode());		
		bankAsserts.assertBalance(savingsAccountNumber,initialBalance+1);
	}
	
	
	
	
	@Test
	public void transferShouldAllowOverDraftFromOverDraftAccount() {
		
		double od=1000;
		double amountTransferred=initialBalance+od;
		double finalBalance =  - od - od/100;
		
		Response response=bank.transfer(odAccountNumber, amountTransferred, correctPassword, savingsAccountNumber);
		
		assertEquals(ResponseStatus.SUCCESS, response.getCode());
		bankAsserts.assertBalance(odAccountNumber, finalBalance);
		bankAsserts.assertBalance(savingsAccountNumber, initialBalance+amountTransferred);
		
	}
	
	

	@Test
	public void canChangePasswordWithValidCurrentPassword() {
		var newPassword="newPassword";
		var result=bank.changePassword(savingsAccountNumber, correctPassword, newPassword);
	
		assertEquals(ResponseStatus.SUCCESS,result.getCode());
		//how do I know password is actually changed
		var balance=bank.getBalance(savingsAccountNumber, newPassword);
		assertEquals(initialBalance,balance,0);
		
	}
	
	@Test 
	public void changePasswordFailsForInvalidCurrentPassword() {
		String newPassword="new password";
		var result=bank.changePassword(savingsAccountNumber, "not"+correctPassword, newPassword);
		
		assertEquals(ResponseStatus.INVALID_CREDENTIALS,result.getCode());
		//how do I know password is actually changed
	}
	
	
	@Test 
	public void changePasswordFailsForInvalidAccountNumber() {
		String newPassword="new password";
		var result=bank.changePassword(-1, "not"+correctPassword, newPassword);
		
		assertEquals(ResponseStatus.INVALID_ACCOUNT,result.getCode());
		//how do I know password is actually changed
	}
	
	
	 
	

}

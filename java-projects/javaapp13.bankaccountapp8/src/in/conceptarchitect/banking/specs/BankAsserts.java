package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;

import in.conceptarchitect.banking.Bank;

public class BankAsserts {
	
	Bank bank;
	private String password;
	private double defaultBalance;

	public BankAsserts(Bank bank,String password, double defaultBalance) {
		super();
		this.bank = bank;
		this.password=password;
		this.defaultBalance=defaultBalance;
	}
	
	
	


	public void assertBalance(int accountNumber, double expectedBalance) {
		var account=bank.getAccount(accountNumber, password);
		double balance= account.getBalance();		
		assertEquals(expectedBalance, balance,0.01);
	}
	
	public void assertBalanceUnchanged(int accountNumber) {
		assertBalance(accountNumber,defaultBalance);
	}
	

}

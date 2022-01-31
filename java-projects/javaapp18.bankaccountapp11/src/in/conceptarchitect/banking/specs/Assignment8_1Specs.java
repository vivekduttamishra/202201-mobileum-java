package in.conceptarchitect.banking.specs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.OverdraftAccount;
import in.conceptarchitect.banking.SavingsAccount;

public class Assignment8_1Specs {
	
	ArrayList<BankAccount> accounts;
	BankAccount a1,a2,a3,a4,a5;
	String password="p@ss";
	
	ArrayList<BankAccount> selectedAccounts;
	
	
	@Before
	public void arrange() {
		a1=new SavingsAccount(1,"Vivek Mishra", "p@ss",50000);
		a2=new CurrentAccount(2, "Sanjay Mall","otherpassword",20000);
		a3=new OverdraftAccount(3,"Shivanshi Mishra","p@ss",50000);
		a4=new OverdraftAccount(4,"Amit Singh","p@ss",25000);
		a5=new CurrentAccount(5,"Aman Singh","p@ss",30000);
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		accounts.add(a4);
		accounts.add(a5);
		
		a3.withdraw(51000,password); //now balance is in negative		
		
	}
	

	@Test
	public void searchCanReturnAllCurrentAccounts() {
		
		selectedAccounts= null; //search for current accounts
		
		assertEquals(2, selectedAccounts.size());
		
	}
	
	@Test
	public void searchCanReturnAccountWithNegativeBalance() {
		selectedAccounts=null; //search for accounts with negative balance
		
		assertEquals(1, selectedAccounts.size());
		assertEquals(a3, selectedAccounts.get(0));
	}
	
	@Test
	public void searchCanReturnAllAccountWithLastNameMishra() {
		selectedAccounts=null;
		
		assertEquals(2, selectedAccounts.size());
		assertEquals(a1,selectedAccounts.get(0));
		assertEquals(a3, selectedAccounts.get(1));
		
	}
	
	@Test
	public void searchAccountsWithRegularPassword() {
		selectedAccounts=null;
		
		assertEquals(4, selectedAccounts.size());
	}
	
	
}

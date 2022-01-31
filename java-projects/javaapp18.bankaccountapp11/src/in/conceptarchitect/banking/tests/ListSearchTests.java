package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.OverdraftAccount;
import in.conceptarchitect.banking.SavingsAccount;
import in.conceptarchitect.banking.helpers.BankSearchUtils;
import in.conceptarchitect.utils.searchutils.ListSearch;
import in.conceptarchitect.utils.searchutils.Matcher;

public class ListSearchTests {
	
	ArrayList<BankAccount> accounts;
	BankAccount a1,a2,a3,a4,a5;
	String password="p@ss";
	
	List<BankAccount> selectedAccounts;
	
	
	@Before
	public void arrange() {
		a1=new SavingsAccount(1,"Vivek Mishra", "p@ss",50000);
		a2=new CurrentAccount(2, "Sanjay Mall","otherpassword",20000);
		a3=new OverdraftAccount(3,"Shivanshi Mishra","p@ss",50000);
		a4=new OverdraftAccount(4,"Amit Singh","p@ss",25000);
		a5=new CurrentAccount(5,"Aman Singh","p@ss",30000);
		accounts=new ArrayList<>();
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		accounts.add(a4);
		accounts.add(a5);
		
		a3.withdraw(51000,password); //now balance is in negative		
		
	}
	
	class CurrentAccountMatcher implements Matcher<BankAccount>{

		@Override
		public boolean isMatch(BankAccount value) {
			// TODO Auto-generated method stub
			return value instanceof CurrentAccount;
		}
		
	}
	

	@Test
	public void searchCanReturnAllCurrentAccounts() {
		
		var currentAccountMatcher= new CurrentAccountMatcher();
		
		selectedAccounts= ListSearch.search(accounts, currentAccountMatcher);
		
		assertEquals(2, selectedAccounts.size());
		
	}
	
	@Test
	public void searchCanReturnAccountWithNegativeBalance() {
		
		
		Matcher<BankAccount> negativeBalance= new Matcher<BankAccount>() {

			@Override
			public boolean isMatch(BankAccount account) {
				// TODO Auto-generated method stub
				return account.getBalance()<0;
			}
			
		};
		
		
		selectedAccounts=ListSearch.search(accounts, negativeBalance);
		
		assertEquals(1, selectedAccounts.size());
		assertEquals(a3, selectedAccounts.get(0));
	}
	
	@Test
	public void searchCanReturnAllAccountWithLastNameMishra() {
		
//		Matcher<BankAccount> mishras= (account) -> { 
//					return account.getName().contains("Mishra") ; 
//					} ;
		
		
		
	//	Matcher<BankAccount> mishras = account -> account.getName().contains("Mishra");
		
		
		selectedAccounts=ListSearch.search(accounts, account->account.getName().contains("Mishra"));
		
		
		
		
		assertEquals(2, selectedAccounts.size());
		assertEquals(a1,selectedAccounts.get(0));
		assertEquals(a3, selectedAccounts.get(1));
		
	}
	
	@Test
	public void searchAccountsWithRegularPassword() {
		
		Matcher<BankAccount> matchPassword= account ->{			
			try {
				account.authenticate(password);
				return true;
			}catch(Exception ex) {
				return false;
			}
		};
		
		selectedAccounts=ListSearch.search(accounts, matchPassword);
		
		assertEquals(4, selectedAccounts.size());
	}
	
	
}








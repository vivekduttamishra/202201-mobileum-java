package in.conceptarchitect.banking;

import java.io.Console;

import in.conceptarchitect.banking.exceptions.InsufficientBalanceException;
import in.conceptarchitect.banking.exceptions.InvalidAccountException;
import in.conceptarchitect.banking.exceptions.InvalidAccountTypeException;

public class Bank {

	
	private String name;
	private double rate;
	private AccountRepository repository;
	

	public Bank(String name, double rate, AccountRepository repository) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.rate=rate;
		//store=new ArrayAccountStore();
		this.repository=repository;
	}

	public String getName() { 
		// TODO Auto-generated method stub
		return name;
	}

	public double getRate() {
		// TODO Auto-generated method stub
		return rate;
	}
	
	
	

	public int openAccount(String accountType,String name, String password,  double amount) {
		// TODO Auto-generated method stub
		
		BankAccount account=null;
		if(accountType.equals("savings"))
				account= new SavingsAccount(0, name,password,amount);
		else if(accountType.equals("current"))
				account=new CurrentAccount(0,name,password,amount);
		else if(accountType.equals("overdraft"))
				account=new OverdraftAccount(0, name, password, amount);
		
		if(account==null)
				throw new InvalidAccountTypeException(0,"Invalid Account Type :"+accountType);
		
		return repository.addAccount(account);
	}

		
	public BankAccount getAccount(int accountNumber, String password) {
		var account=repository.getAccount(accountNumber);
		account.authenticate(password);
		return account;
	}
	
	
	public double closeAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		var account=getAccount(accountNumber,password);
		
		if(account.getBalance()<0)
			throw new InsufficientBalanceException(accountNumber,-account.getBalance());
		
		repository.removeAccount(accountNumber);
		return account.getBalance();
	}

	

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return repository.getAccountCount();
	}

	

	public void deposit(int accountNumber, double amount) {
		// TODO Auto-generated method stub
		var account=repository.getAccount(accountNumber);		
		account.deposit(amount);
		
	}

	public void withdraw(int accountNumber, double amount, String password) {
		// TODO Auto-generated method stub
		var account=repository.getAccount(accountNumber);
		account.withdraw(amount, password);
		
	}

	public void transfer(int accountNumber, double amount, String password, int targetAccount) {
		// TODO Auto-generated method stub
		var source= repository.getAccount(accountNumber);
		var target=repository.getAccount(targetAccount);
			
		source.withdraw(amount, password);
		target.deposit(amount);
	}

	public void creditInterest() {
		// TODO Auto-generated method stub
		for(var account :repository. getAllActiveAdccounts()) {
				account.creditInterest(rate);
		}
	}

	public double getBalance(int accountNumber, String password) {
		// TODO Auto-generated method stub
		var account=repository.getAccount(accountNumber);
		account.authenticate(password);
		return account.getBalance();
		
	}

	
	public void changePassword(int accountNumber, String currentPassword, String newPassword) {
		var account=repository.getAccount(accountNumber);
		account.changePassword(currentPassword, newPassword);
		
	}

	public String[] getAllAccountsInfo() {
		// TODO Auto-generated method stub
		String [] info= new String [ repository.getAccountCount()];
		var x=0;
		
		for(var account : repository.getAllActiveAdccounts()) {
			info[x]=account.toString();
			x++;		
		}
		
		return info;
		
	}
	 

}
















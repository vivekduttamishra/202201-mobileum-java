package in.conceptarchitect.banking;

import in.conceptarchitect.banking.exceptions.InvalidAccountException;

public class ArrayAccountStore {


	int accountCount=0;	
	int lastAccountNumber=0;
	BankAccount [] accounts=new BankAccount[100];
	

	public int addAccount(BankAccount account) {
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		account.setAccountNumber(accountNumber);
		accounts[accountNumber]=account;
		return accountNumber;
	}
	
	public BankAccount getAccount(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts[accountNumber]==null)
			throw new InvalidAccountException(accountNumber);

		//it either returns a valid value or throws an exception
		return accounts[accountNumber];
	}

	public void removeAccount(int accountNumber) {
		accounts[accountNumber]=null;
		accountCount--;
	}
	
	public BankAccount[] getAllActiveAdccounts() {
		
		BankAccount [] activeAccounts=new BankAccount[accountCount];
		var i=0;
		for(var account : accounts) {
			if(account!=null) {
				activeAccounts[i]=account;
				i++;
			}
		}
		
		return activeAccounts;
	
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}
	
	

}

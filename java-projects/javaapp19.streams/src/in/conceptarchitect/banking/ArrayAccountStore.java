package in.conceptarchitect.banking;

import in.conceptarchitect.banking.exceptions.InvalidAccountException;

public class ArrayAccountStore implements AccountRepository {


	int accountCount=0;	
	int lastAccountNumber=0;
	BankAccount [] accounts=new BankAccount[100];
	

	@Override
	public int addAccount(BankAccount account) {
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		account.setAccountNumber(accountNumber);
		accounts[accountNumber]=account;
		return accountNumber;
	}
	
	@Override
	public BankAccount getAccount(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts[accountNumber]==null)
			throw new InvalidAccountException(accountNumber);

		//it either returns a valid value or throws an exception
		return accounts[accountNumber];
	}

	@Override
	public void removeAccount(int accountNumber) {
		accounts[accountNumber]=null;
		accountCount--;
	}
	
	@Override
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

	@Override
	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}

	@Override
	public void save() {
		//It's only a memory repo. We don't save
		
	}
	
	

}

package in.conceptarchitect.banking;

import java.util.ArrayList;

import in.conceptarchitect.banking.exceptions.InvalidAccountException;

public class ArrayListAccountRepository implements AccountRepository {

	
	ArrayList<BankAccount> accounts=new ArrayList<BankAccount>();
	int lastId=0;
	

	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		lastId++;
		account.setAccountNumber(lastId);
		accounts.add(account);
		return lastId;
	}

	@Override
	public BankAccount getAccount(int accountNumber) {
		// TODO Auto-generated method stub
		validateAccountNumber(accountNumber);
		
		
		for(var account : accounts)
			if(account.getAccountNumber()==accountNumber)
				return account;
		
		throw new InvalidAccountException(accountNumber);
	}

	private void validateAccountNumber(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastId)
			throw new InvalidAccountException(accountNumber);
	}

	@Override
	public void removeAccount(int accountNumber) {
		// TODO Auto-generated method stub
		
		var account=getAccount(accountNumber);
		
		accounts.remove(account);
		
	}

	@Override
	public BankAccount[] getAllActiveAdccounts() {
		// TODO Auto-generated method stub
		BankAccount [] array= {};
		return (BankAccount[]) accounts.toArray(array);
	}

	@Override
	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accounts.size();
	}
	

	@Override
	public void save() {
		//It's only a memory repo. We don't save
		
	}
	

}

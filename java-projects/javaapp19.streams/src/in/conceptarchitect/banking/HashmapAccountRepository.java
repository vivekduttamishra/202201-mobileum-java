package in.conceptarchitect.banking;

import java.io.Serializable;
import java.util.HashMap;

import in.conceptarchitect.banking.exceptions.InvalidAccountException;

public class HashmapAccountRepository implements AccountRepository,Serializable {

	HashMap<Integer, BankAccount> accounts= new HashMap<Integer, BankAccount>();
	int lastId=0;

	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		lastId++;
		account.setAccountNumber(lastId);
		accounts.put(lastId, account);
		return lastId;
	}

	@Override
	public BankAccount getAccount(int accountNumber) {
		// TODO Auto-generated method stub
		if(accounts.containsKey(accountNumber))
			return accounts.get(accountNumber);
		else
			throw new InvalidAccountException(accountNumber);
	}

	@Override
	public void removeAccount(int accountNumber) {
		// TODO Auto-generated method stub
		var account=getAccount(accountNumber);
		accounts.remove(accountNumber);

	}

	@Override
	public BankAccount[] getAllActiveAdccounts() {
		// TODO Auto-generated method stub
		BankAccount [] array= {};
		
		return accounts.values().toArray(array);
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

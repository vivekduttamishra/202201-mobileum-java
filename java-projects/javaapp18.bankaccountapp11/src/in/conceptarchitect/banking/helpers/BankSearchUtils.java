package in.conceptarchitect.banking.helpers;

import java.util.ArrayList;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.CurrentAccount;

public class BankSearchUtils {

	public static ArrayList<BankAccount> searchCurrentAccounts(ArrayList<BankAccount> accounts) {
		// TODO Auto-generated method stub
		ArrayList<BankAccount> result=new ArrayList<>();
		for(var account :accounts) {
			if(account instanceof CurrentAccount) {
				result.add(account);
			}
		}
		return result;
	}
	
	
	public static ArrayList<BankAccount> search(ArrayList<BankAccount> accounts, int type, String name, double minBalance, double maxBalance) {
		// TODO Auto-generated method stub
		ArrayList<BankAccount> result=new ArrayList<>();
		for(var account :accounts) {
			
			if(type==1 && account instanceof CurrentAccount) //search for CurrentAccount
				result.add(account);
			else if(type==2 &&  account.getName().contains("Mishra"))  //search for Mishra
				result.add(account);
			else if(type==3 && account.getBalance()<0) //negative balance
				result.add(account);
			
			
			if(account instanceof CurrentAccount) {
				result.add(account);
			}
		}
		return result;
	}
	

	public static ArrayList<BankAccount> searchAccountWithNegativeBalance(ArrayList<BankAccount> accounts) {
		// TODO Auto-generated method stub
		ArrayList<BankAccount> result=new ArrayList<>();
		for(var account : accounts) {
			if(account.getBalance()<0)
				result.add(account);
		}
		
		return result;
	}

	public static ArrayList<BankAccount> searchAccountWithNameContaining(ArrayList<BankAccount> accounts,
			String name) {
		// TODO Auto-generated method stub	
		ArrayList<BankAccount> result=new ArrayList<>();
		for(var account:accounts) {
			if(account.getName().contains(name))
				result.add(account);
		}
		return result;
	}

	public static ArrayList<BankAccount> searchAccountWithPassword(ArrayList<BankAccount> accounts, String password) {
		// TODO Auto-generated method stub
		ArrayList<BankAccount> result=new ArrayList<>();
		for(var account:accounts) {
			try {
				account.authenticate(password);
				result.add(account);
			}catch(Exception ex) {
				
			}
			
		}
		return result;
	}
	
	

}

package javaapp04.bankaccountapp01;

import in.conceptarchitect.banking.BankAccount;

public class BankAccountTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bank Account Tests...");
		
		final String correctPassword="p@ss";
		
		BankAccount a1=new BankAccount(1, "Vivek",correctPassword, 20000,12);
		
		//test for withdrawal
		
		a1.withdraw(2000, "not-"+correctPassword);
		System.out.println(a1.getBalance());		
		
		a1.withdraw(-100, correctPassword); //withdrawing using right password but negative amount
		System.out.println(a1.getBalance()); 
		
		a1.withdraw(30000,correctPassword); //shouldn't be withdrawn
		System.out.println(a1.getBalance()); 
		
		a1.withdraw(5000, "p@ss"); //should be withdrawn
		System.out.println(a1.getBalance());
					
		
	}

}

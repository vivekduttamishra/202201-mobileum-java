package javaapp04.bankaccountapp01;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.utils.Input;


public class BankingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Input input=new Input();
		
//		int accountNumber=input.readInt("account number?");
//		String name=input.readString("Name?");
//		String password=input.readString("password?");
//		int amount=input.readInt("Amount?");
//		int interestRate=input.readInt("Interest Rate?");
//		BankAccount a1=new BankAccount(accountNumber, name, password, amount, interestRate);

		
		BankAccount a1=new BankAccount(1, "Vivek", "p@ss#1", 20000, 12);
		a1.show();
		
		
		
		a1.deposit();
		a1.withdraw();
		
		a1.show();
	}

}

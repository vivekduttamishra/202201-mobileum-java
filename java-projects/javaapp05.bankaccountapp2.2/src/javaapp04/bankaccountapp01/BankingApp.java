package javaapp04.bankaccountapp01;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.utils.Input;


public class BankingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Input input=new Input();
	
		BankAccount a1=new BankAccount(1, "Vivek", "p@ss#1", 20000, 12);
		System.out.println(a1.info());
	}

}

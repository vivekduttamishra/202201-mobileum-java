package javaapp04.bankaccountapp01;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.ResponseStatus;

public class BankAccountTestingV2 {
	
	static void depositTest(String testName, BankAccount account, int amount, boolean expectedResult) {
		var result=account.deposit(amount);
		if(result==expectedResult) {
			System.out.println("PASSED\t\t"+testName);			
		} else {
			System.err.println("FAILED\\t\t"+testName);
			System.err.printf("\t\t\tEXPCTED %s\tACTUAL %s\n", expectedResult,result);
		}
		
	}
	
	static void withdrawTest(String testName, BankAccount account, int amount, String password, ResponseStatus expectedResult) {
		
		
		var response = account.withdraw(amount, password);
		var result=response.getCode();
		
		if(result==expectedResult) {
			System.out.println("PASSED\t\t"+testName);
		} else {
			System.err.println("FAILED\t\t"+testName);
			System.err.printf("\t\t\tExpected: %s\tActual:%s\n",expectedResult,result);
		}	
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bank Account Tests...");
		final String correctPassword="p@ss";
		final int balance=20000;
		BankAccount a1=new BankAccount(1, "Vivek",correctPassword, balance,12);
		
		depositTest("Should fail for negative amount",a1,-1,false);
		
		depositTest("Should pass for positive amount",a1,5000,true);
		
		withdrawTest("Should fail for wrong password", a1, balance-1, "not_"+correctPassword, ResponseStatus.INVALID_CREDENTIALS);
		
		withdrawTest("Should fail for negative amount", a1, -1 , correctPassword, ResponseStatus.INVALID_AMOUNT);
		
		withdrawTest("Should fail for insufficient balance",a1, balance+1, correctPassword,ResponseStatus.INSUFFICIENT_FUNDS);
		
		withdrawTest("Should work for valid amount and password", a1,1000,correctPassword, ResponseStatus.SUCCESS);
		
		
		
		
	}

}

package in.conceptarchitect.banking;

import in.conceptarchitect.utils.Input;

public class BankAccount {
	
	
	private String name; //account holder name
	private int accountNumber;
	private double interestRate;
	private double balance;
	private String password;
	Input input=new Input();
	
	public BankAccount(int accountNumber, String name,String password, double amount, double interestRate) {
		this.accountNumber=accountNumber;
		this.name=name;
		this.password=password;
		balance=amount;  //this keyword is option here
		this.interestRate=interestRate;
				
	}
	
	
	public void deposit() {
		int amount=input.readInt("Amount to Deposit? ");
		if(amount>0) {
			balance+=amount;
			System.out.println("Amount is deposited");
		} else {
			System.out.println("Invalid amount. Deposit Failed");
		}		
	}
	
	
	
	
	public void withdraw() {
		int amount=input.readInt("Amount to withdraw?");
		String password=input.readString("password?");
		if(amount<0 ) {
			System.out.println("Invalid Amount. Withdraw Rejected");
		} else if(amount>balance) {
			System.out.println("Insufficient Balance. Withraw Rejected");
		} else if(!this.password.equals(password))
			System.out.println("Invalid credential. withdraw rejected");
		else {
			balance-=amount;
			System.out.println("Please collect your cash...");
		}
	}
	
	
	public void show() {
		//System.out.println(accountNumber+"\t"+name+"\t"+balance);
		System.out.printf("%d\t%s\t%f\t%f\n",accountNumber,name,balance,interestRate);
	}
	
	

}

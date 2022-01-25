package in.conceptarchitect.banking;

public class SavingsAccount extends BankAccount {

	public SavingsAccount(int accountNumber, String name, String password, double amount) {
		super(accountNumber, name, password, amount);
		// TODO Auto-generated constructor stub
	}

	public double getMinBalance() {
		// TODO Auto-generated method stub
		return 5000;
	}

	@Override
	public Response withdraw(double amount, String password) {
		
		//validation for Savings Account only
		if(amount>getBalance()-getMinBalance())
			return new Response(ResponseStatus.INSUFFICIENT_FUNDS,"Insufficient Funds");
		
		//Rest of the work is already done by super class withdraw
		return super.withdraw(amount, password);
		
	}
	

}

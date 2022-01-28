package in.conceptarchitect.banking;

public class OverdraftAccount extends BankAccount {

	double odLimit;
	public OverdraftAccount(int accountNumber, String name, String password, double amount) {
		super(accountNumber, name, password, amount);
		// TODO Auto-generated constructor stub
		calculateOdLimit();
	}

	private void calculateOdLimit() {
		// TODO Auto-generated method stub
		
		odLimit=Math.max(balance/10,odLimit);
	}

	public double getOdLimit() {
		// TODO Auto-generated method stub 
		return odLimit;
	}
	
	@Override
	public void deposit(double deposit) {
		// TODO Auto-generated method stub
		super.deposit(deposit);
		calculateOdLimit();
		
	}
	
	@Override
	public void creditInterest(double interestRate) {
		// TODO Auto-generated method stub
		super.creditInterest(interestRate);
		calculateOdLimit();
	}
	
	@Override
	public double getMaxWithdrawableAmount() {
		// TODO Auto-generated method stub
		return balance+odLimit;
	}
	
	@Override
	public void withdraw(double amount, String password) {	
		
		super.withdraw(amount, password);
		if(balance<0) {
			var fee= balance/100;
			balance+=fee;
		}
			
		
	}

}

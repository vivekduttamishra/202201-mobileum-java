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
	public boolean deposit(double deposit) {
		// TODO Auto-generated method stub
		var result= super.deposit(deposit);
		calculateOdLimit();
		return result;
	}
	
	@Override
	public void creditInterest(double interestRate) {
		// TODO Auto-generated method stub
		super.creditInterest(interestRate);
		calculateOdLimit();
	}
	
	@Override
	public Response withdraw(double amount, String password) {	
		if(!this.authenticate(password))
			return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Credentials");
		if(amount<0)
			return new Response(ResponseStatus.INVALID_AMOUNT,"Enter Positive Amount");
		if(amount>balance+odLimit)
			return new Response(ResponseStatus.INSUFFICIENT_FUNDS,"Insufficient Funds");
		
		balance-=amount;
		if(balance<0) {
			var fee= balance/100;
			balance+=fee;
		}
			
		return new Response(ResponseStatus.SUCCESS,null);
	}

}

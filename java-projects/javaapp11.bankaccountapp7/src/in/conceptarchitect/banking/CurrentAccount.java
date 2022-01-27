package in.conceptarchitect.banking;

public class CurrentAccount extends BankAccount {

	public CurrentAccount(int accountNumber, String name, String password, double amount) {
		super(accountNumber, name, password, amount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void creditInterest(double interestRate) {
		//DO NOTHING
	}

	@Override
	protected double getMaxWithdrawableAmount() {
		// TODO Auto-generated method stub
		return balance;
	}
}

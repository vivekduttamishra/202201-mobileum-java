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
	public double getMaxWithdrawableAmount() {
		// TODO Auto-generated method stub
		return getBalance()-getMinBalance();
	}
	

}

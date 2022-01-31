package in.conceptarchitect.banking;

public interface AccountRepository {

	int addAccount(BankAccount account);

	BankAccount getAccount(int accountNumber);

	void removeAccount(int accountNumber);

	BankAccount[] getAllActiveAdccounts();

	int getAccountCount();
	
	void save();

}
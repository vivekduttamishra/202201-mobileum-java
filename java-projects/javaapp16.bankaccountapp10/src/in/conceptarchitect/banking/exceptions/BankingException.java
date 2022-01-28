package in.conceptarchitect.banking.exceptions;

public class BankingException extends RuntimeException {
	
	int accountNumber;
	
	

	public int getAccountNumber() {
		return accountNumber;
	}

	public BankingException(int accountNumber) {
		// TODO Auto-generated constructor stub
		this(accountNumber,"Banking Exception");
	}

	public BankingException(int accountNumber,String message) {
		super(message);
		// TODO Auto-generated constructor stub
		this.accountNumber=accountNumber;
	}

	public BankingException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BankingException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BankingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

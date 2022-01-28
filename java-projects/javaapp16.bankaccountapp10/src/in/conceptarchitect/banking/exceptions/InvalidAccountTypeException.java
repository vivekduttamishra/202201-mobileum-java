package in.conceptarchitect.banking.exceptions;

public class InvalidAccountTypeException extends BankingException {

	public InvalidAccountTypeException(int accountNumber) {
		super(accountNumber);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(int accountNumber, String message) {
		super(accountNumber, message);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

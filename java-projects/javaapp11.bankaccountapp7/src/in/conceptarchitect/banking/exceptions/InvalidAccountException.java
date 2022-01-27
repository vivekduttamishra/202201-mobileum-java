package in.conceptarchitect.banking.exceptions;

public class InvalidAccountException extends BankingException {

	public InvalidAccountException(int accountNumber) {
		this(accountNumber,"Invalid Account");
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountException(int accountNumber, String message) {
		super(accountNumber, message);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

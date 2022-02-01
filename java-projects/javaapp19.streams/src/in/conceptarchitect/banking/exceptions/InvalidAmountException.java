package in.conceptarchitect.banking.exceptions;

public class InvalidAmountException extends BankingException {

	double amount;
	public double getAmount() {return amount;}
	
	public InvalidAmountException(int accountNumber,double amount) {
		this(accountNumber,amount,"Invalid Amount Entered");
		// TODO Auto-generated constructor stub
	}

	public InvalidAmountException(int accountNumber,double amount, String message) {
		super(accountNumber, message);
		// TODO Auto-generated constructor stub
		this.amount=amount;
	}

	public InvalidAmountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAmountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidAmountException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
